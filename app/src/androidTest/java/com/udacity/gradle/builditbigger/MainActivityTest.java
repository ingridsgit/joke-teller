package com.udacity.gradle.builditbigger;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    private IdlingResource idlingResource;
    private IdlingRegistry idlingRegistry;



    @Before
    public void registerIdlingResource() {
        idlingResource = mainActivityTestRule.getActivity().getIdlingResource();
        idlingRegistry = IdlingRegistry.getInstance();
        idlingRegistry.register(idlingResource);
    }

    @Test
    public void clickOnButton_returnsAJoke(){
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html

        ViewInteraction appCompatButton = onView(
                allOf(withText("Tell Joke"),
                        childAtPosition(
                                allOf(withId(R.id.fragment),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        onView(withId(R.id.joke_text_view)).check(matches(not(withText(""))));

    }

    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null && idlingRegistry != null) {
            idlingRegistry.unregister(idlingResource);
        }
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }


}