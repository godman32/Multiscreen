package com.example.gm32.multiscreen;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener=
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    } else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                        mMediaPlayer.start();
                    } else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                        releaseMediaPlayer();
                    }
                }
            };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp){
            releaseMediaPlayer();
        }
    };

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_numbers, container, false);

        mAudioManager= (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> word= new ArrayList<Word>();
        word.add(new Word("satu", "Lutti", R.mipmap.emoji1, R.raw.lagu_1));
        word.add(new Word("dua", "Lutti", R.mipmap.emoji1, R.raw.lagu));
        word.add(new Word("tiga", "Lutti", R.mipmap.emoji1, R.raw.lagu_2));
        word.add(new Word("empat", "Lutti", R.mipmap.emoji1, R.raw.lagu_2));
        word.add(new Word("lima", "Lutti", R.mipmap.emoji2, R.raw.lagu_1));
        word.add(new Word("enam", "Lutti", R.mipmap.emoji2, R.raw.lagu_2));
        word.add(new Word("tujuh", "Lutti", R.mipmap.emoji2, R.raw.lagu_2));
        word.add(new Word("delapan", "Lutti", R.mipmap.emoji2, R.raw.lagu_2));
        word.add(new Word("sembilan", "Lutti", R.mipmap.emoji2, R.raw.lagu_2));


        // LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);


        //int index=0;

        //while(index<word.size()){
        //   TextView wordView= new TextView(this);
        //   wordView.setText(word.get(index));
        //    rootView.addView(wordView);

        //   index++;
        //}

        final WordAdapter adapter= new WordAdapter(getActivity(),word,R.color.category_numbers);

        ListView listView= (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word words= word.get(position);

                releaseMediaPlayer();

                int result= mAudioManager.requestAudioFocus(
                        mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
                );

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    //mAudioManager.registerMediaButtonEventReceiver();

                    mMediaPlayer= MediaPlayer.create(getActivity(), words.getmAudioResourceId());
                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }


            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        if(mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

}
