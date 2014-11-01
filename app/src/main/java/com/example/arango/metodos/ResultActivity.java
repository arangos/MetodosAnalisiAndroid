package com.example.arango.metodos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by Felipe on 01/11/2014.
 */
public class ResultActivity extends Activity {

    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados);
        // Get the message from the intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(ResultActivity.EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.txtResultados);
        if(message != null) {
            // Create the text view
            //textView.setTextSize(40);
            textView.setText(message);
        }else{
            textView.setTextSize(40);
            textView.setText("No hay resultados actualmente");
        }

        // Set the text view as the activity layout
        //setContentView(textView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_result, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_back:
                finish();
                break;
        }
        return true;
    }
}
