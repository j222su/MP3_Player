package com.example.mp3player;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MusicPermission {

    private static final String TAG="MP3입니다.";
    private static final int REQUEST_CODE=100;
    private static final String[] REQUIRED_PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static MusicPermission musicPermission = new MusicPermission();

    public MusicPermission() {
    }

    static MusicPermission getInstance() {
        return musicPermission;
    }

    public void permissionCheck(Context context, Activity activity) {
        Log.d(TAG, "permissionCheck(싱글톤)");
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //권한이 허용되지 않았을 때
//            Toast.makeText(context, "권한 허용해주세요", Toast.LENGTH_LONG).show();
            Log.d(TAG, "permissionCheck() 권한 허용 안 되어있을 때");
            ActivityCompat.requestPermissions(activity, REQUIRED_PERMISSIONS, REQUEST_CODE);
            if(ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //사용자에게 설명
                //사용자의 응답을 기다리는 스레드
                //설명 후 다시 권한 요청
//                Toast.makeText(context, "설명", Toast.LENGTH_LONG).show();
            } else {
                //사용권한 요청(설명X)
                ActivityCompat.requestPermissions(activity, REQUIRED_PERMISSIONS, REQUEST_CODE);
                Log.d(TAG, "permissionCheck() 요청");

            }
        } else {
            //이미 권한이 부여되었을 때
//            Toast.makeText(context, "권한 허용되어있음", Toast.LENGTH_LONG).show();
        }
        return;
    }
}
