package com.gambiapro.android.task7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.gambiapro.android.task7.domain.Product;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProductAdapter adapter = new ProductAdapter(this);
        ListView productList = (ListView) findViewById(R.id.main_productList);
        productList.setAdapter(adapter);
    }

    public void onBtnAddClick(View view) {
        Random random = new Random();

        Product product = new Product(-1L, "Jack Daniels",
                random.nextDouble() * 25,
                random.nextInt(350)
        );
        ProductDataStore.getInstance().create(product);
        view.invalidate();
    }
}
