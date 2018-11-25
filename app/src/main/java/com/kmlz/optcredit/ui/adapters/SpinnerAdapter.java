package com.kmlz.optcredit.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kmlz.optcredit.R;
import com.kmlz.optcredit.network.responses.CategoryResponse;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<CategoryResponse> {

    List<CategoryResponse> categoryList;

    private LayoutInflater mInflater;

    public SpinnerAdapter(Context context,
                          List<CategoryResponse> categoryList,
                          Activity activity) {
        super(context, R.layout.category_spinner_layout);
        this.categoryList = categoryList;
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        View row = mInflater.inflate(R.layout.category_spinner_layout, parent, false);
        TextView label = (TextView) row.findViewById(R.id.categoryTextView);
        label.setText(categoryList.get(position).getCatName());


        //Set meta data here and later we can access these values from OnItemSelected Event Of Spinner
        row.setTag(R.string.meta_position, categoryList.get(position).getCatId());
        row.setTag(R.string.meta_title, categoryList.get(position).getCatName());

        return row;
    }

}
