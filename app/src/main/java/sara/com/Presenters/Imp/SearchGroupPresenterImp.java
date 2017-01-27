package sara.com.Presenters.Imp;

import android.content.Context;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import sara.com.Models.Group;
import sara.com.Presenters.Interfaces.SearchGroupPresenter;
import sara.com.Utils.Constants;
import sara.com.Utils.General;
import sara.com.Utils.NetWorkHelper;
import sara.com.Utils.Parser;
import sara.com.ViewsInterfaces.SearchGroupInterface;
import sara.com.VolleyHelper.MainVolley;
import sara.com.VolleyHelper.VolleyCallback;

public class SearchGroupPresenterImp implements SearchGroupPresenter {

    private static String TAG = "SearchGroupPresenterImp";
    private SearchGroupInterface searchGroupInterface;
    private Context context;
    private String groupTitle;
    private int pageNo;

    public SearchGroupPresenterImp(SearchGroupInterface searchGroupInterface) {

        this.searchGroupInterface = searchGroupInterface;
        context = (Context) searchGroupInterface;
    }

    private HashMap<String, String> getUrlParams() {

        HashMap<String, String> params = new HashMap<>();
        params.put(Constants.APIKEY, Constants.API_KEY);
        params.put(Constants.METHOD, Constants.method_search_group);
        params.put(Constants.FORMAT, Constants.FORMAT_TYPE);
        params.put(Constants.PAGE, String.valueOf(pageNo));
        params.put(Constants.PER_PAGE, "15");
        params.put(Constants.SEARCH_TITLE, groupTitle);
        return params;

    }

    @Override
    public void searchGroups(String text, int pageNo) {

        this.groupTitle = text;
        this.pageNo = pageNo;

        if (NetWorkHelper.checkInternetConnection(context)) {

            searchGroupInterface.showDialog();
            String url = NetWorkHelper.buildURL(getUrlParams());

            MainVolley.getVolleyResponse(context, Request.Method.GET, url, new VolleyCallback() {
                @Override
                public void onSuccess(String response) {

                    try {
                        response = response.substring(14, response.length() - 1);
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject groups = jsonObject.getJSONObject("groups");
                        JSONArray group = groups.getJSONArray("group");

                        ArrayList<Group> result = Parser.groupParse(group.toString());
                        searchGroupInterface.updateGroups(result);
                        searchGroupInterface.hideDialog();

                    } catch (Exception e) {

                        General.errorLog(TAG, e.getMessage());

                        searchGroupInterface.hideDialog();
                    }
                }

                @Override
                public void onError(String msg) {

                    searchGroupInterface.hideDialog();
                    General.errorLog(TAG, msg);
                }
            });
        } else {

            searchGroupInterface.internetConnectionError();
        }
    }
}
