package sara.com.ViewsInterfaces;

import java.util.ArrayList;

import sara.com.Base.BaseInterface;
import sara.com.Models.Group;

public interface SearchGroupInterface extends BaseInterface {

    void updateGroups(ArrayList<Group> groups);
}
