package com.kmlz.optcredit.ui.activities;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kmlz.optcredit.R;
import com.kmlz.optcredit.network.ApiClient;
import com.kmlz.optcredit.network.ApiInterface;
import com.kmlz.optcredit.network.request.RegisterRequest;
import com.kmlz.optcredit.network.responses.MainResponse;

import cn.refactor.lib.colordialog.PromptDialog;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText ed_name, ed_email, ed_number, ed_pass;
    Button b_register;
    private AlertDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressDialog = new SpotsDialog.Builder().setContext(this).build();
        initViews();
        setupClickListeners();

    }

    private void setupClickListeners() {
        b_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( !ed_email.getText().toString().trim().isEmpty()
                        || !ed_name.getText().toString().trim().isEmpty()
                        || !ed_number.getText().toString().trim().isEmpty()
                        || !ed_pass.getText().toString().trim().isEmpty()

                        ){

                    registerUser();

                } else {
                    Toast.makeText(RegisterActivity.this, getString(R.string.empty_fields), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private  void registerUser(){
        progressDialog.show();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(ed_email.getText().toString().trim());
        registerRequest.setFirstname(ed_name.getText().toString().trim());
        registerRequest.setNumber(ed_number.getText().toString().trim());
        registerRequest.setPass(ed_pass.getText().toString().trim());
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.register(registerRequest).enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getCode().equals("1010")) {
                        Toast.makeText(RegisterActivity.this, getString(R.string.success_register),Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        new PromptDialog(RegisterActivity.this)
                                .setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                                .setAnimationEnable(true)
                                .setTitleText("Səhv")
                                .setContentText("Bu istifadəçi artıq qeydiyyatdan keçib")
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
                progressDialog.dismiss();
            }
        });
    }

    private  void initViews(){
        ed_email = findViewById(R.id.edit_text_mail);
        ed_name = findViewById(R.id.edit_text_name);
        ed_number = findViewById(R.id.edit_text_nubmer);
        ed_pass = findViewById(R.id.edit_text_pass);
        b_register = findViewById(R.id.btn_register);
    }
}
