package com.example.user.schooltrackerparents.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.user.schooltrackerparents.GeneralMsgsActivity;
import com.example.user.schooltrackerparents.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by USER on 07-02-2018.
 */

public class ExpandableAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> titleArray;
    private HashMap<String,ArrayList<String>>childArray;
    private LayoutInflater inflater;

    public ExpandableAdapter(GeneralMsgsActivity generalMsgsActivity,
                             ArrayList<String> headerArrayList, HashMap<String, ArrayList<String>> childList) {
    }

    @Override
    public int getGroupCount() {
        return this.titleArray.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.childArray.get(this.titleArray.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.titleArray.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle=(String)getGroup(groupPosition);
        if (convertView==null){
            convertView=inflater.inflate(R.layout.gm_header,null);
        }
        TextView textHeader=convertView.findViewById(R.id.textViewHeader);
        textHeader.setText(headerTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {

        final String childText=(String)getChild(groupPosition,childPosition);
        if (convertView==null){
            convertView=inflater.inflate(R.layout.gm_child,null);
        }
        TextView textView=convertView.findViewById(R.id.textViewChild);
        textView.setText(childText);
        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
