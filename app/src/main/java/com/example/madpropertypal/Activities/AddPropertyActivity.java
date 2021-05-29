package com.example.madpropertypal.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madpropertypal.Databases.DatabaseHelperClass;
import com.example.madpropertypal.Constructors.PropertyModelClass;
import com.example.madpropertypal.R;
import com.google.android.material.textfield.TextInputLayout;

public class AddPropertyActivity extends AppCompatActivity {
    TextInputLayout txtName;
    TextInputLayout txtLocation;
    TextInputLayout txtSize;
    TextInputLayout txtPrice;
    TextInputLayout txtDescription;
    TextInputLayout txtConstructionYear;
    TextInputLayout txtFloorsNumber;
    TextInputLayout txtLocalAmenities;
    Integer txtBedroomsNumber;
    Integer txtBathroomsNumber;
    Button btn_add_property;
    Button btn_view_property;
    Spinner firstSpinner;
    Spinner secondSpinner;
    String spinnerError = "Select an option";
    NumberPicker np;
    NumberPicker np2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);

        //Variable declaration
        txtName = findViewById(R.id.txt_il_name);
        txtLocation = findViewById(R.id.txt_il_location);
        txtSize = findViewById(R.id.decimal_size);
        txtPrice = findViewById(R.id.decimal_price);
        txtLocalAmenities = findViewById(R.id.txt_local_amenities);
        txtDescription = findViewById(R.id.txt_description);
        txtConstructionYear = findViewById(R.id.txt_construction_year);
        txtFloorsNumber = findViewById(R.id.floors_number);

        btn_add_property = findViewById(R.id.button_add_property);
        btn_view_property = findViewById(R.id.button_view_property);

        firstSpinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> myAdapter = ArrayAdapter.createFromResource(AddPropertyActivity.this, R.array.home, android.R.layout.simple_spinner_item);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        firstSpinner.setAdapter(myAdapter);

        secondSpinner = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> myAdapter2 = ArrayAdapter.createFromResource(AddPropertyActivity.this, R.array.lease, android.R.layout.simple_spinner_item);
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        secondSpinner.setAdapter(myAdapter2);

        np = findViewById(R.id.np);
        np.setMinValue(1);
        np.setMaxValue(9);
        np.setWrapSelectorWheel(true);

        np2 = findViewById(R.id.np2);
        np2.setMinValue(1);
        np2.setMaxValue(9);
        np2.setWrapSelectorWheel(true);

        btn_add_property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Converting entered data into String Type
                String stringNameNumber = txtName.getEditText().getText().toString();
                String stringLocation = txtLocation.getEditText().getText().toString();
                String stringSize = txtSize.getEditText().getText().toString();
                String stringPrice = txtPrice.getEditText().getText().toString();
                //Converting selected options from Spinners into String Type
                String stringPropertyType = firstSpinner.getSelectedItem().toString();
                String stringLeaseType = secondSpinner.getSelectedItem().toString();
                String stringConstructionYear = txtConstructionYear.getEditText().getText().toString();
                String stringFloorsNumber = txtFloorsNumber.getEditText().getText().toString();
                String stringDescription = txtDescription.getEditText().getText().toString();
                String stringLocalAmenities = txtLocalAmenities.getEditText().getText().toString();
                //Getting the values from Number Pickers
                txtBedroomsNumber = np.getValue();
                txtBathroomsNumber = np2.getValue();
                //Then saving them into String Type
                String stringBedroomsNumber = txtBedroomsNumber.toString();
                String stringBathroomsNumber = txtBathroomsNumber.toString();

                if (stringNameNumber.isEmpty()) {
                    txtName.setError("Error, required field!");
                } else {
                    txtName.setError(null);
                }
                if (stringLocation.isEmpty()) {
                    txtLocation.setError("Error, required field!");
                } else {
                    txtLocation.setError(null);
                }
                if (stringSize.isEmpty()) {
                    txtSize.setError("Error, required field!");
                } else {
                    txtSize.setError(null);
                }
                if (stringPrice.isEmpty()) {
                    txtPrice.setError("Error, required field!");
                } else {
                    txtPrice.setError(null);
                }
                if (firstSpinner.getSelectedItemPosition() == 0) {
                    TextView spinner1Error = (TextView) firstSpinner.getSelectedView();
                    spinner1Error.setError("");
                    spinner1Error.setText(spinnerError);
                    spinner1Error.setTextColor(Color.RED);
                }
                if (secondSpinner.getSelectedItemPosition() == 0) {
                    TextView spinner2Error = (TextView) secondSpinner.getSelectedView();
                    spinner2Error.setError("");
                    spinner2Error.setText(spinnerError);
                    spinner2Error.setTextColor(Color.RED);
                }
                if (!stringNameNumber.isEmpty() && !stringLocation.isEmpty() && !stringSize.isEmpty() && !stringPrice.isEmpty() &&
                        firstSpinner.getSelectedItemPosition() != 0 && secondSpinner.getSelectedItemPosition() != 0) {
                    DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(AddPropertyActivity.this);
                    PropertyModelClass propertyModelClass = new PropertyModelClass(stringNameNumber, stringLocation, stringSize, stringPrice,
                            stringPropertyType, stringLeaseType, stringConstructionYear, stringFloorsNumber, stringDescription,
                            stringLocalAmenities, stringBedroomsNumber, stringBathroomsNumber);
                    databaseHelperClass.addProperty(propertyModelClass);
                    Toast.makeText(AddPropertyActivity.this, "Property was added", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                } else {
                    Toast.makeText(AddPropertyActivity.this, "Required fields cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_view_property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPropertyActivity.this, ShowPropertyActivity.class);
                startActivity(intent);
            }
        });

    }
}