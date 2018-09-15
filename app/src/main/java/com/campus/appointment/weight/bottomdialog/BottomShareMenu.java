package com.campus.appointment.weight.bottomdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.campus.appointment.R;

public abstract class BottomShareMenu extends Dialog {

    public BottomShareMenu(Context context) {
        super(context, R.style.Dialog_Fullscreen);
        setContentView(onBindView());
        Window window=this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.ButtomMenuEnterExitAnimation);
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int)(display.getWidth()); //设置宽度
        window.setAttributes(lp);
        setData();
    }
    protected abstract View onBindView();
    protected abstract void setData();
}