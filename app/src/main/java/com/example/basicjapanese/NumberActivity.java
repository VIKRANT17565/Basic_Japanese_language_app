package com.example.basicjapanese;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.AndroidException;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumberActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        final ArrayList<Word> word = new ArrayList<>();

        word.add(new Word("One", "いち \n(ichi)", R.drawable.number_one, R.raw.ichi));
        word.add(new Word("Two", "に \n(ni)", R.drawable.number_two, R.raw.ni));
        word.add(new Word("Three", "さん \n(san)", R.drawable.number_three, R.raw.san));
        word.add(new Word("Four", "し、よん \n(shi, yon)", R.drawable.number_four, R.raw.yon));
        word.add(new Word("Five", "ご \n(go)", R.drawable.number_five, R.raw.go));
        word.add(new Word("Six", "ろく \n(roku)", R.drawable.number_six, R.raw.roku));
        word.add(new Word("Seven", "しち、なな \n(shichi, nana)", R.drawable.number_seven, R.raw.nana));
        word.add(new Word("Eight", "はち \n(hachi)", R.drawable.number_eight, R.raw.hachi));
        word.add(new Word("Nine", "く、きゅう \n(ku, kyuu)", R.drawable.number_nine, R.raw.kyuu));
        word.add(new Word("Ten", "じゅう \n(juu)", R.drawable.number_ten, R.raw.juu));

        ListView rootView = findViewById(R.id.rootView);
        WordAdapater listItems = new WordAdapater(this, word);
        rootView.setAdapter(listItems);


        rootView.setClickable(true);
        rootView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word mainWord = word.get(i);

                mediaPlayer = MediaPlayer.create(NumberActivity.this, mainWord.getAudioResourceId());
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