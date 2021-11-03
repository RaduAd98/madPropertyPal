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

import com.example.madpropertypal.Constructors.PropertyModelClass;
import com.example.madpropertypal.Databases.DatabaseHelperClass;
import com.example.madpropertypal.R;
import com.google.android.material.textfield.TextInputLayout;

public class AddPropertyActivity extends AppCompatActivity {
    TextInputLayout txtName,
            txtLocation,
            txtSize,
            txtPrice,
            txtDescription,
            txtConstructionYear,
            txtFloorsNumber,
            txtLocalAmenities;
    Integer integerBedroomsNumber,
            integerBathroomsNumber;
    Button btn_add_property,
            btn_view_property;
    Spinner spinnerPropertyType,
            spinnerLeaseType;
    String spinnerError = "Select an option",
            textBoxError = "This is a required field";
    NumberPicker npBedrooms,
            npBathrooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);

        initializeViews();

        spinnerAdapter(spinnerPropertyType, R.array.home);
        spinnerAdapter(spinnerLeaseType, R.array.lease);

        setMinMaxValue(npBedrooms);
        setMinMaxValue(npBathrooms);

        btn_add_property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Converting entered data into String Type
                String stringNameNumber = txtName.getEditText().getText().toString();
                String stringLocation = txtLocation.getEditText().getText().toString();
                String stringSize = txtSize.getEditText().getText().toString();
                String stringPrice = txtPrice.getEditText().getText().toString();
                //Converting selected options from Spinners into String Type
                String stringPropertyType = spinnerPropertyType.getSelectedItem().toString();
                String stringLeaseType = spinnerLeaseType.getSelectedItem().toString();
                String stringConstructionYear = txtConstructionYear.getEditText().getText().toString();
                String stringFloorsNumber = txtFloorsNumber.getEditText().getText().toString();
                String stringDescription = txtDescription.getEditText().getText().toString();
                String stringLocalAmenities = txtLocalAmenities.getEditText().getText().toString();
                //Getting the values from Number Pickers
                integerBedroomsNumber = npBedrooms.getValue();
                integerBathroomsNumber = npBathrooms.getValue();
                //Then saving them into String Type
                String stringBedroomsNumber = integerBedroomsNumber.toString();
                String stringBathroomsNumber = integerBathroomsNumber.toString();

                checkTextBoxInput(stringNameNumber, txtName);
                checkTextBoxInput(stringLocation, txtLocation);
                checkTextBoxInput(stringSize, txtSize);
                checkTextBoxInput(stringPrice, txtPrice);

                checkSpinnerInput(spinnerPropertyType);
                checkSpinnerInput(spinnerLeaseType);

                if (!stringNameNumber.isEmpty() && !stringLocation.isEmpty() && !stringSize.isEmpty() && !stringPrice.isEmpty() &&
                        spinnerPropertyType.getSelectedItemPosition() != 0 && spinnerLeaseType.getSelectedItemPosition() != 0) {
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

    //Variable declaration
    private void initializeViews() {
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
        spinnerPropertyType = findViewById(R.id.spinnerPropertyType);
        spinnerLeaseType = findViewById(R.id.spinnerLeaseType);
        npBedrooms = findViewById(R.id.npBedrooms);
        npBathrooms = findViewById(R.id.npBathrooms);
    }

    private void spinnerAdapter(Spinner spinner, int array) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(AddPropertyActivity.this, array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setMinMaxValue(NumberPicker numberPicker) {
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(9);
        numberPicker.setWrapSelectorWheel(true);
    }

    private void checkTextBoxInput(String string, TextInputLayout textInputLayout) {
        if (string.isEmpty()) {
            textInputLayout.setError(textBoxError);
        } else {
            textInputLayout.setError(null);
        }
    }

    private void checkSpinnerInput(Spinner spinner) {
        if (spinner.getSelectedItemPosition() == 0) {
            TextView spinnerSetTextError = (TextView) spinner.getSelectedView();
            spinnerSetTextError.setText(spinnerError);
            spinnerSetTextError.setTextColor(Color.RED);
        }
    }
}