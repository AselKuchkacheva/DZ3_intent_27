package com.example.dz3_intent_27;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;
    ImageView imageView;
    TextView textView;
    Button btnNextPage;
    Button btnForSend;
    public String text1;
    public String image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.getImage);
        textView = findViewById(R.id.getText);
        btnNextPage = findViewById(R.id.btnNextPage);
        btnForSend = findViewById(R.id.btnSend);
    }

    public void toSecondActivity(View view) {
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void sentToGMail(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, "asel.kuchkacheva@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Отправка");
        intent.putExtra(Intent.EXTRA_TEXT, text1);
        startActivity(Intent.createChooser(intent, "Send Email"));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            image1 = data.getStringExtra(SecondActivity.IMAGE);
            text1 = data.getStringExtra(SecondActivity.TEXT);
            imageView.setImageURI(Uri.parse(image1));
            textView.setText(text1);
        }
    }
}