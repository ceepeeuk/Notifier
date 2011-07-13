package com.statichiss.AuctionNotifier;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity implements View.OnClickListener {

    private final String TAG = getClass().getName();

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
                final DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());

                final EditText searchItem = new EditText(MainActivity.this);
                searchItem.setSingleLine();

                final Spinner durationSpinner = new Spinner(MainActivity.this);
                Cursor durationCursor = databaseHelper.getDurations();
                final SimpleCursorAdapter durationAdapter = new SimpleCursorAdapter(MainActivity.this, R.layout.simple_spinner_dropdown,
                        durationCursor, new String[]{DatabaseHelper.SCHEDULE_DESCRIPTION}, new int[]{R.id.name_entry});
                durationAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
                durationSpinner.setAdapter(durationAdapter);

                LinearLayout layout = new LinearLayout(MainActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.addView(searchItem);
                layout.addView(durationSpinner);

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext())
                        .setMessage(R.string.main_add_new_dialog_txt)
                        .setView(layout)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Add to DB
                                databaseHelper.addNewSearch(searchItem.getText().toString(), durationSpinner.getSelectedItemId());
                                // TODO Set new Alarm (leave for now, but use alarm helper to support phone restarts, need BroadcastReceiver too)

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
