package com.madfish.james.findyourvision;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by home on 2017-01-29.
 */
//ref http://webnautes.tistory.com/822
public class CameraDevice {

    private AppCompatActivity mContext;
    public final static int CAMERA_REQUEST_CODE = 100;
    public final static int REQUEST_CODE_STORAGE_PERM = 321;

    public CameraDevice(AppCompatActivity context){
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
    public void requestNecessaryPermissions() {
        // make array of permissions which you want to ask from user.
        String[] permissions = new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // have arry for permissions to requestPermissions method.
            // and also send unique Request code.
            mContext.requestPermissions(permissions, REQUEST_CODE_STORAGE_PERM);
        }
    }
    public void startCamera() {

        if ( preview == null ) {
            preview = new Preview(mContext, (SurfaceView) mContext.findViewById(R.id.surfaceView));
            preview.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            ((FrameLayout) findViewById(R.id.layout)).addView(preview);
            preview.setKeepScreenOn(true);

            preview.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    camera.takePicture(shutterCallback, rawCallback, jpegCallback);
                }
            });
        }

        preview.setCamera(null);
        if (camera != null) {
            camera.release();
            camera = null;
        }

        int numCams = Camera.getNumberOfCameras();
        if (numCams > 0) {
            try {
                camera = Camera.open(0);
                camera.setDisplayOrientation(90);

                camera.startPreview();
                Toast.makeText(this, "camera start", Toast.LENGTH_LONG).show();

            } catch (RuntimeException ex) {
                Toast.makeText(ctx, "camera_not_found " + ex.getMessage().toString(), Toast.LENGTH_LONG).show();
                Log.d(TAG, "camera_not_found " + ex.getMessage().toString());
            }
        }


        preview.setCamera(camera);
    }
}
