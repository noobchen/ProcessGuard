package com.cyc.processguard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.cyc.processguard.service.Service1;
import com.cyc.processguard.service.Service2;

public class MyActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    Button startService = null;
    Button finishActivity = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        startService = (Button) findViewById(R.id.startService);
        finishActivity = (Button) findViewById(R.id.finishActivity);

        startService.setOnClickListener(this);
        finishActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int buttonId = v.getId();

        switch (buttonId) {
            case R.id.startService:
                startServiceIntent();
                break;

            case R.id.finishActivity:
                finishCurrentActivity();
                break;
        }
    }

    private void finishCurrentActivity() {
        MyActivity.this.finish();
    }

    private void startServiceIntent() {
        Intent service1 = new Intent(MyActivity.this, Service1.class);
        Intent service2 = new Intent(MyActivity.this, Service2.class);

        MyActivity.this.startService(service1);
        MyActivity.this.startService(service2);

    }
}
