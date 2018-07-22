package com.example.sobbr.verbon;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by sobbR on 10/24/2016.
 */

public class SaveInfo extends Activity {
    EditText e_id;
    String id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_info_layout);
        e_id = (EditText)findViewById(R.id.d_id);

    }
    public void saveData(View view){
        id = e_id.getText().toString();
        BackgroundTask backgroundTask =  new BackgroundTask(this);
        if(!id.isEmpty()&&id.charAt(0)!=' '&&id.charAt(id.length()-1)!=' '){
            backgroundTask.execute("add_info",id);

        }else{
            Toast.makeText(this,"please enter valid word",Toast.LENGTH_SHORT).show();
        }
    }
    public void clearWord(View view){
        e_id.setText("");
    }
}
