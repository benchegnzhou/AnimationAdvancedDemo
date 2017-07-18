package com.ztsc.house.dailog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

import com.ztsc.house.R;
import com.ztsc.house.utils.LogUtil;


/**
 * Created by lenovo on 2016/9/8.
 */
public class DialogCallSecurity extends Dialog {

    private final String phoneNum;
    private OnClickListener_RegisterCall mOnClickCancel;
    private final Context mContext;

    /**
     * 创建的时候直接的将项目的信息传入
     *
     * @param context
     *
     */
    public DialogCallSecurity(Context context, String phoneNum) {
        super(context, R.style.CallSecurityDialog);
        mContext = context;
        this.phoneNum = phoneNum;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_call_security);

        TextView join_cancel = (TextView) findViewById(R.id.join_cancel);
        TextView join_confirm = (TextView) findViewById(R.id.join_confirm);
//        TextView baoming_tanceng_message = (TextView) findViewById(R.id.baoming_tanceng_message);
        //dialog所在的屏幕
        Window window = getWindow();
        LayoutParams attributes = window.getAttributes();//获取屏幕中的属性
        attributes.gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;//设置屏幕中的控件的位置
        window.setAttributes(attributes);//设置新的属性，将原有的属性效果覆盖
        //不能通过window设置去除标题栏和边框操作，根据源码提示需要通过styles.xml设置


        join_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {    //手抖，取消的按钮
                //隐藏对话框
                dismiss();
            }
        });
        join_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {    //拨打电话
                LogUtil.e("打印");
                if (mOnClickCancel != null) {
                    //通过消息的方式发送请求
                    mOnClickCancel.onCallClick(0);
                    callphoneNumer();
                    //隐藏对话框
                    dismiss();
                }
//
            }
        });


        //2.让某段文字变色
        //baoming_tanceng_message.setText(showTextWithColor("不再参与:寻找_镜头君_助力流动儿童公益",0xFF333333));
//        baoming_tanceng_message.setText(showTextWithColor("拨打电话:" + phoneNum, 0xFF1E1E22));


    }

    /**
     *  拨打电话
     */
    private void callphoneNumer() {


    }


    public void SetOnRegisterCancelCallClickListener(OnClickListener_RegisterCall onClickCancel) {
         mOnClickCancel = onClickCancel;
    }

    /**
     * 确定
     */
    public interface OnClickListener_RegisterCall {
        void onCallClick(int position);
    }

    /**
     * 让后面的文字显示颜色给定的颜色，默认色是酒红色
     *
     * @param string
     * @param color
     * @return
     */
    private CharSequence showTextWithColor(String string, int color) {
        SpannableString ss = new SpannableString(string);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(0xFFFD5056);   //红色前景色
        ForegroundColorSpan colorSpan2 = new ForegroundColorSpan(color);  //默认色
//		BackgroundColorSpan
        int end = string.indexOf(":") + 1;

        ss.setSpan(colorSpan, 0, end, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(colorSpan2, end, string.length(), SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);

        return ss;
    }

}







