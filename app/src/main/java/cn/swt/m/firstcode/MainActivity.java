package cn.swt.m.firstcode;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hnq.mylibrary.DownApkConstant;
import hnq.mylibrary.DownApkIntentService;

public class MainActivity extends AppCompatActivity {
    //    SimpleDraweeView mSimpleDraweeView;
    TextView s;
    private CameraManager manager;// 声明CameraManager对象
    private Camera m_Camera = null;// 声明Camera对象
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    EditText edNum;
    JiKeView mJiKeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edNum = (EditText) findViewById(R.id.ed_num);
        mJiKeView = (JiKeView) findViewById(R.id.thumbUpView);
    }

    public void setNum(View v) {
        try {
            int num = Integer.valueOf(edNum.getText().toString().trim());
            mJiKeView.setCount(num).setThumbUp(true);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "只能输入整数", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 手电筒控制方法
     *
     * @param lightStatus
     * @return
     */
    private void lightSwitch(final boolean lightStatus) {
        if (lightStatus) { // 关闭手电筒
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                try {
                    manager.setTorchMode("0", false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                if (m_Camera != null) {
                    m_Camera.stopPreview();
                    m_Camera.release();
                    m_Camera = null;
                }
            }
        } else { // 打开手电筒
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                try {
                    manager.setTorchMode("0", true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                final PackageManager pm = getPackageManager();
                final FeatureInfo[] features = pm.getSystemAvailableFeatures();
                for (final FeatureInfo f : features) {
                    if (PackageManager.FEATURE_CAMERA_FLASH.equals(f.name)) { // 判断设备是否支持闪光灯
                        if (null == m_Camera) {
                            m_Camera = Camera.open();
                        }
                        final Camera.Parameters parameters = m_Camera.getParameters();
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        m_Camera.setParameters(parameters);
                        m_Camera.startPreview();
                    }
                }
            }
        }
    }


    /**
     * 判断Android系统版本是否 >= M(API23)
     */
    private boolean isM() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void openLight(View v) {

    }

    public void download(View v) {
        Intent intent = new Intent(MainActivity.this, DownApkIntentService.class);
        Bundle bundle = new Bundle();
        bundle.putString(DownApkConstant.URL, "http://101.28.249.94/apk.r1.market.hiapk.com/data/upload/apkres/2017/4_11/15/com.baidu.searchbox_034250.apk");
        bundle.putString(DownApkConstant.NAME, "sss.apk");
        bundle.putInt(DownApkConstant.IMAGE_ID, R.mipmap.ic_launcher);
        bundle.putString(DownApkConstant.TITLE, "应用更新");
        intent.putExtras(bundle);
        startService(intent);
    }

    public void show(View view) {
     /*   final ProgressDialogUtils progressDialogUtils = new ProgressDialogUtils(this);
        progressDialogUtils.showDialog("加载");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialogUtils.DissDialog();
            }
        }, 3000);*/
/*        News news = new News();
        news.setTitle("你好");
        news.save();
        List<News> list = DataSupport.findAll(News.class);
        for (News news1 : list) {
            Log.d("swt", news1.getTitle());
        }*/
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("this is content title")
                .setContentText("this is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
//                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setVibrate(new long[]{0, 1000, 1000, 1000})
                .setLights(Color.WHITE, 1000, 1000)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build();
        notificationManager.notify(1, notification);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}
