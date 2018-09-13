package com.example.deepak.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    int Quantity = 2;
    String str;
    String Name;

    public void submitOrder(View view) {
        CheckBox WhippedCream = (CheckBox) findViewById(R.id.cream);

        CheckBox Chocolate = (CheckBox) findViewById(R.id.chocolate);

        EditText EnterName = (EditText) findViewById(R.id.EnterName);

        boolean hasWhippedCream = WhippedCream.isChecked();

        boolean hasChocolate = Chocolate.isChecked();

        Name= EnterName.getText().toString();

        int price = calculatePrice(hasWhippedCream, hasChocolate);

        str = createOrder(price, hasWhippedCream, hasChocolate, Name);

        displayMessage(str);


    }

    public void EmailOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, str);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order Sumary : " + Name);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }


    public void display(int num) {
        TextView txt1 = (TextView) findViewById(R.id.qty_txt);
        txt1.setText(" " + num);
    }


    public void Increment(View view) {
        if (Quantity >= 100) {
            Toast.makeText(this, "Quantity cannot be greater than hundread", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Quantity = Quantity + 1;
            display(Quantity);
        }
    }

    public void Decrement(View view) {
        if (Quantity <= 1) {
            Toast.makeText(this, "Quantity cannot be leass than one", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Quantity = Quantity - 1;
            display(Quantity);
        }
    }

    public void displayMessage(String str) {
        TextView txt2 = (TextView) findViewById(R.id.price);

        txt2.setText(str);
    }

    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int baseprice = 5;

        if (hasWhippedCream) {
            baseprice += 1;
        }
        if (hasChocolate) {
            baseprice += 2;
        }

        int price = baseprice * Quantity;

        return price;
    }

    public String createOrder(int price, boolean haswhippedCream, boolean hasChocolate, String Name) {
        String str = "Name : " + Name + "\nHas Whipped Cream : " + haswhippedCream + "\nHas Chocolate : " + hasChocolate + "\nQuantity = " + Quantity + "\nTotal = $" + price + "\nThank You";
        return str;
    }


}
