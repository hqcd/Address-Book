package com.example.quinten.addressbook;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ViewContact extends AppCompatActivity {

    TextView nameTV, phoneTV, emailTV, streetTV, cityTV, stateTV, zipTV;
    int id;
    String name, phone, email, street, city, state, zip;
    Cursor cursor;
    AddressBookDatabaseHelper addressBookDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        nameTV = (TextView)findViewById(R.id.nameTV);
        phoneTV = (TextView)findViewById(R.id.phoneTV);
        emailTV = (TextView)findViewById(R.id.emailTV);
        streetTV = (TextView)findViewById(R.id.streetTV);
        cityTV = (TextView)findViewById(R.id.cityTV);
        stateTV = (TextView)findViewById(R.id.stateTV);
        zipTV = (TextView)findViewById(R.id.zipTV);

        Intent intent = getIntent();
        String selectedName = intent.getStringExtra("name");

        addressBookDatabaseHelper = new AddressBookDatabaseHelper(this);
        cursor = addressBookDatabaseHelper.getItemId(selectedName);
        cursor.moveToFirst();

        //Pull the data from the db for this specific entry
        id = cursor.getInt(0);
        name = cursor.getString(1);
        phone = cursor.getString(2);
        email = cursor.getString(3);
        street = cursor.getString(4);
        city = cursor.getString(5);
        state = cursor.getString(6);
        zip = cursor.getString(7);

        //Populate text views
        nameTV.setText(name);
        phoneTV.setText(phone);
        emailTV.setText(email);
        streetTV.setText(street);
        cityTV.setText(city);
        stateTV.setText(state);
        zipTV.setText(zip);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.editContact:
                editContact();break;
            case R.id.deleteContact:
                deleteContact(); break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void deleteContact()
    {
        AlertDialog.Builder deleteConfirmation = new AlertDialog.Builder(this);
        deleteConfirmation.setNegativeButton("Cancel", null);
        deleteConfirmation.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addressBookDatabaseHelper.delete(id);
                finish();
            }
        });
        deleteConfirmation.setMessage("Are you sure you want to delete this contact?");
        deleteConfirmation.setTitle("Delete Contact?");

        AlertDialog dialog = deleteConfirmation.create();
        dialog.show();

    }

    public void editContact()
    {
        //Pass contact fields to the new activity through explicit intent
        Intent i = new Intent(getApplicationContext(), EditContact.class);
        i.putExtra("id", id);
        i.putExtra("name", name);
        i.putExtra("phone", phone);
        i.putExtra("email", email);
        i.putExtra("street", street);
        i.putExtra("city", city);
        i.putExtra("state", state);
        i.putExtra("zip", zip);

        startActivity(i);

    }
}
