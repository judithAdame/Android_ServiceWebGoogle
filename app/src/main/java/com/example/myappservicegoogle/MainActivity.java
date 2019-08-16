package com.example.myappservicegoogle;
//PRATIQUE CONNECTION A UN SERVICE WEB DE GOOGLE!!!!
//https://maps.googleapis.com/maps/api/timezone/json?location=39.6034810,-119.6822510&timestamp=1331161200&key=AIzaSyARiki0HBLlyR7xH0K3e4eifaSLzx8b-7E
//https://developers.google.com/maps/documentation/timezone/intro#Requests
//https://drive.google.com/file/d/1bSudAIfnvoPkWx_nKvs4RP6dvNMIYP4p/view
//https://drive.google.com/file/d/1Eh3J6sHxa0fWxRWEqSZy0QXtE4_UMeqT/view
//https://drive.google.com/file/d/1xv-orzvE9OxJTepGPDChYc2h9IN2jx4s/view

//https://maps.googleapis.com/maps/api/distancematrix/json?origins=Vancouver+BC|Seattle&destinations=San+Francisco|Victoria+BC&mode=bicycling&language=fr-FR&key=AIzaSyARiki0HBLlyR7xH0K3e4eifaSLzx8b-7E
//https://developers.google.com/maps/documentation/distance-matrix/intro#RequestParameters
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickChercher(View Main2Activity) {
        TextView latitud = (TextView) findViewById(R.id.editTextLatitud);
        TextView altitud = (TextView) findViewById(R.id.editTextAltitud);
        Intent monIntent = new Intent(this, Main2Activity.class);
        monIntent.putExtra("latitud", Double.parseDouble(latitud.getText().toString()));
        monIntent.putExtra("altitud", Double.parseDouble(altitud.getText().toString()));
        startActivity(monIntent);
    }
}
