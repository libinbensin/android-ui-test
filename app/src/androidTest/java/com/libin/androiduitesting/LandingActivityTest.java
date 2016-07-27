package com.libin.androiduitesting;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * @author Libin
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LandingActivityTest {

    @Rule
    public ActivityTestRule<LandingActivity> mActivityTestRule = new ActivityTestRule<>(LandingActivity.class);

    @Test
    public void testActivityNavigation(){
        onView(withId(R.id.landing_add_to_cart)).perform(click());

        onView(withId(R.id.cart_checkout_button)).perform(click());

        onView(withId(R.id.checkout_place_order_button)).perform(click());

        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());

        onView(withText(R.string.add_to_cart)).check(matches(isDisplayed()));
    }
}
