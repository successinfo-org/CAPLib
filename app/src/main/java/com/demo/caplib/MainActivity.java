package com.demo.caplib;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.hp.eos.android.conf.HydraManager;
import com.hp.eos.android.sandbox.GlobalSandbox;
import com.hp.eos.android.service.DeviceService;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setSystemUiVisibility(DeviceService.DEFAULT_SYSTEM_UI_FLAG);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        HydraManager.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onResume() {
        super.onResume();
        HydraManager.onResume(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        HydraManager.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        HydraManager.destroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        GlobalSandbox globalSandbox  = HydraManager.getLastGlobalSandbox();
        if(globalSandbox != null){
            globalSandbox.pageContainer.onActivityResult(requestCode,resultCode,data);
        }
    }

}
