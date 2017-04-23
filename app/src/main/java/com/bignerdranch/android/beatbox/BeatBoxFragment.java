package com.bignerdranch.android.beatbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Dmitry on 23.04.2017.
 */

public class BeatBoxFragment extends Fragment {
    public static BeatBoxFragment newInstance(){
        return new BeatBoxFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beat_box, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_beat_box_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        return view;
    }

    private class SoundHolder extends RecyclerView.ViewHolder{
        private Button mButton;
        public SoundHolder(LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.list_item_sound,container,false);

            mButton = (Button)itemView.findViewById(R.id.list_item_sound_button);
        }
    }
}
