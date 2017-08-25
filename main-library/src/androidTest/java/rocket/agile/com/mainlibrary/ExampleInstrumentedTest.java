//package rocket.agile.com.mainlibrary;
//
//import android.content.Context;
//import android.support.test.InstrumentationRegistry;
//import android.support.test.rule.ActivityTestRule;
//import android.support.test.runner.AndroidJUnit4;
//import org.junit.ClassRule;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import rocket.agile.com.mainlibrary.activity.LayoutView_SideMenu;
//import rocket.agile.com.mainlibrary.activity.MasterView;
//import tools.fastlane.screengrab.Screengrab;
//import tools.fastlane.screengrab.locale.LocaleTestRule;
//import static android.support.test.espresso.Espresso.onView;
//import static android.support.test.espresso.action.ViewActions.click;
//import static android.support.test.espresso.matcher.ViewMatchers.withId;
//import static org.junit.Assert.*;
//
///**
// * Instrumentation test, which will execute on an Android device.
// *
// * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
// */
//@RunWith(AndroidJUnit4.class)
//public class ExampleInstrumentedTest {
////    @Test
////    public void useAppContext() throws Exception {
////        // Context of the app under test.
////        Context appContext = InstrumentationRegistry.getTargetContext();
////
////        assertEquals("rocket.agile.com.mainlibrary", appContext.getPackageName());
////        testTakeScreenshot();
////    }
//
//    @ClassRule
//    public static final LocaleTestRule localeTestRule = new LocaleTestRule();
//
//    @Rule
//    public ActivityTestRule<MasterView> activityRule = new ActivityTestRule<>(MasterView.class);
//
//    @Test
//    public void testTakeScreenshot() {
//        Screengrab.screenshot("ScreenShot-MasterView");
//
////        onView(withId(R.id.fab)).perform(click());
////
////        Screengrab.screenshot("after_button_click");
//    }
//
//}
