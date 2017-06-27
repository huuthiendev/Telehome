package com.hhthien.luanvan.telehome.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hhthien.luanvan.telehome.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by HUUTHIEN on 6/22/2017.
 */

public class SlideSanPhamAdapter extends PagerAdapter {
    private List<String> list;
    private LayoutInflater inflater;
    private Context context;

    public SlideSanPhamAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout = inflater.inflate(R.layout.slide, view, false);
        ImageView myImage = (ImageView) myImageLayout.findViewById(R.id.image);
        Picasso.with(context).load(list.get(position).toString()).into(myImage);
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }
}
