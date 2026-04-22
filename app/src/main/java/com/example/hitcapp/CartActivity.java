package com.example.hitcapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageButton btnBack;
    Button btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recyclerView);
        btnBack = findViewById(R.id.btnBack);
        btnCheckout = findViewById(R.id.btnCheckout);

        // QUAY LẠI
        btnBack.setOnClickListener(v -> finish());

        // THANH TOÁN
        btnCheckout.setOnClickListener(v -> {

            if (Cart.listCart == null || Cart.listCart.isEmpty()) {
                Toast.makeText(this, "Giỏ hàng trống", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
            startActivity(intent);
        });

        DTAdapter adapter = new DTAdapter(this, Cart.listCart, true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}