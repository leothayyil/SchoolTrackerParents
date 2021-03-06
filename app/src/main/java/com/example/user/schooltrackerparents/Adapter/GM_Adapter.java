package com.example.user.schooltrackerparents.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.schooltrackerparents.GeneralMsgsActivity;
import com.example.user.schooltrackerparents.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by USER on 07-02-2018.
 */

public class GM_Adapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> _listDataHeader;
    int groupId;
    private HashMap<String, List<String>> _listDataChild;

    public GM_Adapter(GeneralMsgsActivity teach_sentBoxActivity, List<String> listDataHeader,
                      HashMap<String, List<String>> listDataChild) {
        this._context=teach_sentBoxActivity;
        this._listDataHeader=listDataHeader;
        this._listDataChild=listDataChild;
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosition);
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
         groupId= (int) getGroupId(groupPosition);
        if (convertView==null){
            LayoutInflater inflater=(LayoutInflater)this._context.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.gm_header,null);
        }
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.textViewHeader);

        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {

        String headerTitle=(String)getChild(groupId,childPosition);
        if (convertView==null){
            LayoutInflater inflater=(LayoutInflater)this._context.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.gm_child,null);

        }
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.textViewChild);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
