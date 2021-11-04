package com.example.madpropertypal.Activities;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madpropertypal.Adapters.PropertyAdapterClass;
import com.example.madpropertypal.Constructors.PropertyModelClass;
import com.example.madpropertypal.Databases.DatabaseHelperClass;
import com.example.madpropertypal.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class ShowPropertyActivity extends AppCompatActivity {

    EditText nameText,
            areaText,
            typeText,
            bedroomsText;
    Button advancedSearch,
            btnUpload;
    RecyclerView recyclerView;
    WebView browser;
    PropertyAdapterClass propertyAdapterClass;
    List<PropertyModelClass> propertyModelClasses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_property);

        initializeViews();

        recyclerView.setLayoutManager(new LinearLayoutManager(ShowPropertyActivity.this));
        recyclerView.setHasFixedSize(true);

        //Calling getPropertyList method from DatabaseHelperClass class to fetch the property list
        final DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(ShowPropertyActivity.this);
        propertyModelClasses = databaseHelperClass.getPropertyList();

        //Checking if the list contains any property
        if (propertyModelClasses.size() == 0) {
            Toast.makeText(ShowPropertyActivity.this, "No property is stored in the database", Toast.LENGTH_SHORT).show();
            nameText.setVisibility(View.GONE);
            areaText.setVisibility(View.GONE);
            typeText.setVisibility(View.GONE);
            bedroomsText.setVisibility(View.GONE);
            advancedSearch.setVisibility(View.GONE);
            btnUpload.setVisibility(View.GONE);
        }
        //If it contains at least one property, it shows the list using the Adapter
        else {
            propertyAdapterClass = new PropertyAdapterClass(propertyModelClasses, ShowPropertyActivity.this);
            recyclerView.setAdapter(propertyAdapterClass);
        }

        //Listener for text entry in search box
        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String search = nameText.getText().toString();
                //If every search box is empty, it shows the whole list
                if (search.isEmpty()) {
                    propertyModelClasses = databaseHelperClass.getPropertyList();
                    propertyAdapterClass.setProperty(propertyModelClasses);
                }
                //Otherwise it gets the filtered results
                else {
                    propertyModelClasses = databaseHelperClass.searchPropertyList(search);
                }
                //If the list is modified, the adapter is notified of the changes
                if (propertyModelClasses.size() != 0) {
                    Toast.makeText(ShowPropertyActivity.this, "Matching with " + propertyModelClasses.size() + " results", Toast.LENGTH_SHORT).show();
                    propertyAdapterClass.setProperty(propertyModelClasses);
                    propertyAdapterClass.notifyDataSetChanged();
                }
                //Adapter should be cleared if there are no corresponding results
                else {
                    Toast.makeText(ShowPropertyActivity.this, "No entry found", Toast.LENGTH_SHORT).show();
                    propertyModelClasses.clear();
                    propertyAdapterClass.setProperty(propertyModelClasses);
                    propertyAdapterClass.notifyItemRangeRemoved(0, propertyAdapterClass.getItemCount());
                }
            }
        });

        advancedSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //The keyboard is automatically closed after pressing the search button (helps with displaying the results correctly)
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    //Getting the text in a String format
                    String areaSearch = areaText.getText().toString();
                    String typeSearch = typeText.getText().toString();
                    String bedroomsSearch = bedroomsText.getText().toString();

                    //If every search box is empty, it shows the whole list
                    if (areaSearch.isEmpty() && typeSearch.isEmpty() && bedroomsSearch.isEmpty()) {
                        propertyModelClasses = databaseHelperClass.getPropertyList();
                    }
                    //Otherwise get the filtered results
                    else {
                        propertyModelClasses = databaseHelperClass.advancedSearchPropertyList(areaSearch, typeSearch, bedroomsSearch);
                    }
                    //If the filtered results has at least one entry, it shows it
                    if (propertyModelClasses.size() != 0) {
                        Toast.makeText(ShowPropertyActivity.this, "Found " + propertyModelClasses.size() + " results", Toast.LENGTH_SHORT).show();
                        propertyAdapterClass.setProperty(propertyModelClasses);
                        propertyAdapterClass.notifyDataSetChanged();
                    }
                    //Adapter should be cleared if there are no corresponding results
                    else {
                        Toast.makeText(ShowPropertyActivity.this, "No entry found", Toast.LENGTH_SHORT).show();
                        propertyModelClasses.clear();
                        propertyAdapterClass.setProperty(propertyModelClasses);
                        propertyAdapterClass.notifyItemRangeRemoved(0, propertyAdapterClass.getItemCount());
                    }
                } catch (Exception e) {
                    Toast.makeText(ShowPropertyActivity.this, "Insert something", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //After button press, the browser shows up and buttons disappear
                browser.setVisibility(View.VISIBLE);
                btnUpload.setVisibility(View.GONE);
                advancedSearch.setVisibility(View.GONE);
                StringBuilder jsonString = new StringBuilder("{\"userId\":\"ra6804a\",\"detailList\":[");

                //A loop for each object in the list
                for (PropertyModelClass p : propertyModelClasses) {
                    jsonString.append("{\"name\":\"").append(p.getNameNumber()).append("\",");
                    jsonString.append("\"location\":\"").append(p.getLocation()).append("\",");
                    jsonString.append("\"size\":\"").append(p.getSize()).append("\",");
                    jsonString.append("\"price\":\"").append(p.getPrice()).append("\",");
                    jsonString.append("\"property_type\":\"").append(p.getPropertyType()).append("\",");
                    jsonString.append("\"lease_type\":\"").append(p.getLeaseType()).append("\",");
                    jsonString.append("\"construction_year\":\"").append(p.getConstructionYear()).append("\",");
                    jsonString.append("\"floors_number\":\"").append(p.getFloorsNumber()).append("\",");
                    jsonString.append("\"description\":\"").append(p.getDescription()).append("\",");
                    jsonString.append("\"local_amenities\":\"").append(p.getLocalAmenities()).append("\",");
                    jsonString.append("\"bedrooms_number\":\"").append(p.getBedroomsNumber()).append("\",");
                    jsonString.append("\"bathrooms_number\":\"").append(p.getBathroomsNumber()).append("\"},");
                }
                //When the loop is over (no more properties), JSON must be closed
                jsonString.deleteCharAt(jsonString.length() - 1);
                jsonString.append("]}");
                try {
                    URL pageURL = new URL(getString(R.string.url));
                    HttpsURLConnection con = (HttpsURLConnection) pageURL.openConnection();
                    JsonThread myTask = new JsonThread(ShowPropertyActivity.this, con, jsonString.toString());
                    Thread t1 = new Thread(myTask, "JSON Thread");
                    t1.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //Method for back key
    @Override
    public void onBackPressed() {
        if (browser.getVisibility() == View.VISIBLE) {
            browser.setVisibility(View.GONE);
            btnUpload.setVisibility(View.VISIBLE);
            advancedSearch.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }

    static class JsonThread implements Runnable {
        final AppCompatActivity activity;
        final HttpURLConnection con;
        final String jsonPayLoad;

        public JsonThread(AppCompatActivity activity, HttpURLConnection con, String jsonPayload) {
            this.activity = activity;
            this.con = con;
            this.jsonPayLoad = jsonPayload;
        }

        @Override
        public void run() {
            String response = "";
            if (prepareConnection()) {
                response = postJson();
            } else {
                response = "Error preparing the connection";
            }
            showResult(response);
        }

        boolean prepareConnection() {
            try {
                con.setDoOutput(true);
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type,", "application/x-www-form-urlencoded");
                return true;
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            return false;
        }

        String postJson() {
            String response = "";
            try {
                String postParameters = "jsonpayload=" + URLEncoder.encode(jsonPayLoad, "UTF-8");
                con.setFixedLengthStreamingMode(postParameters.getBytes().length);
                PrintWriter out = new PrintWriter(con.getOutputStream());
                out.print(postParameters);
                out.close();
                int responseCode = con.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    response = readStream(con.getInputStream());
                } else {
                    response = "Error contacting server: " + responseCode;
                }
            } catch (Exception e) {
                response = "Error executing code";
            }
            return response;
        }

        void showResult(final String response) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String page = generatePage(response);
                    ((ShowPropertyActivity) activity).browser.loadData(page, "text/html", "UTF-8");
                }
            });
        }

        String readStream(InputStream in) {
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                String nextLine = "";
                while ((nextLine = reader.readLine()) != null) {
                    sb.append(nextLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String string = sb.toString();
            //Making the data that will be displayed more readable
            string = string.replace("{", "{</br>&ensp;&ensp;");
            string = string.replace(":", " : ");
            string = string.replace(",", ",</br>&ensp;&ensp;");
            string = string.replace("}", "</br>}");
            return string;
        }

        String generatePage(String content) {
            return "<html><body><p>" + content + "</p></body></html>";
        }
    }

    //Variables declaration
    private void initializeViews() {
        nameText = findViewById(R.id.edit_text_search_bar_name);
        areaText = findViewById(R.id.edit_text_search_bar_area);
        typeText = findViewById(R.id.edit_text_search_bar_type);
        bedroomsText = findViewById(R.id.edit_text_search_bar_bedrooms);
        advancedSearch = findViewById(R.id.button_advanced_search);
        btnUpload = findViewById(R.id.button_upload_final);
        browser = findViewById(R.id.browser);
        recyclerView = findViewById(R.id.recycler_view);
    }
}