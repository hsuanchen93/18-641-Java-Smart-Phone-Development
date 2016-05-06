package com.example.amychen.hw2_mortgageapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.amychen.hw2_mortgageapp.R;
import com.example.amychen.hw2_mortgageapp.model.Mortgage;

import java.util.ArrayList;

/**
 * MainActivity - allow user to enter values
 */
public class MainActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        // Populate the months in the dropdown menu
        Spinner year = (Spinner) findViewById(R.id.spinner_year);
        ArrayList<String> years = new ArrayList<String>();
        for(int i=2001; i<2032; i++) {
            years.add(String.valueOf(i));
        }
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(yearAdapter);

        // Populate the years in the dropdown menu
        Spinner month = (Spinner) findViewById(R.id.spinner_month);
        ArrayList<String> months = new ArrayList<String>();
        for(int i=1; i<13; i++) {
            months.add(String.valueOf(i));
        }
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, months);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month.setAdapter(monthAdapter);

        // Setup the "CALCULATE" button
        Button calculate = (Button) findViewById(R.id.button_calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input data
                double purchasePrice = Double.parseDouble(((EditText) findViewById(R.id.editText_PurchasePrice)).getText().toString());
                double mortgageTerm = Double.parseDouble(((EditText) findViewById(R.id.editText_MortgageTerm)).getText().toString());
                double interestRate = Double.parseDouble(((EditText) findViewById(R.id.editText_InterestRate)).getText().toString());
                int firstPaymentYear = Integer.parseInt(((Spinner) findViewById(R.id.spinner_year)).getSelectedItem().toString());
                int firstPaymentMonth = Integer.parseInt(((Spinner)findViewById(R.id.spinner_month)).getSelectedItem().toString());

                // Perform calculation
                Mortgage mortgage = new Mortgage(purchasePrice, mortgageTerm, interestRate, firstPaymentYear, firstPaymentMonth);
                mortgage.calculate();

                // Save data to database
                mortgage.saveToDB(mContext);

                // Start ResultActivity and send the calculation result
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("monthly_payment", mortgage.getMonthlyPayment());
                intent.putExtra("total_payment", mortgage.getTotalPayment());
                intent.putExtra("payoff_date_year", mortgage.getPayoffDateYear());
                intent.putExtra("payoff_date_month", mortgage.getPayoffDateMonth());
                startActivity(intent);
            }
        });
    }
}
