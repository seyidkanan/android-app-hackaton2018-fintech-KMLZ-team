package com.kmlz.optcredit.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.kmlz.optcredit.R;

public class NewExpenceActivity extends AppCompatActivity {


    EditText ed_amount, ed_name;
    Spinner spin_type;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expence);

        initViews();

    }

    private void initViews(){
        ed_amount = findViewById(R.id.edit_text_amount);
        ed_name = findViewById(R.id.edit_text_name);
        spin_type = findViewById(R.id.id_spinner_expence_type);
        add = findViewById(R.id.id_button_add_exp);

    }

    public void addExpence(View view) {
        if ( !ed_amount.getText().toString().isEmpty()
                || !ed_name.getText().toString().isEmpty()
                )
                {
                sendDataToServer();

        } else {
            Toast.makeText(this,getString(R.string.empty_fields),Toast.LENGTH_LONG).show();
        }
    }

    private  void sendDataToServer(){

    }
}
