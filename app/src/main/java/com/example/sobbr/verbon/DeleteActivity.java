package com.example.sobbr.verbon;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by sobbR on 10/22/2016.
 */

public class DeleteActivity extends Activity {
    Button Del;
    EditText PASS;
    String Pass;
    DbOperations DOP;
    Context CTX = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_layout);
        Del = (Button) findViewById(R.id.b_delete);
        PASS = (EditText) findViewById(R.id.del_pass);

        Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = PASS.getText().toString();
                DOP = new DbOperations(CTX);
                SQLiteDatabase SQ = DOP.getReadableDatabase();
                Cursor crsr= DOP.getInformation(SQ);
                while (crsr.moveToNext()) {
                    String chek = crsr.getString(crsr.getColumnIndex(ProductContract.ProductEntry.ID));
                    if(chek.equals(id)){
                        DOP.deleteUser(DOP,id);
                        Toast.makeText(getBaseContext(),"word Removed", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                Toast.makeText(getBaseContext(),"no such word", Toast.LENGTH_LONG).show();


                }
        });
    }
    public void clearWord(View view){
        PASS.setText("");
    }
}
