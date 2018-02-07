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


    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
