package com.ztsc.house.dailog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingle.widget.LoadingView;
import com.ztsc.house.R;


/**
 * Created by benchengzhou on 2017/3/28 11:13 .
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： TestActivityActivity
 * 备    注：
 */

public class LoadingDialog extends Dialog implements View.OnClickListener{
    // 加载默认的状态
    public static final int STATE_UNLOADED = 1;
    // 加载的状态
    public static final int STATE_LOADING = 2;
    // 加载失败的状态
    public static final int STATE_ERROR = 3;
    // 加载空的状态
    public static final int STATE_EMPTY = 4;
    // 加载成功的状态
    public static final int STATE_SUCCEED = 5;

    //界面数据当前的状态
    private int mCurrentState = STATE_UNLOADED;
    private LoadingDialog mLoadingDialog;
    public LoadingView loadingView;
    private Context mContext;
    private RelativeLayout loadingRl;
    private RelativeLayout errorRl;
    private onErrorReloadCallBack mOnErrorReload;
    private TextView errorRelaod;

    public LoadingDialog(Context context ) {
        super(context);
        mContext = context.getApplicationContext();
    }
    public LoadingDialog(Context context,onErrorReloadCallBack onErrorReload) {
        super(context);
        mContext = context.getApplicationContext();
        mOnErrorReload=onErrorReload;
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_item_loading);


        loadingView = (LoadingView) findViewById(R.id.loadView);
        loadingRl = (RelativeLayout) findViewById(R.id.rl_loading);
        errorRl = (RelativeLayout) findViewById(R.id.rl_on_error);
        errorRelaod = (TextView) findViewById(R.id.tv_error_relaod);
        errorRelaod.setOnClickListener(this);
//        loadingView.setLoadingText("正在加载");

//        TextView baoming_tanceng_message = (TextView) findViewById(R.id.baoming_tanceng_message);
        //dialog所在的屏幕
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();//获取屏幕中的属性
        attributes.gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;//设置屏幕中的控件的位置
        window.setAttributes(attributes);//设置新的属性，将原有的属性效果覆盖
        //不能通过window设置去除标题栏和边框操作，根据源码提示需要通过styles.xml设置


        loadingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {    //手抖，取消的按钮
                //隐藏对话框
                dismiss();
            }
        });
    }


    public void SetOnErrorReloadClickListener(onErrorReloadCallBack onErrorReload ){
        mOnErrorReload = onErrorReload;
    }

    @Override
    public void onClick(View view) {
         switch (view.getId()) {
                     case R.id.tv_error_relaod :
                         if(mLoadingDialog.mOnErrorReload!=null&&mCurrentState==STATE_ERROR){
                             mLoadingDialog.mOnErrorReload.onReloadClick();
                         }
                         break;

          }
    }

    /**
     * 确定
     */
    public interface onErrorReloadCallBack {
        void onReloadClick( );
    }





    public void  startLoading(){
        loadingView.startLoading(0);
    }

    /*public LoadingDialog builder() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(mContext);
        }
        return mLoadingDialog;
    }*/

    public LoadingDialog dialogShow() {
//        mLoadingDialog = builder();
        mLoadingDialog = this;
        mLoadingDialog.show();
        return mLoadingDialog;
    }

    public void SyncState(int currentState  ) {
        mCurrentState=currentState;
        switch (currentState) {
            case STATE_UNLOADED:    //未加载的状态
                dismiss();
                break;
            case STATE_LOADING:     //加载中的状态
                dialogShow();
                mLoadingDialog.loadingRl.setVisibility(View.VISIBLE);
                mLoadingDialog.errorRl.setVisibility(View.GONE);

                break;
            case STATE_SUCCEED:     //加载成功的状态
                dismiss();
                break;
            case STATE_EMPTY:       //空状态
                break;
            case STATE_ERROR:       //加载失败的状态
                dialogShow();
                mLoadingDialog.loadingRl.setVisibility(View.GONE);
                mLoadingDialog.errorRl.setVisibility(View.VISIBLE);
                break;


        }
    }

    public int getCurrentState() {
        return mCurrentState;
    }

    public void setCurrentState(int currentState) {
        this.mCurrentState = currentState;
    }


}
