package com.noth.nothapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.noth.nothapp.databinding.ActivityEditUserBinding;

public class EditUserActivity extends AppCompatActivity {
    ActivityEditUserBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.imvBackToLoginScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditUserActivity.this, UserActivity.class));
            }
        });
        binding.btnConfirmChangeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditUserActivity.this,"Thay đổi thông tin hồ sơ thành công",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditUserActivity.this, MainActivity.class));
            }
        });
    }
}