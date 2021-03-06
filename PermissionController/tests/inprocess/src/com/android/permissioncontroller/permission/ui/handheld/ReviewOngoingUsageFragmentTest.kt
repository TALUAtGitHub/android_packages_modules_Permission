/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.permissioncontroller.permission.ui.handheld

import android.Manifest.permission.CAMERA
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.android.permissioncontroller.permission.PermissionHub2Test
import com.android.permissioncontroller.permission.ui.CAMERA_TEST_APP_LABEL
import com.android.permissioncontroller.permission.ui.ReviewOngoingUsageActivity
import com.android.permissioncontroller.permission.ui.grantTestAppPermission
import com.android.permissioncontroller.permission.ui.installTestAppThatUsesCameraPermission
import com.android.permissioncontroller.permission.ui.uninstallTestApps
import org.junit.After
import org.junit.Rule
import org.junit.Test

/**
 * Simple tests for {@link ReviewOngoingUsageFragment}
 */
class ReviewOngoingUsageFragmentTest : PermissionHub2Test() {
    @get:Rule
    val managePermissionsActivity = object : ActivityTestRule<ReviewOngoingUsageActivity>(
            ReviewOngoingUsageActivity::class.java) {
        override fun beforeActivityLaunched() {
            installTestAppThatUsesCameraPermission()
            grantTestAppPermission(CAMERA)

            accessCamera()
        }
    }

    @Test
    fun cameraAccessShouldBeShown() {
        // Click on app entry
        onView(withText(CAMERA_TEST_APP_LABEL))
                .inRoot(isDialog())
                .perform(click())
    }

    @After
    fun cleanUp() = uninstallTestApps()
}