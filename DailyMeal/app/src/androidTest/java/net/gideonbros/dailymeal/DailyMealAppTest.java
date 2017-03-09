package net.gideonbros.dailymeal;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import net.gideonbros.dailymeal.presentation.view.activities.DailyMealActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest @RunWith(AndroidJUnit4.class) public class DailyMealAppTest {

  @Rule public ActivityTestRule<DailyMealActivity> mActivityTestRule =
      new ActivityTestRule<>(DailyMealActivity.class);

  @Test public void dailyMealAppTest() {
    ViewInteraction appCompatButton =
        onView(allOf(withId(R.id.action_button), withText("Order"), isDisplayed()));
    appCompatButton.perform(click());

    ViewInteraction appCompatImageButton = onView(allOf(withContentDescription("Navigate up"),
        withParent(allOf(withId(R.id.toolbar), withParent(withId(R.id.collapsing_toolbar)))),
        isDisplayed()));
    appCompatImageButton.perform(click());

    ViewInteraction appCompatButton2 =
        onView(allOf(withId(R.id.action_button), withText("Order"), isDisplayed()));
    appCompatButton2.perform(click());

    ViewInteraction appCompatImageButton2 = onView(allOf(withContentDescription("Navigate up"),
        withParent(allOf(withId(R.id.toolbar), withParent(withId(R.id.collapsing_toolbar)))),
        isDisplayed()));
    appCompatImageButton2.perform(click());

    ViewInteraction floatingActionButton = onView(allOf(withId(R.id.fab), isDisplayed()));
    floatingActionButton.perform(click());

    ViewInteraction appCompatImageButton3 = onView(
        allOf(withContentDescription("Open navigation drawer"), withParent(withId(R.id.toolbar)),
            isDisplayed()));
    appCompatImageButton3.perform(click());

    pressBack();

    ViewInteraction appCompatImageView = onView(
        allOf(withId(R.id.search_button), withContentDescription("Search"),
            withParent(allOf(withId(R.id.search_bar), withParent(withId(R.id.action_search)))),
            isDisplayed()));
    appCompatImageView.perform(click());

    ViewInteraction searchAutoComplete = onView(allOf(withId(R.id.search_src_text),
        withParent(allOf(withId(R.id.search_plate), withParent(withId(R.id.search_edit_frame)))),
        isDisplayed()));
    searchAutoComplete.perform(replaceText("stea"), closeSoftKeyboard());

    ViewInteraction appCompatImageView2 = onView(
        allOf(withId(R.id.search_close_btn), withContentDescription("Clear query"), withParent(
            allOf(withId(R.id.search_plate), withParent(withId(R.id.search_edit_frame)))),
            isDisplayed()));
    appCompatImageView2.perform(click());

    pressBack();
  }
}
