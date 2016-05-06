package com.example.amychen.hw2_mortgageapp.exception;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * MortgageException - custom exception handling
 */
public class MortgageException {
    private int errorno;
    private String errormsg;

    /* Constructor */
    public MortgageException() {
        super();
        errorno = 0;
        errormsg = null;
    }

    public MortgageException(int errorno, String errormsg) {
        super();
        this.errorno = errorno;
        this.errormsg = errormsg;
        printmyproblem();
    }

    /* Getters */
    public int getErrorno() {
        return errorno;
    }

    public String getErrormsg() {
        return errormsg;
    }

    /* Setters */
    public void setErrorno(int errorno) {
        this.errorno = errorno;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    /* Print */
    public void printmyproblem() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        String formattedDate = sdf.format(date);
        String message = formattedDate + ": AutoException [errorno=" +
                errorno + ", errormsg=" + errormsg + "\n";

        File logFile = new File("/data/data/com.example.amychen.hw2_mortgageapp/log.file");
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(message);
            buf.newLine();
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("%s", message);
    }

    /* Fix Exceptions */
    public static void fix(int errno) {
        FixMortgageException f = new FixMortgageException();
        switch (errno) {
            case 1:
                f.fix1(errno);
        }
    }

    /* List all possible Exceptions */
    public void allExceptions() {
        try {
            FileReader file = new FileReader("Exceptions.txt");
            BufferedReader buff = new BufferedReader(file);
            boolean eof = false;

            while (!eof) {
                String line = buff.readLine();
                if (line == null) {
                    eof = true;
                } else {
                    System.out.println(line);
                }
            }
            buff.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}