package com.gambiapro.android.task7;

import android.content.Context;

import com.gambiapro.android.task7.database.BarDatabase;
import com.gambiapro.android.task7.domain.Product;

import java.util.List;

public class ProductDataStore {
    private static ProductDataStore instance;

    private Context context;
    private BarDatabase database;
    private List<Product> list;

    private ProductDataStore() {

    }

    public static ProductDataStore getInstance() {
        if (instance == null) {
            instance = new ProductDataStore();
        }

        return instance;
    }

    public void setContext(Context context) {
        this.context = context;
        database = new BarDatabase(context);
        list = database.readAll();
    }

    public List<Product> getProducts() {
        return list;
    }

    public Product getProduct(int position) {
        return list.get(position);
    }

    public void create(Product product) {
        database.create(product);
        list.add(product);
    }

    public void update(int position, Product product) {
        database.update(product);
        list.set(position, product);
    }

    public void delete(int position) {
        database.delete(getProduct(position));
        list.remove(position);
    }
}
