package com.ghozay19.kamus.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class EngBhsModel implements Parcelable {

    private int id;
    private String word;
    private String detail;

    public EngBhsModel(){

    }

    public EngBhsModel(int id, String word, String detail){
        this.id = id;
        this.word = word;
        this.detail = detail;
    }

    public EngBhsModel(String word, String detail) {
        this.word = word;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.word);
        dest.writeString(this.detail);
    }

    protected EngBhsModel(Parcel in) {
        this.id = in.readInt();
        this.word = in.readString();
        this.detail = in.readString();
    }

    public static final Creator<EngBhsModel> CREATOR = new Creator<EngBhsModel>() {
        @Override
        public EngBhsModel createFromParcel(Parcel source) {
            return new EngBhsModel(source);
        }

        @Override
        public EngBhsModel[] newArray(int size) {
            return new EngBhsModel[size];
        }
    };
}