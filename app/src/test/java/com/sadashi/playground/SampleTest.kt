package com.sadashi.playground

import android.app.Activity
import android.content.Context
import com.sadashi.playground.sample.Module
import com.sadashi.playground.sample.Sample
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

class SampleTest : Spek({

    describe("Mock Module Class") {
        context("#getMockModuleString") {
            it("valid pattern") {
                val module = mockk<Module>()
                every { module.getString() } returns "mock string"

                val sample = Sample(module)
                assertEquals("mock string", sample.getMockModuleString())
                verify { module.getString() }
                confirmVerified()
            }
        }

        context("#getMockModuleInt") {
            it("valid pattern") {
                val module = mockk<Module>()
                every { module.getInt() } returns 0

                val sample = Sample(module)
                assertEquals(0, sample.getMockModuleInt())
                verify { module.getInt() }
                confirmVerified()
            }
        }
    }

    describe("Mock Context, Activity etc") {
        context("#mockContext") {
            it("Mock Context") {
                val resultString = "mock context sample"
                val context = mockk<Context>()
                every { context.getString(any()) } returns resultString
                val sample = Sample(mockk())

                assertEquals(resultString, sample.mockContext(context))
                verify { context.getString(any()) }
                confirmVerified()
            }

            it("#mockActivity") {
                val resultString = "com.mockk"
                val activity = mockk<Activity>()
                every { activity.callingPackage } returns resultString
                val sample = Sample(mockk())

                assertEquals(resultString, sample.mockActivity(activity))
                verify { activity.callingPackage }
                confirmVerified()
            }
        }
    }

    describe("#testSingle") {
        beforeEachTest {
            RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        }
        afterEachTest {
            RxJavaPlugins.reset()
            RxAndroidPlugins.reset()
        }

        context("Module returns true") {
            it("Succeed to testSingle()") {
                val module = mockk<Module>()
                every { module.getSingle() } returns Single.just(listOf(1, 2, 3))

                val sample = Sample(module)
                sample.testSingle().test()
                    .assertValues("1", "3")
                    .assertComplete()
            }
        }

        context("step execute") {
            it("step 1") {
                val testScheduler1 = TestScheduler()
                RxJavaPlugins.setIoSchedulerHandler { testScheduler1 }
                val testScheduler2 = TestScheduler()
                RxJavaPlugins.setComputationSchedulerHandler { testScheduler2 }
                val module = mockk<Module>()
                every { module.getSingle() } returns Single.just(listOf(1, 2, 3))

                val sample = Sample(module)
                val subscriber = sample.testSingle().test()

                subscriber
                    .assertNoValues()
                    .assertNoErrors()
                    .assertNotComplete()

                testScheduler1.triggerActions()
                subscriber
                    .assertNoValues()
                    .assertNoErrors()
                    .assertNotComplete()

                subscriber
                    .assertValues("1", "3")
                    .assertComplete()
            }
        }

    }

})