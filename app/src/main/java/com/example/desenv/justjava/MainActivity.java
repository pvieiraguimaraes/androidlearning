package com.example.desenv.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int price;
    private int total;

    private int quantity = 0;

    private TextView quantityTextView;
    private TextView priceTextView;
    private CheckBox whippedCream;
    private CheckBox chocolate;
    private EditText nameClient;

    private String[] emailAddresses = {"pvieiraguimaraes@gmail.com"};

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

    public CheckBox getWhippedCream() {
        return whippedCream;
    }

    public void setWhippedCream(CheckBox whippedCream) {
        this.whippedCream = whippedCream;
    }

    public CheckBox getChocolate() {
        return chocolate;
    }

    public void setChocolate(CheckBox chocolate) {
        this.chocolate = chocolate;
    }

    public EditText getNameClient() {
        return nameClient;
    }

    public void setNameClient(EditText nameClient) {
        this.nameClient = nameClient;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setPrice(5);
        setTotal(0);

        setQuantityTextView((TextView) findViewById(R.id.quantity_text_view));
        setPriceTextView((TextView) findViewById(R.id.price_text_view));
        setWhippedCream((CheckBox) findViewById(R.id.whipped_cream));
        setChocolate((CheckBox) findViewById(R.id.chocolate));
        setNameClient((EditText) findViewById(R.id.name_client));
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        calculatePrice();
        createOrder();

        setTotal(0);
    }

    private void calculatePrice() {
        if (getWhippedCream().isChecked()) {
            setTotal(getTotal() + 1);
        }
        if (getChocolate().isChecked()) {
            setTotal(getTotal() + 2);
        }
    }

    private void createOrder() {
        String message = getString(R.string.order_title) + System.getProperty("line.separator");

        String nameClient = getNameClient().getText().toString();

        message += System.getProperty("line.separator") + getString(R.string.label_name) + nameClient;
        message += System.getProperty("line.separator") + getString(R.string.label_add_whipped_cream) + getWhippedCream().isChecked();
        message += System.getProperty("line.separator") + getString(R.string.label_add_chocolate) + getChocolate().isChecked();
        message += System.getProperty("line.separator") + getString(R.string.label_quantity) + getQuantity();
        message += System.getProperty("line.separator") + getString(R.string.label_total) + NumberFormat.getCurrencyInstance().format(getTotal());
        message += System.getProperty("line.separator") + getString(R.string.label_thank_you);

        String subject = getString(R.string.subject_email_order) + nameClient;
        composeEmail(emailAddresses, subject, message);
    }

    public void composeEmail(String[] addresses, String subject, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);

        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, getString(R.string.send_email_message)));
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display() {
        getQuantityTextView().setText(String.valueOf(getQuantity()));

        setTotal(getQuantity() * getPrice());
        getPriceTextView().setText(getString(R.string.label_total) + NumberFormat.getCurrencyInstance().format(getTotal()));
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(String message) {
        getPriceTextView().setText(message);
    }

    public void increase(View view) {
        if (getQuantity() == 100) {
            showMessage(getString(R.string.max_value_quantity_message));
        } else {
            setQuantity(Integer.parseInt(getQuantityTextView().getText().toString()) + 1);
            display();
        }
    }

    public void decrease(View view) {
        if (getQuantity() == 1) {
            showMessage(getString(R.string.min_value_quantity_message));
        } else {
            setQuantity(Integer.parseInt(getQuantityTextView().getText().toString()) - 1);
            display();
        }
    }

    private void showMessage(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }
}