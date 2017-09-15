package com.dhitoshi.xfrs.huixiaobao.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dxs on 2017/9/7.
 */
public class VisitBean implements Parcelable {
    /**
     * id : 2
     * createtime : 2017-09-10 08:00:00
     * nexttime : 2017-10-10 08:00:00
     * feedtype : 回访电话
     * feedbody : 回访内容
     * notes : 备注
     * advice : 客户建议
     * img : http://xfrsimg.oss-cn-hangzhou.aliyuncs.com/hxbfeedback/15046927810fd7f519abfc477a43beebfd7528ba29.png
     * customer_name : kehu
     * feedman_name : tes1t
     */
    private int id;
    private String createtime;
    private String nexttime;
    private String feedtype;
    private String feedbody;
    private String notes;
    private String advice;
    private String img;
    private String customer_name;
    private String feedman_name;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCreatetime() {
        return createtime;
    }
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    public String getNexttime() {
        return nexttime;
    }
    public void setNexttime(String nexttime) {
        this.nexttime = nexttime;
    }
    public String getFeedtype() {
        return feedtype;
    }
    public void setFeedtype(String feedtype) {
        this.feedtype = feedtype;
    }
    public String getFeedbody() {
        return feedbody;
    }
    public void setFeedbody(String feedbody) {
        this.feedbody = feedbody;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getFeedman_name() {
        return feedman_name;
    }

    public void setFeedman_name(String feedman_name) {
        this.feedman_name = feedman_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.createtime);
        dest.writeString(this.nexttime);
        dest.writeString(this.feedtype);
        dest.writeString(this.feedbody);
        dest.writeString(this.notes);
        dest.writeString(this.advice);
        dest.writeString(this.img);
        dest.writeString(this.customer_name);
        dest.writeString(this.feedman_name);
    }

    public VisitBean() {
    }

    protected VisitBean(Parcel in) {
        this.id = in.readInt();
        this.createtime = in.readString();
        this.nexttime = in.readString();
        this.feedtype = in.readString();
        this.feedbody = in.readString();
        this.notes = in.readString();
        this.advice = in.readString();
        this.img = in.readString();
        this.customer_name = in.readString();
        this.feedman_name = in.readString();
    }

    public static final Parcelable.Creator<VisitBean> CREATOR = new Parcelable.Creator<VisitBean>() {
        @Override
        public VisitBean createFromParcel(Parcel source) {
            return new VisitBean(source);
        }

        @Override
        public VisitBean[] newArray(int size) {
            return new VisitBean[size];
        }
    };
}
