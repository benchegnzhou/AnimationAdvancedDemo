package com.ztsc.house.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.gson.Gson;
import com.ztsc.house.R;
import com.ztsc.house.application.MApplication;
import com.ztsc.house.bean.TestBody;
import com.ztsc.house.customview.WheelView;
import com.ztsc.house.utils.Base64Util;
import com.ztsc.house.utils.DeviceMessageUtils;
import com.ztsc.house.utils.FileUtils;
import com.ztsc.house.utils.LogUtil;
import com.ztsc.house.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.lang.String.valueOf;

public class TestActivity extends AppCompatActivity implements OnClickListener {

    @Bind(R.id.btn_net_test)
    Button btnNetTest;
    @Bind(R.id.btn_base64_test)
    Button btnBase64Test;
    @Bind(R.id.btn_other_test)
    Button btnOtherTest;
    @Bind(R.id.btn_gson_test)
    Button btnGsonTest;
    @Bind(R.id.pickerview)
    WheelView pickerview;
    private static final String[] PLANETS = new String[]{"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Uranus", "Neptune", "Pluto"};
    @Bind(R.id.over_pull_test)
    Button overPullTest;

    private String url;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        initData();
        initListener();
    }

    private void initListener() {
        btnNetTest.setOnClickListener(this);
        btnBase64Test.setOnClickListener(this);
        btnOtherTest.setOnClickListener(this);
        btnGsonTest.setOnClickListener(this);
        overPullTest.setOnClickListener(this);
    }

    private void initData() {
        pickerview.setOffset(1);
        pickerview.setItems(Arrays.asList(PLANETS));
        pickerview.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                ToastUtils.showToastShort("选择了" + selectedIndex + "和" + item);
            }
        });

    }


    /**
     * 设置请求参数
     * 这部分是月榜和总榜共同使用的请求体
     */
    private JSONObject requestBody(String username, String code) {


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("service", "user");
            jsonObject.put("function", "checkZCCode");
            jsonObject.put("phoneNum", username);
            jsonObject.put("code", code);
            jsonObject.put("machineId", DeviceMessageUtils.getIMEI(this));
            jsonObject.put("machineName", DeviceMessageUtils.getMobileInfo(this));
            jsonObject.put("clientType", 1);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 网络参考链接：  http://blog.csdn.net/lv_fq/article/details/52313622?ref=myread
     *
     * @param url
     * @param map
     * @param file
     */
    protected void post_file(final String url, final Map<String, Object> map, File file) {

        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (file != null) {
            // MediaType.parse() 里面是上传的文件类型。
            RequestBody body = RequestBody.create(MediaType.parse("image/png"), file);
            String filename = file.getName();
            // 参数分别为， 请求key ，文件名称 ， RequestBody
            requestBody.addFormDataPart("headImage", file.getName(), body);
//            file = new File(FileUtils.getSDCardPath(this) + "/UserPic2.png");
//            requestBody.addFormDataPart("upload", file.getName(), RequestBody.create(MediaType.parse("image/png"), file ));
//            file = new File(FileUtils.getSDCardPath(this) + "/UserPic3.png");
//            requestBody.addFormDataPart("upload", file.getName(), RequestBody.create(MediaType.parse("image/png"), file ));
        }
        if (map != null) {
            // map 里面是请求中所需要的 key 和 value
            for (Map.Entry entry : map.entrySet()) {
                requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
            }
        }
        //自己添加的手动参数
        requestBody.addFormDataPart("userName", "binbin");
        Request request = new Request.Builder().url(url).post(requestBody.build()).tag("asdasd").build();
        // readTimeout("请求超时时间" , 时间单位);
        MApplication.getSOkHttpClient().newBuilder().readTimeout(5000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.e("lfq", "onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String str = response.body().string();
                    LogUtil.e("lfq", response.message() + " , body " + str);

                } else {
                    LogUtil.e("lfq", response.message() + " error : body " + response.body().string());
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_net_test:
                postMultipartBodyTest();
                break;
            case R.id.btn_base64_test:
                Base64Util.test();
                break;
            case R.id.btn_gson_test:
                gsonTest();
                break;

            case R.id.over_pull_test:   //自定义scrollview 实现平滑过度下拉
                startActivity(new Intent(TestActivity.this,TestOverScrollViewActivity.class));
                break;


        }
    }

    /**
     *
     */
    private void gsonTest() {

        //step 1: 同样的需要创建一个OkHttpClick对象
        //OkHttpClient okHttpClient = new OkHttpClient();

        //step 2: 创建  FormBody.Builder
        FormBody formBody = new FormBody.Builder()

                .add("machineId", DeviceMessageUtils.getIMEI(this))
                .add("machineName", DeviceMessageUtils.getMobileInfo(this))
                .build();

        String url = "http://192.168.1.80:9090/house/order.json";
        //step 3: 创建请求
        Request request = new Request.Builder().url(url)
                .post(formBody)
                .build();

        //step 4： 建立联系 创建Call对象
        MApplication.getSOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.e(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String StringResult = response.body().string();
                LogUtil.e("请求返回的结果是--------:" + StringResult);
                TestBody testbody = new Gson().fromJson(StringResult, TestBody.class);
                LogUtil.e(testbody.toString());

            }
        });


    }


    public void postMultipartBodyTest() {

        file = new File(FileUtils.getSDCardPath(this) + "/UserPic.png");


        if (!file.exists()) {
            LogUtil.e("文件不存在，文件路径" + file.getPath());
            return;
        }
//        url = "http://file.loushubin.cn/do_upload.php";
        url = "http://192.168.1.23:8080/Service?service=user&function=getCodeForRegister";
        new Thread(new Runnable() {
            @Override
            public void run() {
                post_file(url, null, file);
                LogUtil.e("文件长传成功");
            }
        }).start();

    }

