package com.hhthien.luanvan.telehome.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhthien.luanvan.telehome.Common.PassDataChonLoaiSanPham;
import com.hhthien.luanvan.telehome.Models.LoaiSanPham;
import com.hhthien.luanvan.telehome.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by HUUTHIEN on 6/8/2017.
 */

public class ChonLoaiSanPhamAdapter extends RecyclerView.Adapter<ChonLoaiSanPhamAdapter.LoaiSanPhamViewHolder> {

    Context context;
    List<LoaiSanPham> list;
    LoaiSanPham loaisanpham;
    PassDataChonLoaiSanPham passDataChonLoaiSanPham;

    public ChonLoaiSanPhamAdapter(Context context, List<LoaiSanPham> list, PassDataChonLoaiSanPham passDataChonLoaiSanPham) {
        this.context = context;
        this.list = list;
        this.passDataChonLoaiSanPham = passDataChonLoaiSanPham;
    }

    @Override
    public LoaiSanPhamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_chon_loai_san_pham, parent, false);

        LoaiSanPhamViewHolder viewHolder = new LoaiSanPhamViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LoaiSanPhamViewHolder holder, final int position) {
        loaisanpham = list.get(position);
        holder.tvTenLoaiSP.setText(loaisanpham.getTenloaisp());
        Picasso.with(context).load(loaisanpham.getHinhloaisp()).into(holder.imgHinhLoaiSP);

        holder.linearChonLoaiSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passDataChonLoaiSanPham.sendLoaiSanPham(list.get(position).getId(), list.get(position).getTenloaisp());
            }
        });
    }

    @Override
    public int getItemCount() {return list.size();}

    public class LoaiSanPhamViewHolder extends RecyclerView.ViewHolder {

        TextView tvTenLoaiSP;
        ImageView imgHinhLoaiSP;
        LinearLayout linearChonLoaiSanPham;

        public LoaiSanPhamViewHolder(View itemView) {
            super(itemView);
            tvTenLoaiSP = (TextView) itemView.findViewById(R.id.tvTenLoaiSP);
            imgHinhLoaiSP = (ImageView) itemView.findViewById(R.id.imgHinhLoaiSP);
            linearChonLoaiSanPham = (LinearLayout) itemView.findViewById(R.id.linearChonLoaiSanPham);
        }
    }
}
