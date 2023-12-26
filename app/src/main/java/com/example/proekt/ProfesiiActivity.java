package com.example.proekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProfesiiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesii);

        String myJson=loadJSONFromAsset("IT.json");



        TextView text_nazvanie= (TextView)findViewById(R.id.napravlenie);
        TextView text_opisanie_naz= (TextView)findViewById(R.id.opisanie);
        TextView text_opisanie= (TextView)findViewById(R.id.opisanie_text);
        TextView text_shapka= (TextView)findViewById(R.id.shapka);
        ListView list_napravlenie=(ListView)findViewById(R.id.list_napravlenie);

        Bundle arguments = getIntent().getExtras();
        String text_naz=arguments.getString("Napravlenie");
        String text_hap=arguments.getString("Shapka");

        List<String> values = new ArrayList<String>();


        text_shapka.setText(text_hap);

            Log.e("LOG_TAG",text_naz);
            if (text_naz.equals("IT")) {
                text_opisanie_naz.setText("Об IT направлениях");
//
            } else if (text_naz.equals("Экономика")) {
                text_opisanie_naz.setText("Об Экономических направлениях");
//            Log.i("btn_text",text_bt);
            } else if (text_naz.equals("Энергетика")) {
                text_opisanie_naz.setText("Об Энергетических направлениях");
//            Log.i("btn_text",text_bt);
            } else if (text_naz.equals("Химия")) {
                text_opisanie_naz.setText("Об Химических направлениях");
//            Log.i("btn_text",text_bt);
            }
        try {
            JSONObject rootJSONObject= new JSONObject(myJson);

            JSONObject naz_JSONArray = rootJSONObject.getJSONObject(text_naz);
            String opisanie=new String();

            for(int i = 0; i<naz_JSONArray.getJSONArray("О направлениях").length(); i++){
                opisanie=opisanie+naz_JSONArray.getJSONArray("О направлениях").getString(i)+" ";
                Log.e("LOG_TAG", "Key = " + naz_JSONArray.getJSONArray("О направлениях").getString(i));
            }
            Log.e("LOG_TAG", opisanie);
            JSONObject spisoknapravlenie=naz_JSONArray.getJSONObject("Направления");
            Iterator iterator=spisoknapravlenie.keys();

            text_opisanie.setText(opisanie);


            for(int i = 0; i<naz_JSONArray.getJSONObject("Направления").length(); i++){
                values.add(spisoknapravlenie.names().getString(i));
//                Log.e("TAG_flag", "Key = " + spisoknapravlenie.names().getString(i));
            }




            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, values);
            list_napravlenie.setAdapter(adapter);



//            Log.d("LOG_TAG",iterator.toString());
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        list_napravlenie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String text = values.get(position);
//                Log.d("LOG_TAG", "itemClick: position = " + text );

                Intent intent = new Intent(ProfesiiActivity.this, DetailActivity.class);
                intent.putExtra("Shapka",text_hap);
                intent.putExtra("Napravlenie",text_naz);
                intent.putExtra("Napravlenie_datail",text);
                startActivity(intent);
            }
        });

    }
    public void back_active_napravlenie(View view){
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

