package com.example.myphone;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.databinding.BindingAdapter;


public final class MyDataBinding {
    private MyDataBinding() {
    }

    @BindingAdapter("layout_height")
    public static void MyAdapter(View view, Boolean isMax) {
        if (isMax) {
            ViewGroup.LayoutParams param = view.getLayoutParams();
            param.height = FrameLayout.LayoutParams.MATCH_PARENT;
            view.setLayoutParams(param);
        } else {
            ViewGroup.LayoutParams param = view.getLayoutParams();
            param.height = (int) view.getResources().getDisplayMetrics().ydpi;
            view.setLayoutParams(param);
        }
    }
}
