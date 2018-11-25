package com.kmlz.optcredit.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.kmlz.optcredit.network.responses.OfferObj;
import com.kmlz.optcredit.network.responses.OffersResponse;
import com.kmlz.optcredit.ui.adapters.OffersAdapter;
import com.kmlz.optcredit.utils.Constants;
import com.kmlz.optcredit.utils.Helper;
import com.kmlz.optcredit.utils.PreferenceIO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OffersFragment extends Fragment {

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offer,container,false);
        initViews(view);
        if (Helper.isNetworkAvailable(getActivity())){
            getOffers();
        }
        return view;
    }

    private void getOffers() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        MainRequestBody mainRequestBody = new MainRequestBody();
        mainRequestBody.setToken(PreferenceIO.getInstance(getActivity()).readParam(Constants.KEY_PREF_TOKEN));
        apiInterface.getOffers(mainRequestBody).enqueue(new Callback<OffersResponse>() {
            @Override
            public void onResponse(Call<OffersResponse> call, Response<OffersResponse> response) {
                if (response.body() != null){
                    if (response.body().getCode().equals("1032")){
                        List<OfferObj> offerObjs = response.body().getBody();
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                        recyclerView.setLayoutManager(linearLayoutManager);
                        OffersAdapter offersAdapter = new OffersAdapter(offerObjs,getActivity());
                        recyclerView.setAdapter(offersAdapter);
                    } else {

                    }
                }
            }

            @Override
            public void onFailure(Call<OffersResponse> call, Throwable t) {

            }
        });
    }


    private void initViews(View view){
        recyclerView = view.findViewById(R.id.id_recycler_view_offer);
    }

}
