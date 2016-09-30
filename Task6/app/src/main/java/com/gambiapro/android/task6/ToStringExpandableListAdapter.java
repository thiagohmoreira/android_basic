package com.gambiapro.android.task6;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class ToStringExpandableListAdapter<K, V> extends BaseExpandableListAdapter {
    private final Context context;
    private final List<K> groups;
    private final Map<K, List<V>> data;

    public ToStringExpandableListAdapter(Context context, List<K> groups, Map<K, List<V>> data) {
        this.context = context;
        this.groups = groups;
        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        K group = groups.get(groupPosition);
        return data.get(group).size();
    }

    @Override
    public K getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public V getChild(int groupPosition, int childPosition) {
        return data.get(getGroup(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        //@TODO: Check if child ids collisions are a problem
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        K group = getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }

        TextView label = (TextView) convertView.findViewById(R.id.listGroup_label);
        label.setTypeface(null, Typeface.BOLD); //Not too good, just to show it is possible
        label.setText(group.toString());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        V item = getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }

        TextView label = (TextView) convertView.findViewById(R.id.lisItem_label);
        label.setText(item.toString());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
