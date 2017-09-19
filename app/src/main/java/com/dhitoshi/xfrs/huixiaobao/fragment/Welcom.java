package com.dhitoshi.xfrs.huixiaobao.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import com.dhitoshi.xfrs.huixiaobao.view.Login;
import java.util.Arrays;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
public class Welcom extends Fragment {
    private static final String ARG_POSITION = "position";
    @BindView(R.id.welcome)
    ImageView welcome;
    Unbinder unbinder;
    private int position;
    private List<Integer> welcoms = Arrays.asList(R.mipmap.one, R.mipmap.two, R.mipmap.three);
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(getActivity(), Login.class));
            SharedPreferencesUtil.Save(getActivity(), "isFirst", false);
            getActivity().finish();
        }
    };
    public Welcom() {
    }
    public static Welcom newInstance(int position) {
        Welcom fragment = new Welcom();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcom, container, false);
        unbinder = ButterKnife.bind(this, view);
        welcome.setImageResource(welcoms.get(position));
        return view;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        handler.removeCallbacks(runnable);
        if (isVisibleToUser && position == 2) {
            handler.postDelayed(runnable, 2000);
        } else {
            // 不可见时不执行操作
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        handler.removeCallbacks(runnable);
    }
}
