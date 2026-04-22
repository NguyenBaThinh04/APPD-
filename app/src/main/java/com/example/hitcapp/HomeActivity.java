package com.example.hitcapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DTAdapter adapter;
    ArrayList<Dt> list;
    ImageView imgCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnLogout = findViewById(R.id.btnLogout);
        imgCart = findViewById(R.id.imgCart);
        recyclerView = findViewById(R.id.recyclerView);

        btnLogout.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        imgCart.setOnClickListener(v -> {
            startActivity(new Intent(this, CartActivity.class));
        });

        list = new ArrayList<>();

        list.add(new Dt("Samsung S24 Ultra", 30000000, "Hiệu năng mạnh", R.drawable.s24ultra));
        list.add(new Dt("iPhone 15 Pro Max", 35000000, "Cao cấp", R.drawable.iphone15));
        list.add(new Dt("Xiaomi 14", 20000000, "Giá tốt", R.drawable.xiaomi14));
        list.add(new Dt("OPPO Find X6", 25000000, "Camera đẹp", R.drawable.oppo));

        adapter = new DTAdapter(this, list, false);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}