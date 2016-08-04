package com.libin.androiduitesting;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * @author Libin
 */
@RunWith(AndroidJUnit4.class)
public class DeeplinkTest {

    private static final String TEST_PACKAGE
            = "com.libin.androiduitesting";
    private static final int LAUNCH_TIMEOUT = 5000;
    private UiDevice mDevice;

    @Before
    public void startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = mDevice.getCurrentPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);

        // Launch the app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(TEST_PACKAGE);
        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(TEST_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT);
    }

    @Test
    public void testDeeplinkURL() throws UiObjectNotFoundException {
//        UiObject2 object = mDevice.findObject(By.desc("Deeplink Test"));
//        object.click();
//
//        String url = "https://www.google.com/";
//
//        UiScrollable listView = new UiScrollable(new UiSelector());
//        UiObject listViewItem = listView.getChildByText(new UiSelector()
//                .className(android.widget.TextView.class.getName()), url);
//        listViewItem.click();
//
//        // Wait for the app to appear
//        mDevice.wait(Until.hasObject(By.pkg("com.google.chrome").depth(0)),
//                10000);
        onView(withId(R.id.landing_add_to_cart)).perform(click());

    }
}
