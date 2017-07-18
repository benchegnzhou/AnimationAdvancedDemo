package com.ztsc.house.dailog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.ztsc.house.R;


/**
 * Created by benchengzhou on 2017/6/12  10:19 .
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： SubmitUploadingDialog
 * 备    注：自定义进度条
 */

public class SubmitUploadingDialog extends Dialog {
    private Context context ;
    private String progressText ;
    public SubmitUploadingDialog(Context context) {
        this(context, R.style.dialog_uploading);
        this.context = context ;
    }
    public SubmitUploadingDialog(Context context, String progressText) {
        this(context,R.style.dialog_uploading) ;
        this.context = context ;
        this.progressText = progressText ;
    }

    public SubmitUploadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected SubmitUploadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }




    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.custom_loading_progressbar) ;
            TextView title =  (TextView) findViewById(R.id.custom_imageview_progress_title);
            title.setText(progressText==null?"加载数据中，请稍后...":progressText) ;
        }
        /**
         * @see Dialog#show()
         */
        @Override
        public void show() {
            try{
                if(!isShowing()){
                    super.show();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            ImageView im =  (ImageView) findViewById(R.id.custom_imageview_progress_bar);
            im.startAnimation(AnimationUtils.loadAnimation(context, R.anim.animate_uploading_dialog));
        }
    }







