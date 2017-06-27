package com.hhthien.luanvan.telehome.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhthien.luanvan.telehome.Common.PassData;
import com.hhthien.luanvan.telehome.Models.ThuongHieu;
import com.hhthien.luanvan.telehome.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by HUUTHIEN on 6/18/2017.
 */

public class ThuongHieuAdapter extends RecyclerView.Adapter<ThuongHieuAdapter.ThuongHieuViewHolder> {
    Context context;
    List<ThuongHieu> list;
    ThuongHieu thuonghieu;
    PassData passData;

    public ThuongHieuAdapter(Context context, List<ThuongHieu> list, PassData passData) {
        this.context = context;
        this.list = list;
        this.passData = passData;
    }

    @Override
    public ThuongHieuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_thuong_hieu, parent, false);

        ThuongHieuViewHolder viewHolder = new ThuongHieuViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ThuongHieuViewHolder holder, final int position) {
        thuonghieu = list.get(position);
        holder.tvTenThuongHieu.setText(thuonghieu.getTenth());
        Picasso.with(context).load(thuonghieu.getHinhth()).into(holder.imgThuongHieu);
        holder.cvThuongHieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passData.sendTH(list.get(position).getId(), list.get(position).getTenth());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ThuongHieuViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenThuongHieu;
        ImageView imgThuongHieu;
        CardView cvThuongHieu;

        public ThuongHieuViewHolder(View itemView) {
            super(itemView);
            tvTenThuongHieu = (TextView) itemView.findViewById(R.id.tvTenThuongHieu);
            imgThuongHieu = (ImageView) itemView.findViewById(R.id.imgThuongHieu);
            cvThuongHieu = (CardView) itemView.findViewById(R.id.cvThuongHieu);
        }
    }
}
