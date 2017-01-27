package sara.com.Utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


public class General {


    public static void showToast(Context context, String msg) {

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void errorLog(String tag, String msg) {
        Log.e(tag, msg);
    }


}
