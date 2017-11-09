package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import com.alibaba.mobileim.ui.atmessage.ReceiveAtMsgListFragment;
import com.alibaba.mobileim.ui.atmessage.SendAtMessageListFragment;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.MsgTabAdapter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
public class AtMsgList extends BaseView {
    @BindView(R.id.msg_viewpager)
    ViewPager msgViewpager;
    @BindView(R.id.msg_tablayout)
    TabLayout msgTablayout;
    private MsgTabAdapter adapter;
    private List<Fragment> fragmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.at_msg_list);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
        initBaseViews();
        setTitle("全部@消息");
        fragmentList=new ArrayList<>();
        fragmentList.add(createFragment(ReceiveAtMsgListFragment.class,getIntent().getBundleExtra("bundle")));
        fragmentList.add(createFragment(SendAtMessageListFragment.class,getIntent().getBundleExtra("bundle")));
        adapter=new MsgTabAdapter(getSupportFragmentManager(),fragmentList);
        msgViewpager.setAdapter(adapter);
        msgTablayout.setupWithViewPager(msgViewpager);
    }
    public Fragment createFragment(Class<? extends Fragment> fragment, Bundle argument) {
        if(argument == null) {
            return this.createFragmentWithNullArg(fragment);
        } else {
            Fragment f = null;

            try {
                Method e = fragment.getDeclaredMethod("newInstance", new Class[]{Bundle.class});
                e.setAccessible(true);

                try {
                    Object e1 = fragment.newInstance();
                    f = (Fragment)e.invoke(e1, new Object[]{argument});
                } catch (IllegalAccessException var6) {
                    var6.printStackTrace();
                } catch (InvocationTargetException var7) {
                    var7.printStackTrace();
                } catch (InstantiationException var8) {
                    var8.printStackTrace();
                }
            } catch (NoSuchMethodException var9) {
                var9.printStackTrace();
            }

            return f;
        }
    }
    private Fragment createFragmentWithNullArg(Class<? extends Fragment> fragment) {
        Fragment f = null;

        try {
            Method e = fragment.getDeclaredMethod("newInstance", new Class[0]);
            e.setAccessible(true);

            try {
                Object e1 = fragment.newInstance();
                f = (Fragment)e.invoke(e1, new Object[0]);
            } catch (IllegalAccessException var5) {
                var5.printStackTrace();
            } catch (InvocationTargetException var6) {
                var6.printStackTrace();
            } catch (InstantiationException var7) {
                var7.printStackTrace();
            }
        } catch (NoSuchMethodException var8) {
            var8.printStackTrace();
        }

        return f;
    }
}
