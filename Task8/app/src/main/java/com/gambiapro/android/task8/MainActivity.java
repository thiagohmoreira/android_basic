package com.gambiapro.android.task8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final String KEY = "key";

    TextView txtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtInfo = (TextView) findViewById(R.id.main_txtInfo);

        txtInfo.setText("MainActivity.onCreate");
        if (savedInstanceState == null) {
            txtInfo.append("\nFirst run");
        } else {
            txtInfo.append("\n" + savedInstanceState.getString(KEY));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(KEY, "Saved instance value");
    }

    @Override
    protected void onResume() {
        super.onResume();
        txtInfo.append("\nMainActivity.onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        txtInfo.append("\nMainActivity.onPause");

        if (isFinishing()) {
            Toast.makeText(this, "Asta la vista baby...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "I'll be back!", Toast.LENGTH_SHORT).show();

        super.onDestroy();
    }
}
