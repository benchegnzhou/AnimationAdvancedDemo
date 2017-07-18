package com.ztsc.house.meui;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.squareup.picasso.Picasso;
import com.yalantis.ucrop.entity.LocalMedia;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ztsc.house.BaseActivity;
import com.ztsc.house.R;
import com.ztsc.house.bean.user.UserIconChangeResponseBody;
import com.ztsc.house.bean.user.UserMessageChangeResponseBody;
import com.ztsc.house.common.ConstantValue;
import com.ztsc.house.customview.CircleImageView;
import com.ztsc.house.dailog.Dialog_Sex_Select;
import com.ztsc.house.dailog.PhotoSelectedDialog;
import com.ztsc.house.eventbusbody.UserIconChangeEvent;
import com.ztsc.house.eventbusbody.UserMessageChangeEvent;
import com.ztsc.house.eventbusbody.ZTAnyEventType;
import com.ztsc.house.network.api.doParams.ZTHouseHttpClient;
import com.ztsc.house.usersetting.UserInformationManager;
import com.ztsc.house.utils.Bitmap2Base64;
import com.ztsc.house.utils.CircleImageTransformation;
import com.ztsc.house.utils.FileUtils;
import com.ztsc.house.utils.LogUtil;
import com.ztsc.house.utils.ToastUtils;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;


public class SetMeActivity extends BaseActivity {
    @Bind(R.id.me_usericon)
    CircleImageView meUsericon;
    @Bind(R.id.setme_nicename_tv)
    TextView setmeNicenameTv;
    @Bind(R.id.setme_nice_rl)
    AutoRelativeLayout setmeNiceRl;
    @Bind(R.id.setme_sex_tv)
    TextView setmeSexTv;
    @Bind(R.id.setme_sex_rl)
    AutoRelativeLayout setmeSexRl;
    @Bind(R.id.setme_telnum_tv)
    TextView setmeTelnumTv;
    @Bind(R.id.setme_telnum_rl)
    RelativeLayout setmeTelnumRl;
    @Bind(R.id.setme_myaddress_rl)
    RelativeLayout setmeMyaddressRl;
    @Bind(R.id.activity_setme)
    AutoLinearLayout activitySetme;
    @Bind(R.id.btn_logout)
    Button btnLogout;
    @Bind(R.id.test_rl)
    AutoRelativeLayout testRl;
    @Bind(R.id.rl_bar)
    AutoRelativeLayout rlBar;
    @Bind(R.id.tv_address1)
    TextView tvAddress1;
    @Bind(R.id.rl_back)
    AutoRelativeLayout rlBack;
    @Bind(R.id.btn_experience)
    Button btnExperience;
    @Bind(R.id.tv_tittle)
    TextView tvTittle;

    private ArrayList<LocalMedia> localMedias = new ArrayList<>();   //用于存放选中后的照片
    private boolean hadPermission;
    private String filePath;
    private static final int REQUEST_CODE_PHOTO_RESULT = 200;
    private File mCropTempFile;
    private String mSex;

    @Override
    protected void initListener() {
        rlBack.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        String from = intent.getStringExtra("from");

        switch (from) {
            case "regist":
                rlBack.setVisibility(View.GONE);
                tvTittle.setText("注册成功");
                btnExperience.setVisibility(View.VISIBLE);
                break;
            case "me_fragment":
                btnLogout.setVisibility(View.VISIBLE);
                break;
        }


        initialization();
        String phoneNum = UserInformationManager.getInstance().getPhoneNum();
        setmeNicenameTv.setText(UserInformationManager.getInstance().getNickName());
        if (!TextUtils.isEmpty(phoneNum) && phoneNum.length() > 6) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < phoneNum.length(); i++) {
                char c = phoneNum.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            setmeTelnumTv.setText(sb.toString());
        } else {
            ToastUtils.showToastShort("获取手机号码错误");
        }


        Log.i("myppp", UserInformationManager.getInstance().getPhoneNum() + "");

        int gender = UserInformationManager.getInstance().getGender();
        if (gender == 0) {
            setmeSexTv.setText("男");
            mSex = "男";
        } else {
            setmeSexTv.setText("女");
            mSex = "女";
        }
//        tvAddress.setText(UserInformationManager);

