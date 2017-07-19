package com.example.lenovo.text_music.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lenovo on 2017/7/6.
 */

public class MusicBean implements Parcelable{
    private int music_id; //ID
    private String music_name;//歌曲名
    private String music_album;//专辑名
    private String music_artst;//歌手名
    private String music_file_path;//文件路径
    private int music_duration;//歌曲时长
    private long music_size;//文件大小
    private boolean music_my_love = false;//我喜欢
    private String music_icon_path = "";//歌曲图片地址

    protected MusicBean(Parcel in) {
        music_id = in.readInt();
        music_name = in.readString();
        music_album = in.readString();
        music_artst = in.readString();
        music_file_path = in.readString();
        music_duration = in.readInt();
        music_size = in.readLong();
        music_my_love = in.readByte() != 0;
        music_icon_path = in.readString();
    }

    public static final Creator<MusicBean> CREATOR = new Creator<MusicBean>() {
        @Override
        public MusicBean createFromParcel(Parcel in) {
            return new MusicBean(in);
        }

        @Override
        public MusicBean[] newArray(int size) {
            return new MusicBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(music_id);
        parcel.writeString(music_name);
        parcel.writeString(music_album);
        parcel.writeString(music_artst);
        parcel.writeString(music_file_path);
        parcel.writeInt(music_duration);
        parcel.writeLong(music_size);
        parcel.writeByte((byte) (music_my_love ? 1 : 0));
        parcel.writeString(music_icon_path);
    }

    public MusicBean() {

    }

    public int getMusic_id() {
        return music_id;
    }

    public void setMusic_id(int music_id) {
        this.music_id = music_id;
    }

    public String getMusic_icon_path() {
        return music_icon_path;
    }

    public void setMusic_icon_path(String music_icon_path) {
        this.music_icon_path = music_icon_path;
    }

    public boolean isMusic_my_love() {
        return music_my_love;
    }

    public void setMusic_my_love(boolean music_my_love) {
        this.music_my_love = music_my_love;
    }

    public int getMusic_duration() {
        return music_duration;
    }

    public void setMusic_duration(int music_duration) {
        this.music_duration = music_duration;
    }

    public String getMusic_file_path() {
        return music_file_path;
    }

    public void setMusic_file_path(String music_file_path) {
        this.music_file_path = music_file_path;
    }

    public String getMusic_artst() {
        return music_artst;
    }

    public void setMusic_artst(String music_artst) {
        this.music_artst = music_artst;
    }

    public String getMusic_album() {
        return music_album;
    }

    public void setMusic_album(String music_album) {
        this.music_album = music_album;
    }

    public String getMusic_name() {
        return music_name;
    }

    public void setMusic_name(String music_name) {
        this.music_name = music_name;
    }

    public long getMusic_size() {
        return music_size;
    }

    public void setMusic_size(long music_size) {
        this.music_size = music_size;
    }

    @Override
    public String toString() {
        return "MusicBean{" +
                "music_id='" + music_id + '\'' +
                ", music_name='" + music_name + '\'' +
                ", music_album='" + music_album + '\'' +
                ", music_artst='" + music_artst + '\'' +
                ", music_file_path='" + music_file_path + '\'' +
                ", music_duration=" + music_duration +
                ", music_size=" + music_size +
                ", music_my_love=" + music_my_love +
                ", music_icon_path='" + music_icon_path + '\'' +
                '}';
    }
}
