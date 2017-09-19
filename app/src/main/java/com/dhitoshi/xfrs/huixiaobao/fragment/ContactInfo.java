package com.dhitoshi.xfrs.huixiaobao.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dhitoshi.xfrs.huixiaobao.R;

public class ContactInfo extends Fragment {

    public ContactInfo() {

    }
    public static ContactInfo newInstance() {
        ContactInfo fragment = new ContactInfo();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contact_info, container, false);
    }

}
