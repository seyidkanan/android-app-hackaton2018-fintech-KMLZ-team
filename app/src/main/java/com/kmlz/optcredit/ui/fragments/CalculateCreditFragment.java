package com.kmlz.optcredit.ui.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.kmlz.optcredit.R;
import com.kmlz.optcredit.network.ApiClient;
import com.kmlz.optcredit.network.ApiInterface;
import com.kmlz.optcredit.network.request.CreditCalcRequest;
import com.kmlz.optcredit.network.responses.CreditTypesResponse;
import com.kmlz.optcredit.network.responses.MainResponse;
import com.kmlz.optcredit.ui.activities.LoginActivity;
import com.kmlz.optcredit.utils.Constants;
import com.kmlz.optcredit.utils.Helper;
import com.kmlz.optcredit.utils.PreferenceIO;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import cn.refactor.lib.colordialog.PromptDialog;
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
                if (!ed_amount.getText().toString().trim().isEmpty()
                        && !ed_lenght.getText().toString().trim().isEmpty()
                        && !ed_percent.getText().toString().trim().isEmpty()){

                    if (Helper.isNetworkAvailable(getActivity())) {
                        makeCalculations();
                    } else {
                        Toast.makeText(getActivity(),getString(R.string.nointernet),Toast.LENGTH_LONG).show();
                    }


                } else {
                    Toast.makeText(getActivity(),getString(R.string.empty_fields),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private  void makeCalculations(){

        double amount = Double.parseDouble(ed_amount.getText().toString().trim());
        int months = Integer.parseInt(ed_lenght.getText().toString().trim());
        int percents = Integer.parseInt(ed_percent.getText().toString().trim());
        Log.e("tag",amount+" "+months+" "+percents);
        double perMonthPay =  ((amount + (amount * (percents/100))) / months);
       // final double onnan = round(perMonthPay,2);
        CreditCalcRequest creditCalcRequest = new CreditCalcRequest();
        creditCalcRequest.setToken(PreferenceIO.getInstance(getActivity()).readParam(Constants.KEY_PREF_TOKEN));
        creditCalcRequest.setCal_loan(amount);
        creditCalcRequest.setCal_percent(percents);
        creditCalcRequest.setCal_period(months);
        creditCalcRequest.setCal_type(ids.get(spin_type.getSelectedItemPosition()));
        NumberFormat formatter = new DecimalFormat("#0.00");
        final String calculatedAmount = formatter.format(perMonthPay);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.calculateCredit(creditCalcRequest).enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if (response.body() != null){
                    if (response.body().getCode().equals("1014")) {
                        new PromptDialog(getActivity())
                                .setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                                .setAnimationEnable(true)
                                .setTitleText("")
                                .setContentText("Aylığ ödəniş "+ calculatedAmount+" azn təşkil edəcək")
                                .setPositiveListener("ok", new PromptDialog.OnPositiveListener() {
                                    @Override
                                    public void onClick(PromptDialog dialog) {
                                        dialog.dismiss();
                                    }
                                }).show();
                    } else {
                        new PromptDialog(getActivity())
                                .setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                                .setAnimationEnable(true)
                                .setTitleText("Səhv")
                                .setContentText("Serverda səhv baş verdi, zəhmət olmasa biraz sonra cəhd edin")
                                .setPositiveListener("ok", new PromptDialog.OnPositiveListener() {
                                    @Override
                                    public void onClick(PromptDialog dialog) {
                                        dialog.dismiss();
                                    }
                                }).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {

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
