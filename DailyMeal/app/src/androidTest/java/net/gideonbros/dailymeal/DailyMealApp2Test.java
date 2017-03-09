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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest @RunWith(AndroidJUnit4.class) public class DailyMealApp2Test {

  @Rule public ActivityTestRule<DailyMealActivity> mActivityTestRule =
      new ActivityTestRule<>(DailyMealActivity.class);

  @Test public void dailyMealApp2Test() {
    ViewInteraction floatingActionButton = onView(allOf(withId(R.id.fab), isDisplayed()));
    floatingActionButton.perform(click());

    ViewInteraction floatingActionButton2 = onView(allOf(withId(R.id.fab), isDisplayed()));
    floatingActionButton2.perform(click());

    ViewInteraction appCompatImageButton = onView(
        allOf(withContentDescription("Open navigation drawer"), withParent(withId(R.id.toolbar)),
            isDisplayed()));
    appCompatImageButton.perform(click());

    ViewInteraction appCompatCheckedTextView =
        onView(allOf(withId(R.id.design_menu_item_text), withText("Rate app"), isDisplayed()));
    appCompatCheckedTextView.perform(click());

    ViewInteraction appCompatImageButton2 =
        onView(allOf(withId(R.id.directions_button), isDisplayed()));
    appCompatImageButton2.perform(click());

    ViewInteraction appCompatButton =
        onView(allOf(withId(R.id.action_button), withText("Order"), isDisplayed()));
    appCompatButton.perform(click());

    pressBack();

    ViewInteraction appCompatButton2 =
        onView(allOf(withId(R.id.action_button), withText("Order"), isDisplayed()));
    appCompatButton2.perform(click());

    pressBack();
  }
}
