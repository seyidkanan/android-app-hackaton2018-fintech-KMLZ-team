package com.kmlz.optcredit.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kmlz.optcredit.R;

public class ExpencesFragment extends Fragment {

    FloatingActionButton fab;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expences,container,false);
        initViews(view);
        configClickListeners();
        getExpences();
        return view;
    }

    private void getExpences() {

    }

    private void configClickListeners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private  void  initViews(View view){
        recyclerView = view.findViewById(R.id.id_recycler_view_expences);
        fab = view.findViewById(R.id.id_fab);
    }
}