        setmeNiceRl.setOnClickListener(this);
        setmeTelnumRl.setOnClickListener(this);
        setmeSexRl.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        testRl.setOnClickListener(this);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_setme;
    }

    private void initialization() {
        //圆角图片的加载
        Picasso.with(this).
                //正式测试的时候这里替换真正的地址
                        load(new File(FileUtils.getSDCardPath(this) + "/" + UserInformationManager.getInstance().getUserId() + ".png")).
                transform(new CircleImageTransformation()).
                skipMemoryCache().
                skipMemoryCache().
                placeholder(R.mipmap.me_head).
                error(R.mipmap.me_head).
                noFade().
                resize(210, 210).//指定压缩参考的宽高比
                centerCrop().
                into(meUsericon);//设置不需要渐渐显示的动画效果
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test_rl:
                filePath = FileUtils.getSDCardPath(this) + "/" + UserInformationManager.getInstance().getUserId() + ".png";
                LogUtil.e("文件 是否存在：" + new File(filePath).exists());
                File file = new File(FileUtils.getSDCardPath(this));
                requestPermission();                //动态权限请求

                LogUtil.e("权限：" + hadPermission);
                PhotoSelectedDialog.showSingleSelectDialog(this, filePath, hadPermission);

                break;
            case R.id.setme_nice_rl:                // 昵称更换,跳转新界面
                Intent intent = new Intent(this, SetMeChangeMsgActivity.class);
                String niceName = setmeNicenameTv.getText().toString();
                intent.putExtra("eventCode", ConstantValue.changeNiceNameCode);
                intent.putExtra("nicename", TextUtils.isEmpty(niceName) ? "" : niceName);
                startActivityForResult(intent, ConstantValue.REQUEST_CODE_NICENAMECHANGE_RESULT);
                break;
            case R.id.setme_telnum_rl:              //电话修改
//                Intent intentTel = new Intent(this, SetMeChangeMsgActivity.class);
//                String telNum = setmeTelnumTv.getText().toString();
//                intentTel.putExtra("eventCode", ConstantValue.changTelCode);
//                intentTel.putExtra("telNum", TextUtils.isEmpty(telNum) ? "" : telNum);
//                startActivityForResult(intentTel, ConstantValue.REQUEST_CODE_TELNUMCHANGE_RESULT);

                break;
            case R.id.setme_sex_rl:
                ToastUtils.showToastShort("性别选择");
                selectSex();
                break;

            case R.id.rl_back:
                finish();
                break;

            case R.id.btn_logout:
                UserInformationManager.getInstance().logout();
                EMClient.getInstance().logout(true, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        Log.i("aaabb", "退出登录成功");
                    }

                    @Override
                    public void onError(int i, String s) {
                        Log.i("aaabb", "退出登录失败");
                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
                setResult(10);
                finish();
                break;
        }
    }

    @OnClick(R.id.btn_experience)
    public void onClick() {
        finish();
    }

    private void selectSex() {
        Dialog_Sex_Select custom_dialog = new Dialog_Sex_Select(this, setmeSexTv.getText().toString());
        custom_dialog.show();
        custom_dialog.SetOnRegisterMaleClickListener(new Dialog_Sex_Select.OnClickListener_RegisterSex() {
            @Override
            public void onRegisterSexClick(String sex) {
                ToastUtils.showToastShort(sex);
                mSex = sex;
                int i;
                if (sex.equals("男")) {
                    i = 0;
                } else {
                    i = 1;
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("{\"gender\":").append(i).append("}");
                ZTHouseHttpClient.doUserMessageChange(UserInformationManager.getInstance().getUserId(), UserInformationManager.getInstance().getToken(), stringBuffer.toString());
            }
        });
    }


    /**
     * 修改性别
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UserMessageChangeEvent event) {
        LogUtil.e(event.getCode() + "-------" + event.getResult());
        if (event.getCode() == ZTAnyEventType.SUCCESS) {
            UserMessageChangeResponseBody userMessageChangeResponseBody = event.parseResult();
            LogUtil.e(userMessageChangeResponseBody.toString());
            UserMessageChangeResponseBody.ResultBean result = userMessageChangeResponseBody.getResult();
            int status = result.getStatus();
            if (status == 0) {
                ToastUtils.showToastShort("修改性别成功");
                setmeSexTv.setText(mSex);
                int i;
                if (mSex.equals("男")) {
                    i = 0;
                    setmeSexTv.setText("男");

                } else {
                    i = 1;
                    setmeSexTv.setText("女");
                }
                UserInformationManager.getInstance().setGender(i);
            } else {
                ToastUtils.showToastShort("修改性别失败");
            }
        } else {
            ToastUtils.showToastShort("网络不通，请稍后重试");
        }
    }


    /**
     * 启用相册对图片进行裁剪
     *
     * @param uri
     */
    private void startPhotoZoom(Uri uri) {
        try {
            Intent intent = new Intent("com.android.camera.action.CROP", null);
            intent.setDataAndType(uri, "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 150);
            intent.putExtra("outputY", 150);
            intent.putExtra("scale", true);
            intent.putExtra("return-data", false);
            LogUtil.e("文件存储路径" + filePath);

            mCropTempFile = new File(filePath);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCropTempFile));
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            intent.putExtra("noFaceDetection", true);
            startActivityForResult(intent, REQUEST_CODE_PHOTO_RESULT);
        } catch (ActivityNotFoundException e) {
//            Toast.makeText(this, R.string.account_avatar_nickname_no_camera, Toast.LENGTH_SHORT).show();
        }
    }

