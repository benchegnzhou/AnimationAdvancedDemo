package com.ztsc.house.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.ztsc.house.application.MApplication;
import com.ztsc.house.bean.userdata.CityDictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/10/10.
 * 本地文件操作的工具类
 */
public class FileUtils {
    /**
     * Environment.getExternalStorageDirectory()getRootDirectory()//获取手机根目录
     * Environment.getExternalStorageDirectory()getExternalStorageDirectory()//获取SD卡根目录
     */
    public static String sAbsolutePath = Environment.getExternalStorageDirectory().getPath();  //获取内置存储卡的路径
    public static String iconDir = sAbsolutePath + "/LifeOfZhengTu/pic";                  //创建公司表示的文件夹层级


    /**
     * 本地上传图片--本机
     * 优先使用sd卡的存储路径，当sd卡未就绪的情况下使用手机内存空间
     * 但是实际测试的时候sd卡路径在没有sd 的时候使用的是手机内存虚拟出来的内存
     *
     * @param pContext
     * @return
     */
    public static String getSDCardPath(Context pContext) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/zhengtushuchuang_dir/pic";
            String path = Environment.getExternalStorageDirectory().getPath().toString() + "/zhengtushuchuang_dir/Pictures";
            File PathDir = new File(path);
            if (!PathDir.exists()) {
                PathDir.mkdirs();
            }
            LogUtil.e("文件存在：" + PathDir.exists());
            return path;
        } else {
            return pContext.getCacheDir().getAbsolutePath();
        }
    }

    /**
     * 本地上传图片--本机
     * 优先使用sd卡的存储路径，当sd卡未就绪的情况下使用手机内存空间
     * 但是实际测试的时候sd卡路径在没有sd 的时候使用的是手机内存虚拟出来的内存
     *
     * @param pContext
     * @return
     */
    public static String getSDCardPath4Setting(Context pContext) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/zhengtushuchuang_dir/pic";
            String path = Environment.getExternalStorageDirectory().getPath().toString() + "/zhengtushuchuang_dir/Datas";
            File PathDir = new File(path);
            if (!PathDir.exists()) {
                PathDir.mkdirs();
            }
            LogUtil.e("文件存在：" + PathDir.exists());
            return path;
        } else {
            return pContext.getCacheDir().getAbsolutePath();
        }
    }


    /***
     * 将大文件拷贝到sd卡目录
     * 待测试
     *
     * @param targetFilePath 文件拷贝到目标路径
     * @param assetFileName  需要拷贝的文件
     * @throws IOException
     */
    public static void copyAssetsDataToSD(String assetFileName, String targetFilePath) throws IOException {
        //目标路径或者需要拷贝的文件不存在，将会退出
        if (TextUtils.isEmpty(assetFileName) || TextUtils.isEmpty(targetFilePath)) {
            return;
        }
        File file = new File(targetFilePath);
        if (file.isFile()) {   //是一个文件夹，则删除
            file.delete();
        } else if (file.exists()) {
            return;     //文件已经存在，不在执行拷贝
        }

        InputStream myInput;
        OutputStream myOutput = new FileOutputStream(targetFilePath);

        myInput = MApplication.sAppContext.getAssets().open(assetFileName);
        byte[] buffer = new byte[1024];
        int length = myInput.read(buffer);
        while (length > 0) {
            myOutput.write(buffer, 0, length);
            length = myInput.read(buffer);
        }

        myOutput.flush();
        myInput.close();
        myOutput.close();
    }


    /**
     * 追加文件：使用FileOutputStream，在构造FileOutputStream时，把第二个参数设为true
     *
     * @param file
     * @param conent
     */
    public static void textFileAppendWrite(String file, String conent) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            out.write(conent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 覆盖的方式写入文本数据：使用FileOutputStream，在构造FileOutputStream时，把第二个参数设为true
     *
     * @param file
     * @param conent
     */
    public static void textFileWrite(String file, String conent) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, false)));

            out.write(conent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 读取文本文件中的内容，用行的方式读取
     *
     * @param strFilePath
     * @return
     */
    public static String ReadTextFile(String strFilePath) {
        String path = strFilePath;
        String content = ""; //文件内容字符串
        //打开文件
        File file = new File(path);
        //如果path是传递过来的参数，可以做一个非目录的判断
        if (file.isDirectory()) {
            LogUtil.e("TestFile", "The File doesn't not exist.");
        } else {
            try {
                InputStream instream = new FileInputStream(file);
                if (instream != null) {
                    InputStreamReader inputreader = new InputStreamReader(instream);
                    BufferedReader buffreader = new BufferedReader(inputreader);
                    String line;
                    //分行读取
                    while ((line = buffreader.readLine()) != null) {
                        content += line + "\n";
                    }
                    instream.close();
                }
            } catch (java.io.FileNotFoundException e) {
                LogUtil.e("TestFile", "The File doesn't not exist.");
            } catch (IOException e) {
                LogUtil.e("TestFile", e.getMessage());
            }
        }
        return content;
    }

    /**
     * 读取文本文件中的内容，用行的方式读取
     * 小区的搜索历史解析获取
     *
     * @param strFilePath
     * @return
     */
   /* public static List<CommunityVillageHistoryJsonBeen.CommunitiesBean> VillageTextFileRead(String strFilePath) {
        String path = strFilePath;
        List<CommunityVillageHistoryJsonBeen.CommunitiesBean> communitiesBeenList = new ArrayList<>();
        //打开文件
        File file = new File(path);
        //如果path是传递过来的参数，可以做一个非目录的判断
        if (file.isDirectory()) {
            file.delete();   //被文件夹占用直接删除
            LogUtil.e("TestFile", "The File doesn't not exist.");
        } else {
            try {
                InputStream instream = new FileInputStream(file);
                if (instream != null) {
                    InputStreamReader inputreader = new InputStreamReader(instream);
                    BufferedReader buffreader = new BufferedReader(inputreader);
                    String line;
                    //分行读取
                    while ((line = buffreader.readLine()) != null) {
                        String[] split = line.split("[,]");
                        if (split != null && split.length > 0) {
                            CommunityVillageHistoryJsonBeen.CommunitiesBean communitiesBean = new CommunityVillageHistoryJsonBeen.CommunitiesBean();
                            communitiesBean.setZoneId(split[0]);
                            communitiesBean.setZoneName(split[1]);
                            if (!TextUtils.isEmpty(split[2].trim())) {
                                communitiesBean.setZoneX(split[2]);
                            } else {
                                communitiesBean.setZoneX("");    //默认返回""
                            }
                            if (!TextUtils.isEmpty(split[3].trim())) {
                                communitiesBean.setZoneY(split[3]);
                            } else {
                                communitiesBean.setZoneY("");
                            }
                            communitiesBeenList.add(communitiesBean);
                        }

                    }
                    instream.close();
                }
            } catch (java.io.FileNotFoundException e) {
                LogUtil.e("TestFile", "The File doesn't not exist.");
            } catch (Exception e) {
                LogUtil.e("TestFile", e.getMessage());
                file.delete();   //被文件夹占用直接删除
            }
        }
        return communitiesBeenList;
    }
*/

    /**
     * 解析本地文件，将对应的城市解析成对象
     *
     * @param strFilePath
     * @return
     */
    public static List<CityDictionary> cityDictionarieFileRead(String strFilePath) {
        String path = strFilePath;
        List<CityDictionary> communitiesBeenList = new ArrayList<>();
        //打开文件
        File file = new File(path);
        //如果path是传递过来的参数，可以做一个非目录的判断
        if (file.isDirectory()) {
            file.delete();   //被文件夹占用直接删除
            LogUtil.e("TestFile", "The File doesn't not exist.");
        } else {
            try {
                InputStream instream = new FileInputStream(file);
                if (instream != null) {
                    InputStreamReader inputreader = new InputStreamReader(instream);
                    BufferedReader buffreader = new BufferedReader(inputreader);
                    String line;
                    //分行读取
                    while ((line = buffreader.readLine()) != null) {
                        String[] split = line.split("[,]");
                        if (split != null && split.length > 0) {
                            CityDictionary cityDictionary = new CityDictionary();

                            cityDictionary.setCityName(split[0]);
                            cityDictionary.setCityENAME(split[1]);
                            cityDictionary.setCityCODE(split[2]);
                            cityDictionary.setCityADDRESS(split[3]);
                            cityDictionary.setCityX(split[4]);
                            cityDictionary.setCityY(split[5]);
                            communitiesBeenList.add(cityDictionary);
                        }
                    }
                    inputreader.close();
                }
                instream.close();

            } catch (java.io.FileNotFoundException e) {
                LogUtil.e("The File doesn't not exist.",e);
            } catch (Exception e) {
                LogUtil.e("城市文件读取异常了：", e.getMessage());
                file.delete();   //被文件夹占用直接删除
            }
        }
        if(communitiesBeenList.size()>0){
            communitiesBeenList.remove(0);    //移除首条信息
        }
        return communitiesBeenList;
    }


}
