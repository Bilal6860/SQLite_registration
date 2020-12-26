package com.example.sqliteregistration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Login_activity extends AppCompatActivity {
    SQLiteDatabase objSQl;
    String selectquery;
    EditText etid, etpassword;
    Button btnlogin;
    String userid, userpassword, useremail, username;
    Boolean inputchecker = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        // Intent intent=getIntent();
        etid = findViewById(R.id.etLoginid);
        etpassword = findViewById(R.id.etLoginpassword);
        btnlogin = findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etid.getText().toString().isEmpty()) {
                    Toast.makeText(Login_activity.this, "All imputes are Required", Toast.LENGTH_SHORT).show();
                }
              else   if (etpassword.getText().toString().isEmpty()) {
                    Toast.makeText(Login_activity.this, "All imputes are Required", Toast.LENGTH_SHORT).show();
                } else {
                    createDatabase();
                    createTable();
                    getdata();
                }


            }
        });
    }

    public void getdata() {


        try {
            Cursor c = objSQl.rawQuery("SELECT id,name,email,password FROM AndroidJSonTable WHERE id='" + etid.getText().toString() + "'", null);
            c.moveToFirst();
            userid = c.getString(0);
            username = c.getString(1);
            useremail = c.getString(2);
            userpassword = c.getString(3);

            if (!userid.equals(etid.getText().toString())) {
                Toast.makeText(this, "id or password are invalid", Toast.LENGTH_SHORT).show();

            }
            if (!userpassword.equals(etpassword.getText().toString())) {
                Toast.makeText(this, "id or password are invalid", Toast.LENGTH_SHORT).show();
            } else {

                new AlertDialog.Builder(this)
                        .setTitle("Login Successful \n")
                        .setMessage("ID:\t\t" + userid + "\nName:\t\t" + username + "\nEmail:\t\t" + useremail + "\nPassword\t\t" + userpassword)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(R.drawable.done)
                        .show();
                c.close();
                etid.getText().clear();
                etpassword.getText().clear();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Record not found!", Toast.LENGTH_SHORT).show();
        }
    }

    public void createDatabase() {
        try {
            objSQl = openOrCreateDatabase("AndroidJSonDataBase", Context.MODE_PRIVATE, null);
        } catch (Exception ex) {
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }

    public void createTable() {
        try {
            objSQl.execSQL(
                    "CREATE TABLE IF NOT EXISTS AndroidJSonTable(" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            "name VARCHAR," +
                            " email VARCHAR " +
                            ",password VARCHAR);" +
                            "");
        } catch (Exception ex) {
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }

}

