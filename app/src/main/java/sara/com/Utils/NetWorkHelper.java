package sara.com.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.URISyntaxException;
import java.util.HashMap;

import cz.msebera.android.httpclient.client.utils.URIBuilder;

public class NetWorkHelper {

    public static boolean checkInternetConnection(Context context) {


        boolean HaveConnectedWifi = false;
        boolean HaveConnectedMobile = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    HaveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    HaveConnectedMobile = true;
        }
        return HaveConnectedWifi || HaveConnectedMobile;
    }

    public static String buildURL(HashMap<String, String> params) {


        URIBuilder builder = new URIBuilder()
                .setScheme("https")
                .setHost(Constants.BASE_URL);

        for (HashMap.Entry<String, String> param : params.entrySet()) {

            builder.addParameter(param.getKey(), param.getValue());
        }
        try {
            return builder.build().toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}