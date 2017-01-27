package sara.com.Presenters.Imp;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import sara.com.Models.Photo;
import sara.com.Presenters.Interfaces.SearchPhotoPresenter;
import sara.com.Utils.Constants;
import sara.com.Utils.General;
import sara.com.Utils.NetWorkHelper;
import sara.com.Utils.Parser;
import sara.com.ViewsInterfaces.SearchPhotoInterface;
import sara.com.VolleyHelper.MainVolley;
import sara.com.VolleyHelper.VolleyCallback;

public class SearPhotoPresenterImp implements SearchPhotoPresenter {

    private static String TAG = "SearchPhotoPresenterImp";
    private SearchPhotoInterface searchPhotoInterface;
    private ArrayList<Photo> result;
    private Context context;
    private String tag;
    private int pageNo;

    public SearPhotoPresenterImp(SearchPhotoInterface searchPhotoInterface) {

        this.searchPhotoInterface = searchPhotoInterface;
        context = (Context) searchPhotoInterface;
    }

    @Override
    public void searchPhotos(String tag, int page_no) {

        this.tag = tag;
        this.pageNo = page_no;

        if (NetWorkHelper.checkInternetConnection(context)) {

            searchPhotoInterface.showDialog();

            String url = NetWorkHelper.buildURL(getUrlParams());

            MainVolley.getVolleyResponse(context, Request.Method.GET, url, new VolleyCallback() {
                @Override
                public void onSuccess(String response) {

                    try {

                        response = response.substring(14, response.length() - 1);
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject photos = jsonObject.getJSONObject("photos");
                        JSONArray photo = photos.getJSONArray("photo");

                        result = Parser.photoParse(photo.toString());
                        searchPhotoInterface.updatePhotos(result);

                        searchPhotoInterface.hideDialog();

                    } catch (Exception e) {

                        General.errorLog(TAG, e.getMessage());
                        searchPhotoInterface.hideDialog();
                    }
                }

                @Override
                public void onError(String msg) {
                    searchPhotoInterface.hideDialog();
                    General.errorLog(TAG, msg);

                }
            });

        } else {

            searchPhotoInterface.internetConnectionError();
        }
    }


    private HashMap<String, String> getUrlParams() {

        HashMap<String, String> params = new HashMap<>();
        params.put(Constants.APIKEY, Constants.API_KEY);
        params.put(Constants.METHOD, Constants.method_search_photo);
        params.put(Constants.FORMAT, Constants.FORMAT_TYPE);
        params.put(Constants.PAGE, String.valueOf(pageNo));
        params.put(Constants.PER_PAGE, "10");
        params.put(Constants.SEARCH_TITLE, tag);
        return params;

    }

}
