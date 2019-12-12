package com.example.handle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView=(TextView)findViewById(R.id.textView);
        final TextView textView1=(TextView)findViewById(R.id.textView3);
        final Handler handler=new Handler(){
            public void handleMessage(Message msg){
                textView1.setText((String)msg.obj);
                textView.setText(msg.arg1+"\n"+msg.arg2);
                //textView1.setText((String)msg.obj);
            }

        };
        final Runnable runnable=new Runnable() {
            @Override
            public void run() {
                int a=0;int s=0;String str="线程测试";
                while(a<=50){
                    Message message=new Message();
                    message.obj=str;
                    message.arg1=a;
                    message.arg2=s;
                    handler.sendMessage(message);
                    a+=10;
                    s+=1;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        /*final  Runnable runnable1=new Runnable() {
            @Override
            public void run() {
                String str="线程测试";
                Message message=new Message();
                message.obj=str;
                handler.sendMessage(message);
            }
        };*/
        Button bn=(Button)findViewById(R.id.button);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread work=new Thread(null,runnable,"WorkTread");
                //Thread work1=new Thread(null,runnable1,"WorkThread1");
                //work1.start();
                work.start();
            }
        });
    }
}
