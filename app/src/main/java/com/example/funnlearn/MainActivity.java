package com.example.funnlearn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


  import android.os.Bundle;
    //import android.support.v7.app.AppCompatActivity;

    //add dependencies to your class
    import java.io.BufferedReader;
    import java.io.InputStreamReader;
    import java.net.URL;
    import javax.net.ssl.HttpsURLConnection;
    import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
        public static TextView display;
        private static EditText search;
        private Button find;
        String url;
        String keyword;

    public static Database_helper myDB;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
           // new CallbackTask().execute(dictionaryEntries());
            display = (TextView)findViewById(R.id.displaytv);
            search = (EditText)findViewById(R.id.searchpt);
            find = (Button)findViewById(R.id.searchbtn);

            myDB = new Database_helper(this);
        }



        private String dictionaryEntries() {
            final String language = "en-gb";
            final String word = search.getText().toString();//"ace";
            final String fields = "definitions";
            final String strictMatch = "false";
            final String word_id = word.toLowerCase();
            return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
        }

    public void requestApiButtonClick(View v) {
        CallbackTask callbackTask = new CallbackTask(this);
        url = dictionaryEntries();



        if (validate()) {
            keyword = search.getText().toString().trim();
            Toast.makeText(MainActivity.this, keyword, Toast.LENGTH_SHORT).show();
            /*boolean isInserted = myDB.insertData(keyword);
            if(isInserted == true){
                Toast.makeText(MainActivity.this, "data is inserted", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this, "data not inserted", Toast.LENGTH_SHORT).show();
            }*/
            callbackTask.execute(url);
            Boolean isInserted1 = myDB.insertData1(keyword);
            if(isInserted1 == true){
                Toast.makeText(MainActivity.this, "data is inserted", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this, "data not inserted", Toast.LENGTH_SHORT).show();
            }



            //Intent i = new Intent(MainActivity.this,  CallbackTask.class);
            //i.putExtra("keyword", keyword);
            //startActivity(i);




        }
    }



    public boolean validate(){
        Boolean res = false;

        String key = search.getText().toString().trim();
        if(key.isEmpty()){
            Toast.makeText(MainActivity.this,"enter the Search Word",Toast.LENGTH_SHORT).show();
        }
        else{
            res = true;
        }
        return res;
    }
    }

    /*   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
*/