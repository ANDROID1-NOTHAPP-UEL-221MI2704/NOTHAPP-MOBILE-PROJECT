package com.noth.nothapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.noth.nothapp.Utils.Util;
import com.noth.nothapp.databinding.ActivityContinuebuyBinding;

public class ContinuebuyActivity extends AppCompatActivity {
    ActivityContinuebuyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_continue_buy);
        binding = ActivityContinuebuyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        int slMuaHang = intent.getIntExtra("soluong",0);
        binding.txtSoLuongDonHang.setText(String.valueOf(slMuaHang));
        binding.btnTiepTucMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xóa sản phẩm đã ấn mua thành công, sau đó khởi tạo Màn hình Cảm ơn và Tiếp tục mua hàng
                Util.CartArrayList.clear();
                startActivity(new Intent(ContinuebuyActivity.this, MainActivity.class));
            }
        });
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}