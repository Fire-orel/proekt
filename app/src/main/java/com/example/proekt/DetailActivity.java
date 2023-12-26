package com.example.proekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView view_shapka= (TextView)findViewById(R.id.shapka);
        TextView view_napravlenie_kratko= (TextView)findViewById(R.id.napravlenie_kratko);
        TextView view_napravlenie_polnoe= (TextView)findViewById(R.id.napravlenie_polnoe);
        TextView view_opisanie= (TextView)findViewById(R.id.opisanie_view);


        Bundle arguments = getIntent().getExtras();
        String text_Shapka=arguments.getString("Shapka");
        String text_nap=arguments.getString("Napravlenie_datail");
        String text_naz=arguments.getString("Napravlenie");

        view_shapka.setText(text_Shapka);
        view_napravlenie_kratko.setText(text_nap);


        String myJson=loadJSONFromAsset("IT.json");


        try {
            JSONObject rootJSONObject= new JSONObject(myJson);
            JSONObject naz_JSONArray = rootJSONObject.getJSONObject(text_naz);
//            String opisanie=naz_JSONArray.getString("О направлениях");
            JSONObject spisoknapravlenie=naz_JSONArray.getJSONObject("Направления");
            Iterator iterator=spisoknapravlenie.keys();

           JSONObject napravlenie=spisoknapravlenie.getJSONObject(text_nap);

           String nazvanie=napravlenie.getString("Название");
           view_napravlenie_polnoe.setText(nazvanie);

           String values=new String();

           values=values+"Описание\n";

           values=values+napravlenie.getString("Описание")+"\n";

           values=values+"\nЧему научим?\n";

           for(int i = 0; i<napravlenie.getJSONArray("Чему научим?").length(); i++){
                values=values+napravlenie.getJSONArray("Чему научим?").getString(i)+"\n";
//                Log.e("TAG_flag", "Key = " + spisoknapravlenie.names().getString(i));
            }


//            Log.d("LOG_TAG",znach);
//
//
           view_opisanie.setText(values);





//            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                    android.R.layout.simple_list_item_1, values);
//            list_napravlenie.setAdapter(adapter);



//            Log.d("LOG_TAG",iterator.toString());
        }
        catch (JSONException e) {
            Log.e("ОШИБКАААА","");
            e.printStackTrace();
        }


    }

    public void back_active_napravlenie_detail(View view){
        Intent intent = new Intent(this, ProfesiiActivity.class);
        startActivity(intent);
    }

    public String loadJSONFromAsset(String fileName)
    {
        String json;
        try
        {
            InputStream is = getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            //noinspection ResultOfMethodCallIgnored
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex)
        {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}