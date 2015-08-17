package com.example.hsj.l002service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

/*
    Service的生命周期只有onCreate和onDestory，很简单
 */
public class EchoService extends Service {
    public EchoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        System.out.println("onBind");
        //throw new UnsupportedOperationException("Not yet implemented");
        return echoServiceBinder;
    }

    private final EchoServiceBinder echoServiceBinder = new EchoServiceBinder();
    public class EchoServiceBinder extends Binder {

        //通过内部类获取EchoService实例的方法
        public EchoService getService() {
            return EchoService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startTimer();
        System.out.println("onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopTimer();
        System.out.println("onDestory()");
    }

    //启动Service只能实现对Service的启动，但不能与Service进行数据交换
    //而绑定可以实现与Service的数据交换
    private Timer timer = null;
    private TimerTask timerTask = null;

    private int i = 0;

    public int getCurrentNum() {
        return i;
    }


    public void startTimer() {
        if(timer == null) {
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    i++;
                    System.out.println(i);
                }
            };
            timer.schedule(timerTask, 1000, 1000);
        }
    }

    public void stopTimer() {
        if(timer != null) {
            timerTask.cancel();
            timer.cancel();

            timer = null;
            timerTask = null;
            System.out.println("stop timer");
        }
    }
}
