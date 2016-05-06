package com.example.amychen.hw2_mortgageapp.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;
import java.text.DateFormatSymbols;

/**
 * Database
 */
public class DatabaseConnector {
    private static final String DATABASE_NAME = "mortgage";
    private SQLiteDatabase database; // database object
    private DatabaseOpenHelper databaseOpenHelper; // database helper

    /**
     * public Constructor
     * @param context
     */
    public DatabaseConnector(Context context) {
        // create a new DatabaseOpenHelper
        databaseOpenHelper = new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
    }

    /**
     * open - open the database connection
     */
    public void open() throws SQLException {
        // create or open a database for reading/writing
        database = databaseOpenHelper.getWritableDatabase();
    }

    /**
     * close - close the database connection
     */
    public void close() {
        if (database != null)
            database.close();
    }

    /**
     * insert - insert new user-input data into the database
     * @param purchasePrice
     * @param mortgageTerm
     * @param interestRate
     * @param firstPaymentYear
     * @param firstPaymentMonth
     */
    public void insert(double purchasePrice, double mortgageTerm, double interestRate, int firstPaymentYear, int firstPaymentMonth, double monthlyPayment, double totalPayment, int payoffDateYear, int payoffDateMonth) {
        ContentValues newMortgage = new ContentValues();
        newMortgage.put("purchasePrice", purchasePrice);
        newMortgage.put("mortgageTerm", mortgageTerm);
        newMortgage.put("interestRate", interestRate);
        newMortgage.put("firstPaymentYear", firstPaymentYear);
        newMortgage.put("firstPaymentMonth", firstPaymentMonth);
        newMortgage.put("monthlyPayment", monthlyPayment);
        newMortgage.put("totalPayment", totalPayment);
        newMortgage.put("payoffDateYear", payoffDateYear);
        newMortgage.put("payoffDateMonth", payoffDateMonth);

        try {
            open();
            database.insert(DATABASE_NAME, null, newMortgage);
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * printDatabase()
     * @return
     */
    public void printDatabase() {
        try {
            open();
            String query = "SELECT * FROM " + DATABASE_NAME;
            Cursor cursor = database.rawQuery(query, null);

            // print database content
            if (cursor != null && cursor.getCount() > 0) {
                int idIndex = cursor.getColumnIndex("_id");
                int purchasePriceIndex = cursor.getColumnIndex("purchasePrice");
                int mortgageTermIndex = cursor.getColumnIndex("mortgageTerm");
                int interestRateIndex = cursor.getColumnIndex("interestRate");
                int firstPaymentYearIndex = cursor.getColumnIndex("firstPaymentYear");
                int firstPaymentMonthIndex = cursor.getColumnIndex("firstPaymentMonth");
                int monthlyPaymentIndex = cursor.getColumnIndex("monthlyPayment");
                int totalPaymentIndex = cursor.getColumnIndex("totalPayment");
                int payoffDateYearIndex = cursor.getColumnIndex("payoffDateYear");
                int payoffDateMonthIndex = cursor.getColumnIndex("payoffDateMonth");
                if (cursor.moveToFirst()) {
                    do {
                        Log.i("ID", cursor.getString(idIndex));
                        Log.i("Purchase Price", cursor.getString(purchasePriceIndex));
                        Log.i("Mortgage Term", cursor.getString(mortgageTermIndex));
                        Log.i("InterestRate", cursor.getString(interestRateIndex));

                        int firstPaymentMonth = Integer.valueOf(cursor.getString(firstPaymentMonthIndex));
                        String firstPayment = new DateFormatSymbols().getMonths()[firstPaymentMonth-1] + ", " + cursor.getString(firstPaymentYearIndex);
                        Log.i("First Payment", firstPayment);

                        Log.i("Monthly Payment", cursor.getString(monthlyPaymentIndex));
                        Log.i("Total Payment", cursor.getString(totalPaymentIndex));

                        int payoffDateMonth = Integer.valueOf(cursor.getString(payoffDateMonthIndex));
                        String payoffDate = new DateFormatSymbols().getMonths()[payoffDateMonth-1] + ", " + cursor.getString(payoffDateYearIndex);
                        Log.i("Payoff Date", payoffDate);
                    } while (cursor.moveToNext());
                }
            }

            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * DatabaseOpenHelper Class
     */
    private class DatabaseOpenHelper extends SQLiteOpenHelper {
        /**
         * Public Constructor
         * @param context
         * @param name
         * @param factory
         * @param version
         */
        public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        /**
         * onCreate - create the mortgage table when the database is created
         * @param db
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            // Query to create a new table named mortgage
            String createQuery = "CREATE TABLE " + DATABASE_NAME +
                    "(_id integer primary key autoincrement," +
                    "purchasePrice REAL, mortgageTerm REAL, interestRate REAL, " +
                    "firstPaymentYear INTEGER, firstPaymentMonth INTEGER, monthlyPayment REAL, " +
                    "totalPayment REAL, payoffDateYear INTEGER, payoffDateMonth INTEGER);";
            db.execSQL(createQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
