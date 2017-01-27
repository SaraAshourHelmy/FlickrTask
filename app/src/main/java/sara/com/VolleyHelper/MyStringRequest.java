package sara.com.VolleyHelper;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MyStringRequest extends StringRequest {


    public MyStringRequest(int method, String url, Response.Listener<String> listener
            , Response.ErrorListener errorListener) {

        super(method, url, listener, errorListener);

    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {


        return new HashMap<>();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return new HashMap<>();
    }


    @Override
    public RetryPolicy getRetryPolicy() {
        return super.getRetryPolicy();
    }
}
