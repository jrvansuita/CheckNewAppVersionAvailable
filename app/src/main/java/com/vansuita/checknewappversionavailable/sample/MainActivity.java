package com.vansuita.checknewappversionavailable.sample;

import android.os.Bundle;
import android.util.Log;

import com.vansuita.checknewappversionavailable.CheckNewAppVersion;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CheckNewAppVersion(this, 30000, "com.harex.android.ubpay").setOnTaskCompleteListener(new CheckNewAppVersion.ITaskComplete() {
            @Override
            public void onTaskComplete(CheckNewAppVersion.Result result) {
                //Checks if there is a new version available on Google Play Store.
                result.hasNewVersion();

                //Get the new published version code of the app.
                result.getNewVersionCode();

                //Get the app current version code.
                result.getOldVersionCode();

                //Opens the Google Play Store on your app page to do the update.
                result.openUpdateLink(MainActivity.this);

                Log.d("CheckNewAppVersion", "CheckNewAppVersion Sample" +
                    "packageVersion : " + result.getOldVersionCode() + "\n" +
                    "googlePlayVersion : " + result.getNewVersionCode());
            }
        }).execute();

    }
}
