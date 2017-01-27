package sara.com.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import sara.com.Models.Group;
import sara.com.Models.Photo;

public class Parser {

    private static final String TAG = "Parser";

    public static ArrayList<Photo> photoParse(String parse) {

        ArrayList<Photo> lst = null;
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            Type type = new TypeToken<ArrayList<Photo>>() {
            }.getType();
            lst = gson.fromJson(parse, type);
        } catch (Exception e) {
            General.errorLog(TAG, e.getMessage());
        }
        return lst;
    }

    public static ArrayList<Group> groupParse(String parse) {

        ArrayList<Group> lst = null;
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            Type type = new TypeToken<ArrayList<Group>>() {
            }.getType();
            lst = gson.fromJson(parse, type);
        } catch (Exception e) {

            General.errorLog(TAG, e.getMessage());
        }
        return lst;
    }
}
