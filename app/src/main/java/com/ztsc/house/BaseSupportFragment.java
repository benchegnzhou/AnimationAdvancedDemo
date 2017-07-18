package com.ztsc.house;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.ztsc.house.dailog.SubmitUploadingDialog;
import com.ztsc.house.ui.MainActivity;

import butterknife.ButterKnife;

import pub.devrel.easypermissions.BaseSuppoprtPermissionFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseSupportFragment extends BaseSuppoprtPermissionFragment {

    protected MainActivity activity;
    private SubmitUploadingDialog uploadingDialog;

    public BaseSupportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = getContentView();     //要求子类重写并传入对应的布局
        ButterKnife.bind(this, view);
        initListener();
        initData();
        return view;
    }




    /**
     * 由子类实现并且返回对应的完成监听操作和控件adapter的设置
     */
    protected abstract void initListener();

    /**
     * 用于子类的数据的初始化
     */
    protected abstract void initData();

    /**
     * 由子类实现并且返回对应的view
     *
     * @return
     */
    protected abstract View getContentView();

    protected void intent2Activity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(activity, tarActivity );
        startActivity(intent);
    }


    /**
     * 提交样式，不带有文字的ProgressBar
     */
    public void createProgressBar() {
        this.createProgressBar(null);
    }
    /**
     * 提交样式，带有文字的ProgressBar
     * @param text 提示的文字内容
     */
    public void createProgressBar(String text) {

        if (uploadingDialog == null) {
            if (text == null) {
                uploadingDialog = new SubmitUploadingDialog(getActivity());
            } else {
                uploadingDialog = new SubmitUploadingDialog(getActivity(), text);
            }
            uploadingDialog.setCancelable(true);
        }
        if (!uploadingDialog.isShowing()) {
            uploadingDialog.show();
        }
    }

    /**
     * 提交对话框隐藏
     */
    public void disMissProgress() {
        if (uploadingDialog != null && uploadingDialog.isShowing()) {
            uploadingDialog.dismiss();
        }
    }




}
