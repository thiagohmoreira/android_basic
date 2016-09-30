package com.gambiapro.android.task6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    List<String> groups;
    Map<String, List<String>> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Populate the data
        populate();

        //Link the list view
        ToStringExpandableListAdapter<String, String> listAdapter = new ToStringExpandableListAdapter<>(this, groups, items);

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.main_listView);
        listView.setAdapter(listAdapter);
    }

    private void populate() {
        groups = new ArrayList<>();
        items = new HashMap<>();

        //Group 1
        String group1 = "Group 1";
        ArrayList<String> group1Items = new ArrayList<>();
        group1Items.add("Item 1.1");
        group1Items.add("Item 1.2");
        group1Items.add("Item 1.3");
        group1Items.add("Item 1.4");

        groups.add(group1);
        items.put(group1, group1Items);

        //Group 2
        String group2 = "Group 2";
        ArrayList<String> group2Items = new ArrayList<>();
        group2Items.add("Item 2.1");
        group2Items.add("Item 2.2");
        group2Items.add("Item 2.3");

        groups.add(group2);
        items.put(group2, group2Items);

        //Group 3
        String group3 = "Group 3";
        ArrayList<String> group3Items = new ArrayList<>();
        group3Items.add("Item 3.1");
        group3Items.add("Item 3.2");
        group3Items.add("Item 3.3");

        groups.add(group3);
        items.put(group3, group3Items);
    }
}
