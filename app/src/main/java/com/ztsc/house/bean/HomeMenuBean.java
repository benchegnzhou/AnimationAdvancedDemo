package com.ztsc.house.bean;

/**
 * Created by xuyang on 2017/7/17.
 */

public class HomeMenuBean {
    private String classifyName;
    private int itemIcon;

    public HomeMenuBean(String classifyName, int itemIcon) {
        this.classifyName = classifyName;
        this.itemIcon = itemIcon;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public int getItemIcon() {
        return itemIcon;
    }

    public void setItemIcon(int itemIcon) {
        this.itemIcon = itemIcon;
    }
}
