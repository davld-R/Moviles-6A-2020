package com.david.mypet.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.david.mypet.R;

public class SlideshowFragment extends Fragment {

    Context context;
    View view;

    public static SlideshowFragment newInstance(String param1, String param2) {
        SlideshowFragment fragment = new SlideshowFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_slideshow, container, false);
        context = view.getContext();


        return view;
    }
}