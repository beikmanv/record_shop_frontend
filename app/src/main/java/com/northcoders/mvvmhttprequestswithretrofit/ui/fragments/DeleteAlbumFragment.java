package com.northcoders.mvvmhttprequestswithretrofit.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.northcoders.mvvmhttprequestswithretrofit.R;


public class DeleteAlbumFragment extends Fragment {

    public DeleteAlbumFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete_album, container, false);
    }
}
