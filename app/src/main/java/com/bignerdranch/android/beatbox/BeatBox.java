package com.bignerdranch.android.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry on 23.04.2017.
 */

public class BeatBox {

    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;

    private static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS =5;

    public List<Sound> getSounds() {
        return mSounds;
    }

    public BeatBox(Context context){
        mAssets = context.getAssets();
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSounds();
    }



    private void loadSounds(){
        String[] soundNames;
        try{
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length +" sounds");
        }catch (IOException ioe){
            Log.e(TAG, "Could not list assets", ioe);
            return;
        }

        for(String filename : soundNames)
        {
            try{
                String assetPath = SOUNDS_FOLDER+"/"+filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            } catch (IOException ioe){
                Log.e(TAG, "Could not load sound " + filename, ioe);
            }
        }
    }

    private void load(Sound sound) throws IOException{
        AssetFileDescriptor fileDescriptor = mAssets.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(fileDescriptor,1);
        sound.setSoundId(soundId);
    }

    public void play(Sound sound){
        Integer soundId = sound.getSoundId();
        if(soundId == null)
        {
            return;
        }
        mSoundPool.play(soundId,1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void release(){ mSoundPool.release(); }
}
