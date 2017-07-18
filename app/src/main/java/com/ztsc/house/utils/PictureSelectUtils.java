package com.ztsc.house.utils;

import android.app.Activity;
import android.support.v4.content.ContextCompat;

import com.yalantis.ucrop.entity.LocalMedia;
import com.yalantis.ucrop.util.FunctionConfig;
import com.yalantis.ucrop.util.PictureConfig;
import com.ztsc.house.R;

import java.util.List;


/**
 * Created by benchengzhou on 2017/3/28.
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 备    注：
 */
public class PictureSelectUtils {


    /**
     * 图片回调方法
     */
//    private PictureConfig.OnSelectResultCallback resultCallback = new PictureConfig.OnSelectResultCallback() {
//        private List<LocalMedia> selectMedia;
//
//        @Override
//        public void onSelectSuccess(List<LocalMedia> resultList) {
//            List<LocalMedia> selectMedia = resultList;
//            Log.e("callBack_result", selectMedia.size() + "");
//            if (selectMedia != null) {
//                Log.e("call_back", selectMedia.toString());
////                    adapter.setList(selectMedia);
////                    adapter.notifyDataSetChanged();
//            }
//        }
//    };

    public static int selectType = 1;
    public static boolean isCheckNumMode = true;
    public static int copyMode = FunctionConfig.COPY_MODEL_1_1;

    public static int selectMode = FunctionConfig.MODE_MULTIPLE;
    public static boolean isShow = true;
    public static boolean isPreviewVideo = true;
    public static boolean enablePreview = true;
    public static boolean enableCrop = true;
    //    List<LocalMedia> selectMedia = new ArrayList<>();  //照片回传
    public static boolean theme = true;
    public static boolean selectImageType = true;


    /** 功能描述： 多张图片选择，可以使能进行压缩
     * @param mContext       必须传入的actrivity
     * @param maxSelectNum   可以选择的图片的最大的数目，默认是一张，可以不传
     * @param isCompress     是不是对选中的图片进行压缩
     * @param qualityPercent 图片压缩的百分比 isCompress=true时有效
     * @param selectMedia    备选图片存储的集合,注意最好使用成员变量，保证可以连续选择
     * @param resultCallback 选择成功的回掉
     */
    public static void selectPhotos(Activity mContext, int maxSelectNum, boolean isCompress, int qualityPercent, List<LocalMedia> selectMedia, PictureConfig.OnSelectResultCallback resultCallback) {
        if (maxSelectNum < 1) {    //最多可选的图片的数目
            maxSelectNum = 1;
        }
        if (qualityPercent < 10) {
            qualityPercent = 10;
        } else if (qualityPercent > 100) {
            qualityPercent = 100;  //100表示不进行压缩
        }

        // 进入相册
        /**
         * type --> 1图片 or 2视频
         * copyMode -->裁剪比例，默认、1:1、3:4、3:2、16:9
         * maxSelectNum --> 可选择图片的数量
         * selectMode         --> 单选 or 多选
         * isShow       --> 是否显示拍照选项 这里自动根据type 启动拍照或录视频
         * isPreview    --> 是否打开预览选项
         * isCrop       --> 是否打开剪切选项
         * isPreviewVideo -->是否预览视频(播放) mode or 多选有效
         * ThemeStyle -->主题颜色
         * CheckedBoxDrawable -->图片勾选样式
         * cropW-->裁剪宽度 值不能小于100  如果值大于图片原始宽高 将返回原图大小
         * cropH-->裁剪高度 值不能小于100
         * isCompress -->是否压缩图片
         * setRecordVideoSecond 录视频的秒数，默认不限制
         * setRecordVideoDefinition 视频清晰度  Constants.HIGH 清晰  Constants.ORDINARY 低质量
         * setImageSpanCount -->每行显示个数
         * setCheckNumMode 是否显示QQ选择风格(带数字效果)
         * setPreviewColor 预览文字颜色
         * setCompleteColor 完成文字颜色
         * setPreviewBottomBgColor 预览界面底部背景色
         * setBottomBgColor 选择图片页面底部背景色
         * setCompressQuality 设置裁剪质量，默认无损裁剪
         * setSelectMedia 已选择的图片
         * 注意-->type为2时 设置isPreview or isCrop 无效
         * 注意：Options可以为空，默认标准模式
         */

        int selector = R.drawable.select_cb;
        FunctionConfig config = new FunctionConfig();
        config.setType(selectType);      //1图片 or 2视频
        config.setCopyMode(copyMode);   //裁剪比例
        config.setCompress(isCompress);  //是否压缩图片
        config.setMaxSelectNum(maxSelectNum);  //最多选择限制
        config.setSelectMode(selectMode);  //是否多选
        config.setShowCamera(isShow);  //是否支持预览
        config.setEnablePreview(enablePreview);    //
        config.setEnableCrop(enableCrop);       //单张图片的时候允许裁剪
        config.setPreviewVideo(isPreviewVideo);    //是否预览视频(播放) mode or 多选有效
        config.setRecordVideoDefinition(FunctionConfig.HIGH);// 视频清晰度
        config.setRecordVideoSecond(60);// 视频秒数
//    config.setCropW(cropW);
//    config.setCropH(cropH);
        config.setCheckNumMode(isCheckNumMode);  //qq风格
        config.setCompressQuality(qualityPercent);    //设置裁剪质量，默认无损裁剪
        config.setImageSpanCount(3);        //每行显示个数
        config.setSelectMedia(selectMedia);  //已选择的图片
        if (theme) {
            config.setThemeStyle(ContextCompat.getColor(mContext, R.color.blue));
            // 可以自定义底部 预览 完成 文字的颜色和背景色
            if (!isCheckNumMode) {
                // QQ 风格模式下 这里自己搭配颜色，使用蓝色可能会不好看
                config.setPreviewColor(ContextCompat.getColor(mContext, R.color.white));
                config.setCompleteColor(ContextCompat.getColor(mContext, R.color.white));
                config.setPreviewBottomBgColor(ContextCompat.getColor(mContext, R.color.blue));
                config.setBottomBgColor(ContextCompat.getColor(mContext, R.color.blue));
            }
        }
        if (selectImageType) {       //是不是自定义的样式
            config.setCheckedBoxDrawable(selector);
        }
        // 先初始化参数配置，在启动相册
        PictureConfig.init(config);
        // 设置回调函数
        PictureConfig.openPhoto(mContext, resultCallback);
    }

