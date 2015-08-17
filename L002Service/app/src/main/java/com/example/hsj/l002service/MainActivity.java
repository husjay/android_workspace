package com.example.hsj.l002service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements View.OnClickListener, ServiceConnection {

    //btnStartService btnStopService启动和停止服务
    //btnBindService btnUnbindService绑定和结绑定服务
    private Button btnStartService, btnStopService, btnBindService, btnUnbindService, btnGetCurrentNum;
    private Intent intentService;
    private EchoService echoService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentService = new Intent(this, EchoService.class);

        //找到对应的按键关键值
        btnStartService = (Button) findViewById(R.id.btnStartService);
        btnStopService = (Button) findViewById(R.id.btnStopService);
        btnBindService = (Button) findViewById(R.id.btnBindService);
        btnUnbindService = (Button) findViewById(R.id.btnUnbindService);
        btnGetCurrentNum = (Button) findViewById(R.id.btnGetCurrentNum);

        //设置按键监听
        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnBindService.setOnClickListener(this);
        btnUnbindService.setOnClickListener(this);
        btnGetCurrentNum.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStartService:
                startService(intentService);    //启动服务
                break;
            case R.id.btnStopService:
                stopService(intentService);     //停止服务
                break;
            case R.id.btnBindService:
                bindService(intentService, this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnbindService:
                unbindService(this);
                echoService = null;
                break;
            case R.id.btnGetCurrentNum:
                if(echoService != null) {
                    System.out.println("当前服务中的Number是："+echoService.getCurrentNum());
                }
                break;
            default:break;
        }
    }

    //启动Service只能实现对Service的启动，但不能与Service进行数据交换
    //而绑定可以实现与Service的数据交换

    //监视连接的状态，当绑定到service时调用
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        System.out.println("service on connection");

        //为什么要这样大费周折来创建EchoService的对象
        //因为Service是有系统控制的，用户没有权限创建Service
        //用户只能通过startService或bindService来请求系统创建Service

        //局部变量service就是EchoService中的方法Ibind返回的IBinder类型的变量
        echoService = ((EchoService.EchoServiceBinder)service).getService();
    }

    //当service断开时调用
    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
