package com.noth.nothapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.noth.nothapp.databinding.ActivityDetailsBinding;
import com.noth.nothapp.Model.Cart;
import com.noth.nothapp.Model.Popular;
import com.noth.nothapp.R;
import com.noth.nothapp.Utils.Util;

import java.text.NumberFormat;
import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {
    ActivityDetailsBinding binding;
    Popular popular;
    int numberOder = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_details);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        onShowData();
        onBack();
        customNumberOder();
        onClickMuaNgay();
    }

    private void onClickMuaNgay() {
        binding.layoutMuaNgay.setOnClickListener(new View.OnClickListener() { //Xử lý sự kiện nút mua ngay
            @Override
            public void onClick(View view) {
                if (Util.CartArrayList.size()>0){
                    int soLuongSp = Integer.parseInt(binding.txtNumber.getText().toString()); //Lấy ra số lượng sản phẩm
                    boolean exits = false;
                    //Kiểm tra nếu người dùng đặt cùng 1 sản phẩm thì sẽ tăng số lượng sản phẩm đó lên
                    for(int i=0;i<Util.CartArrayList.size();i++){
                        if (Util.CartArrayList.get(i).getNameProduct().equals(popular.getNamePopular())){
                            Util.CartArrayList.get(i).setNumberProduct(Util.CartArrayList.get(i).getNumberProduct()+soLuongSp);
                            Util.CartArrayList.get(i).setPriceProduct(Util.CartArrayList.get(i).getNumberProduct()*popular.getMoneyPopular());
                            exits = true;
                        }
                    }
                    if (!exits){
                        int sl = Integer.parseInt(binding.txtNumber.getText().toString());
                        int giaMoi =sl*popular.getMoneyPopular();
                        Util.CartArrayList.add(new Cart(popular.getImgPopular(),popular.getNamePopular(),giaMoi,sl));
                    }
                }else {
                    int sl = Integer.parseInt(binding.txtNumber.getText().toString());
                    int giaMoi =sl*popular.getMoneyPopular();
                    Util.CartArrayList.add(new Cart(popular.getImgPopular(),popular.getNamePopular(),giaMoi,sl));
                }
                startActivity(new Intent(DetailsActivity.this, CartActivity.class));
            }
        });
    }

    private void customNumberOder() {
        //Nút tăng số lượng
        binding.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOder = numberOder+1;
                binding.txtNumber.setText(String.valueOf(numberOder));
            }
        });
        //Nút giảm số lượng
        binding.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numberOder>1){
                    numberOder =numberOder-1;
                }
                binding.txtNumber.setText(String.valueOf(numberOder));
            }
        });
    }

    private void onBack() {
        //Nút quay lại
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailsActivity.this,MainActivity.class));
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void onShowData() {
        //Nhận dữ liệu và hiển thị lên màn hình
        Intent intent = getIntent();
        popular = (Popular) intent.getSerializableExtra("popular");
        if (popular!=null){
            binding.nameDetails.setText(popular.getNamePopular());
            binding.imgDetails.setImageResource(popular.getImgPopular());
            binding.txtMoneyPopular.setText(NumberFormat.getNumberInstance(Locale.getDefault()).format(popular.getMoneyPopular()) +" vnd");
            binding.rating.setRating(popular.getRating());
        }
    }
}