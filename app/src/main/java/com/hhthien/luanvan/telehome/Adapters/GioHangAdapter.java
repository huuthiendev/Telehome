package com.hhthien.luanvan.telehome.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhthien.luanvan.telehome.Common.ModelGioHang;
import com.hhthien.luanvan.telehome.Models.GioHang;
import com.hhthien.luanvan.telehome.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by HUUTHIEN on 7/1/2017.
 */

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.GioHangViewHolder> {
    Context context;
    List<GioHang> list;
    GioHang giohang;
    TextView tvTongTienGH;
    DecimalFormat formatter = new DecimalFormat("#,###,###");
    ModelGioHang modelGioHang;

    public GioHangAdapter(Context context, List<GioHang> list) {
        this.context = context;
        this.list = list;
        modelGioHang = new ModelGioHang();
        modelGioHang.MoKetNoiSQL(context);
    }

    public GioHangAdapter(Context context, List<GioHang> list, TextView tvTongTienGH) {
        this.context = context;
        this.list = list;
        this.tvTongTienGH = tvTongTienGH;
        modelGioHang = new ModelGioHang();
        modelGioHang.MoKetNoiSQL(context);
    }

    @Override
    public GioHangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_gio_hang, parent, false);

        GioHangViewHolder viewHolder = new GioHangViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final GioHangViewHolder holder, final int position) {
        giohang = list.get(position);
        holder.tvTenSPGH.setText(giohang.getTensp());
        holder.tvGiaSPGH.setText(formatter.format(giohang.getGia()) + " đ");
        holder.tvSoLuongGH.setText(String.valueOf(giohang.getSoluong()));
        Picasso.with(context).load(giohang.getHinhsp()).into(holder.imgHinhSPGH);

        holder.btnCongGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(holder.tvSoLuongGH.getText().toString());
                soluong ++;
                modelGioHang.CapNhatSoLuongSanPhamGioHang(list.get(position).getMasp(), soluong);
                CapNhatTongTien(position, soluong);
                holder.tvSoLuongGH.setText(String.valueOf(soluong));
            }
        });

        holder.btnTruGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(holder.tvSoLuongGH.getText().toString());
                if (soluong > 1) {
                    soluong--;
                    modelGioHang.CapNhatSoLuongSanPhamGioHang(list.get(position).getMasp(), soluong);
                    CapNhatTongTien(position, soluong);
                    holder.tvSoLuongGH.setText(String.valueOf(soluong));
                }
            }
        });

        holder.btnXoaSPGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Thông báo")
                        .setMessage("Bạn có chắc chắn muốn xóa sản phẩm khỏi giỏ hàng?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                modelGioHang.XoaSanPhamTrongGioHang(list.get(position).getMasp());
                                list.remove(position);
                                int tongtien = 0;
                                for (GioHang item : list) {
                                    tongtien += item.getTongtien();
                                }
                                tvTongTienGH.setText("Tổng tiền: " + formatter.format(tongtien) + " đ");
                                notifyDataSetChanged();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
    }

    @Override
    public int getItemCount() {return list.size();}

    public class GioHangViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhSPGH;
        TextView tvTenSPGH, tvGiaSPGH, tvSoLuongGH;
        ImageButton btnXoaSPGH;
        ImageView btnTruGH, btnCongGH;


        public GioHangViewHolder(View itemView) {
            super(itemView);
            imgHinhSPGH = (ImageView) itemView.findViewById(R.id.imgHinhSPGH);
            tvTenSPGH = (TextView) itemView.findViewById(R.id.tvTenSPGH);
            tvGiaSPGH = (TextView) itemView.findViewById(R.id.tvGiaSPGH);
            tvSoLuongGH = (TextView) itemView.findViewById(R.id.tvSoLuongGH);
            btnXoaSPGH = (ImageButton) itemView.findViewById(R.id.btnXoaSPGH);
            btnTruGH = (ImageView) itemView.findViewById(R.id.btnTruGH);
            btnCongGH = (ImageView) itemView.findViewById(R.id.btnCongGH);

            int tongtien = 0;
            for (GioHang item : list) {
                tongtien += item.getTongtien();
            }
            tvTongTienGH.setText("Tổng tiền: " + formatter.format(tongtien) + " đ");
        }
    }

    private void CapNhatTongTien(int position, int soluong) {
        GioHang giohang = list.get(position);
        giohang.setSoluong(soluong);
        giohang.setTongtien(soluong * giohang.getGia());
        int tongtien = 0;
        for (GioHang item : list) {
            tongtien += item.getTongtien();
        }
        tvTongTienGH.setText("Tổng tiền: " + formatter.format(tongtien) + " đ");
    }
}