/*

    */
/**
 * 用户post获取验证码请求
 *//*

    public void getCodeRequest(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String s = response.body().string();
            LogUtil.e(s);
            RegistGetCodeResponseBody registGetCodeResponseBody = gson.fromJson(s, RegistGetCodeResponseBody.class);
            RegistGetCodeResponseBody.ResultBean result = registGetCodeResponseBody.getResult();
            if (registGetCodeResponseBody.getCode() == 200) {
                int phoneNumStatus = result.getPhoneNumStatus();
                if (phoneNumStatus == 0) {
//                    rlCodeSend.setVisibility(View.GONE);
//                    rlCodeCheck.setVisibility(View.VISIBLE);
                } else {
                    ToastUtils.showToastShort("手机号不可用或已注册");
                }
            }


        } else {
            throw new IOException("Unexpected code " + response);
        }
    }



    */
/**
 * 用户post获取验证码请求
 *//*

    public void getCodeAgainRequest(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String s = response.body().string();
            LogUtil.e(s);
            RegistGetCodeResponseBody registGetCodeResponseBody = gson.fromJson(s, RegistGetCodeResponseBody.class);
            RegistGetCodeResponseBody.ResultBean result = registGetCodeResponseBody.getResult();
            if (registGetCodeResponseBody.getCode() == 200) {
//                请求成功
            }
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }


    */
/**
 * 用户post校验验证码
 *//*

    void captchaCheckPost(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {

            String s = response.body().string();
            LogUtil.e(s);
            RegistCodeCheckResponseBody registCodeCheckResponseBody = gson.fromJson(s, RegistCodeCheckResponseBody.class);
            if (registCodeCheckResponseBody.getCode() == 200) {
                RegistCodeCheckResponseBody.ResultBean result = registCodeCheckResponseBody.getResult();
                //写入用户信息
                UserInformationManager.getInstance().setUserInformation(true,
                        result.getHeadImage(),
                        result.getPhoneNum(),
                        result.getToken(),
                        result.getGender(),
                        result.getNickName(),
                        result.getUserId(),
                        result.getIsIdentification(),
                        null,
                        null);
                rlCodeCheck.setVisibility(View.GONE);
                rlPasswordSet.setVisibility(View.VISIBLE);
            } else {
                ToastUtils.showToastShort("验证码错误");

            }
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

*/


}
