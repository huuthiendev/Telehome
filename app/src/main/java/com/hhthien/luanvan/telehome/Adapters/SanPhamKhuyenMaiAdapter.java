package com.hhthien.luanvan.telehome.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
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
 * Created by HUUTHIEN on 7/7/2017.
 */

public class SanPhamKhuyenMaiAdapter extends RecyclerView.Adapter<SanPhamKhuyenMaiAdapter.SanPhamKhuyenMaiViewHolder> {

    Context context;
    List<SanPham> list;
    SanPham sanpham;

    public SanPhamKhuyenMaiAdapter(Context context, List<SanPham> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public SanPhamKhuyenMaiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_san_pham_khuyen_mai, parent, false);

        SanPhamKhuyenMaiViewHolder viewHolder = new SanPhamKhuyenMaiViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SanPhamKhuyenMaiViewHolder holder, final int position) {
        sanpham = list.get(position);
        holder.tvTenSPKM.setText(sanpham.getTensp());
        holder.tvPhanTramKM.setText(sanpham.getPhantramkm() + "%");
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        holder.tvGiaSPKM.setText(formatter.format(sanpham.getGia()) + " đ");
        holder.tvGiaGocSPKM.setText(formatter.format(sanpham.getGiagoc()) + " đ");
        holder.tvGiaGocSPKM.setPaintFlags(holder.tvGiaGocSPKM.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Picasso.with(context).load(sanpham.getHinhsp()).into(holder.imgSanPhamKM);
        final Activity activity = (Activity) context;
        holder.cvSanPhamKM.setOnClickListener(new View.OnClickListener() {
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

    public class SanPhamKhuyenMaiViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenSPKM, tvGiaSPKM, tvGiaGocSPKM, tvPhanTramKM;
        CardView cvSanPhamKM;
        ImageView imgSanPhamKM;

        public SanPhamKhuyenMaiViewHolder(View itemView) {
            super(itemView);
            tvTenSPKM = (TextView) itemView.findViewById(R.id.tvTenSPKM);
            tvGiaSPKM = (TextView) itemView.findViewById(R.id.tvGiaSPKM);
            tvGiaGocSPKM = (TextView) itemView.findViewById(R.id.tvGiaGocSPKM);
            tvPhanTramKM = (TextView) itemView.findViewById(R.id.tvPhanTramKM);
            cvSanPhamKM = (CardView) itemView.findViewById(R.id.cvSanPhamKM);
            imgSanPhamKM = (ImageView) itemView.findViewById(R.id.imgSanPhamKM);
        }
    }
}
