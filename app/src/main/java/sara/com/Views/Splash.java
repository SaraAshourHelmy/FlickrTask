package sara.com.Views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import sara.com.Base.BaseActivity;
import sara.com.Utils.General;
import sara.com.Flickrtask.R;

public class Splash extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        viewSplash();

    }

    private void viewSplash() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                moveToNext();
            }
        }, 2000);

    }

    private void moveToNext() {

        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
        finish();
    }
}
