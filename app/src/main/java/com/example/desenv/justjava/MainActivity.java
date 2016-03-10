package com.example.desenv.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    private int quantity = 0;

    private TextView quantityTextView;
    private TextView priceTextView;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public TextView getQuantityTextView() {
        return quantityTextView;
    }

    public void setQuantityTextView(TextView quantityTextView) {
        this.quantityTextView = quantityTextView;
    }

    public TextView getPriceTextView() {
        return priceTextView;
    }

    public void setPriceTextView(TextView priceTextView) {
        this.priceTextView = priceTextView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setQuantityTextView((TextView) findViewById(R.id.quantity_text_view));
        setPriceTextView((TextView) findViewById(R.id.price_text_view));
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayPrice(getQuantity() * 5);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        getQuantityTextView().setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        getPriceTextView().setText(NumberFormat.getCurrencyInstance().format(number));
    }

    public void increase(View view) {
        setQuantity(Integer.parseInt(getQuantityTextView().getText().toString()) + 1);
        display(getQuantity());
    }

    public void decrease(View view) {
        setQuantity(Integer.parseInt(getQuantityTextView().getText().toString()) - 1);
        display(getQuantity());
    }
}