package com.example.quinten.addressbook;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewContact extends AppCompatActivity {

    AddressBookDatabaseHelper mydb;

    EditText nameET, phoneET, emailET, streetET, cityET, stateET, zipET;
    String name, phone, email, street, city, state, zip;
    Button addContactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        nameET = (EditText)findViewById(R.id.nameET);
        phoneET = (EditText)findViewById(R.id.phoneET);
        emailET = (EditText)findViewById(R.id.emailET);
        streetET = (EditText)findViewById(R.id.streetET);
        cityET = (EditText)findViewById(R.id.cityET);
        stateET = (EditText)findViewById(R.id.stateET);
        zipET = (EditText)findViewById(R.id.zipET);

        addContactButton = (Button)findViewById(R.id.addContactButton);


        mydb = new AddressBookDatabaseHelper(this);

    }

    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.addContactButton:
                addContact(); break;
        }
    }

    public void addContact()
    {
        name = nameET.getText().toString();
        phone = phoneET.getText().toString();
        email = emailET.getText().toString();
        street = streetET.getText().toString();
        city = cityET.getText().toString();
        state = stateET.getText().toString();
        zip = zipET.getText().toString();

        //Make sure name field is inputted
        if(nameET.getText().toString().length() > 0)
        {
            boolean isInserted = mydb.insertData(name, phone, email, street, city, state, zip);

            if(isInserted)
            {
                Toast.makeText(getApplicationContext(), "Contact Added", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Failed to Add Contact", Toast.LENGTH_SHORT).show();

            }

            finish();
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
