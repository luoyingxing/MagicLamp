package com.luo.magiclamp.frame;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.luo.magiclamp.R;
import com.luo.magiclamp.frame.network.MyNoHttp;
import com.luo.magiclamp.utils.Logger;
import com.luo.magiclamp.utils.NetworkUtils;


/**
 * BaseFragment
 * <p/>
 * Created by luoyingxing on 2016/10/09
 */
public class BaseFragment extends HandleFragment {
    protected String mTag;
    protected Logger mLog;
    protected BaseActivity mActivity;
    protected ProgressDialog mDialog;

    @Override
    public void onAttach(Activity activity) {
        mTag = getClass().getSimpleName();
        mLog = new Logger(mTag, Log.VERBOSE);
        mLog.v("OnAttach()");
        super.onAttach(activity);
        mActivity = (BaseActivity) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mLog.v("onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLog.v("onCreateView()");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        mLog.v("onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
        mActivity.setTitle(getClass().getSimpleName());
    }

    @Override
    public void onStart() {
        mLog.v("onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        mLog.v("onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        mLog.v("onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        mLog.v("onStop()");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        mLog.v("onDestroyView()");
        super.onDestroyView();
        hideIME();
        hideDialog();
        MyNoHttp.cancelAll();
        mActivity.getRightImage().setImageDrawable(null);
        mActivity.setOnRightImageClick(null);
        mActivity.getRightText().setText("");
        mActivity.setOnRightTextClick(null);
    }

    @Override
    public void onDestroy() {
        mLog.v("onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        mLog.v("onDetach()");
        super.onDetach();
    }

    /**
     * 子类可重载该方法,以监听物理返回键.
     *
     * @return 如果希望覆盖默认实现, 则返回true
     */
    public boolean onBackPressed() {
        return false;
    }

    private Toast toast = null;

    protected void showToast(Object msg) {
        if (toast != null) {
            toast.cancel();
            toast = Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_SHORT);
        } else
            toast = Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    protected void showLongToast(Object msg) {
        Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_LONG).show();
    }

    public void showNetworkError() {
        showToast(getResources().getString(R.string.msg_network_error));
    }

    public void showDialog() {
        showDialog(getResources().getString(R.string.msg_network_loading));
    }

    public void showDialog(String message) {
        showDialog(null, message);
    }

    public void showDialog(String title, String message) {
        hideDialog();
        mDialog = ProgressDialog.show(getActivity(), title, message);
        mDialog.setCanceledOnTouchOutside(true);
    }

    public void hideDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    public void showIME() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(getActivity().getCurrentFocus(), 0);
        mLog.d("showIME()");
    }

    public void showIMEForce(View view) {
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                showIME();
            }
        }, 100);
    }


    public void hideIME() {
        mLog.d("inputMode=" + getActivity().getWindow().getAttributes().softInputMode);
        if (getActivity().getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED) {
            return;
        }
        mLog.d("hideIME()");
        InputMethodManager imm = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        View currentFocus = getActivity().getCurrentFocus();
        if (currentFocus != null) {
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            mLog.d("hideIME() failed");
        }
    }

    public boolean checkNetwork() {
        if (NetworkUtils.isNetConnected()) {
            return true;
        }
        showNetworkError();
        return false;
    }

}
