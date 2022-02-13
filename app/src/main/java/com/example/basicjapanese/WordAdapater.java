package com.example.basicjapanese;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class WordAdapater extends ArrayAdapter {

    public WordAdapater(Context context, ArrayList<Word> word) {
        super(context, 0, word);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word currentWord = (Word) getItem(position);

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.defaultText);
        defaultTextView.setText(currentWord.getDefaultTranslation());

        TextView jpnTextView = (TextView) listItemView.findViewById(R.id.jpnText);
        jpnTextView.setText(currentWord.getJapaneseTranslation());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        if (currentWord.hasImage()){
            imageView.setImageResource(currentWord.getImageResourceId());
        }else{
            imageView.setVisibility(View.GONE);
        }


        return listItemView;
    }


}
