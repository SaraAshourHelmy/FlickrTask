package sara.com.Base;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import sara.com.Utils.Constants;
import sara.com.Utils.General;
import sara.com.Flickrtask.R;


public class BaseActivity extends AppCompatActivity implements View.OnTouchListener {

    private static final int INTERNET = 1;
    private static final int NETWORK = 2;
    protected ProgressDialog dialog;
    protected RelativeLayout rlvToolbar, rlvToolbarLogo;
    protected FrameLayout iconSearch;
    protected EditText etToolbarSearch;

    protected void internetError() {


        String msg = getResources().getString(R.string.internet_error);

        General.showToast(this, msg);
    }

    protected void initViews(int type, Toolbar toolbar) {

        setSupportActionBar(toolbar);
        rlvToolbar = (RelativeLayout) toolbar.findViewById(R.id.rlv_toolbar);
        rlvToolbarLogo = (RelativeLayout) toolbar.findViewById(R.id.rlv_toolbar_logo);
        iconSearch = (FrameLayout) toolbar.findViewById(R.id.icon_search);
        etToolbarSearch = (EditText) toolbar.findViewById(R.id.et_toolbar_search);
        etToolbarSearch.setOnTouchListener(this);

        if (type == Constants.GENERAL_SEARCH) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            rlvToolbar.setVisibility(View.GONE);
            rlvToolbarLogo.setVisibility(View.VISIBLE);
        } else {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            rlvToolbar.setVisibility(View.VISIBLE);
            rlvToolbarLogo.setVisibility(View.GONE);
        }
    }

    protected void showProgressDialog() {

        dialog = new ProgressDialog(this);
        dialog.setMessage(getResources().getString(R.string.wait));
        dialog.setCancelable(false);
        dialog.show();
    }

    protected void hideProgressDialog() {

        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (v == etToolbarSearch) {
            etToolbarSearch.setError(null);
            etToolbarSearch.setFocusableInTouchMode(true);
        }
        return false;
    }

    protected void hideKeyboard() {

        InputMethodManager imm =
                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    protected String getSearchTag() {

        String tag = "";
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(Constants.SEARCH_TAG)) {
            tag = bundle.getString(Constants.SEARCH_TAG);
            etToolbarSearch.setText(tag);
        }
        return tag;
    }

    protected void showProgress(View view) {

        if (view != null) {
            Animation bottomUp = AnimationUtils.loadAnimation(this,
                    R.anim.anim_up);
            view.startAnimation(bottomUp);
            view.setVisibility(View.VISIBLE);
        }
    }

    protected void hideProgress(final View view) {

        if (view != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Animation bottom_down = AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.anim_down);
                    view.startAnimation(bottom_down);
                    view.setVisibility(View.GONE);
                }
            }, 700);
        }


    }



}
