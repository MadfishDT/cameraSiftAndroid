package com.madfish.james.findyourvision;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Camera;

/**
 * Created by home on 2017-01-29.
 */

public class CameraDevice {

    private Context mContext;
    public CameraDevice(Context context){
        if(context != null) {
            mContext = context;
        }
    }
    public boolean hasPermissions() {
        int res = 0;
        // list all permissions which you want to check are granted or not.
        String[] permissions = new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        for (String perms : permissions){
            res = mContext.checkCallingOrSelfPermission(perms);
            if (!(res == PackageManager.PERMISSION_GRANTED)){
                // it return false because your app dosen't have permissions.
                return false;
            }
        }
        // it return true, your app has permissions.
        return true;
    }
    public boolean startCamera(){

    }
}
