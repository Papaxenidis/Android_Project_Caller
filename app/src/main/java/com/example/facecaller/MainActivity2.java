package com.example.facecaller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import static android.Manifest.permission.CALL_PHONE;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {


    DBHelper DB;

    TextView[] arrTxtView;
    String[] numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        arrTxtView = new TextView[54];
        numbers = new String[27];
        DB = new DBHelper(this);


        for (int i = 0; i < 54; i++) {


            String texId = "t" + (i + 1);

            int resID = getResources().getIdentifier(texId, "id", getPackageName());
            arrTxtView[i] = ((TextView) findViewById(resID));
            arrTxtView[i].setOnClickListener(this);


        }


        doit(arrTxtView);


    }


    private void doit(TextView[] arrTxtView) {


        Cursor res = DB.getdata();

        if (res.getCount() == 0) {
            Toast.makeText(MainActivity2.this, "Δεν υπάρχει διαθέσιμη επαφή", Toast.LENGTH_SHORT).show();
            return;

        }


        for (int i = 0; i <= 1000; i += 2) {
            if (res.moveToNext() == false) {
                break;


            }


            arrTxtView[i].setText(res.getString(0));
            arrTxtView[i + 1].setText(res.getString(1));


            arrTxtView[i].setVisibility(View.VISIBLE);
            arrTxtView[i + 1].setVisibility(View.VISIBLE);


        }


    }


    @Override
    public void onClick(View v) {


        String k = v.getResources().getResourceName(v.getId());
        String new_k = k.substring(k.length() - 3);

//check
        String numberOnly = new_k.replaceAll("[^0-9]", "");
        int w = Integer.parseInt(numberOnly);

        if (Integer.parseInt(numberOnly) % 2 == 1) {


            String arithmos_til = arrTxtView[w].getText().toString();

            Intent i = new Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:" + arithmos_til));

            if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                startActivity(i);
            } else {
                requestPermissions(new String[]{CALL_PHONE}, 1);
            }


        }


    }

}