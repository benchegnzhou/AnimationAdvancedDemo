package com.ztsc.house.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ztsc.house.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class GuideActivity extends AppCompatActivity {
    @Bind(R.id.btn_startuse)
    Button btnStartuse;
    @Bind(R.id.vp_guide)
    ViewPager vpGuide;
    @Bind(R.id.ll)
    LinearLayout ll;
    private int images[] = {R.mipmap.guide1, R.mipmap.guide2, R.mipmap.guide3, R.mipmap.guide4};
    private List<ImageView> imageViewList;
    private ImageView icons[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        //初始化数据源
        initData();
        //初始化小图标
        initIcons();
        //创建适配器对象
        MyGuideVPAdapter myGuideVPAdapter = new MyGuideVPAdapter();
        vpGuide.setAdapter(myGuideVPAdapter);

        addOnPageChange();


    }


    //ViewPager页改变的监听事件
    private void addOnPageChange() {
        vpGuide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             *
             * @param position   当前滑动选中位置 position
             * @param positionOffset   位置偏移 [0,1)
             * @param positionOffsetPixels  page页滑动的像素值
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            /**
             * 页面选中时回调方法
             * @param position   选中的page页索引值
             */
            @Override
            public void onPageSelected(int position) {
                if (position == imageViewList.size() - 1) {
                    btnStartuse.setVisibility(View.VISIBLE);
                } else {
                    btnStartuse.setVisibility(View.GONE);
                }
                for (int i = 0; i < icons.length; i++) {
                    icons[i].setEnabled(true);
                }
                icons[position].setEnabled(false);

            }

            /**
             *  滚动状态发生改变时回调方法
             *  int state：表示状态
             *  SCROLL_STATE_IDEL  停止状态
             *  SCROLL_STATE_FLYING  快速滑动状态
             *  SCROLL_STATE_TOUCH_SCROLL  触摸滑动状态
             * @param state
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //初始化dot小图标
    public void initIcons() {
        icons = new ImageView[images.length];
        for (int i = 0; i < icons.length; i++) {
            //获取容器中第i个子
            icons[i] = (ImageView) ll.getChildAt(i);
            icons[i].setEnabled(true);
            //给ImageView贴标签
            icons[i].setTag(i);
            icons[i].setClickable(true);
            //给ImageView定义选中点击事件
            //点击dot，Viewpager页面也要发生改变
            icons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //设置viewPager当前的Item是第(int)view.getTag()个
                    vpGuide.setCurrentItem((int) view.getTag());
                }
            });
        }
        icons[0].setEnabled(false);
    }

    //开始使用按钮的点击事件
    @OnClick(R.id.btn_startuse)
    public void startuse() {
        Intent intent = new Intent(GuideActivity.this,  MainActivity.class);
        GuideActivity.this.startActivity(intent);
        GuideActivity.this.finish();
    }

    //初始化图片数据
    private void initData() {
        imageViewList = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]);
            imageViewList.add(imageView);
        }
    }

    //适配器的创建
    public class MyGuideVPAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViewList.get(position);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(imageView);
            return imageViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView(imageViewList.get(position));
        }
    }


}
