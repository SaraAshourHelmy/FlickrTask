package sara.com.ViewsInterfaces;

import java.util.ArrayList;

import sara.com.Base.BaseInterface;
import sara.com.Models.Photo;

public interface SearchPhotoInterface extends BaseInterface {

    void updatePhotos(ArrayList<Photo> photos);
}
