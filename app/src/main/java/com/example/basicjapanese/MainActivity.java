package com.example.basicjapanese;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String listContent[] = {"Numbers", "Colors", "Phrases"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        ArrayAdapter arrayAdapter =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listContent);
        listView.setAdapter(arrayAdapter);

        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //Using Explicit intent
                if (position == 0) {

                    Intent intent = new Intent(MainActivity.this, NumberActivity.class);

                    startActivity(intent);
                }

                else if (position == 1) {

                    Intent intent = new Intent(MainActivity.this, ColorActivity.class);

                    startActivity(intent);
                }

                else if (position == 2) {

                    Intent intent = new Intent(MainActivity.this, PhrasesActivity.class);

                    startActivity(intent);
                }


            }
        });

    }
}


