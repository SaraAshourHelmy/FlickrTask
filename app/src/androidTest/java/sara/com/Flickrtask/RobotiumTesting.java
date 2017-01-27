package sara.com.Flickrtask;

import android.test.ActivityInstrumentationTestCase2;
import android.util.DisplayMetrics;

import com.robotium.solo.Solo;

import sara.com.Views.SearchActivity;


@SuppressWarnings("rawtypes")
public class RobotiumTesting extends ActivityInstrumentationTestCase2 {

    private Solo solo;
    private static int Photo = 1;
    private static int Group = 2;

    public RobotiumTesting() {
        super(SearchActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testRun() {

        setSearchTag(Photo);
        // scroll Up & Down
        scrollUpDown();

        // clear search tag
        setNewSearch();

        setSearchTag(Group);
        solo.sleep(2000);

        // scroll Group
        scrollUpDown();
        setNewSearch();
    }

    private void setSearchTag(int type) {
        // wait for searchActivity
        solo.waitForActivity(solo.getCurrentActivity().toString(), 1000);


        // write search tag
        solo.enterText(0, "Panda");
        solo.waitForActivity(solo.getCurrentActivity().toString(), 1000);

        if (type == Photo)
            solo.clickOnRadioButton(0);
        else
            solo.clickOnRadioButton(1);

        // click button
        solo.clickOnImage(1);
        solo.waitForDialogToClose();
    }

    private void scrollUpDown() {

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int screenWidth = metrics.widthPixels;
        int screenHeight = metrics.heightPixels;
        int fromX, toX, fromY, toY = 0;
        fromX = screenWidth / 2;
        toX = screenWidth / 2;
        fromY = (screenHeight / 2) + (screenHeight / 3);
        toY = (screenHeight / 2) - (screenHeight / 3);



        // Drag UP
        solo.drag(fromX, toX, fromY, toY, 40);
        solo.drag(fromX, toX, fromY, toY, 40);
        solo.drag(fromX, toX, fromY, toY, 40);
        solo.drag(fromX, toX, fromY, toY, 40);

        // scroll up
        solo.drag(fromX, toX, toY, fromY, 40);
        solo.drag(fromX, toX, toY, fromY, 40);
        solo.drag(fromX, toX, toY, fromY, 40);
        solo.drag(fromX, toX, toY, fromY, 40);

    }

    private void setNewSearch() {

        solo.clearEditText(0);
        solo.clickOnImage(0);
        solo.waitForActivity(solo.getCurrentActivity().toString(), 3000);


        solo.clickOnEditText(0);
        solo.enterText(0, "Cats");
        solo.clickOnImage(0);
        solo.waitForDialogToClose();

        solo.waitForActivity(solo.getCurrentActivity().toString(), 3000);
        scrollUpDown();

        // for back
        solo.clickOnImage(1);
        solo.sleep(3000);

    }

    @Override
    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }
}
