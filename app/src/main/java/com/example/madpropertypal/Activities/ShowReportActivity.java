package com.example.madpropertypal.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madpropertypal.Databases.DatabaseHelperClass;
import com.example.madpropertypal.R;
import com.example.madpropertypal.Adapters.ReportAdapterClass;
import com.example.madpropertypal.Constructors.ReportModelClass;

import java.util.List;

public class ShowReportActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_report);

        recyclerView = findViewById(R.id.recycler_view_report);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowReportActivity.this));
        recyclerView.setHasFixedSize(true);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("ID")) {
            Integer ID = intent.getIntExtra("ID", 0);

            //Calling ReportList method and setting on RecyclerView Adapter class
            DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(ShowReportActivity.this);
            List<ReportModelClass> reportModelClasses = databaseHelperClass.getReportList(ID);

            //Checking if the list contains any report
            if (reportModelClasses.size() == 0) {

                Toast.makeText(ShowReportActivity.this, "No reports for this property", Toast.LENGTH_SHORT).show();
            } else {
                ReportAdapterClass reportAdapterClass = new ReportAdapterClass(reportModelClasses, ShowReportActivity.this);
                recyclerView.setAdapter(reportAdapterClass);
            }
        }
    }
}
