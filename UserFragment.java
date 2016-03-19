package com.example.proto.launcher.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proto.launcher.R;

/**
 * Created by Proto on 1/31/2016.
 */
public class UserFragment extends Fragment {

    public UserFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.user, container, false);

        return rootView;
    }
}
