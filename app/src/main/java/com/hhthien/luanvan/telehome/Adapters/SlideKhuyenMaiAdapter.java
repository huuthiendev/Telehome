package com.hhthien.luanvan.telehome.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hhthien.luanvan.telehome.Activities.KhuyenMaiActivity;
import com.hhthien.luanvan.telehome.Models.KhuyenMai;
import com.hhthien.luanvan.telehome.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by HUUTHIEN on 6/9/2017.
 */

public class SlideKhuyenMaiAdapter extends PagerAdapter {
    private List<KhuyenMai> list;
    private LayoutInflater inflater;
    private Context context;

    public SlideKhuyenMaiAdapter(Context context, List<KhuyenMai> list) {
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
    public Object instantiateItem(ViewGroup view, final int position) {
        View myImageLayout = inflater.inflate(R.layout.slide, view, false);
        ImageView myImage = (ImageView) myImageLayout.findViewById(R.id.image);
        Picasso.with(context).load(list.get(position).getHinhkm()).into(myImage);
        final Activity activity = (Activity) context;
        myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KhuyenMaiActivity.class);
                intent.putExtra("makm", list.get(position).getId());
                context.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
