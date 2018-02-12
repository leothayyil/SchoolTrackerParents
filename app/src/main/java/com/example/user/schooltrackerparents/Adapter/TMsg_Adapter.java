package com.example.user.schooltrackerparents.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.user.schooltrackerparents.R;
import com.example.user.schooltrackerparents.SentReplyActivity;
import com.example.user.schooltrackerparents.TeacherMsgsActivity;

import java.util.HashMap;
import java.util.List;


public class TMsg_Adapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> _listDataHeader;
    private HashMap<String, List<String>> _listDataChild;
    private List<String> _listDataId;

    public TMsg_Adapter(TeacherMsgsActivity teach_sentBoxActivity, List<String> listDataHeader,
                        HashMap<String, List<String>> listDataChild, List<String> listDataId) {
        this._context=teach_sentBoxActivity;
        this._listDataHeader=listDataHeader;
        this._listDataChild=listDataChild;
        this._listDataId=listDataId;
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
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String headerTitle=(String)getGroup(groupPosition);
        if (convertView==null){
            LayoutInflater inflater=(LayoutInflater)this._context.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.gm_header,null);
        }
        TextView lblListHeader =  convertView
                .findViewById(R.id.textViewHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {

        final String childTitle=(String)getChild(groupPosition,childPosition);
        final String id=_listDataId.get(childPosition);
        if (convertView==null){
            LayoutInflater inflater=(LayoutInflater)this._context.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.gm_child,null);
        }
        TextView lblListChild = (TextView) convertView
                .findViewById(R.id.textViewChild);
        lblListChild.setTypeface(null, Typeface.BOLD);
        lblListChild.setText("Message : " +childTitle);
        lblListChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4=new Intent(_context,SentReplyActivity.class);
                intent4.putExtra("msg_id",id+"");
                _context.startActivity(intent4);
            }
        });
        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
