package com.ztsc.house.adapter;


import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;
import java.util.Map;

public class ViewPagerAdapter extends PagerAdapter {
    List<Map<String, Object>> viewLists;
    DisplayImageOptions options;
    ImageLoader imageLoader;
  //  ViewPager viewPager;

    public ViewPagerAdapter(DisplayImageOptions options, List<Map<String, Object>> viewLists, ImageLoader imageLoader) {
        this.options = options;
        this.viewLists = viewLists;
        this.imageLoader = imageLoader;
        //this.viewPager=viewPager;
    }

    //获得size
    @Override
    public int getCount() {
        return viewLists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ImageView x = (ImageView) viewLists.get(position).get("view");
        x.setScaleType(ImageView.ScaleType.FIT_CENTER);
        container.removeView(x);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView x = (ImageView) viewLists.get(position).get("view");
        x.setScaleType(ImageView.ScaleType.FIT_CENTER);
/*        ViewGroup.LayoutParams ZTHouseHttpClient = x.getLayoutParams();
        ZTHouseHttpClient.height=viewPager.getHeight();
        ZTHouseHttpClient.width =viewPager.getWidth();*/
        imageLoader.displayImage(viewLists.get(position).get("url").toString(), x, options);
        container.addView(x, 0);
        return viewLists.get(position).get("view");
    }






}
