package com.example.toluemoruwa.taxcalc;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.QRCode;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Tolu Emoruwa on 19/02/2018.
 */

public class TaxCalcOrigins extends Activity {
    ImageView imgv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr); // show UI
        imgv = (ImageView) findViewById(R.id.imageView);

        //the below are setting up the information that goes onto the receipt
        String passedCurrency = getIntent().getExtras().getString("currency");
        String passedPrice = getIntent().getExtras().getString("price");
        String passedTax = getIntent().getExtras().getString("tax");
        String passedTaxCurr = getIntent().getExtras().getString("taxcurr");
        String passedTotal = getIntent().getExtras().getString("total");
        Date curDate = new Date();
        SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        String DateToStr = formattedDate.format(curDate);

        String write = "Price(" + passedCurrency + "):" + passedPrice + System.getProperty("line.separator") +
                "Tax(%) : " + passedTax + System.getProperty("line.separator") +
                "Tax Value: " + passedTaxCurr + System.getProperty("line.separator") +
                "Total( " + passedCurrency + "):" +
                passedTotal + System.getProperty("line.separator") +
                "Date:" + curDate;//building the receipt
    //printBarCode(Random());
        printBarCode(write);
}
    private String Random() {
        Random rand = new Random();

        String Randoms = "";
            for (int i = 1; i <= 100; i++) {

                int n = rand.nextInt(50) + 1;

            Randoms = Randoms + n + ", ";
            }
            Randoms = Randoms.substring(0,Randoms.length()-2);
            return Randoms;
    }



    //printBarCode creates and prints a barcode with all the customers receipt details
    private void printBarCode(String text) {

        MultiFormatWriter mfw = new MultiFormatWriter();
        try {
            BitMatrix bmw = mfw.encode(text, BarcodeFormat.QR_CODE, 600, 600);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap btm = barcodeEncoder.createBitmap(bmw);
            imgv.setImageBitmap(btm);
        } catch (WriterException e) {
            e.printStackTrace();
            // String passedArg = getIntent().getExtras().getString("arg");
            // enteredValue.setText(passedArg);

        }
    }
}
