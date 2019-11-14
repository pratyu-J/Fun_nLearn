package com.example.funnlearn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import static com.example.funnlearn.MainActivity.display;
import static com.example.funnlearn.MainActivity.myDB;

public class CallbackTask extends AsyncTask<String, Integer, String> {

    Context context;
    // myDB = new Database_helper(CallbackTask.this);

    CallbackTask (Context context){

        this.context = context;
    }
    Activity cntxt;



@Override
protected String doInBackground(String... params) {
    final String app_id = "cc3ba99b";
final String app_key = "a0945179cb39f3d79bf85d774715a173";
        try {
              URL url = new URL(params[0]);
              HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
              urlConnection.setRequestProperty("Accept","application/json");
              urlConnection.setRequestProperty("app_id",app_id);
              urlConnection.setRequestProperty("app_key",app_key);

        // read the output from the server
              BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
              StringBuilder stringBuilder = new StringBuilder();

              String line = null;
              while ((line = reader.readLine()) != null) {
              stringBuilder.append(line + "\n");
               }
              return stringBuilder.toString();

        }
        catch (Exception e) {
        e.printStackTrace();
        return e.toString();
        }
   }

@Override
protected void onPostExecute(String s)
        {

        super.onPostExecute(s);
        String str;
            try{
                JSONObject js = new JSONObject(s);
                JSONArray results = js.getJSONArray("results");

                JSONObject lentries = results.getJSONObject(0);
                JSONArray larray = lentries.getJSONArray("lexicalEntries");

                JSONObject entries = larray.getJSONObject(0);
                JSONArray e = entries.getJSONArray("entries");

                JSONObject sensesArray = e.getJSONObject(0);
                JSONArray sensesA = sensesArray.getJSONArray("senses");

                JSONObject def = sensesA.getJSONObject(0);
                JSONArray definition = def.getJSONArray("definitions");

                str = definition.getString(0);

                Boolean isInserted2 = myDB.insertData2(str);
                if(isInserted2 == true){
                    Toast.makeText(cntxt.getApplicationContext(),"data is inserted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(cntxt.getApplicationContext(),"data not inserted",Toast.LENGTH_SHORT).show();
                }
                display.setText(str);
                Toast.makeText(context,str,Toast.LENGTH_SHORT).show();



            }
            catch(JSONException e){
                e.printStackTrace();

        }

       // System.out.println(s);
        }
}


