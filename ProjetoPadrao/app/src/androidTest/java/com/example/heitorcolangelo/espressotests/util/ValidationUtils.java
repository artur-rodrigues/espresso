package com.example.heitorcolangelo.espressotests.util;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.test.espresso.Espresso;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Artur on 30/12/2019.
 */
public final class ValidationUtils {
    private ValidationUtils() {}

    public static void viewWithIdIsDisplayed(@IdRes int id) {
        onView(withId(id)).check(matches(isDisplayed()));
    }

    public static void viewWithTextIsDisplayed(String text) {
        onView(withText(text)).check(matches(isDisplayed()));
    }

    public static void viewWithTextIsDisplayed(@StringRes int id) {
        onView(withText(id)).check(matches(isDisplayed()));
    }

    public static void viewWithIdHasText(@IdRes int id, String text) {
        onView(withId(id)).check(matches(withText(text)));
    }

    public static void viewWithIdHasText(@IdRes int id, @StringRes int textId) {
        onView(withId(id)).check(matches(withText(textId)));
    }

    public static void viewWithIdHasHint(@IdRes int id, String text) {
        onView(withId(id)).check(matches(withHint(text)));
    }

    public static void viewWithIdHasHint(@IdRes int id, @StringRes int textId) {
        onView(withId(id)).check(matches(withHint(textId)));
    }

    public static void clickViewWithId(@IdRes int id) {
        onView(withId(id)).perform(click());
    }
}
