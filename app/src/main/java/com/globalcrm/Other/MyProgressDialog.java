package com.globalcrm.Other;

import android.app.Dialog;
import android.content.Context;
import android.widget.ProgressBar;
import android.widget.RelativeLayout.LayoutParams;

import com.globalcrm.R;


public class MyProgressDialog extends Dialog {

    public MyProgressDialog show(Context context, CharSequence title,
                                 CharSequence message) {
        return show(context, title, message, false);
    }

    public MyProgressDialog show(Context context, CharSequence title,
                                 CharSequence message, boolean indeterminate) {
        return show(context, title, message, indeterminate, false, null);
    }

    public MyProgressDialog show(Context context, CharSequence title,
                                 CharSequence message, boolean indeterminate, boolean cancelable) {
        return show(context, title, message, indeterminate, cancelable, null);
    }

    public static MyProgressDialog show(Context context, CharSequence title,
                                        CharSequence message, boolean indeterminate, boolean cancelable,
                                        OnCancelListener cancelListener) {
        MyProgressDialog dialog = new MyProgressDialog(context);
        dialog.setTitle(title);
        dialog.setCancelable(cancelable);
        dialog.setOnCancelListener(cancelListener);
        ProgressBar progressBar = new ProgressBar(context);
        progressBar.getIndeterminateDrawable().setColorFilter(context.getResources().getColor(R.color.colorPrimaryDark), android.graphics.PorterDuff.Mode.SRC_IN);
        /* The next line will add the ProgressBar to the dialog. */
        dialog.addContentView(progressBar, new LayoutParams(85, 85));
        dialog.show();

        return dialog;
    }
    public MyProgressDialog(Context context) {
        super(context, R.style.NewDialog);
    }
}