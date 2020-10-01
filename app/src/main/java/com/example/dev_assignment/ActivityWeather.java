package com.example.dev_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.GridLayout;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ActivityWeather extends AppCompatActivity {

    //this is the JSON Data URL
    //make sure you are using the correct ip else it will not work
    private static final String URL_PRODUCTS = "https://samples.openweathermap.org/data/2.5/forecast/daily?id=524901&lang=zh_cn&appid=b1b15e88fa797225412429c1c50c122a1";
    //a list to store all the products

    List<Forecast> forecast;
    RecyclerView recyclerView;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1, GridLayout.VERTICAL, false));
        //initializing the productlist
        forecast = new ArrayList<>();
        //this method will fetch and parse json
        //to display it in recyclerview
        loadForecast();
    }

    private void loadForecast() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String address="";
                            //converting the string to json array object
                            JSONObject array = new JSONObject(response);
                            JSONArray Jarray  = array.getJSONArray("list");


                            JSONObject objLocation = array.getJSONObject("city");
                            address=objLocation.getString("name");

                            //traversing through all the object
                            for (int i = 0; i < Jarray.length(); i++) {
                                JSONObject forecasts = Jarray.getJSONObject(i);


                                //adding the product to product list
                                forecast.add(new Forecast(
                                        forecasts.getLong("dt"),
                                        forecasts.getDouble("pressure"),
                                        forecasts.getString("humidity")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            ForecastAdapter adapter = new ForecastAdapter(ActivityWeather.this,forecast,address);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
        {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<>();
                return MyData;
            }
        };

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}