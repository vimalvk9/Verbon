package com.example.sobbr.verbon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void addProduct(View view){
        startActivity(new Intent(this,SaveInfo.class));

    }
    public void displayProducts(View view){
        startActivity(new Intent(this,DisplayProduct.class));
    }

    public void deleteProduct(View view){
        startActivity(new Intent(this,DeleteActivity.class));
    }

}

