package com.example.quinten.addressbook;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    AddressBookDatabaseHelper myDBAdapter;
    ListView contactList;
    SQLiteDatabase myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contactList = (ListView)findViewById(R.id.contactList);

        myDBAdapter = new AddressBookDatabaseHelper(this);
        myDB = myDBAdapter.getReadableDatabase();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        //updateContactList();
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
        //Select only the NAME column
        String[] projection = {AddressBookDatabaseHelper.COL_1, AddressBookDatabaseHelper.COL_2};

        //Query the db for all the values in the NAME column
        Cursor cursor = myDB.query("contact_table", projection, null, null, null, null, null, null);

        //Adapter binds the data from the cursor to the list view
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, null, null, 0);
        contactList.setAdapter(adapter);


    }
}
