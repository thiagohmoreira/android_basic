package com.gambiapro.android.task9;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtName;
    EditText txtResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Menu test");
        actionBar.setDisplayHomeAsUpEnabled(true);

        txtName = (EditText) findViewById(R.id.main_txtName);
        txtResponse = (EditText) findViewById(R.id.main_txtResponse);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void onBtnGoClick(View view) {
        goToSecondActivity();
    }

    private void goToSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("name", txtName.getText().toString());
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeCustomAnimation(this, R.anim.fade_in, R.anim.fade_out);
        ActivityCompat.startActivityForResult(this, intent, 1, optionsCompat.toBundle());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            txtResponse.setText(data.getStringExtra("return"));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mainMenu_about:
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setTitle("About");
                alertBuilder.setIcon(android.R.drawable.ic_dialog_info);
                alertBuilder.setMessage("This is a great demo application!");
                alertBuilder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertBuilder.show();
                break;
            case android.R.id.home:
                Toast.makeText(this, "Go Home!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.mainMenu_next:
                goToSecondActivity();
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Quit confirmation");
        alertBuilder.setIcon(android.R.drawable.ic_dialog_alert);
        alertBuilder.setMessage("Are you sure you want to exit the application?");
        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertBuilder.setPositiveButton("Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertBuilder.show();
    }
}
