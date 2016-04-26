package com.example.kalinhristov.get.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Comment implements Parcelable {

    @SerializedName("by")
    @Expose
    private String by;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("kids")
    @Expose
    private List<Integer> kids = new ArrayList<Integer>();
    @SerializedName("parent")
    @Expose
    private Integer parent;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("type")
    @Expose
    private String type;

    private Comment(Parcel in) {
        by = in.readString();
        id = in.readInt();
        kids = new ArrayList<>();
        in.readList(kids, Integer.class.getClassLoader());
        parent = in.readInt();
        text = in.readString();
        time = in.readInt();
        type = in.readString();
    }

    public static final Parcelable.Creator<Comment> CREATOR
            = new Parcelable.Creator<Comment>() {
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    /**
     *
     * @return
     * The by
     */
    public String getBy() {
        return by;
    }

    /**
     *
     * @param by
     * The by
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The kids
     */
    public List<Integer> getKids() {
        return kids;
    }

    /**
     *
     * @param kids
     * The kids
     */
    public void setKids(List<Integer> kids) {
        this.kids = kids;
    }

    /**
     *
     * @return
     * The parent
     */
    public Integer getParent() {
        return parent;
    }

    /**
     *
     * @param parent
     * The parent
     */
    public void setParent(Integer parent) {
        this.parent = parent;
    }

    /**
     *
     * @return
     * The text
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     * The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     * The time
     */
    public Integer getTime() {
        return time;
    }

    /**
     *
     * @param time
     * The time
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(by);
        parcel.writeInt(id);
        parcel.writeList(kids);
        parcel.writeInt(parent);
        parcel.writeString(text);
        parcel.writeInt(time);
        parcel.writeString(type);
    }
}
