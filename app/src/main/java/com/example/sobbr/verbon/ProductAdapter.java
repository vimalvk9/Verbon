package com.example.sobbr.verbon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sobbR on 1/7/2017.
 */

public class ProductAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public ProductAdapter(Context context, int resource){
      super(context, resource);

    }

    public void add(Product object) {
        list.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ProductHolder productHolder;

        if(row==null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.display_product_row,parent,false);
            productHolder = new ProductHolder();
            productHolder.tx_id = (TextView)row.findViewById(R.id.t_id);
            row.setTag(productHolder);

        }
        else{
            productHolder = (ProductHolder)row.getTag();

        }
        Product product = (Product)getItem(position);
        productHolder.tx_id.setText(product.getId());

        return row;
    }

    static class ProductHolder{
        TextView tx_id;
    }
 }
