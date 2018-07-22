package com.example.sobbr.verbon;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by sobbR on 1/7/2017.
 */

public class BackgroundTask extends AsyncTask<String,Product,String>{
    Context ctx;
    ProductAdapter productAdapter;
    Activity activity;
    ListView listView;

    BackgroundTask(Context ctx){
        this.ctx =  ctx;
        activity =(Activity)ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        DbOperations dbOperations = new DbOperations(ctx);
        if(method.equals("add_info")){
            String Id = params[1];
            SQLiteDatabase db = dbOperations.getWritableDatabase();
            Cursor cursr = dbOperations.getInformation(db);
            while (cursr.moveToNext()) {
                String chek = cursr.getString(cursr.getColumnIndex(ProductContract.ProductEntry.ID));
                if(chek.equals(Id)){

                    return "word is already added";
                }
            }
                dbOperations.addInformation(db,Id);
            return  "word added";

        }
        else if(method.equals("get_info")){

            listView = (ListView)activity.findViewById(R.id.display_listview);
            SQLiteDatabase db = dbOperations.getReadableDatabase();
            Cursor cursor = dbOperations.getInformation(db);
            productAdapter = new ProductAdapter(ctx,R.layout.display_product_row);
            String id;

            Utility.count = cursor.getCount();


            while (cursor.moveToNext()){
                    id =cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.ID));
                    if(Utility.searchStr==""){
                        Product product = new Product(id);
                        publishProgress(product);
                    }
                    else if(id.startsWith(Utility.searchStr)){
                        Product product = new Product(id);
                        publishProgress(product);

                    }

            }

            return "get_info";
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Product... values) {
        productAdapter.add(values[0]);

    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("get_info")){
           listView.setAdapter(productAdapter);
        }
        else {
            Toast.makeText(ctx, result, Toast.LENGTH_SHORT).show();
        }

        if(Utility.flag){
            Toast.makeText(ctx,"total words:"+Utility.count,Toast.LENGTH_SHORT).show();
            Utility.flag = false;
        }

    }
}
