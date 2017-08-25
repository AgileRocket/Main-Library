package rocket.agile.com.mainlibrary;

import android.support.test.rule.ActivityTestRule;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import rocket.agile.com.mainlibrary.activity.LayoutManager;
import rocket.agile.com.mainlibrary.activity.MasterView;
import tools.fastlane.screengrab.Screengrab;
import tools.fastlane.screengrab.UiAutomatorScreenshotStrategy;
import tools.fastlane.screengrab.locale.LocaleTestRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by keithkowalski on 8/18/17.
 */

@RunWith(JUnit4.class)
public class JUnit4StyleTests {

    @ClassRule
    public static final LocaleTestRule localeTestRule = new LocaleTestRule();

    @Rule
    public ActivityTestRule<LayoutManager> activityRule = new ActivityTestRule<>(LayoutManager.class);

    @BeforeClass
    public static void beforeAll() {
        Screengrab.setDefaultScreenshotStrategy(new UiAutomatorScreenshotStrategy());
    }

    @Test
    public void testTakeScreenshot() {
        onView(withId(R.id.id_main)).check(matches(isDisplayed()));
        Screengrab.screenshot("id_main");
    }

//    @Test
//    public void testTakeMoreScreenshots() {
//        onView(withId(R.id.nav_button)).perform(click());
//
//        Screengrab.screenshot("anotherActivity");
//
//        onView(withId(R.id.show_dialog_button)).perform(click());
//
//        Screengrab.screenshot("anotherActivity-dialog");
//
//        onView(withText(android.R.string.ok)).perform(click());
//    }
}
