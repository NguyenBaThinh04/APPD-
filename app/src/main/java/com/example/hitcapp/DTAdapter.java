package com.example.hitcapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DTAdapter extends RecyclerView.Adapter<DTAdapter.ViewHolder> {

    Context context;
    List<Dt> list;
    boolean isCart;

    public DTAdapter(Context context, List<Dt> list, boolean isCart) {
        this.context = context;
        this.list = list;
        this.isCart = isCart;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_dt, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // ⚠️ LẤY POSITION AN TOÀN
        int pos = holder.getAdapterPosition();
        if (pos == RecyclerView.NO_POSITION) return;

        Dt c = list.get(pos);

        holder.txtName.setText(c.name);
        holder.txtPrice.setText("Giá: " + c.price + " VND");
        holder.txtDesc.setText(c.desc);

        // tránh crash nếu image lỗi
        if (c.image != 0) {
            holder.imgProduct.setImageResource(c.image);
        }

        // 👉 CLICK ITEM (mở detail)
        holder.itemView.setOnClickListener(v -> {
            int p = holder.getAdapterPosition();
            if (p == RecyclerView.NO_POSITION) return;

            Dt item = list.get(p);

            if (!isCart) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("name", item.name);
                i.putExtra("price", item.price);
                i.putExtra("desc", item.desc);
                i.putExtra("image", item.image);
                context.startActivity(i);
            }
        });

        if (isCart) {
            // 👉 TRONG GIỎ HÀNG
            holder.btnAddCart.setVisibility(View.GONE);
            holder.btnDelete.setVisibility(View.VISIBLE);

            holder.btnDelete.setOnClickListener(v -> {
                int p = holder.getAdapterPosition();
                if (p == RecyclerView.NO_POSITION) return;

                Cart.listCart.remove(p);
                notifyItemRemoved(p);
                Toast.makeText(context, "Đã xóa", Toast.LENGTH_SHORT).show();
            });

        } else {
            // 👉 TRANG CHÍNH
            holder.btnAddCart.setVisibility(View.VISIBLE);
            holder.btnDelete.setVisibility(View.GONE);

            holder.btnAddCart.setOnClickListener(v -> {
                Cart.listCart.add(c);
                Toast.makeText(context, "Đã thêm " + c.name, Toast.LENGTH_SHORT).show();
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // ================= VIEW HOLDER =================
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtPrice, txtDesc;
        ImageView imgProduct;
        Button btnAddCart, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtDesc = itemView.findViewById(R.id.txtDesc);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            btnAddCart = itemView.findViewById(R.id.btnAddCart);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}