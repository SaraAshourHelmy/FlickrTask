package sara.com.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import sara.com.Base.BaseActivity;
import sara.com.Utils.Constants;
import sara.com.Utils.General;
import sara.com.Flickrtask.R;

public class SearchActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private EditText etGeneralSearch;
    private ImageView imgSearch;
    private RadioGroup rgSearch;
    private int selectType = Constants.PHOTO_SEARCH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        toolbar = (Toolbar) findViewById(R.id.bar_search);
        initViews(Constants.GENERAL_SEARCH, toolbar);
    }

    @Override
    protected void initViews(int type, Toolbar toolbar) {
        super.initViews(type, toolbar);

        etGeneralSearch = (EditText) findViewById(R.id.et_general_search);
        imgSearch = (ImageView) findViewById(R.id.img_general_search);
        rgSearch = (RadioGroup) findViewById(R.id.rg_search);
        imgSearch.setOnClickListener(this);

        rgSearch.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.rb_photos:
                        selectType = Constants.PHOTO_SEARCH;
                        break;
                    case R.id.rb_groups:
                        selectType = Constants.GROUP_SEARCH;
                        break;
                }
            }
        });

        etGeneralSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                etGeneralSearch.setError(null);
                return false;
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v == imgSearch) {
            if (checkSearchTag()) {
                moveToSearchView();
            }
        }

    }

    private boolean checkSearchTag() {
        String tag = etGeneralSearch.getText().toString();
        if (tag.length() > 0)
            return true;
        else {
            etGeneralSearch.setError(getResources().getString(R.string.tag_error));
            return false;
        }
    }

    private void moveToSearchView() {

        hideKeyboard();
        String tag = etGeneralSearch.getText().toString();
        Intent intent = null;
        if (selectType == Constants.PHOTO_SEARCH)
            intent = new Intent(SearchActivity.this, SearchPhotoActivity.class);
        else if (selectType == Constants.GROUP_SEARCH) {
            intent = new Intent(SearchActivity.this, SearchGroupActivity.class);
        }

        intent.putExtra(Constants.SEARCH_TAG, tag);
        startActivity(intent);
        etGeneralSearch.setText("");
    }
}
