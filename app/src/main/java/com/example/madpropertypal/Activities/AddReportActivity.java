package com.example.madpropertypal.Activities;

import static com.example.madpropertypal.Utils.DataUtils.checkSpinnerInput;
import static com.example.madpropertypal.Utils.DataUtils.checkTextBoxInput;
import static com.example.madpropertypal.Utils.DataUtils.spinnerAdapter;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madpropertypal.Constructors.ReportModelClass;
import com.example.madpropertypal.Databases.DatabaseHelperClass;
import com.example.madpropertypal.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class AddReportActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    public static final String NAME = "name";
    TextInputLayout textOfferPrice,
            textExpiryDate;
    TextView textNameNumber,
            textViewDate;
    TextInputLayout textViewComments,
            textOfferConditions;
    Button btn_add_report_final;
    Spinner spnInterest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);
        Bundle extras = getIntent().getExtras();
        textNameNumber = findViewById(R.id.report_property_name_number);
        if (extras != null) {
            String name = extras.getString(NAME);
            if (name != null) {
                textNameNumber.setText(name);
            }
        }

        initializeViews();

        spinnerAdapter(AddReportActivity.this, spnInterest, R.array.interest);

        //When Add report button is pressed, shows the date dialog picker
        textViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showViewDatePickerDialog();
            }
        });
        btn_add_report_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Converting variables into String Type
                String viewComments = textViewComments.getEditText().getText().toString();
                String offerPrice = textOfferPrice.getEditText().getText().toString();
                String expiryDate = textExpiryDate.getEditText().getText().toString();
                String offerConditions = textOfferConditions.getEditText().getText().toString();
                String nameNumber = textNameNumber.getText().toString();
                String viewDate = textViewDate.getText().toString();
                String viewInterest = spnInterest.getSelectedItem().toString();

                checkTextBoxInput(AddReportActivity.this, nameNumber, textNameNumber);
                checkTextBoxInput(AddReportActivity.this, viewDate, textViewDate);

                checkSpinnerInput(spnInterest);

                if (!textNameNumber.getText().toString().isEmpty() && spnInterest.getSelectedItemPosition() != 0 && !textViewDate.getText().toString().isEmpty()) {
                    DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(AddReportActivity.this);
                    ReportModelClass reportModelClass = new ReportModelClass(nameNumber, viewDate, viewInterest, offerPrice, offerConditions, expiryDate, viewComments);
                    databaseHelperClass.addReport(reportModelClass);
                    Toast.makeText(AddReportActivity.this, "Report was added", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                } else {
                    Toast.makeText(AddReportActivity.this, "Required fields cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //The date picker dialog
    private void showViewDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    //On selecting a date, will display the selected date
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month = month + 1;
        String date = dayOfMonth + "/" + month + "/" + year;
        textViewDate.setText(date);
    }

    //Variables declaration
    private void initializeViews() {
        spnInterest = findViewById(R.id.report_interest_spinner);
        textViewComments = findViewById(R.id.report_view_comments);
        textOfferPrice = findViewById(R.id.report_offer_price);
        textExpiryDate = findViewById(R.id.report_expiry_date);
        textOfferConditions = findViewById(R.id.report_offer_conditions);
        textViewDate = findViewById(R.id.report_view_date);
        btn_add_report_final = findViewById(R.id.button_add_report_final);
    }
}