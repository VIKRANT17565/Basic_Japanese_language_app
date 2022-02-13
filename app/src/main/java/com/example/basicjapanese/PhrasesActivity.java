package com.example.basicjapanese;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

public class PhrasesActivity extends AppCompatActivity {

    private AudioManager audioManager;
    private MediaPlayer mediaPlayer;

    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if (i == AUDIOFOCUS_LOSS_TRANSIENT  || i == AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }else if (i == AudioManager.AUDIOFOCUS_GAIN){
                mediaPlayer.start();
            }else if (i == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        final ArrayList<Word> word = new ArrayList<>();

        word.add(new Word("Yes", "はい \n(hai)", R.raw.yes));
        word.add(new Word("No", "いいえ \n(iie)", R.raw.no));
        word.add(new Word("Good morning","おはよ \n(Ohayō)", R.raw.goodmorning));
        word.add(new Word("Hello", "こんにちは \n(konnichiwa)", R.raw.hello));
        word.add(new Word("Goodbye", "さようなら \n(Sayōnara)", R.raw.goodbye));
        word.add(new Word("Thank you", "ありがとう \n(arigatou)", R.raw.thankyou));
        word.add(new Word("I'm sorry", "ごめんなさい \n(gomen nasai)", R.raw.imsorry));
        word.add(new Word("Excuse me", "すみません \n(sumimasen)", R.raw.excuseme));
        word.add(new Word("Do you speak English?","えいごをはなせますか。\n(Eigo o hanasemasu ka)", R.raw.doyouspeekenglish));
        word.add(new Word("I only speak a little Japanese","わたしは にほんごがすこししか はなせません。\n(Watashiwa nihongo sukoshi shika hanasemasen)", R.raw.icanspeeklittelbitjapanese));
        word.add(new Word("What is your name?","おなまえはなんですか。\n(O-namae wa nan desu ka)", R.raw.whatisyourname));
        word.add(new Word("My name is ....","わたしのなまえは　かり です。\n(Watashi no namae wa .... desu)", R.raw.mynameis));
        word.add(new Word("How are you?","おげんきですか。\n(O-genki desu ka)", R.raw.howareyou));
        word.add(new Word("I'm fine","はい げんきです。\n(hai, Genki desu)", R.raw.imfine));
        word.add(new Word("I don't understand","わかりません。\n(Wakarimasen)", R.raw.idontunderstand));
        word.add(new Word("I understand","わかります。 \n(wakarimasu)", R.raw.iunderstand));



        ListView rootView = findViewById(R.id.phrasesList);
        WordAdapater listItems = new WordAdapater(this, word);
        rootView.setAdapter(listItems);

        rootView.setClickable(true);
        rootView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word mainWord = word.get(i);

                int result = audioManager.requestAudioFocus(afChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(PhrasesActivity.this, mainWord.getAudioResourceId());
                    mediaPlayer.start();
                }
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

            audioManager.abandonAudioFocus(afChangeListener);

        }
    }

}