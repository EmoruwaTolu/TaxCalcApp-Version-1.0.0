package com.example.toluemoruwa.taxcalc;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText currency;
    EditText price;
    TextView tax;
    TextView taxcurr; //tax currency for tax
    TextView total;
    TextView totalcurrency; //Total currency infront of total
    public static final String MainActivity = "MainActivity";
    public static final String TaxCalcOrigins = "TaxCalcOrigins";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        currency = (EditText) findViewById(R.id.txtCurrency);
        price = (EditText) findViewById(R.id.txtPrice);
        tax = (TextView) findViewById(R.id.txtTax);
        taxcurr = (TextView) findViewById(R.id.txtTaxCurr);
        total = (TextView) findViewById(R.id.txtTotal);
        totalcurrency = (TextView) findViewById(R.id.txtCurr);


        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String y = price.getText().toString();
              //  String price = "0";
                try {
                    int z = Integer.parseInt(y);
                    int realTax = findTax(z);
                    double TaxValue = findTaxValue(z, realTax);
                    double TotalValue = findTotalValue(TaxValue, z);
                    tax.setText(realTax + "");
                    taxcurr.setText(TaxValue + "");
                    total.setText(TotalValue + "");
                }
                catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(),"Some error occured",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




        currency.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String content = currency.getText().toString(); //gets you the contents of edit text
                totalcurrency.setText(content); //displays it in a textview2


           //     System.out.println(tax);

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(MainActivity.this,TaxCalcOrigins.class);
                intent.putExtra("currency", currency.getText().toString());
                intent.putExtra("price", price.getText().toString());
                intent.putExtra("tax", tax.getText().toString());
                intent.putExtra("taxcurr", taxcurr.getText().toString());
                intent.putExtra("total", total.getText().toString());
                startActivity(intent);
               // startActivity(new Intent(MainActivity.this, TaxCalcOrigins.class));
            }
        });
    }
    //findTotalValue adds the value of the tax to the value of items purchased
    public double findTotalValue(double price,int taxcurr){
        double TotalValue = (price + taxcurr);
        return TotalValue;
    }

    //findTaxValue is created to find the numerical value of the tax based on the price of products being bought
    public double  findTaxValue(double price,int tax)
    {
       double TaxValue = (price * tax)/100;
        return TaxValue;
    }
    //findTax is created to determine the tax percentage for the products bought
    public int findTax(int price) {
        int x = 0;

        if (price <= 500) {
            x = 5;
        } else if (price > 500 && price <= 1000) {
            x = 10;
        } else if (price > 1000 && price <= 1500) {
            x = 15;
        } else if (price > 1500) {
            x = 20;
        }

        return x;

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
                return super.onOptionsItemSelected(item);
    }
}