    /**
     * 功能描述： 单张图片的选择，可以使能对图片的裁剪
     * @param mContext          需要使用的必须的activity
     * @param isCompress        压缩图片使能
     * @param qualityPercent    压缩质量 0-100
     * @param cropW             裁剪框的宽  必须大于100
     * @param cropH             裁剪框的高  必须大于100
     * @param enableCrop        裁剪使能
     * @param selectMedia       保存选中图片的集合
     * @param resultCallback    选中图片后的会掉
     */
    public static void selectSinglePhoto(Activity mContext, boolean isCompress, int qualityPercent, int cropW, int cropH, boolean enableCrop, List<LocalMedia> selectMedia, PictureConfig.OnSelectResultCallback resultCallback) {


        int maxSelectNum = 1;    //单张图片
        if (qualityPercent < 10) {
            qualityPercent = 10;
        } else if (qualityPercent > 100) {
            qualityPercent = 100;  //100表示不进行压缩
        }

        int selectType = 1;  //1图片 or 2视频
        boolean isCheckNumMode = true;    //选中的图片的标识 是不是数字
        int copyMode = FunctionConfig.COPY_MODEL_1_1;    //裁剪模式

        int selectMode = FunctionConfig.MODE_SINGLE;  //单图选择和多图选择
        boolean isShow = true;
        boolean isPreviewVideo = true;
        boolean enablePreview = true;
//         boolean enableCrop = true;     //裁剪使能
//    List<LocalMedia> selectMedia = new ArrayList<>();  //照片回传
        boolean theme = true;
        boolean selectImageType = true;  //是不是自定义的样式


        // 进入相册
        /**
         * type --> 1图片 or 2视频
         * copyMode -->裁剪比例，默认、1:1、3:4、3:2、16:9
         * maxSelectNum --> 可选择图片的数量
         * selectMode         --> 单选 or 多选
         * isShow       --> 是否显示拍照选项 这里自动根据type 启动拍照或录视频
         * isPreview    --> 是否打开预览选项
         * isCrop       --> 是否打开剪切选项
         * isPreviewVideo -->是否预览视频(播放) mode or 多选有效
         * ThemeStyle -->主题颜色
         * CheckedBoxDrawable -->图片勾选样式
         * cropW-->裁剪宽度 值不能小于100  如果值大于图片原始宽高 将返回原图大小
         * cropH-->裁剪高度 值不能小于100
         * isCompress -->是否压缩图片
         * setRecordVideoSecond 录视频的秒数，默认不限制
         * setRecordVideoDefinition 视频清晰度  Constants.HIGH 清晰  Constants.ORDINARY 低质量
         * setImageSpanCount -->每行显示个数
         * setCheckNumMode 是否显示QQ选择风格(带数字效果)
         * setPreviewColor 预览文字颜色
         * setCompleteColor 完成文字颜色
         * setPreviewBottomBgColor 预览界面底部背景色
         * setBottomBgColor 选择图片页面底部背景色
         * setCompressQuality 设置裁剪质量，默认无损裁剪
         * setSelectMedia 已选择的图片
         * 注意-->type为2时 设置isPreview or isCrop 无效
         * 注意：Options可以为空，默认标准模式
         */
   /* String ws = et_w.getText().toString().trim();
    String hs = et_h.getText().toString().trim();
    if (!isNull(ws) && !isNull(hs)) {
        cropW = Integer.parseInt(ws);
        cropH = Integer.parseInt(hs);
    }*/
        int selector = R.drawable.select_cb;
        FunctionConfig config = new FunctionConfig();
        config.setType(selectType);      //1图片 or 2视频
        config.setCopyMode(copyMode);   //裁剪比例
        config.setCompress(isCompress);  //是否压缩图片
        config.setMaxSelectNum(maxSelectNum);  //最多选择限制
        config.setSelectMode(selectMode);  //是否多选
        config.setShowCamera(isShow);  //是否支持预览
        config.setEnablePreview(enablePreview);    //
        config.setEnableCrop(enableCrop);       //单张图片的时候允许裁剪
        config.setPreviewVideo(isPreviewVideo);    //是否预览视频(播放) mode or 多选有效
        config.setRecordVideoDefinition(FunctionConfig.HIGH);// 视频清晰度
        config.setRecordVideoSecond(60);// 视频秒数
        config.setCropW(cropW);
        config.setCropH(cropH);
        config.setCheckNumMode(isCheckNumMode);  //qq风格
        config.setCompressQuality(qualityPercent);    //设置裁剪质量，默认无损裁剪
        config.setImageSpanCount(4);        //每行显示个数
        config.setSelectMedia(selectMedia);  //已选择的图片
        if (theme) {
            config.setThemeStyle(ContextCompat.getColor(mContext, R.color.blue));
            // 可以自定义底部 预览 完成 文字的颜色和背景色
            if (!isCheckNumMode) {
                // QQ 风格模式下 这里自己搭配颜色，使用蓝色可能会不好看
                config.setPreviewColor(ContextCompat.getColor(mContext, R.color.white));
                config.setCompleteColor(ContextCompat.getColor(mContext, R.color.white));
                config.setPreviewBottomBgColor(ContextCompat.getColor(mContext, R.color.blue));
                config.setBottomBgColor(ContextCompat.getColor(mContext, R.color.blue));
            }
        }
        if (selectImageType) {       //是不是自定义的样式
            config.setCheckedBoxDrawable(selector);
        }
        // 先初始化参数配置，在启动相册
        PictureConfig.init(config);
        // 设置回调函数
        PictureConfig.openPhoto(mContext, resultCallback);
    }

