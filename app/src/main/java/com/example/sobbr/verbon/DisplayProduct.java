package com.example.sobbr.verbon;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class DisplayProduct extends AppCompatActivity {
    EditText editText;
    ListView listView;
    DisplayProduct displayProduct = this;
    BackgroundTask backgroundTask2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_product_layout);
        editText =(EditText)findViewById(R.id.txtsearch);
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute("get_info");
        Utility.flag = true;
        listView = (ListView)findViewById(R.id.display_listview);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Utility.searchStr = s.toString();
                    backgroundTask2 = new BackgroundTask(displayProduct);
                    backgroundTask2.execute("get_info");
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms

                        backgroundTask2.cancel(true);
                    }
                }, 250);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }

}

