package demo.com.groupchildstestdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GroupChildsModelResult {

  @SerializedName("sort")
  @Expose
  private List<GroupChildsModel> sort = null;

  public List<GroupChildsModel> getSort() {
    return sort;
  }

  public void setSort(List<GroupChildsModel> sort) {
    this.sort = sort;
  }

}
