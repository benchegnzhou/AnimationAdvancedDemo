import com.ztsc.house.customview.CanBeListenScrollView;

/**
 * Created by xuyang on 2017/4/21.
 */

public interface ScrollViewListener {
    void onScrollChanged(CanBeListenScrollView scrollView, int x, int y, int oldx, int oldy);
}
