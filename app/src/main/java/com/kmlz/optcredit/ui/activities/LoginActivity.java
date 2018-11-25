package com.kmlz.optcredit.ui.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kmlz.optcredit.R;
import com.kmlz.optcredit.network.ApiClient;
import com.kmlz.optcredit.network.ApiInterface;
import com.kmlz.optcredit.network.request.LoginRequest;
import com.kmlz.optcredit.network.responses.LoginResponse;
import com.kmlz.optcredit.utils.Constants;
import com.kmlz.optcredit.utils.Helper;
import com.kmlz.optcredit.utils.PreferenceIO;

import cn.refactor.lib.colordialog.PromptDialog;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button buttonLogin;
    private EditText e_login, e_pass;

    private AlertDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonLogin = findViewById(R.id.btn_login);
        e_login = findViewById(R.id.edit_text_mail);
        e_pass = findViewById(R.id.edit_text_pass);

        progressDialog = new SpotsDialog.Builder().setContext(this).build();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!e_login.getText().toString().trim().isEmpty() && !e_pass.getText().toString().trim().isEmpty()) {
                    if (Helper.isNetworkAvailable(LoginActivity.this)) {
                        logintioAccount();
                    } else {
                        Toast.makeText(LoginActivity.this, getString(R.string.nointernet), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, getString(R.string.empty_fields), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void logintioAccount() {
        progressDialog.show();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(e_login.getText().toString().trim());
        loginRequest.setPass(e_pass.getText().toString().trim());
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getResp().getCode().equals(Constants.KEY_SUCCES)) {
                        PreferenceIO.getInstance(LoginActivity.this).writeParam(Constants.KEY_PREF_TOKEN, response.body().getResp().getToken());
                        PreferenceIO.getInstance(LoginActivity.this).writeParam(Constants.KEY_PREF_USER_EXIST, "1");
                        startActivity(new Intent(LoginActivity.this, TabsActivity.class));
                    } else {
                        new PromptDialog(LoginActivity.this)
                                .setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                                .setAnimationEnable(true)
                                .setTitleText("Error")
                                .setContentText("Email və ya şifrə yalnışdır")
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
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();
                new PromptDialog(LoginActivity.this)
                        .setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                        .setAnimationEnable(true)
                        .setTitleText("Səhv")
                        .setContentText(t.getMessage())
                        .setPositiveListener("ok", new PromptDialog.OnPositiveListener() {
                            @Override
                            public void onClick(PromptDialog dialog) {
                                dialog.dismiss();
                            }
                        }).show();
            }
        });
    }
}