    /**
     * 功能描述：   视频选择
     * @param mContext          必须传入的activity
     * @param selectMedia       选中视频的存放集合
     * @param resultCallback    选择成功和的回调
     */
    public static void selectVideo(Activity mContext, List<LocalMedia> selectMedia, PictureConfig.OnSelectResultCallback resultCallback) {


        int maxSelectNum = 1;    //单张图片


        int selectType = 2;  //1图片 or 2视频
        boolean isCheckNumMode = true;    //选中的图片的标识 是不是数字
        int copyMode = FunctionConfig.COPY_MODEL_1_1;    //裁剪模式

        int selectMode = FunctionConfig.MODE_MULTIPLE;  //单图选择和多图选择
        boolean isShow = true;
        boolean isPreviewVideo = true;
        boolean enablePreview = true;
//         boolean enableCrop = true;     //裁剪使能
//    List<LocalMedia> selectMedia = new ArrayList<>();  //照片回传
        boolean theme = true;
        boolean selectImageType = true;  //是不是自定义的样式


        // 进入相册
        /**
         * type --> 1图片 or 2视频
         * copyMode -->裁剪比例，默认、1:1、3:4、3:2、16:9
         * maxSelectNum --> 可选择图片的数量
         * selectMode         --> 单选 or 多选
         * isShow       --> 是否显示拍照选项 这里自动根据type 启动拍照或录视频
         * isPreview    --> 是否打开预览选项
         * isCrop       --> 是否打开剪切选项
         * isPreviewVideo -->是否预览视频(播放) mode or 多选有效
         * ThemeStyle -->主题颜色
         * CheckedBoxDrawable -->图片勾选样式
         * cropW-->裁剪宽度 值不能小于100  如果值大于图片原始宽高 将返回原图大小
         * cropH-->裁剪高度 值不能小于100
         * isCompress -->是否压缩图片
         * setRecordVideoSecond 录视频的秒数，默认不限制
         * setRecordVideoDefinition 视频清晰度  Constants.HIGH 清晰  Constants.ORDINARY 低质量
         * setImageSpanCount -->每行显示个数
         * setCheckNumMode 是否显示QQ选择风格(带数字效果)
         * setPreviewColor 预览文字颜色
         * setCompleteColor 完成文字颜色
         * setPreviewBottomBgColor 预览界面底部背景色
         * setBottomBgColor 选择图片页面底部背景色
         * setCompressQuality 设置裁剪质量，默认无损裁剪
         * setSelectMedia 已选择的图片
         * 注意-->type为2时 设置isPreview or isCrop 无效
         * 注意：Options可以为空，默认标准模式
         */
   /* String ws = et_w.getText().toString().trim();
    String hs = et_h.getText().toString().trim();
    if (!isNull(ws) && !isNull(hs)) {
        cropW = Integer.parseInt(ws);
        cropH = Integer.parseInt(hs);
    }*/
        int selector = R.drawable.select_cb;
        FunctionConfig config = new FunctionConfig();
        config.setType(selectType);      //1图片 or 2视频
        config.setCopyMode(copyMode);   //裁剪比例
//        config.setCompress(isCompress);  //是否压缩图片
        config.setMaxSelectNum(maxSelectNum);  //最多选择限制
        config.setSelectMode(selectMode);  //是否多选
        config.setShowCamera(isShow);  //是否支持预览
        config.setEnablePreview(enablePreview);    //
        config.setEnableCrop(enableCrop);       //单张图片的时候允许裁剪
        config.setPreviewVideo(isPreviewVideo);    //是否预览视频(播放) mode or 多选有效
        config.setRecordVideoDefinition(FunctionConfig.HIGH);// 视频清晰度
        config.setRecordVideoSecond(60);// 视频秒数
//        config.setCropW(cropW);
//        config.setCropH(cropH);
        config.setCheckNumMode(isCheckNumMode);  //qq风格
//        config.setCompressQuality(qualityPercent);    //设置裁剪质量，默认无损裁剪
        config.setImageSpanCount(4);        //每行显示个数
        config.setSelectMedia(selectMedia);  //已选择的图片
        if (theme) {
            config.setThemeStyle(ContextCompat.getColor(mContext, R.color.blue));
            // 可以自定义底部 预览 完成 文字的颜色和背景色
            if (!isCheckNumMode) {
                // QQ 风格模式下 这里自己搭配颜色，使用蓝色可能会不好看
                config.setPreviewColor(ContextCompat.getColor(mContext, R.color.white));
                config.setCompleteColor(ContextCompat.getColor(mContext, R.color.white));
                config.setPreviewBottomBgColor(ContextCompat.getColor(mContext, R.color.blue));
                config.setBottomBgColor(ContextCompat.getColor(mContext, R.color.blue));
            }
        }
        if (selectImageType) {       //是不是自定义的样式
            config.setCheckedBoxDrawable(selector);
        }
        // 先初始化参数配置，在启动相册
        PictureConfig.init(config);
        // 设置回调函数
        PictureConfig.openPhoto(mContext, resultCallback);
    }


}
