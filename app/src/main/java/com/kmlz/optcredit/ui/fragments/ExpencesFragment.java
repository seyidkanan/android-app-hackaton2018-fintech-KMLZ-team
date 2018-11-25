package com.kmlz.optcredit.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kmlz.optcredit.R;
import com.kmlz.optcredit.network.ApiClient;
import com.kmlz.optcredit.network.ApiInterface;
import com.kmlz.optcredit.network.request.MainRequestBody;
import com.kmlz.optcredit.network.responses.ExpensensResponse;
import com.kmlz.optcredit.ui.activities.NewExpenceActivity;
import com.kmlz.optcredit.ui.activities.TabsActivity;
import com.kmlz.optcredit.ui.adapters.ExpencesAdapter;
import com.kmlz.optcredit.utils.Constants;
import com.kmlz.optcredit.utils.PreferenceIO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpencesFragment extends Fragment {

    FloatingActionButton fab;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expences,container,false);
        initViews(view);
        configClickListeners();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getExpences();
    }

    private void getExpences() {
        MainRequestBody mainRequestBody= new MainRequestBody();
        mainRequestBody.setToken(PreferenceIO.getInstance(getActivity()).readParam(Constants.KEY_PREF_TOKEN));
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getExpenses(mainRequestBody).enqueue(new Callback<ExpensensResponse>() {
            @Override
            public void onResponse(Call<ExpensensResponse> call, Response<ExpensensResponse> response) {
                if (response.body() != null){
                    if (response.body().getCode().equals("1026")){
                        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                        recyclerView.setLayoutManager(llm);
                        ExpencesAdapter expencesAdapter =  new ExpencesAdapter(getActivity(),response.body().getBody());
                        recyclerView.setAdapter(expencesAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ExpensensResponse> call, Throwable t) {

            }
        });

    }

    private void configClickListeners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),NewExpenceActivity.class));
            }
        });
    }

    private  void  initViews(View view){
        recyclerView = view.findViewById(R.id.id_recycler_view_expences);
        fab = view.findViewById(R.id.id_fab);
    }
}
