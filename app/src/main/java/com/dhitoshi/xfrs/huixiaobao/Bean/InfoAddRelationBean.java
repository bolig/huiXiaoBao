package com.dhitoshi.xfrs.huixiaobao.Bean;
import java.util.List;
/**
 * Created by dxs on 2017/9/15.
 */

public class InfoAddRelationBean {
    private List<BaseBean> relation;
    private List<BaseBean> position;

    public List<BaseBean> getRelation() {
        return relation;
    }

    public void setRelation(List<BaseBean> relation) {
        this.relation = relation;
    }

    public List<BaseBean> getPosition() {
        return position;
    }

    public void setPosition(List<BaseBean> position) {
        this.position = position;
    }
}
