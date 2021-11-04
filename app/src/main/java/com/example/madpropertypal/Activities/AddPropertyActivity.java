package com.example.madpropertypal.Activities;

import static com.example.madpropertypal.Utils.DataUtils.checkSpinnerInput;
import static com.example.madpropertypal.Utils.DataUtils.checkTextBoxInput;
import static com.example.madpropertypal.Utils.DataUtils.setMinMaxValue;
import static com.example.madpropertypal.Utils.DataUtils.spinnerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
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
    Button btn_add_property,
            btn_view_property;
    Spinner spnPropertyType,
            spnLeaseType;
    NumberPicker npBedrooms,
            npBathrooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);

        initializeViews();

        spinnerAdapter(AddPropertyActivity.this, spnPropertyType, R.array.home);
        spinnerAdapter(AddPropertyActivity.this, spnLeaseType, R.array.lease);

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
                String stringPropertyType = spnPropertyType.getSelectedItem().toString();
                String stringLeaseType = spnLeaseType.getSelectedItem().toString();
                String stringConstructionYear = txtConstructionYear.getEditText().getText().toString();
                String stringFloorsNumber = txtFloorsNumber.getEditText().getText().toString();
                String stringDescription = txtDescription.getEditText().getText().toString();
                String stringLocalAmenities = txtLocalAmenities.getEditText().getText().toString();

                //Getting the values from Number Pickers into String format
                String stringBedroomsNumber = String.valueOf(npBedrooms.getValue());
                String stringBathroomsNumber = String.valueOf(npBathrooms.getValue());

                checkTextBoxInput(AddPropertyActivity.this, stringNameNumber, txtName);
                checkTextBoxInput(AddPropertyActivity.this, stringLocation, txtLocation);
                checkTextBoxInput(AddPropertyActivity.this, stringSize, txtSize);
                checkTextBoxInput(AddPropertyActivity.this, stringPrice, txtPrice);

                checkSpinnerInput(spnPropertyType);
                checkSpinnerInput(spnLeaseType);

                if (!stringNameNumber.isEmpty() && !stringLocation.isEmpty() && !stringSize.isEmpty() && !stringPrice.isEmpty() &&
                        spnPropertyType.getSelectedItemPosition() != 0 && spnLeaseType.getSelectedItemPosition() != 0) {
                    DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(AddPropertyActivity.this);
                    PropertyModelClass propertyModelClass =
                            new PropertyModelClass(stringNameNumber, stringLocation, stringSize, stringPrice,
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
        spnPropertyType = findViewById(R.id.spnPropertyType);
        spnLeaseType = findViewById(R.id.spnLeaseType);
        npBedrooms = findViewById(R.id.npBedrooms);
        npBathrooms = findViewById(R.id.npBathrooms);
    }
}