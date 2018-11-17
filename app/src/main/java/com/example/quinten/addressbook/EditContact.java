package com.example.quinten.addressbook;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditContact extends AppCompatActivity {

     EditText edit_nameET, edit_phoneET, edit_emailET, edit_streetET, edit_cityET, edit_stateET, edit_zipET;
     int id;
     String name, phone, email, street, city, state, zip;
     String newName, newPhone, newEmail, newStreet, newCity, newState, newZip;
     Button editContactButton;
     AddressBookDatabaseHelper addressBookDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        addressBookDatabaseHelper = new AddressBookDatabaseHelper(this);

        //Initialize Views
        edit_nameET = (EditText)findViewById(R.id.edit_nameET);
        edit_phoneET = (EditText)findViewById(R.id.edit_phoneET);
        edit_emailET = (EditText)findViewById(R.id.edit_emailET);
        edit_streetET = (EditText)findViewById(R.id.edit_streetET);
        edit_cityET = (EditText)findViewById(R.id.edit_cityET);
        edit_stateET = (EditText)findViewById(R.id.edit_stateET);
        edit_zipET = (EditText)findViewById(R.id.edit_zipET);

        //Get Strings from intent
        Intent intent = getIntent();

        id = intent.getIntExtra("id", 0);
        name = intent.getStringExtra("name");
        phone = intent.getStringExtra("phone");
        email = intent.getStringExtra("email");
        street = intent.getStringExtra("street");
        city = intent.getStringExtra("city");
        state = intent.getStringExtra("state");
        zip = intent.getStringExtra("zip");


        editContactButton = (Button)findViewById(R.id.editContactButton);

        //Fill the edittext fields
        populateEditFields();

        editContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit();
            }
        });

    }

    public void populateEditFields()
    {

        edit_nameET.setText(name);
        edit_phoneET.setText(phone);
        edit_emailET.setText(email);
        edit_streetET.setText(street);
        edit_cityET.setText(city);
        edit_stateET.setText(state);
        edit_zipET.setText(zip);

    }

    public void edit()
    {
        //Obtain the text from edittext fields
        newName = edit_nameET.getText().toString();
        newPhone = edit_phoneET.getText().toString();
        newEmail = edit_emailET.getText().toString();
        newStreet = edit_streetET.getText().toString();
        newCity = edit_cityET.getText().toString();
        newState = edit_stateET.getText().toString();
        newZip = edit_zipET.getText().toString();

        if(edit_nameET.getText().toString().length() > 0){
            addressBookDatabaseHelper.updateData(id, newName, newPhone, newEmail, newStreet, newCity, newState, newZip);
            Intent returnHome = new Intent(this, MainActivity.class);
            startActivity(returnHome);
        }
        else
        {
            AlertDialog.Builder nameNotNull = new AlertDialog.Builder(this);
            nameNotNull.setTitle("Name Required");
            nameNotNull.setMessage("The Name field must be filled in");
            nameNotNull.setPositiveButton("Ok", null);

            AlertDialog dialog = nameNotNull.create();
            dialog.show();
        }


    }


}
