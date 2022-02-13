package com.example.basicjapanese;

public class Word {

    private String mDefaultTranslation;

    private String mJapaneseTranslation;

    private int mAudioResourceId;

    private int mImageResourceId= NO_IMAGE_PROVIDER;

    private static final int NO_IMAGE_PROVIDER = -1;

    public Word (String defaultTranslation, String japaneseTranslation,int audioResourceId){
        mDefaultTranslation = defaultTranslation;
        mJapaneseTranslation = japaneseTranslation;
        mAudioResourceId = audioResourceId;

    }

    public Word (String defaultTranslation, String japaneseTranslation, int imageResourceId, int audioResourceId){
        mDefaultTranslation = defaultTranslation;
        mJapaneseTranslation = japaneseTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;

    }

    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    public String getJapaneseTranslation(){
        return mJapaneseTranslation;
    }

    public int getImageResourceId(){
        return mImageResourceId;
    }

    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDER;

    }

    public int getAudioResourceId(){
        return mAudioResourceId;
    }

}
