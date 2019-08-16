package com.example.myappservicegoogle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main2Activity extends AppCompatActivity {
    TextView outTextViewDsOffset,outTextViewRawOffset,outTextViewTimeZoneId,outTextViewTimeZoneName,outTextViewStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        outTextViewDsOffset = (TextView) findViewById(R.id.textViewDstOffset2);
        outTextViewRawOffset = (TextView) findViewById(R.id.textViewRawOffset2);
        outTextViewTimeZoneId = (TextView) findViewById(R.id.textViewTimeZoneId2);
        outTextViewTimeZoneName = (TextView) findViewById(R.id.textViewTimeZoneName2);
        outTextViewStatus = (TextView) findViewById(R.id.textViewStatus2);
        new MyTask().execute();
    }

    private class MyTask extends AsyncTask<Void, Void, Void> {
        String oDsOffset, oRowOffset, oTimeZoneId, oTimeZoneName, oStatus;

        @Override
        protected Void doInBackground(Void... params) {
            java.net.URL url = null;

            Intent monIntent = getIntent();

            double InfoReceivedLatitud = ((Intent) monIntent).getDoubleExtra("latitud",99);
            double InfoReceivedAltitud = ((Intent) monIntent).getDoubleExtra("altitud",99);
            System.out.println("\n InfoReceivedLatitud: " + InfoReceivedLatitud);
            System.out.println("\n InfoReceivedAltitud: " + InfoReceivedAltitud);
            try{
                url = new URL("https://maps.googleapis.com/maps/api/timezone/json?location="+InfoReceivedLatitud+","+InfoReceivedAltitud+"&timestamp=1564987020&key=AIzaSyARiki0HBLlyR7xH0K3e4eifaSLzx8b-7E");

                HttpURLConnection client;

                client = (HttpURLConnection) url.openConnection();
                client.setRequestMethod("GET");

                int responseCode = client.getResponseCode();

                System.out.println("\n Sending 'GET' request to URL: " + url);
                System.out.println("\n Response code: " + responseCode);

                InputStreamReader myInput = new InputStreamReader (client.getInputStream());

                BufferedReader in = new BufferedReader(myInput);
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());

                JSONObject obj = new JSONObject(response.toString());

                oDsOffset = " "+obj.getInt("dstOffset");
                oRowOffset = " "+obj.getInt("rawOffset");
                oTimeZoneId = obj.getString("timeZoneId");
                oTimeZoneName= obj.getString("timeZoneName");
                oStatus= " "+obj.getString("status");

            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }catch(JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            outTextViewDsOffset.setText(oDsOffset);
            outTextViewRawOffset.setText(oRowOffset);
            outTextViewTimeZoneId.setText(oTimeZoneId);
            outTextViewTimeZoneName.setText(oTimeZoneName);
            outTextViewStatus.setText(oStatus);

            super.onPostExecute(result);
        }
    }
}
