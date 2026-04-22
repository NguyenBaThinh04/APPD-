package com.example.hitcapp;

import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CheckoutActivity extends AppCompatActivity {

    RecyclerView recyclerCart;
    TextView txtTotal;
    EditText edtName, edtPhone, edtAddress;
    Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        recyclerCart = findViewById(R.id.recyclerCart);
        txtTotal = findViewById(R.id.txtTotal);
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);
        btnPay = findViewById(R.id.btnPay);

        // hiển thị giỏ hàng
        DTAdapter adapter = new DTAdapter(this, Cart.listCart, false);
        recyclerCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerCart.setAdapter(adapter);

        // tính tổng tiền
        int total = 0;
        for (Dt item : Cart.listCart) {
            total += item.price;
        }

        txtTotal.setText("Tổng tiền: " + String.format("%,d VND", total));

        // thanh toán
        btnPay.setOnClickListener(v -> {

            if (Cart.listCart.isEmpty()) {
                Toast.makeText(this, "Giỏ hàng trống", Toast.LENGTH_SHORT).show();
                return;
            }

            String name = edtName.getText().toString();
            String phone = edtPhone.getText().toString();
            String address = edtAddress.getText().toString();

            if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Thanh toán thành công!", Toast.LENGTH_LONG).show();

            // xóa giỏ hàng
            Cart.listCart.clear();
            finish();
        });
    }
}

