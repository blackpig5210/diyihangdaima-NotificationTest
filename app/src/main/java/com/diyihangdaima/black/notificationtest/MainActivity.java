package com.diyihangdaima.black.notificationtest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button sendNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendNotice = (Button) findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_notice:
                NotificationManager manager = (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);//获取NotificationManager
                //Notification notification = new Notification(R.drawable.ic_launcher,
                        //"This is ticker text", System.currentTimeMillis()//创建Notification
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                //实例化通知栏构造器
                builder.setContentTitle("测试标题")
                        .setContentText("测试内容")
                        .setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL))
                        .setTicker("测试通知来了")
                        .setWhen(System.currentTimeMillis())
                        .setPriority(Notification.PRIORITY_DEFAULT) //设置该通知优先级
                    //  .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                        .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                        .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                        //Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission
                        .setSmallIcon(R.drawable.ic_launcher);//设置通知小ICON
//                Intent intent = new Intent(this, NotificationActivity.class);
//                PendingIntent pi = PendingIntent.getActivity(this, 0, intent,
//                        PendingIntent.FLAG_CANCEL_CURRENT);

//                notification.setLatestEventInfo(this, "This is content title", "This is content text",
//                        null);//设定通知布局
                manager.notify(1, builder.build());//让通知显示
                break;
            default:
                break;
        }
    }

    private PendingIntent getDefalutIntent(int flags) {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1,
                new Intent(this, NotificationActivity.class), flags);
        return pendingIntent;
    }
}
