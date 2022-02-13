package com.example.basicjapanese;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        final ArrayList<Word> word = new ArrayList<>();

        word.add(new Word("Red", "あか (aka)", R.drawable.color_red, R.raw.aka));
        word.add(new Word("Green", "みどり (midori)", R.drawable.color_green, R.raw.midori));
        word.add(new Word("Grey", "はいいろ (haiiro)", R.drawable.color_gray, R.raw.haiiro));
        word.add(new Word("Yellow", "きいろ (kiiro)", R.drawable.color_mustard_yellow, R.raw.kiiro));
        word.add(new Word("Brown", "ちゃいろ (chairo)", R.drawable.color_brown, R.raw.chairo));
        word.add(new Word("Black", "くろ (kuro)", R.drawable.color_black, R.raw.kuro));
        word.add(new Word("White", "しろ (shiro)", R.drawable.color_white, R.raw.shiro));


        ListView rootView = findViewById(R.id.colorList);
        WordAdapater listItems = new WordAdapater(this, word);
        rootView.setAdapter(listItems);

        rootView.setClickable(true);
        rootView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word mainWord = word.get(i);

                mediaPlayer = MediaPlayer.create(ColorActivity.this, mainWord.getAudioResourceId());
                mediaPlayer.start();
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        if (mediaPlayer!= null){
            mediaPlayer.release();

            mediaPlayer= null;

        }
    }

}