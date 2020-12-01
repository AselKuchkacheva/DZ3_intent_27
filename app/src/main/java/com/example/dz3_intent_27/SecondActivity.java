package com.example.dz3_intent_27;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.net.URI;

public class SecondActivity extends AppCompatActivity {

    public static Uri image;
    public static String imageToSend;
    public static String textEntered;
    public static final int REQUEST_GALLERY = 1;
    public static final String TEXT = "textKey";
    public static final String IMAGE = "imageKey";
    ImageView imageView;
    EditText editText;
    Button btnSecondAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        imageView = findViewById(R.id.imageForSend);
        editText = findViewById(R.id.textForSend);
        btnSecondAct = findViewById(R.id.btnSecond);
    }

    public void getImage(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"Выбрать картину"), REQUEST_GALLERY);
    }
    public void toMainActivity(View view) {
        textEntered = editText.getText().toString();
        Intent intentTextImage = new Intent(SecondActivity.this, MainActivity.class);
        intentTextImage.putExtra(TEXT, textEntered);
        intentTextImage.putExtra(IMAGE, imageToSend);
        setResult(RESULT_OK,intentTextImage);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_GALLERY && resultCode == RESULT_OK && data != null){
            image = data.getData();
            imageView.setImageURI(image);
            imageToSend = image.toString();
        }
    }
}