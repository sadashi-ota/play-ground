package com.sadashi.playground

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

class SampleTest: Spek({

    describe("Mock Module Class") {
        context("#getMockModuleString") {
            it ("valid pattern") {
                val module = mockk<Module>()
                every { module.getString() } returns "mock string"

                val sample = Sample(module)
                assertEquals("mock string", sample.getMockModuleString())
                verify { module.getString() }
                confirmVerified()
            }
        }

        context("#getMockModuleInt") {
            it ("valid pattern") {
                val module = mockk<Module>()
                every { module.getInt() } returns 0

                val sample = Sample(module)
                assertEquals(0, sample.getMockModuleInt())
                verify { module.getInt() }
                confirmVerified()
            }
        }
    }

})