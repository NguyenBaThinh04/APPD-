package com.example.hitcapp;

import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    TextView txtName, txtPrice, txtDesc;
    ImageView imgProduct;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtDesc = findViewById(R.id.txtDesc);
        imgProduct = findViewById(R.id.imgProduct);
        btnAdd = findViewById(R.id.btnAdd);

        // ================= CHECK INTENT =================
        if (getIntent() == null) {
            Toast.makeText(this, "Không có dữ liệu sản phẩm", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // ================= GET DATA =================
        String name = getIntent().getStringExtra("name");
        int price = getIntent().getIntExtra("price", 0);
        String desc = getIntent().getStringExtra("desc");
        int image = getIntent().getIntExtra("image", 0);

        // ================= SAFE DISPLAY =================
        txtName.setText(name != null ? name : "Không có tên sản phẩm");

        txtPrice.setText(String.format("%,d VND", price));

        txtDesc.setText(desc != null ? desc : "Không có mô tả");

        if (image != 0) {
            imgProduct.setImageResource(image);
        } else {
            imgProduct.setImageResource(android.R.drawable.ic_menu_gallery);
        }

        // ================= ADD TO CART =================
        btnAdd.setOnClickListener(v -> {

            String finalName = name != null ? name : "";
            String finalDesc = desc != null ? desc : "";

            Cart.listCart.add(new Dt(finalName, price, finalDesc, image));

            Toast.makeText(this, "Đã thêm vào giỏ", Toast.LENGTH_SHORT).show();
        });
    }
}