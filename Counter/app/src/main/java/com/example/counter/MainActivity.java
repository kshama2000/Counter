package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;
import android.os.Message;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tcounter;
    Button start,stop;
    int counter=0;
    boolean running=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tcounter = (TextView)findViewById(R.id.textView);
        start=(Button)findViewById(R.id.bstart);
        stop=(Button)findViewById(R.id.bstop);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }
    public void onClick(View v){
        if(v.equals(start)) {
            counter =0;
            running = true;
            new Mycounter().start();
        }
        else if(v.equals(stop)) {
            running = false;
        }
    }

    Handler handler = new Handler()
    {
        public void handleMessage(Message m)
        {
            tcounter.setText(String.valueOf(m.what));
        }
    };
    class Mycounter extends Thread
    {
        public void run()
        {
            while(running)
            {
                counter++;
                handler.sendEmptyMessage(counter);
                try {
                    Thread.sleep( 1000);
                }
                catch(Exception e){}
            }
        }
    }
}