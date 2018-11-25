package com.kmlz.optcredit.ui.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.kmlz.optcredit.R;
import com.kmlz.optcredit.network.ApiClient;
import com.kmlz.optcredit.network.ApiInterface;
import com.kmlz.optcredit.network.responses.CreditTypesResponse;
import com.kmlz.optcredit.utils.Helper;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalculateCreditFragment extends Fragment {


    EditText ed_percent, ed_lenght, ed_amount;
    Spinner spin_type;
    Button calculate;
    List<String> ids = new ArrayList<>();
    private AlertDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator,container,false);
        initViews(view);
        progressDialog = new SpotsDialog.Builder().setContext(getActivity()).build();
        configClickListeners();
        if( Helper.isNetworkAvailable(getActivity())){
            getCrediTypes();
        }

        return view;
    }

    private void getCrediTypes() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getCreditTypes().enqueue(new Callback<CreditTypesResponse>() {
            @Override
            public void onResponse(Call<CreditTypesResponse> call, Response<CreditTypesResponse> response) {
                if (response.body() != null) {
                    if (response.body().getCode().equals("1019")){
                        String [] types = new String[response.body().getResp().size()];
                        for (int i = 0; i<types.length;i++){
                            types[i] = response.body().getResp().get(i).getTYPE_NAME();
                            ids.add(response.body().getResp().get(i).getTYPE_ID());
                        }
                        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(getActivity(),
                                android.R.layout.simple_spinner_dropdown_item,
                                types);
                        spin_type.setAdapter(spinnerArrayAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<CreditTypesResponse> call, Throwable t) {

            }
        });
    }


    private void  configClickListeners(){
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    private  void initViews(View view){
        ed_amount = view.findViewById(R.id.edit_text_amount);
        ed_lenght = view.findViewById(R.id.edit_text_month);
        ed_percent = view.findViewById(R.id.edit_text_percent);
        spin_type = view.findViewById(R.id.id_spinner_credit_type);
        calculate = view.findViewById(R.id.id_button_calculate);
    }
}
