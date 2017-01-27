package sara.com.VolleyHelper;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import sara.com.Utils.General;

public class MainVolley {

    private static String TAG = "SearchImp";

    public static void getVolleyResponse(final Context context, int methodType, String url,
                                         final VolleyCallback callback) {

        RequestQueue requestQueue = CustomRequestQueue.getInstance(context).getRequestQueue();
        MyStringRequest stringRequest = new MyStringRequest(methodType, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            callback.onSuccess(response);

                        } catch (Exception e) {
                            General.errorLog(TAG, e.getMessage());
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                callback.onError(error.toString());

            }
        });

        requestQueue.add(stringRequest);
    }
}
