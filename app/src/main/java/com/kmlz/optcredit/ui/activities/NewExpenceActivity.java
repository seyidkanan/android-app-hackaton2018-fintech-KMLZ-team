package com.kmlz.optcredit.ui.activities;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.kmlz.optcredit.R;
import com.kmlz.optcredit.network.ApiClient;
import com.kmlz.optcredit.network.ApiInterface;
import com.kmlz.optcredit.network.request.ExpenseRequest;
import com.kmlz.optcredit.network.responses.CategoryListResponses;
import com.kmlz.optcredit.network.responses.CategoryResponse;
import com.kmlz.optcredit.network.responses.MainResponse;
import com.kmlz.optcredit.ui.adapters.SpinnerAdapter;
import com.kmlz.optcredit.utils.Constants;
import com.kmlz.optcredit.utils.Helper;
import com.kmlz.optcredit.utils.PreferenceIO;

import java.util.ArrayList;
import java.util.List;

import cn.refactor.lib.colordialog.PromptDialog;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewExpenceActivity extends AppCompatActivity {

    private int catId = 0;

    EditText ed_amount, ed_name;
    Spinner spin_type;
    Button add;

    List<String> ids = new ArrayList<>();
    ApiInterface apiInterface;

    private AlertDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expence);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        initViews();
        if (Helper.isNetworkAvailable(this)) {
            getCategoryFromAPI();
        } else {
            Toast.makeText(NewExpenceActivity.this, getString(R.string.nointernet), Toast.LENGTH_LONG).show();
        }
    }

    private void getCategoryFromAPI() {
        progressDialog.show();
        apiInterface
                .getCategories()
                .enqueue(new Callback<CategoryListResponses>() {
                    @Override
                    public void onResponse(final Call<CategoryListResponses> call, final Response<CategoryListResponses> response) {
                        progressDialog.dismiss();
                        if (response.body() != null) {
                            if (response.body().getCode().equals(Constants.KEY_FOUND)) {
                                String [] types = new String[response.body().getResp().size()];
                                for (int i = 0; i<types.length;i++){
                                    types[i] = response.body().getResp().get(i).getCatName();
                                    ids.add(response.body().getResp().get(i).getCatId());
                                }
                                ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(NewExpenceActivity.this,
                                        android.R.layout.simple_spinner_dropdown_item,
                                        types);
                                spin_type.setAdapter(spinnerArrayAdapter);

                            } else {
                                new PromptDialog(NewExpenceActivity.this)
                                        .setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                                        .setAnimationEnable(true)
                                        .setTitleText("Error")
                                        .setContentText("Bu servisdən istifadə etməkdə çətinlik var. Biraz sonra yoxlayın.")
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
                    public void onFailure(Call<CategoryListResponses> call, Throwable t) {
                        progressDialog.dismiss();
                    }
                });
    }

    private void initViews() {
        progressDialog = new SpotsDialog.Builder().setContext(this).build();
        ed_amount = findViewById(R.id.edit_text_amount);
        ed_name = findViewById(R.id.edit_text_name);
        spin_type = findViewById(R.id.id_spinner_expence_type);
        add = findViewById(R.id.id_button_add_exp);

    }

    public void addExpence(View view) {
        if (!ed_amount.getText().toString().isEmpty()
                && !ed_name.getText().toString().isEmpty()) {
            if (Helper.isNetworkAvailable(this)) {
                sendDataToServer();
            } else {
                Toast.makeText(this, getString(R.string.nointernet), Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, getString(R.string.empty_fields), Toast.LENGTH_LONG).show();
        }
    }

    private void sendDataToServer() {
        ExpenseRequest expenseRequest = new ExpenseRequest();
        expenseRequest.setExpenseName(ed_name.getText().toString().trim());
        expenseRequest.setExpenseAmount(Double.parseDouble(ed_amount.getText().toString().trim()));
        expenseRequest.setToken(PreferenceIO.getInstance(this).readParam(Constants.KEY_PREF_TOKEN));
        expenseRequest.setExpenseCategory(ids.get(spin_type.getSelectedItemPosition()));
        apiInterface.addExpense(expenseRequest)
                .enqueue(new Callback<MainResponse>() {
                    @Override
                    public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                            if (response.body()!=null){
                                if (response.body().getCode().equals("1024")){
                                    Toast.makeText(NewExpenceActivity.this,"Əlavə olundu",Toast.LENGTH_LONG).show();
                                    finish();
                                }
                            }
                    }

                    @Override
                    public void onFailure(Call<MainResponse> call, Throwable t) {

                    }
                });
    }
}
