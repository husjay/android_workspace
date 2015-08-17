package com.example.hsj.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Activity extends ActionBarActivity {

    private Button btnOpenAty;
    private TextView tvOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOut = (TextView) findViewById(R.id.tvOut);

        btnOpenAty = (Button) findViewById(R.id.btnOpenAty);
        btnOpenAty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity.this, Aty.class);

                //Activity之间传递数据方式一
                //i.putExtra("txt", "Hello Aty!");

                //通过Bundle可以传递复杂的数据类型
                Bundle data = new Bundle();
                data.putString("txt","Hello Aty");
                i.putExtras(data);

                //启动Intent发送数据
                //startActivity(i);

                //启动接收数据的方法
                startActivityForResult(i,0);
            }
        });
    }

    //被启动的Activity有返回值时会启动这个方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data != null) {
            String result = data.getStringExtra("result");
            tvOut.setText(result);
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_, menu);
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
}
