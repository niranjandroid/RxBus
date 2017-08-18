package com.niranjan.rxbussample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import com.niranjan.rxbus.RxBus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        EditText editText = (EditText) findViewById(R.id.editText);
        RxBus.getDefault().register(this);
        RxBus.getDefault().subscribe(object -> {
            TestModel testModel = (TestModel) object;
            Log.d("Message", testModel.getMessage());
        }, "Niranjan");

        findViewById(R.id.publish).setOnClickListener(view -> {
            RxBus.getDefault().publish(new TestModel(editText.getText().toString()));
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getDefault().unRegister(this);
    }
}