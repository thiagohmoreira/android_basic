package com.gambiapro.android.task5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ToStringListAdapter<T> extends BaseAdapter {
    private final List<T> data;
    private final Context context;

    public ToStringListAdapter(final List<T> data, final Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        T item = getItem(position);
        View view = LayoutInflater.from(context).inflate(R.layout.simple_list_row, parent, false);

        TextView label = (TextView) view.findViewById(R.id.listRow_label);
        label.setText(item.toString());

        return view;
    }
}
