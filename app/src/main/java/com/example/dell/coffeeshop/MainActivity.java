package com.example.dell.coffeeshop;


/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity =2;
    private String price;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "you cannot have more than 100 cups of coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */

    private void displayQuantity(int numberOfCoffeess) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffeess);
    }


    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "you cannot have less than 1 cup of coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText text = (EditText) findViewById(R.id.name_field);
        String name = text.getText().toString();

        CheckBox americanoCheckbox = (CheckBox) findViewById(R.id.americano_checkbox);
        boolean hasamericano = americanoCheckbox.isChecked();

        CheckBox cafelatteCheckbox = (CheckBox) findViewById(R.id.cafelatte_checkbox);
        boolean hascafelatte = cafelatteCheckbox.isChecked();

        CheckBox cappuccinoCheckbox = (CheckBox) findViewById(R.id.cappuccino_checkbox);
        boolean hascappuccino = cappuccinoCheckbox.isChecked();

        CheckBox espressoCheckbox = (CheckBox) findViewById(R.id.espresso_checkbox);
        boolean hasespresso = espressoCheckbox.isChecked();

        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();

        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckbox.isChecked();

        CheckBox caremelCheckbox = (CheckBox) findViewById(R.id.caramel_checkbox);
        boolean hascaremel = caremelCheckbox.isChecked();


        CheckBox withsugarCheckbox = (CheckBox) findViewById(R.id.withsugar_checkbox);
        boolean haswithsugar = withsugarCheckbox.isChecked();

        CheckBox withoutsugarCheckbox = (CheckBox) findViewById(R.id.withoutsugarcheckbox);
        boolean haswithoutsugar = withoutsugarCheckbox.isChecked();


        int price = calculatePrice(hasamericano, hascafelatte, hascappuccino, hasespresso,
                haswithsugar, haswithoutsugar, hasWhippedCream, hasChocolate, hascaremel);
        int priceMessage = (createOrderSummary(name, price, hasamericano, hascafelatte, hascappuccino, hasespresso, haswithsugar
                , haswithoutsugar, hasWhippedCream, hasChocolate, hascaremel));

        {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, "coffeeshop order for" + name);
            intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }


    }

    private int createOrderSummary(String name, int price, boolean hasamericano, boolean hascafelatte,
                                   boolean hascappuccino, boolean hasespresso, boolean haswithsugar, boolean haswithoutsugar,
                                   boolean hasWhippedCream, boolean hasChocolate, boolean hascaremel) {
        String priceMessage = "Name:" + name;
        priceMessage += "\n Add americano: " + hasamericano;
        priceMessage += "\n Add cafelatte: " + hascafelatte;
        priceMessage += "\n Add cappuccino: " + hascafelatte;
        priceMessage += "\n Add espresso: " + hasespresso;
        priceMessage += "\n Add Whipped Cream: " + hasWhippedCream;
        priceMessage += "\n Add Chocolate: " + hasChocolate;
        priceMessage += "\n Add Cremel: " + hascaremel;
        priceMessage += "\n Add sugar: " + haswithsugar;
        priceMessage += "\n Add Without sugar: " + haswithoutsugar;
        priceMessage += "\n quantity: " + quantity;
        priceMessage += "\n Total Rs.: " + price;
        priceMessage += " \n Thank You";
        return Integer.parseInt(priceMessage);

    }


    private int calculatePrice(boolean addamericano, boolean addcafelatte,
                               boolean addcappuccino, boolean addespresso,
                               boolean addsugar, boolean addwithoutsugar,
                               boolean addWhippedCream, boolean addChocolate, boolean addcaremel) {
        int basePrice = 30;


        if (addamericano) {
            basePrice = basePrice + 30;
            return quantity * basePrice;
        }

        if (addcafelatte) {
            basePrice = basePrice + 40;
            return quantity * basePrice;
        }

        if (addcappuccino) {
            basePrice = basePrice + 50;
            return quantity * basePrice;
        }

        if (addespresso) {
            basePrice = basePrice + 60;
            return quantity * basePrice;
        }

        if (addChocolate) {
            basePrice = basePrice + 2;
            return quantity * basePrice;
        }

        if (addWhippedCream) {
            basePrice = basePrice + 3;
            return quantity * basePrice;
        }

        if (addcaremel) {
            basePrice = basePrice + 3;
            return quantity * basePrice;
        }

        if (addsugar) {
            basePrice = basePrice + 70;
            return quantity * basePrice;
        }

        if (addwithoutsugar) {
            basePrice = basePrice + 80;
            return quantity * basePrice;
        }


        return basePrice;
    }
}