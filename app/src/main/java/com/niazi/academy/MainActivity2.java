package com.niazi.academy;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {


    TextView nametv,emailtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  binding = ActivityMAainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main2);


      String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phoneno");
        String address = getIntent().getStringExtra("address");
        int image = getIntent().getIntExtra("image",0);
/*
        binding.editText.setText(name);
        binding.email.setText(email);
        binding.editTextPhone2.setText(phone);
        binding.subject.setText(address);
        binding.profileImage.setImageResource(image);*/
    }
}