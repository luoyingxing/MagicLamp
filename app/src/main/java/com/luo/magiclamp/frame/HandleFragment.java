package com.luo.magiclamp.frame;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * HandleFragment
 * Hosting Activity must implement HandledInterface
 * <p/>
 * Created by luoyingxing on 16/10/09.
 */
public abstract class HandleFragment extends Fragment {

    protected HandledInterface mHandledInterface;

    /**
     * 所有继承BackHandledFragment的子类都将在这个方法中实现物理Back键按下后的逻辑
     * FragmentActivity捕捉到物理返回键点击事件后会首先询问Fragment是否消费该事件
     * 如果没有Fragment消息时FragmentActivity自己才会消费该事件
     */
    protected abstract boolean onBackPressed();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getActivity() instanceof HandledInterface)) {
            throw new ClassCastException("Hosting Activity must implement HandledInterface");
        } else {
            this.mHandledInterface = (HandledInterface) getActivity();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mHandledInterface.setSelectedFragment(this);
    }
}
