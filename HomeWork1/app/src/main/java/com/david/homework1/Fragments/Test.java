package com.david.homework1.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.david.homework1.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Test#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Test extends Fragment {

    View view;
    Context context;

    View alarmView;
    View alarmView2;

    public Test() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Test.
     */
    // TODO: Rename and change types and number of parameters
    public static Test newInstance(String param1, String param2) {
        Test fragment = new Test();
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
        view = inflater.inflate(R.layout.fragment_test, container, false);
        context = view.getContext();

        lounchWidgwets();
        lounchEvents();

        return view;
    }

    public void lounchWidgwets() {

        alarmView = view.findViewById(R.id.alarm_view);
        alarmView2 = view.findViewById(R.id.alarm_view2);

    }

    public void lounchEvents() {

        Animation animC = AnimationUtils.loadAnimation(context, R.anim.test);
        alarmView.startAnimation(animC);

        Animation animB = AnimationUtils.loadAnimation(context, R.anim.test);
        alarmView2.startAnimation(animB);

    }

}