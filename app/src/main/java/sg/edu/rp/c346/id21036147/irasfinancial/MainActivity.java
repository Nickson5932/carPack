package sg.edu.rp.c346.id21036147.irasfinancial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ListView lvCarpark;
    AsyncHttpClient client;
    ArrayAdapter<Carpark> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lvCarpark = findViewById(R.id.lv);
        client = new AsyncHttpClient();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Carpark> alCarpark = new ArrayList<Carpark>();

        client.get("https://api.data.gov.sg/v1/transport/carpark-availability", new JsonHttpResponseHandler() {

            String total;
            String type;
            String available;

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray jsonArrItems = response.getJSONArray("items");
                    JSONObject firstObj = jsonArrItems.getJSONObject(0);
                    JSONArray jsonArrInfo = firstObj.getJSONArray("carpark_info");
                    for (int i = 0; i < jsonArrInfo.length(); i++) {
                        JSONObject jsonObjForecast = jsonArrInfo.getJSONObject(i);
                        total = jsonObjForecast.getString("total_lots");
                        type = jsonObjForecast.getString("lot_type");
                        available = jsonObjForecast.getString("lots_available");
                        Carpark carpark = new Carpark(total, type, available);
                        alCarpark.add(carpark);
                    }
                } catch (JSONException e) {

                }
                aa = new ArrayAdapter<Carpark>(MainActivity.this, android.R.layout.simple_list_item_1, alCarpark);
                lvCarpark.setAdapter(aa);

            }

        });
    }
}