package com.example.quinten.addressbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
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

    }
}
