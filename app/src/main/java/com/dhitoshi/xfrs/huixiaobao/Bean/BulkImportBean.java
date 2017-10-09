package com.dhitoshi.xfrs.huixiaobao.Bean;
import java.util.List;
/**
 * Created by dxs on 2017/10/9.
 */
public class BulkImportBean {
    private List<EnterResultBean> fail;
    private List<EnterResultBean> success;
    public List<EnterResultBean> getFail() {
        return fail;
    }
    public void setFail(List<EnterResultBean> fail) {
        this.fail = fail;
    }
    public List<EnterResultBean> getSuccess() {
        return success;
    }
    public void setSuccess(List<EnterResultBean> success) {
        this.success = success;
    }
}
