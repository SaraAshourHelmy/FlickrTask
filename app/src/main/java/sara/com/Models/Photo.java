package sara.com.Models;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("id")
    @Expose
    private String photoId;

    @Expose
    private String secret;

    @Expose
    private String server;

    @Expose
    private int farm;

    @Expose(serialize = false, deserialize = false)
    private Bitmap photo;


    @Expose(serialize = false, deserialize = false)
    private String photoUrl;

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getPhotoUrl() {
        // return photoUrl;
        String url = "http://farm" + farm + ".static.flickr.com/"
                + server + "/" + photoId + "_" + secret + "_m.jpg";

        return url;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }

    public String getSecret() {
        return secret;
    }

    public String getServer() {
        return server;
    }

    public int getFarm() {
        return farm;
    }
    
}
