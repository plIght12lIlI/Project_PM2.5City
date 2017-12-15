package com.example.user.project_pm2dot5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String URL="http://opendata2.epa.gov.tw/AQX.json";
    RequestQueue requestQueue=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.button);
        requestQueue=Volley.newRequestQueue(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                int length = response.length();
                                for (int i = 0; i < length; i++) {
                                    try {
                                        JSONObject object = response.getJSONObject(i);
                                        String country=object.getString("County");
                                        String sitename = object.getString("SiteName");
                                        String pm25=object.getString("PM2.5");
                                        Log.d("XXXXXXXXXXXXXXX",country+":"+sitename+":"+pm25);
                                    } catch (JSONException e) {
                                    }
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("XXXXXXXXXX","VolleyError:"+error);
                    }
                });

                requestQueue.add(jsonArrayRequest);
            }
        });

    }
}
