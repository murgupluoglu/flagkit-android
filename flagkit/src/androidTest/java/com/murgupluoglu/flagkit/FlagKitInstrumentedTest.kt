package com.murgupluoglu.flagkit

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class FlagKitInstrumentedTest {
    @Test
    fun FlagKit_test() {

        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val drawable = FlagKit.getDrawable(appContext, "not_exist")
        assertNull(drawable)

        val resId = FlagKit.getResId(appContext, "not_exist")
        assertEquals(0, resId)

        val doFlag = FlagKit.getDrawable(appContext, "do")
        assertNotNull(doFlag)

    }
}