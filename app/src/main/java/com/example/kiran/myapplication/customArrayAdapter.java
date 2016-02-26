package com.example.kiran.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kiran on 02-12-2015.
 */
public class customArrayAdapter extends BaseAdapter {
    static String[] id, name, email, address, gender;
    Context context;
    LayoutInflater layoutInflater;

    ArrayList<ListData>mylist;
    customArrayAdapter(Context context, ArrayList<ListData> mylist) {
        this.context = context;
        this.mylist=mylist;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mylist.size();
    }

    @Override
    public ListData getItem(int position) {
        return mylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mylist.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
HolderClass holderClass;
        if(convertView==null)
        {
            convertView=layoutInflater.inflate(R.layout.textviews,null,true);
            holderClass=new HolderClass(convertView);
            convertView.setTag(holderClass);
        }
        else {
           holderClass=(HolderClass)convertView.getTag();
        }
        ListData listData=getItem(position);
        holderClass.tv_id.setText(listData.getId());
        holderClass.tv_nam.setText(listData.getName());
        holderClass.tv_email.setText(listData.getEmail());
        holderClass.tv_gender.setText(listData.getGender());
        holderClass.tv_address.setText(listData.getAddress());
        return convertView;
    }
    class HolderClass
    {
        TextView tv_id,tv_nam,tv_email,tv_address,tv_gender;
        public HolderClass(View view)
        {
            tv_id=(TextView)view.findViewById(R.id.TV_id);
            tv_nam=(TextView)view.findViewById(R.id.TV_name);
            tv_email=(TextView)view.findViewById(R.id.TV_Email);
            tv_gender=(TextView)view.findViewById(R.id.TV_gender);
            tv_address=(TextView)view.findViewById(R.id.TV_address);
        }
    }
}
