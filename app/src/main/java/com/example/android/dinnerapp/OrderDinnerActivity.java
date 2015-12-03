/*
 * Copyright (C) 2015 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
 
package com.example.android.dinnerapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;


public class OrderDinnerActivity extends Activity {
    String selectedDinnerExtrasKey = String.valueOf(R.string.selected_dinner);
    String thisDinner;
    Button btnAddToCart;
    Button btnStartCheckout;
    Button btnPurchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_dinner);
    }

    protected void onStart() {
        super.onStart();



        // Set the heading
        TextView heading_tv = (TextView) findViewById(R.id.textView_info_heading);
        heading_tv.setText(getResources().getText(R.string.order_online_heading));

        // Set the text
        TextView tv = (TextView) findViewById(R.id.textView_info);
        btnAddToCart = (Button) findViewById(R.id.add_to_cart_btn);
        btnStartCheckout = (Button) findViewById(R.id.start_checkout);
        btnPurchase = (Button) findViewById(R.id.btnPurchase);

        thisDinner = getIntent().getStringExtra(selectedDinnerExtrasKey);
        tv.setText("This is where you will order the selected dinner: \n\n" +
                thisDinner);

        sendViewProductHit(thisDinner, Utility.getDinnerId(thisDinner));
    }

    public void sendViewProductHit(String dinner, String dinnerID) {

        Product product = new Product()
                .setName("dinner")
                .setPrice(5)
                .setVariant(dinner)
                .setId(dinnerID)
                .setQuantity(1);

        ProductAction productAction = new ProductAction(ProductAction.ACTION_DETAIL);

        Tracker tracker = ((MyApplication) getApplication()).getTracker();

        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("Shopping steps")
                .setAction("View Order Dinner Screen")
                .setLabel(dinner)
                .addProduct(product)
                .setProductAction(productAction)
                .build());
    }

    public void addDinnerToCart(View view) {

        // Code goes here to add the dinner to the cart

        Utility.showMyToast("I will add the dinner " + thisDinner + " to the cart", this);

        // Also send an Analytics hit
        sendAddToCartHit();

        btnAddToCart.setVisibility(View.INVISIBLE);
        btnStartCheckout.setVisibility(View.VISIBLE);


    }

    private  void sendAddToCartHit() {

        Product product = new Product()
                .setName("dinner")
                .setPrice(5)
                .setVariant(thisDinner)
                .setId(Utility.getDinnerId(thisDinner))
                .setQuantity(1);

        ProductAction productAction = new ProductAction(ProductAction.ACTION_ADD);

        Tracker tracker = ((MyApplication) getApplication()).getTracker();

        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("Shopping steps")
                .setAction("Add Order To Cart")
                .setLabel(thisDinner)
                .addProduct(product)
                .setProductAction(productAction)
                .build());

    }

    public void startCheckout(View view) {

        // Code goes here to add the dinner to the cart

        Utility.showMyToast("Starting the checkout process for " + thisDinner, this);

        // Also send an Analytics hit
        sendStartCheckoutHit();
        btnPurchase.setVisibility(View.VISIBLE);
        btnStartCheckout.setVisibility(View.INVISIBLE);
    }

    private  void sendStartCheckoutHit() {

        Product product = new Product()
                .setName("dinner")
                .setPrice(5)
                .setVariant(thisDinner)
                .setId(Utility.getDinnerId(thisDinner))
                .setQuantity(1);

        ProductAction productAction = new ProductAction(ProductAction.ACTION_CHECKOUT);

        Tracker tracker = ((MyApplication) getApplication()).getTracker();

        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("Shopping steps")
                .setAction("Start checkout")
                .setLabel(thisDinner)
                .addProduct(product)
                .setProductAction(productAction)
                .build());

    }

    public void purchaseDinner(View view) {

        // Code goes here to add the dinner to the cart

        Utility.showMyToast("Purchasing " + thisDinner, this);

        // Also send an Analytics hit
        sendPurchaseHit();
    }

    private  void sendPurchaseHit() {

        Product product = new Product()
                .setName("dinner")
                .setPrice(5)
                .setVariant(thisDinner)
                .setId(Utility.getDinnerId(thisDinner))
                .setQuantity(1);

        ProductAction productAction = new ProductAction(ProductAction.ACTION_PURCHASE).setTransactionId(Utility.getUniqueTransactionId(Utility.getDinnerId(thisDinner)));

        Tracker tracker = ((MyApplication) getApplication()).getTracker();

        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("Shopping steps")
                .setAction("Purchase")
                .setLabel(thisDinner)
                .addProduct(product)
                .setProductAction(productAction)
                .build());

    }

}
