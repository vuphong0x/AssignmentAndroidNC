package com.phong.hotrohoctap.News;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.phong.hotrohoctap.R;
import com.squareup.picasso.Picasso;


import java.util.List;

public class AdapterDocTin extends ArrayAdapter<DocTin> {

    public AdapterDocTin(Context context, int resource, List<DocTin> items) {
        super(context, resource,items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.itemdt, null);
        }
        DocTin p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView txt = (TextView) view.findViewById(R.id.textview);
            txt.setText(p.Title);

            ImageView imageView=(ImageView) view.findViewById(R.id.imageIteam);
            Picasso.with(getContext()).load(p.Image).into(imageView);

        }
        return view;
    }
}
