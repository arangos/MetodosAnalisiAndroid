package com.example.arango.metodos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Felipe on 01/11/2014.
 */
public class MainActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNoLineales = (Button) findViewById(R.id.btnNoLineales);
        btnNoLineales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new NoLinealesActivity();
                Intent intent = new Intent(getBaseContext(), NoLinealesActivity.class);
                startActivity(intent);
            }
        });
        Button btnLineales = (Button) findViewById(R.id.btnlineales);
        btnLineales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    new Ec_LinealesActivity();
                    Intent intent = new Intent(getBaseContext(), Ec_LinealesActivity.class);
                    startActivity(intent);
            }
        });
        Button btnInterpolacion = (Button) findViewById(R.id.btnInterpolacion);
        btnInterpolacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new InterpolacionActivity();
                Intent intent = new Intent(getBaseContext(), InterpolacionActivity.class);
                startActivity(intent);
            }
        });
        Button btnIntegraionNum = (Button) findViewById(R.id.btnIntegracionNum);
        btnIntegraionNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new IntegracionActivity();
                Intent intent = new Intent(getBaseContext(), IntegracionActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_aboutus:
                new AlertDialog.Builder(this)
                        .setTitle(R.string.dialog_title)
                        .setMessage(R.string.dialog_message)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
