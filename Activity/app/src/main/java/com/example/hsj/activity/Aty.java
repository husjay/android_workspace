package com.example.hsj.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Aty extends ActionBarActivity {

    private Button btnCloseAty;
    private TextView tvOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty);

        btnCloseAty = (Button) findViewById(R.id.btnCloseAty);
        btnCloseAty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.putExtra("result","Hello Main_Activity!");
                setResult(0,i);

                finish();
            }
        });

        tvOut = (TextView) findViewById(R.id.tvOut);
        //取得与当前Activity相关联的Activity的Intent
        //tvOut.setText(getIntent().getStringExtra("txt"));

        //通过Bundle获取传递过来的数据
        Bundle data = getIntent().getExtras();
        String txt = data.getString("txt");
        tvOut.setText(txt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_aty, menu);
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
