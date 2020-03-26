package demo.com.groupchildstestdemo.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class GroupChildsModel {

    public GroupChildsModel() {

    }

    public enum STATE {
        CLOSED,
        OPENED
    }


    @SerializedName("Mid")
    @Expose
    private int mid;
    @SerializedName("Tid")
    @Expose
    private int tid;
    @SerializedName("amount")
    @Expose
    private float amount;
    @SerializedName("narration")
    @Expose
    private String narration;

    public String name;
    public int level;
    public STATE state = STATE.CLOSED;
    public String designation;
    public ArrayList<GroupChildsModel> childsModels = new ArrayList<>();

    public GroupChildsModel(String name, int level , String designation) {
        this.name = name;
        this.level = level;
        this.designation = designation;
    }


    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }



}
