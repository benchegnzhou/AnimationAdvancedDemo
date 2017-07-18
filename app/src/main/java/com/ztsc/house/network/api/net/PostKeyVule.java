package com.ztsc.house.network.api.net;

import android.text.TextUtils;

import com.ztsc.house.application.MApplication;
import com.ztsc.house.eventbusbody.ZTAnyEventType;
import com.ztsc.house.usersetting.UserInformationManager;
import com.ztsc.house.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Jhon on 2017/3/21.
 * 功能描述：
 * 备    注：
 */

public class PostKeyVule {


    /**
     * 键值对提交网络请求
     * @param urlPath
     * @param params
     * @param ztAnyEventType
     */
    public static void doPost(String urlPath, Map<String, String> params, final ZTAnyEventType ztAnyEventType ) {
        doPost(urlPath,params,ztAnyEventType,null);
    }


    /**
     * 带有tag的键值对提交网络请求
     * @param urlPath
     * @param params
     * @param ztAnyEventType
     * @param tag
     */

    public static void doPost(String urlPath, Map<String, String> params, final ZTAnyEventType ztAnyEventType, final String tag) {

        //step 1: 同样的需要创建一个OkHttpClick对象
        //OkHttpClient okHttpClient = new OkHttpClient();
        LogUtil.d("url:", urlPath);

        //step 2: 创建  FormBody.Builder
        FormBody.Builder builder = new FormBody.Builder();

        Iterator iter = params.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();
            LogUtil.e("网络请求数据key:---"+key+"------"+val);
            builder.add(key, val);

        }
        FormBody formBody = builder.build();


        //step 3: 创建请求
        Request request = new Request.Builder().url(urlPath)
                .post(formBody)
                .build();

        //step 4： 建立联系 创建Call对象

        MApplication.getSOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // TODO: 17-1-4  请求失败
                LogUtil.e("网络错误/n错误原因：",e);
                ztAnyEventType.setFailCode();
                ztAnyEventType.setResult("");
                ztAnyEventType.setTag(tag);
                EventBus.getDefault().post(ztAnyEventType);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // TODO: 17-1-4 请求成功
                String StringResult = response.body().string();
                LogUtil.e("网络请求的结果"+StringResult);
                ztAnyEventType.setSuccessCode();
                ztAnyEventType.setResult(StringResult);
                ztAnyEventType.setTag(tag);
                EventBus.getDefault().post(ztAnyEventType);

            }
        });
    }


    /**
     * 下载文件
     *
     * @param fileUrl     文件url
     * @param destFileDir 存储目标目录
     */
    public static void downLoadFile(String fileUrl, final String destFileDir, final  ReqCallBack mCallBack) {
//        final String fileName = MD5.encode(fileUrl);  sd/skd
        final File file = new File(destFileDir);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
            LogUtil.e("文件存在："+parentFile.exists());
        }
        if (TextUtils.isEmpty(destFileDir)) {
            mCallBack.failedCallBack("错误信息", "本地存储路径为空");
            return;
        }
        if (TextUtils.isEmpty(fileUrl)) {
            mCallBack.failedCallBack("错误信息", "网络下载地址不能为空");
            return;
        }
     /*   if (!file.exists()) {
            mCallBack.failedCallBack("错误信息", "文件不存在文件路径：" + destFileDir);
            return;
        }*/
        final Request request = new Request.Builder().url(fileUrl).build();
        final Call call = MApplication.getInstance().getSOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.e("错误信息", e);
                mCallBack.failedCallBack("下载失败", "");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    long total = response.body().contentLength();
                    LogUtil.e("total------>" + total);
                    long current = 0;
                    is = response.body().byteStream();
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        current += len;
                        fos.write(buf, 0, len);
                        LogUtil.e("current------>" + current);
                    }
                    fos.flush();
                    mCallBack.successCallBack(file, "下载成功");
                    UserInformationManager.getInstance().setUserNativeIconPath( destFileDir);
                } catch (IOException e) {
                    LogUtil.e(e.toString());
                    mCallBack.failedCallBack("下载失败", "");
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        LogUtil.e("错误信息", e.toString());
                    }
                }
            }
        });
    }


}
