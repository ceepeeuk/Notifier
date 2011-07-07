package com.statichiss.AuctionNotifier;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button addNewButton = (Button) findViewById(R.id.main_add_new_btn);
        addNewButton.setOnClickListener(this);

        Button deleteButton = (Button) findViewById(R.id.main_add_new_btn);
        deleteButton.setOnClickListener(this);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_add_new_btn:
                final EditText searchItem = new EditText(MainActivity.this);
                searchItem.setSingleLine();
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext())
                        .setMessage(R.string.main_add_new_dialog_txt)
                        .setView(searchItem)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Add to DB
                                DatabaseHelper databaseHelper = new DatabaseHelper();
                                // Set new Alarm
                                // Check now
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });

                builder.create().show();
                break;
            case R.id.main_delete_btn:
                break;
        }
    }
}
