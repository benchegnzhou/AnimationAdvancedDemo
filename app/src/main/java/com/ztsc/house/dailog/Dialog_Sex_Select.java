package com.ztsc.house.dailog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ztsc.house.R;


/**
 * Created by lenovo on 2016/9/8.
 */
public class Dialog_Sex_Select extends Dialog {


    //@Bind(R.id.rg_sex)
    RadioGroup rgSex;
    //@Bind(R.id.rb_male)
    RadioButton rbMale;
    //@Bind(R.id.rb_female)
    RadioButton rbFemale;


//    private static TextView custom_dialog_message;
//
//    public Dialog_Sex_Select(Context context) {
//        this(context,0);
//    }
//
//    public Dialog_Sex_Select(Context context, int themeResId) {
//        super(context, themeResId);
//    }
//
//    public static class Builder {
//        private Context context;
//        private String message;
//
//        public Builder(Context context) {
//            this.context = context;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//
//        public void setMessage(String message) {
//            this.message = message;
//            custom_dialog_message.setText(message);
//        }
//
//        public Dialog_Sex_Select create() {
//            LayoutInflater inflater = (LayoutInflater) context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            final Dialog_Sex_Select dialogShow = new Dialog_Sex_Select(context, R.style.Dialog);
////            Dialog_Sex_Select dialogShow = new Dialog_Sex_Select(context, 0);
//            View layout = inflater.inflate(R.layout.dialog_confirmation, null);
//            dialogShow.addContentView(layout, new LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT
//                    , ViewGroup.LayoutParams.MATCH_PARENT));
//            dialogShow.setContentView(layout);
//            TextView confirmation_text = (TextView) layout.findViewById(R.id.custom_confirmation);
//            TextView custom_cancel_text = (TextView) layout.findViewById(R.id.custom_cancel);
//            custom_dialog_message = (TextView) layout.findViewById(R.id.custom_dialog_message);
//            confirmation_text.setText("confirmation_text");
//            custom_cancel_text.setText("custom_cancel_text");
//
//            return dialogShow;
//        }


    private final Context mContext;
    private TextView message_text;
    private OnClickListener_RegisterSex registerCancel_sex;
    private final String mSex;

    /**
     * 创建的时候直接的将项目的信息传入
     *
     * @param context
     */
    public Dialog_Sex_Select(Context context, String sex) {
        super(context, R.style.AddressDialog);
        mContext = context;
        mSex = sex;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sex_select_dalog);

        rgSex = (RadioGroup) findViewById(R.id.rg_sex);
        rbMale = (RadioButton) findViewById(R.id.rb_male);
        rbFemale = (RadioButton) findViewById(R.id.rb_female);

        if ("女".equals(mSex)) {
            rgSex.check(R.id.rb_female);
        } else {
            rgSex.check(R.id.rb_male);
        }

        //dialog所在的屏幕
        Window window = getWindow();
        LayoutParams attributes = window.getAttributes();//获取屏幕中的属性
        attributes.gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;//设置屏幕中的控件的位置
        window.setAttributes(attributes);//设置新的属性，将原有的属性效果覆盖
        //不能通过window设置去除标题栏和边框操作，根据源码提示需要通过styles.xml设置

//        mListView.setAdapter(new Myadapter());
//
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                //隐藏对话框
//                dismiss();
//
//            }
//        });
// requestData();    //使用富文本将文字改成多样式
//        join_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {    //手抖，不取消的按钮
//                ToastUtils.showToastShort("手抖，不取消");
//                //隐藏对话框
//                dismiss();
//            }
//        });
//        join_confirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {    //报名取消确定按钮
//                if (registerCancel_cancel != null) {
//                    //通过消息的方式发送请求
//                    registerCancel_cancel.onCallClick("确定，并将消息回传");
//                    dismiss();
//                }
//            }
//        });


        //性别的选择
        rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    //选择男
                    case R.id.rb_male:
                        if (registerCancel_sex != null) {
                            //通过消息的方式发送请求
                            registerCancel_sex.onRegisterSexClick("男");
                            dismiss();
                        }
                        break;
                    //选择女
                    case R.id.rb_female:
                        if (registerCancel_sex != null) {
                            //通过消息的方式发送请求
                            registerCancel_sex.onRegisterSexClick("女");
                            dismiss();
                        }
                        break;

                }
            }
        });

    }


    public void SetOnRegisterMaleClickListener(OnClickListener_RegisterSex listener_registerSex) {
        registerCancel_sex = listener_registerSex;
    }


    /**
     * 确定的回调接口
     */
    public interface OnClickListener_RegisterSex {
        void onRegisterSexClick(String text);
    }


}





