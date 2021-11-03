package com.example.facecaller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {


    Button ok,clean;
    EditText editTextname,editTextnumber;
  TextView k;
    DBHelper DB;
    SharedPreferences sp;
    int n;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        clean = findViewById(R.id.transadd32);
        ok = findViewById(R.id.transadd56);



        editTextname = findViewById(R.id.etname);
        editTextnumber = findViewById(R.id.etnumber);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 :- for private mode
        DB = new DBHelper(this);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name = editTextname.getText().toString();
                String number = editTextnumber.getText().toString();
                int b = pref.getInt("key_name", 0);




                if(b<27)
                {
                    if(name.isEmpty())
                    {
                        editTextname.setError("Το πεδίο είναι υποχρεωτικό");
                        editTextname.requestFocus();
                    }else if(number.isEmpty())
                    {
                        editTextnumber.setError("Το πεδίο είναι υποχρεωτικό");
                        editTextnumber.requestFocus();

                    }else
                    {





                        Boolean checkinsertdata = DB.insertuserdata(name,number);//EDW

                        if(checkinsertdata == true)
                        {
                            Toast.makeText(MainActivity3.this, "Επιτυχής εισαγωγή", Toast.LENGTH_SHORT).show();
                            editTextname.setText("");
                            editTextnumber.setText("");

                            SharedPreferences.Editor editor = pref.edit();

                            int x = pref.getInt("key_name", 0);
                            x+=1;

                            editor.putInt("key_name", x);
                            editor.commit();



                        }else
                        {
                            Toast.makeText(MainActivity3.this, "Αποτυχής εισαγωγή", Toast.LENGTH_SHORT).show();
                        }


                    }



                }else
                {


                    Toast.makeText(MainActivity3.this, "Δεν μπορείτε να προσθέσετε άλλη επαφή", Toast.LENGTH_SHORT).show();




                }





            }



        });







        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              editTextname.setText("");
              editTextnumber.setText("");

                Toast.makeText(MainActivity3.this, String.valueOf(n), Toast.LENGTH_SHORT).show();







                }
        });



    }

}