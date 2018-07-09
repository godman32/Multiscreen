package com.example.gm32.multiscreen;

public class Word {

    private String mDefaultTranslation;

    private String mGm32Translation;

    private int mImageResourceId;

    private int mAudioResourceId;

    public Word(String mDefaultTranslation, String mGm32Translation, int mImageResourceId, int mAudioResourceId){
        this.mDefaultTranslation= mDefaultTranslation;
        this.mGm32Translation= mGm32Translation;
        this.mImageResourceId= mImageResourceId;
        this.mAudioResourceId= mAudioResourceId;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getmGm32Translation() {
        return mGm32Translation;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public int getmAudioResourceId() {
        return mAudioResourceId;
    }
}
