package com.dhitoshi.xfrs.huixiaobao.model;

import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.ClientManage;

import java.util.List;

/**
 * Created by dxs on 2017/9/6.
 */

public class ClientModel implements ClientManage.Model{

    @Override
    public void getClientList(String type, String area, String order, String page, Callback<List<ClientBean>> callback) {

    }
}
