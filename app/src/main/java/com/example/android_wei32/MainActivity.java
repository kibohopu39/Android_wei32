package com.example.android_wei32;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private ExpandableListView explist;//可以有收放item的效果
    private LinkedList<HashMap<String,String>> groups=new LinkedList<>();//父item
    private LinkedList<HashMap<String,String>> items1=new LinkedList<>();
    private LinkedList<HashMap<String,String>> items2=new LinkedList<>();
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        explist=findViewById(R.id.explist);
        init();
    }

    /////////處理資料//////////
    private void init(){
        HashMap<String,String> gitem1=new HashMap<>();
        gitem1.put("group","訂單");
        groups.add(gitem1);
        HashMap<String,String> gitem2=new HashMap<>();
        gitem1.put("group","報價");
        groups.add(gitem2);

        for (int i=0;i<10;i++){
            HashMap<String,String> row=new HashMap<>();
            row.put("titile","item1"+i);//
            items1.add(row);
        }
        for (int i=0;i<40;i++){
            HashMap<String,String> row=new HashMap<>();
            row.put("titile","item2"+i);
            items2.add(row);
        }
        myAdapter=new MyAdapter();
        explist.setAdapter(myAdapter);
        explist.expandGroup(0);
        explist.expandGroup(1);

    }










    private class MyAdapter extends BaseExpandableListAdapter{
        private LayoutInflater inflater;
        MyAdapter(){
            inflater=getLayoutInflater();
        }
        @Override
        public int getGroupCount() {
            return groups.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            if (groupPosition==0){
                return items1.size();
            }else{
                return items2.size();
            }
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groups.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            if (groupPosition==0){
                return items1.get(childPosition);
            }else{
                return items2.get(childPosition);
            }
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return groupPosition*1000+childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View view=inflater.inflate(R.layout.title,parent,false);
            TextView title=view.findViewById(R.id.title_text);
            title.setText(groups.get(groupPosition).get("group"));

            return view;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View view=null;
            TextView content=null;
            if (groupPosition==0){
                view=inflater.inflate(R.layout.child,parent,false);
                content=view.findViewById(R.id.child_text);
                content.setText(items1.get(childPosition).get("title"));
                return view;
            }else{
                view=inflater.inflate(R.layout.child_2,parent,false);
                content=view.findViewById(R.id.child2_text);
                content.setText(items2.get(childPosition).get("title"));
            return view;
        }
    }
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }}
