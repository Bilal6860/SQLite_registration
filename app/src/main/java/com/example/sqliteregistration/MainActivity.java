package com.example.sqliteregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etid, etnam, etemail, etpassword;
    Button btnregister, btnlogin, btn_goto;
    ScrollView scrollView;
    SQLiteDatabase sqLiteDb_Obj;
    Boolean inputchecker = true;
    String insert_query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etid = findViewById(R.id.etID);
        etnam = findViewById(R.id.etname);
        etemail = findViewById(R.id.etemail);
        etpassword = findViewById(R.id.etpassword);
        btn_goto = findViewById(R.id.btn_goto);
        btnlogin = findViewById(R.id.btnlogin);
        btnregister = findViewById(R.id.btnregister);
        scrollView = findViewById(R.id.scrollView);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etid.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "All imputes are required", Toast.LENGTH_SHORT).show();

                }
           else if (etnam.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "All imputes are required", Toast.LENGTH_SHORT).show();

                }
                else if (etemail.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "All imputes are required", Toast.LENGTH_SHORT).show();

                }
            else if (etpassword.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "All imputes are required", Toast.LENGTH_SHORT).show();

                }
                else {
                    createDatabase();
                    createTable();
                    insert_function();
                }
            }
        });
        btn_goto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_to();
            }
        });
    }

    public void go_to() {
        Intent intent = new Intent(this, Login_activity.class);
        startActivity(intent);
    }

    public void createDatabase() {
        try {
            sqLiteDb_Obj = openOrCreateDatabase("AndroidJSonDataBase", Context.MODE_PRIVATE, null);
        } catch (Exception ex) {
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }

    public void createTable() {
        try {
            sqLiteDb_Obj.execSQL(
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

    public void insert_function() {
        try {
            insert_query =
                    "INSERT INTO AndroidJSonTable(id,name,email,password)" +
                            "VALUES('" + etid.getText().toString() + "'," +
                            "'" + etnam.getText().toString() + "'," +
                            "'" + etemail.getText().toString() + "'," +
                            "'" + etpassword.getText().toString() + "')";
            sqLiteDb_Obj.execSQL(insert_query);
            Toast.makeText(MainActivity.this, "You are successfully Registered ", Toast.LENGTH_SHORT).show();
            etid.getText().clear();
            etnam.getText().clear();
            etemail.getText().clear();
            etpassword.getText().clear();

        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, "Something went wrong! enter a different ID", Toast.LENGTH_LONG).show();
        }
    }
}