/*
    //圆角图片的加载
    Picasso.with(mContext).
    //正式测试的时候这里替换真正的地址
    load(userImage).
    transform(new CircleImageTransformation()).
    placeholder(R.drawable.help_release_head).
    error(R.drawable.help_release_head).
    noFade().
    resize(210, 70).//指定压缩参考的宽高比
    centerCrop().
    into(holder.icon);//设置不需要渐渐显示的动画效果
    */


    /**
     * 图片裁剪请求回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ConstantValue.REQUEST_CODE_PHOTOGRAPH:
                    File file = new File(filePath);
                    if (file.exists()) {
                        Uri photoUri = Uri.fromFile(file);
                        startPhotoZoom(photoUri);
                    }
                    break;
                case ConstantValue.REQUEST_CODE_ALBUM:
                    if (data != null && data.getData() != null) {
                        Uri photoUri = data.getData();
                        startPhotoZoom(photoUri);
                    }
                    break;
                /*case ConstantValue.REQUEST_CODE_SET_USER_ABOUT:
                    String about = data.getStringExtra("about");
                    tvAbout.setText(about);
                    break;
                */
                case REQUEST_CODE_PHOTO_RESULT:

                    //if (mCropTempFile != null && mCropTempFile.exists()) {
                    //圆角图片的加载

                    Picasso.with(this).
                            //正式测试的时候这里替换真正的地址
                                    load(new File(FileUtils.getSDCardPath(this) + "/" + UserInformationManager.getInstance().getUserId() + ".png")).
                            transform(new CircleImageTransformation()).
                            placeholder(R.mipmap.me_head).
                            skipMemoryCache().
                            error(R.mipmap.me_head).
                            noFade().
                            resize(210, 210).//指定压缩参考的宽高比
                            centerCrop().
                            into(meUsericon);//设置不需要渐渐显示的动画效果
                    //new UploadHeadHandler(this, bid, mCropTempFile).execute();
                    upLoadUserIcon();
                    //}
                    break;
                case ConstantValue.REQUEST_CODE_NICENAMECHANGE_RESULT:                             //昵称修改事件
                    setmeNicenameTv.setText(data.getStringExtra("nicename_new"));

                    break;
                case ConstantValue.REQUEST_CODE_TELNUMCHANGE_RESULT:                                //昵称修改事件
                    setmeTelnumTv.setText(data.getStringExtra("telNum_new"));
                    break;


            }
        }
    }

    /**
     * 用户头像上传
     */
    private void upLoadUserIcon() {

        String imagePath = FileUtils.getSDCardPath(this) + "/" + UserInformationManager.getInstance().getUserId() + ".png";
        String s = Bitmap2Base64.userIconPicture2String(imagePath);
        if (TextUtils.isEmpty(s)) {
            LogUtil.e(s.toString());
            ToastUtils.showToastShort("您要上传的头像不存在");
            return;
        }
        ZTHouseHttpClient.doUserChangeIcon(UserInformationManager.getInstance().getUserId(), UserInformationManager.getInstance().getToken(),
                s);


    }


    /**
     * 社区投诉
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UserIconChangeEvent event) {
        LogUtil.e(event.getCode() + "-------" + event.getResult());
        if (event.getCode() == ZTAnyEventType.SUCCESS) {
            UserIconChangeResponseBody usericonchangebody = event.parseResult();
            LogUtil.e(usericonchangebody.toString());
            if (usericonchangebody.getCode() == 200) {
                LogUtil.e(usericonchangebody.toString());
                if (usericonchangebody.getResult().getStatus() == 0) {
                    ToastUtils.showToastShort("头像修改成功");
                }
            } else {
                ToastUtils.showToastShort("头像修改失败，请稍后再试");
                ToastUtils.showToastShort("失败原因：" + usericonchangebody.getResult().getInformation());
            }
        } else {
            ToastUtils.showToastShort("网络不通，请稍后重试");
        }
    }


    /**
     * 6.0动态网络权限请求
     */
    public void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //申请摄像头的权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    ConstantValue.REQUEST_CODE_PHOTOGRAPH);
            hadPermission = false;
        } else {
            hadPermission = true;
        }
    }


    /**
     * 6.0新权限的注册回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //doNext(requestCode,grantResults);
        if (requestCode == ConstantValue.REQUEST_CODE_PHOTOGRAPH && ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {       //是摄像头权限申请的回调，并且没有成功
        } else if (requestCode == ConstantValue.REQUEST_CODE_PHOTOGRAPH) {
            hadPermission = true;
            PhotoSelectedDialog.setCammerPermission(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    protected void finishActivity() {
        this.finish();
    }

}




