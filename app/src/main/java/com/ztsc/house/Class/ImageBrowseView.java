package com.ztsc.house.Class;

import android.content.Context;
import android.content.Intent;

import java.util.List;

/**
 * Created by xuyang on 2017/5/22.
 */

public interface ImageBrowseView {

    Intent getDataIntent();

    void setImageBrowse(List<String> images, int position);

    Context getMyContext();
}