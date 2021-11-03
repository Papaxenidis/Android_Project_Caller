package com.example.facecaller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Diagrafi_ extends AppCompatActivity {


    EditText name;
    Button button;
    SharedPreferences sp;


    DBHelper DB;
    int n;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagrafi_);


        button = findViewById(R.id.butdel);
        name = findViewById(R.id.etname);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 :- for private mode



        DB = new DBHelper(this);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String nameTXT =  name.getText().toString();


                if(nameTXT.isEmpty())
                {

                    name.setError("Το πεδίο είναι υποχρεωτικό");
                    name.requestFocus();

                }else
                {

                    Boolean checkdeletedata = DB.deletedata(nameTXT);

                    if(checkdeletedata == true)
                    {
                        Toast.makeText(Diagrafi_.this, "Η επαφή διεγράφη επιτυχώς!", Toast.LENGTH_SHORT).show();
                        name.setText(" ");


                        SharedPreferences.Editor editor = pref.edit();

                        int x = pref.getInt("key_name", 0);
                        x-=1;

                        editor.putInt("key_name", x);
                        editor.commit();


                    }else
                    {
                        Toast.makeText(Diagrafi_.this, "Η επαφή που τυπώσατε δεν βρέθηκε.", Toast.LENGTH_SHORT).show();
                    }



                }







            }
        });




    }
}