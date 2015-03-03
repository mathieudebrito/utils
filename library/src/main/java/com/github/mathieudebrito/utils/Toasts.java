package com.github.mathieudebrito.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Toasts {

    public static void show(Context context, int message) {
        Toast.makeText(context, context.getString(message), Toast.LENGTH_LONG).show();
    }

    public static void show(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void warnInternet(Context context) {
        warn(context, R.string.utils_error_internet_not_available);
    }

    public static void ok(Context context, int message) {
        showMessageWithIcon(context, message, -1);
    }

    public static void warn(Context context, int message) {
        showMessageWithIcon(context, message, -1);
    }

    public static void error(Context context, int message) {
        showMessageWithIcon(context, message, -1);
    }

    public static void showMessageWithIcon(Context context, int message, int icon) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);

        LinearLayout horizontalLayout = new LinearLayout(context);
        horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
        horizontalLayout.setGravity(Gravity.CENTER);

        // Add textView
        TextView txtMessage = new TextView(context);
        txtMessage.setTextColor(Color.WHITE);
        txtMessage.setPadding(20, 0, 0, 0);
        txtMessage.setText(message);

        // Layout it
        if (icon != -1) {
            ImageView imgWarn = new ImageView(context);
            imgWarn.setImageResource(icon);
            horizontalLayout.addView(imgWarn);
            imgWarn.getLayoutParams().height = 90;
            imgWarn.getLayoutParams().width = 90;
        }
        horizontalLayout.addView(txtMessage);
        toast.setView(horizontalLayout);

        // Show it
        toast.show();
    }


}