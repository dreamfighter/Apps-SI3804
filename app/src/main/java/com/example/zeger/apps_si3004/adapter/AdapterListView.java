package com.example.zeger.apps_si3004.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.zeger.apps_si3004.R;
import com.example.zeger.apps_si3004.entity.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeger on 18/03/17.
 */

public class AdapterListView extends BaseAdapter{
    private List<Contact> contacts = new ArrayList<Contact>();
    private List<Contact> contactsSearch = new ArrayList<Contact>();
    private Context context;

    public AdapterListView(Context context){
        this.context = context;
    }

    public void refresh(List<Contact> contacts){
        this.contacts.clear();
        this.contactsSearch.clear();

        this.contacts.addAll(contacts);
        this.contactsSearch.addAll(contacts);
        notifyDataSetChanged();
    }

    public void search(String query){
        contactsSearch.clear();
        for (Contact c:contacts){
            if(c.getNama().equals(query)){
                contactsSearch.add(c);
            }
        }
        // fungsinya untuk refresh listview
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return contactsSearch.size();
    }

    @Override
    public Contact getItem(int position) {
        return contactsSearch.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact c = getItem(position);

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, parent, false);

        TextView tvNama = (TextView)view.findViewById(R.id.nama_textview);
        TextView tvNoHp = (TextView)view.findViewById(R.id.nohp_textview);
        ImageView ivAvatar = (ImageView)view.findViewById(R.id.avatar);

        tvNama.setText(c.getNama());
        tvNoHp.setText(c.getNoHp());

        //ivAvatar.setImageBitmap();

        return view;
    }
}
