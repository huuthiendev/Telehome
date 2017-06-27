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

import com.hhthien.luanvan.telehome.Activities.ChiTietSanPhamActivity;
import com.hhthien.luanvan.telehome.Models.SanPham;
import com.hhthien.luanvan.telehome.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by HUUTHIEN on 6/7/2017.
 */

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder> {

    Context context;
    List<SanPham> list;
    SanPham sanpham;
    int itemLayout;

    public SanPhamAdapter(Context context, List<SanPham> list, int itemLayout) {
        this.context = context;
        this.list = list;
        this.itemLayout = itemLayout;
    }

    @Override
    public SanPhamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(itemLayout, parent, false);

        SanPhamViewHolder viewHolder = new SanPhamViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SanPhamViewHolder holder, final int position) {
        sanpham = list.get(position);
        holder.tvTenSP.setText(sanpham.getTensp());
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        holder.tvGiaSP.setText(formatter.format(sanpham.getGia()) + " VNĐ");
        Picasso.with(context).load(sanpham.getHinhsp()).into(holder.imgSanPham);
        final Activity activity = (Activity) context;
        holder.cvSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                intent.putExtra("sanpham", list.get(position));
                context.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    @Override
    public int getItemCount() {return list.size();}

    public class SanPhamViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenSP, tvGiaSP;
        CardView cvSanPham;
        ImageView imgSanPham;

        public SanPhamViewHolder(View itemView) {
            super(itemView);
            tvTenSP = (TextView) itemView.findViewById(R.id.tvTenSP);
            tvGiaSP = (TextView) itemView.findViewById(R.id.tvGiaSP);
            cvSanPham = (CardView) itemView.findViewById(R.id.cvSanPham);
            imgSanPham = (ImageView) itemView.findViewById(R.id.imgSanPham);
        }
    }
}
