package sara.com.Views;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;

import sara.com.Adapters.GroupAdapter;
import sara.com.Base.BaseActivity;
import sara.com.Models.Group;
import sara.com.Presenters.Imp.SearchGroupPresenterImp;
import sara.com.Presenters.Interfaces.SearchGroupPresenter;
import sara.com.Utils.Constants;
import sara.com.Utils.General;
import sara.com.ViewsInterfaces.SearchGroupInterface;
import sara.com.Flickrtask.R;

public class SearchGroupActivity extends BaseActivity implements SearchGroupInterface,
        AbsListView.OnScrollListener, View.OnClickListener {

    private static final String TAG = "SearchGroupActivity";
    private SearchGroupPresenter searchGroupPresenter;
    private ListView lstVGroups;
    private GroupAdapter adapter;
    private boolean flagLoading = true;
    private int groupPageNo = 1;
    private Toolbar toolbar;
    private String tag = "";
    private View progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_group);


        toolbar = (Toolbar) findViewById(R.id.bar_search_group);
        initViews(Constants.GROUP_SEARCH, toolbar);
    }

    @Override
    protected void initViews(int type, Toolbar toolbar) {
        super.initViews(type, toolbar);
        tag = getSearchTag();

        searchGroupPresenter = new SearchGroupPresenterImp(SearchGroupActivity.this);

        searchGroupPresenter.searchGroups(tag, groupPageNo);

        lstVGroups = (ListView) findViewById(R.id.lst_groups);
        progress = findViewById(R.id.progress);
        progress.setVisibility(View.GONE);


        adapter = new GroupAdapter(this, new ArrayList<Group>());

        lstVGroups.setAdapter(adapter);
        lstVGroups.setOnScrollListener(this);
        iconSearch.setOnClickListener(this);

    }

    @Override
    public void internetConnectionError() {

        internetError();
    }

    @Override
    public void showDialog() {


        if (groupPageNo == 1) {

            showProgressDialog();
        } else {
            showProgress(progress);
        }
    }

    @Override
    public void hideDialog() {

        if (groupPageNo == 1) {

            hideProgressDialog();
        } else {

            hideProgress(progress);
        }

        flagLoading = false;
    }

    @Override
    public void updateGroups(ArrayList<Group> groups) {

        adapter.addAll(groups);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0) {

            if (flagLoading == false) {
                flagLoading = true;
                groupPageNo++;

                searchGroupPresenter.searchGroups(tag, groupPageNo);
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

            hideKeyboard();

            flagLoading = true;
            groupPageNo = 1;

            adapter.clear();
            searchGroupPresenter.searchGroups(tag, groupPageNo);

        } else {
            etToolbarSearch.setError(getResources().getString(R.string.tag_error));
        }
    }
}
