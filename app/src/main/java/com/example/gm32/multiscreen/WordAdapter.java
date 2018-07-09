package com.example.gm32.multiscreen;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word>{

    private int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> words, int mColorResourceId){
        super(context,0,words);
        this.mColorResourceId= mColorResourceId;
    }

    @Override
    public View getView(int position, View converseView, ViewGroup parent){

        View listItemView = converseView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Word word= getItem(position);

        TextView gmTextView= (TextView) listItemView.findViewById(R.id.gm32_text_view);
        gmTextView.setText(word.getmGm32Translation());

        TextView defaultTextView= (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(word.getmDefaultTranslation());

        ImageView imageView= (ImageView) listItemView.findViewById(R.id.imageView);
        imageView.setImageResource(word.getmImageResourceId());

        View textContainer= (View) listItemView.findViewById(R.id.text_container);
        int color= ContextCompat.getColor(getContext(),mColorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;

    }

}
