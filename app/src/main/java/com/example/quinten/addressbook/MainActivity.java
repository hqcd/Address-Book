/*
* Created by Quinten Whitaker on 11/14/18
* Homework 3 - Address BOok
* */
package com.example.quinten.addressbook;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AddressBookDatabaseHelper addressBookDatabaseHelper;
    ListView contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contactList = (ListView)findViewById(R.id.contactList);
        addressBookDatabaseHelper = new AddressBookDatabaseHelper(this);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        //Update the list view with names from the db
        updateContactList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.addContact) {
            addContact();
        }

        return super.onOptionsItemSelected(item);
    }

    public void addContact()
    {
        Intent intent = new Intent(getApplicationContext(), NewContact.class);
        startActivity(intent);
    }

    public void updateContactList()
    {
        Cursor data = addressBookDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext())
        {
            listData.add(data.getString(0));
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        contactList.setAdapter(adapter);
        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getItemAtPosition(position).toString();

                Intent intent = new Intent(getApplicationContext(), ViewContact.class);
                intent.putExtra("name", name);
                startActivity(intent);

            }
        });

        System.out.println("CONTACT LIST UPDATED");
    }


}
