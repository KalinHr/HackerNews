package com.example.kalinhristov.get.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Story implements Parcelable {

    @SerializedName("by")
    @Expose
    private String by = null;
    @SerializedName("descendants")
    @Expose
    private Integer descendants = 0;
    @SerializedName("id")
    @Expose
    private Integer id = 0;
    @SerializedName("kids")
    @Expose
    private List<Integer> kids = new ArrayList<>();
    @SerializedName("score")
    @Expose
    private Integer score = 0;
    @SerializedName("time")
    @Expose
    private Integer time = 0;
    @SerializedName("title")
    @Expose
    private String title = null;
    @SerializedName("type")
    @Expose
    private String type = null;
    @SerializedName("url")
    @Expose
    private String url = null;

    private Story(Parcel in) {
        by = in.readString();
        descendants = in.readInt();
        id = in.readInt();
        kids = new ArrayList<>();
        in.readList(kids, Integer.class.getClassLoader());
        score = in.readInt();
        time = in.readInt();
        title = in.readString();
        type = in.readString();
        url = in.readString();
    }

    public static final Parcelable.Creator<Story> CREATOR
            = new Parcelable.Creator<Story>() {
        public Story createFromParcel(Parcel in) {
            return new Story(in);
        }

        public Story[] newArray(int size) {
            return new Story[size];
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
     * The descendants
     */
    public Integer getDescendants() {
        return descendants;
    }

    /**
     *
     * @param descendants
     * The descendants
     */
    public void setDescendants(Integer descendants) {
        this.descendants = descendants;
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
     * The score
     */
    public Integer getScore() {
        return score;
    }

    /**
     *
     * @param score
     * The score
     */
    public void setScore(Integer score) {
        this.score = score;
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
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
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

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(by);
        parcel.writeInt(descendants);
        parcel.writeInt(id);
        parcel.writeList(kids);
        parcel.writeInt(score);
        parcel.writeInt(time);
        parcel.writeString(title);
        parcel.writeString(type);
        parcel.writeString(url);
    }
}