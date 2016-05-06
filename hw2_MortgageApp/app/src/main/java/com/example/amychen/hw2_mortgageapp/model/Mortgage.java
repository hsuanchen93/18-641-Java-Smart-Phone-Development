package com.example.amychen.hw2_mortgageapp.model;

import android.content.Context;

import com.example.amychen.hw2_mortgageapp.util.DatabaseConnector;

/**
 * Mortgage Model
 */
public class Mortgage {
    // User Input
    private double purchasePrice;
    private double mortgageTerm;
    private double interestRate;
    private int firstPaymentYear;
    private int firstPaymentMonth;

    // Calculation Output
    private double monthlyPayment;
    private double totalPayment;
    private int payoffDateYear;
    private int payoffDateMonth;

    /**
     * Default Constructor
     */
    public Mortgage() {
        purchasePrice = -1;
        mortgageTerm = -1;
        interestRate = -1;
        firstPaymentYear = -1;
        firstPaymentMonth = -1;
    }

    /**
     * Public Constructor
     * @param purchasePrice
     * @param mortgageTerm
     * @param interestRate
     * @param firstPaymentYear
     * @param firstPaymentMonth
     */
    public Mortgage(double purchasePrice, double mortgageTerm, double interestRate, int firstPaymentYear, int firstPaymentMonth) {
        this.purchasePrice = purchasePrice;
        this.mortgageTerm = mortgageTerm;
        this.interestRate = interestRate;
        this.firstPaymentYear = firstPaymentYear;
        this.firstPaymentMonth = firstPaymentMonth;
    }

    /**
     * Getters
     */
    public double getMonthlyPayment() {
        return monthlyPayment;
    }
    public double getTotalPayment() {
        return totalPayment;
    }
    public int getPayoffDateYear() {
        return payoffDateYear;
    }
    public int getPayoffDateMonth() {
        return payoffDateMonth;
    }

    /**
     * calculate Method
     */
    public void calculate() {
        // Get monthly payment
        double monthlyInterest = interestRate/100.0/12.0;
        monthlyPayment = (purchasePrice*monthlyInterest)/(1 - Math.pow(1+monthlyInterest, -mortgageTerm*12.0));

        // Get total payment
        totalPayment = monthlyPayment*12.0*mortgageTerm;

        // Get payoff date
        int numMonths = firstPaymentMonth + ((Double) (mortgageTerm * 12.0)).intValue();
        int numYears = numMonths/12;
        payoffDateYear = firstPaymentYear+numYears;
        payoffDateMonth = numMonths-numYears*12;
    }

    /**
     * saveToDB
     */
    public void saveToDB(Context context) {
        DatabaseConnector databaseConnector = new DatabaseConnector(context);
        databaseConnector.insert(purchasePrice, mortgageTerm, interestRate, firstPaymentYear, firstPaymentMonth, monthlyPayment, totalPayment, payoffDateYear, payoffDateMonth);
        databaseConnector.printDatabase();
    }
}
