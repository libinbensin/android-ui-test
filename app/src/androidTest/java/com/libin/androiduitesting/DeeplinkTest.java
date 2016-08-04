package com.libin.androiduitesting;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * @author Libin
 */
@RunWith(AndroidJUnit4.class)
public class DeeplinkTest {

    private static final String HANGOUT_PACKAGE
            = "com.google.android.talk";
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
                .getLaunchIntentForPackage(HANGOUT_PACKAGE);
        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(HANGOUT_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT);
    }

    @Test
    public void testDeeplinkURL() throws UiObjectNotFoundException {
        UiObject2 object = mDevice.findObject(By.desc("Libin Salal, June 26, You: https://www.do"));
//        UiObject2 object = mDevice.findObject(By.desc("Dominos Test"));
        object.click();

        String url = "https://www.dominos.com/en/pages/order/?utm_source=MixMatch&utm_medium=EMAIL&utm_campaign=NAT&utm_agy=HS2&utm_content=160605,HS2_MxMtch,LOYSB-5,9193,1116534&clearOrder=1&couponCode=SB10MM-KKHK3FDFZ7&offercode=9193&treatment=LOYSB-5&segment=&storenumber=7207&fcode=F4747&headline=Nat_Redesign_v3#/locations/search/";
//        String url = "http://links.dominos.com/u.d?K4Gty1rPUOyqxbcl4d1hx=3311&utm_2source=MixMatch&utm_2medium=EMAIL&utm_2campaign=NAT&utm_2agy=HS2&utm_2content=160605_3HS2_2MxMtch_3LOYSB-5_39193_31116534&clearOrder=1&couponCode=SB10MM-KKHK3FDFZ7&offercode=9193&treatment=LOYSB-5&segment=&storenumber=7207&fcode=F4747&headline=Nat_2Redesign_2v3";

        UiScrollable listView = new UiScrollable(new UiSelector());
        UiObject listViewItem = listView.getChildByText(new UiSelector()
                .className(android.widget.TextView.class.getName()), url);
        listViewItem.click();

        // verify if domino's app started and validate that coupon popup shown

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg("com.dominospizza").depth(0)),
                10000);

    }
}
