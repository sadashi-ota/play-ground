package com.sadashi.playground

import android.app.Activity
import android.content.Context
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
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

})