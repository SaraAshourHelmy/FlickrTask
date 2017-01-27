package sara.com.Views;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;

import java.util.ArrayList;

import sara.com.Adapters.PhotoAdapter;
import sara.com.Base.BaseActivity;
import sara.com.Models.Photo;
import sara.com.Presenters.Imp.SearPhotoPresenterImp;
import sara.com.Presenters.Interfaces.SearchPhotoPresenter;
import sara.com.Utils.Constants;
import sara.com.Utils.General;
import sara.com.ViewsInterfaces.SearchPhotoInterface;
import sara.com.Flickrtask.R;

public class SearchPhotoActivity extends BaseActivity implements
        SearchPhotoInterface, AbsListView.OnScrollListener, View.OnClickListener {

    private static final String TAG = "SearchPhotoActivity";
    private int photoPageNo = 1;
    private String tag = "";
    private SearchPhotoPresenter searchPhotoPresenter;
    private GridView grdVPhotos;
    private PhotoAdapter adapter;
    private boolean flagLoading = true;
    private Toolbar toolbar;
    private View progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_photo);

        toolbar = (Toolbar) findViewById(R.id.bar_search_photo);
        initViews(Constants.PHOTO_SEARCH, toolbar);

    }

    @Override
    protected void initViews(int type, Toolbar toolbar) {
        super.initViews(type, toolbar);

        tag = getSearchTag();
        searchPhotoPresenter = new SearPhotoPresenterImp(SearchPhotoActivity.this);
        searchPhotoPresenter.searchPhotos(tag, photoPageNo);

        grdVPhotos = (GridView) findViewById(R.id.grdV_photos);
        progress = findViewById(R.id.progress);
        progress.setVisibility(View.GONE);

        adapter = new PhotoAdapter(this, new ArrayList<Photo>());
        grdVPhotos.setAdapter(adapter);
        grdVPhotos.setOnScrollListener(this);
        iconSearch.setOnClickListener(this);
    }

    @Override
    public void internetConnectionError() {

        internetError();
    }

    @Override
    public void showDialog() {

        if (photoPageNo == 1) {
            showProgressDialog();
        } else {
            showProgress(progress);
        }
    }

    @Override
    public void hideDialog() {

        if (photoPageNo == 1) {
            hideProgressDialog();
        } else {
            hideProgress(progress);
        }

        flagLoading = false;
    }

    @Override
    public void updatePhotos(ArrayList<Photo> photos) {

        adapter.addAll(photos);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {


        if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0) {

            if (flagLoading == false) {
                flagLoading = true;
                photoPageNo++;
                searchPhotoPresenter.searchPhotos(tag, photoPageNo);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == iconSearch) {

            newSearch();
        }
    }

    private void newSearch() {

        tag = etToolbarSearch.getText().toString();
        if (tag.length() > 0) {
            flagLoading = true;
            hideKeyboard();
            photoPageNo = 1;

            adapter.clear();
            searchPhotoPresenter.searchPhotos(tag, photoPageNo);
        } else {
            etToolbarSearch.setError(getResources().getString(R.string.tag_error));
        }
    }
}
