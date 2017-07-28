package com.hckim.listviewre.adapterview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.hckim.listviewre.R;

public class DetailAddressActivity extends AppCompatActivity {

    private EditText mNameEditText; // (2)
    private EditText mPhoneEditText;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_address);

        mNameEditText = (EditText) findViewById(R.id.name_edit); // (3)
        mPhoneEditText = (EditText) findViewById(R.id.phone_edit);
        mImageView = (ImageView) findViewById(R.id.image_view);

        Intent intent = getIntent(); // (1)
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String phone = intent.getStringExtra("phone");
            int picture = intent.getIntExtra("picture", R.mipmap.ic_launcher);

            mNameEditText.setText(name); // (4)
            mPhoneEditText.setText(phone);
            mImageView.setImageResource(picture); // Resource
        }
    }
}
