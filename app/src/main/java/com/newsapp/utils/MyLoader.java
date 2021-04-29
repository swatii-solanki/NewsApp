package com.newsapp.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Window;

import androidx.databinding.DataBindingUtil;

import com.newsapp.R;


public class MyLoader {

    private Dialog dialog;
    private final Context mContext;

    public MyLoader(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        com.newsapp.databinding.ItemLoaderBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_loader, null, false);
        mContext = context;
        try {
            dialog = new Dialog(mContext);
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(binding.getRoot());
//            dialog.getWindow().setBackgroundDrawableResource(android.R.color.white);
//            dialog.getWindow().setLayout(-1, -2);
            dialog.setCancelable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }
}
