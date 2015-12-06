package com.example.android.dinnerapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tagmanager.ContainerHolder;

/**
 * Created by jonathan.cook on 12/5/2015.
 */
public class ShowDailySpecialActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_daily_special);

        TextView tv = (TextView) findViewById(R.id.textView_info);
        tv.setText(updateDailySpecial());
    }

    public void orderDailySpecial(View view) {

        Utility.showMyToast("Ordering daily special", this);

    }

    private String updateDailySpecial() {

        ContainerHolder containerHolder = ((MyApplication) getApplication()).getContainerHolder();
        String value = containerHolder.getContainer().getString("daily-special");
        if (value != null) {
            return value;
        } else {
            return "There are no daily special's today";
        }

    }
}
