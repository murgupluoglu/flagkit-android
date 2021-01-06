package com.murgupluoglu.flagkit

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun FlagKit_test() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val drawable = FlagKit.getDrawable(appContext, "not_exist")
        assertNull(drawable)

        val resId = FlagKit.getResId(appContext, "not_exist")
        assertEquals(0, resId)
    }
}