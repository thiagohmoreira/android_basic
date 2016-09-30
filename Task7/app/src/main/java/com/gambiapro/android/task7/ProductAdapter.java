package com.gambiapro.android.task7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gambiapro.android.task7.domain.Product;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private final Context context;
    private List<Product> list;

    public ProductAdapter(Context context) {
        this.context = context;
        ProductDataStore dataStore = ProductDataStore.getInstance();
        dataStore.setContext(context);
        list = dataStore.getProducts();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Product getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Product product = getItem(position);
        View view = LayoutInflater.from(context).inflate(R.layout.product_list_item, parent, false);

        TextView name = (TextView) view.findViewById(R.id.productListItem_name);
        name.setText(product.getName());

        TextView price = (TextView) view.findViewById(R.id.productListItem_price);
        price.setText(String.format("$ %.2f", product.getPrice()));

        TextView stock = (TextView) view.findViewById(R.id.productListItem_stock);
        stock.setText(String.valueOf(product.getStock()));

        return view;
    }
}
