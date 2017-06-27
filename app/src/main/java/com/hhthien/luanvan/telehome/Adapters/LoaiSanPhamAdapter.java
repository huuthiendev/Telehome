package com.hhthien.luanvan.telehome.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhthien.luanvan.telehome.Activities.LoaiSanPhamActivity;
import com.hhthien.luanvan.telehome.Models.LoaiSanPham;
import com.hhthien.luanvan.telehome.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by HUUTHIEN on 6/8/2017.
 */

public class LoaiSanPhamAdapter extends RecyclerView.Adapter<LoaiSanPhamAdapter.LoaiSanPhamViewHolder> {

    Context context;
    List<LoaiSanPham> list;
    LoaiSanPham loaisanpham;

    public LoaiSanPhamAdapter(Context context, List<LoaiSanPham> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public LoaiSanPhamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_loai_san_pham, parent, false);

        LoaiSanPhamViewHolder viewHolder = new LoaiSanPhamViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LoaiSanPhamViewHolder holder, final int position) {
        loaisanpham = list.get(position);
        holder.tvTenLoaiSP.setText(loaisanpham.getTenloaisp());
        Picasso.with(context).load(loaisanpham.getHinhloaisp()).into(holder.imgHinhLoaiSP);
        final Activity activity = (Activity) context;
        holder.cvLoaiSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoaiSanPhamActivity.class);
                intent.putExtra("maloaisp", list.get(position).getId());
                intent.putExtra("tenloaisp", list.get(position).getTenloaisp());
                context.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    @Override
    public int getItemCount() {return list.size();}

    public class LoaiSanPhamViewHolder extends RecyclerView.ViewHolder {

        TextView tvTenLoaiSP;
        ImageView imgHinhLoaiSP;
        CardView cvLoaiSP;

        public LoaiSanPhamViewHolder(View itemView) {
            super(itemView);
            tvTenLoaiSP = (TextView) itemView.findViewById(R.id.tvTenLoaiSP);
            imgHinhLoaiSP = (ImageView) itemView.findViewById(R.id.imgHinhLoaiSP);
            cvLoaiSP = (CardView) itemView.findViewById(R.id.cvLoaiSP);
        }
    }
}
