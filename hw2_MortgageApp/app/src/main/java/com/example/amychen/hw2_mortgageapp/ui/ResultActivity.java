package com.example.amychen.hw2_mortgageapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.amychen.hw2_mortgageapp.R;

import java.text.DateFormatSymbols;
import java.text.DecimalFormat;

/**
 * Display result of calculation
 */
public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle extras = getIntent().getExtras();

        double monthlyPayment = Double.parseDouble(extras.get("monthly_payment").toString());
        double totalPayment = Double.parseDouble(extras.get("total_payment").toString());
        int payoffDateYear = Integer.parseInt(extras.get("payoff_date_year").toString());
        int payoffDateMonth = Integer.parseInt(extras.get("payoff_date_month").toString());

        ((TextView) findViewById(R.id.textView_TotalMonthlyPaymentResult)).setText(new DecimalFormat("#.##").format(monthlyPayment));
        ((TextView) findViewById(R.id.textView_TotalMortgagePaymentResult)).setText(new DecimalFormat("#.##").format(totalPayment));
        ((TextView) findViewById(R.id.textView_PayoffDateResult)).setText(new DateFormatSymbols().getMonths()[payoffDateMonth-1] + ", " + String.valueOf(payoffDateYear));
    }
}
