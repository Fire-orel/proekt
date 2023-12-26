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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class NapravleniaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_napravlenia);
        TextView textView= (TextView)findViewById(R.id.textView4);

        List<String> catNames = new ArrayList<String>();
        String myJson=loadJSONFromAsset("IT.json");

        ListView listView = findViewById(R.id.List);


        Bundle arguments = getIntent().getExtras();
        String text_bt=arguments.getString("Napravlenie");

//        Log.i("btn_text",text_bt);
//        textView.setText(text_bt);
        try {
            JSONObject rootJSONObject= new JSONObject(myJson);
            if (text_bt.equals("Балаквариат")) {
                textView.setText("Направления Бакалавриата");
                for(int i = 0; i<rootJSONObject.length(); i++){
                    catNames.add(rootJSONObject.names().getString(i));
//                Log.e("TAG_flag", "Key = " + spisoknapravlenie.names().getString(i));
                }

//            Log.i("btn_text",text_bt);
            } else if (text_bt.equals("Магистратура")) {
                textView.setText("Направления Магистратуры");
//            Log.i("btn_text",text_bt);
            }else if (text_bt.equals("Аспирантура")) {
                textView.setText("Направления Аспирантуры");
//            Log.i("btn_text",text_bt);
            }else if (text_bt.equals("Среднее профессиональное образование")) {
                textView.setText("Направления СПО");
//            Log.i("btn_text",text_bt);
            }

        }catch(JSONException e) {
            e.printStackTrace();
        }

// используем адаптер данных
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, catNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String text = catNames.get(position);
                Log.d("LOG_TAG", "itemClick: position = " + text );

                Intent intent = new Intent(NapravleniaActivity.this, ProfesiiActivity.class);
                intent.putExtra("Napravlenie",text );
                intent.putExtra("Shapka",textView.getText());
                startActivity(intent);
            }
        });
    }

    private String loadJSONFromAsset(String fileName) {

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

    public void back_active(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }






}

