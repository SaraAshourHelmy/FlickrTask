package sara.com.Flickrtask;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import sara.com.Views.SearchActivity;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


//@RunWith(AndroidJUnit4.class)
public class espressoTesting {

    @Rule
    public ActivityTestRule<SearchActivity> testRule = new ActivityTestRule<SearchActivity>
            (SearchActivity.class);

    @Test
    public void mainTest() {

        onView(withId(R.id.et_general_search)).perform(click());
        waitTime(1000);

        onView(withId(R.id.et_general_search))
                .perform(typeText("panda"), closeSoftKeyboard());
        waitTime(2000);

        onView(withId(R.id.rb_groups)).perform(click());
        waitTime(1000);

        onView(withId(R.id.img_general_search)).perform(click());
        waitTime(7000);

        onView(withId(R.id.lst_groups)).perform(swipeUp());
        waitTime(2000);


        ViewActions.pressBack();
        waitTime(3000);


    }

    public void waitTime(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
