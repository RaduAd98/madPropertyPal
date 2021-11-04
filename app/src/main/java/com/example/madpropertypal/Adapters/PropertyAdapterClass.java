package com.example.madpropertypal.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madpropertypal.Activities.AddReportActivity;
import com.example.madpropertypal.Activities.ShowReportActivity;
import com.example.madpropertypal.Constructors.PropertyModelClass;
import com.example.madpropertypal.Databases.DatabaseHelperClass;
import com.example.madpropertypal.R;

import java.util.List;

public class PropertyAdapterClass extends RecyclerView.Adapter<PropertyAdapterClass.ViewHolder> {

    List<PropertyModelClass> property;
    Context context;
    DatabaseHelperClass databaseHelperClass;

    public PropertyAdapterClass(List<PropertyModelClass> property, Context context) {
        this.property = property;
        this.context = context;
        databaseHelperClass = new DatabaseHelperClass(context);
    }

    public List<PropertyModelClass> getProperty() {
        return property;
    }

    public void setProperty(List<PropertyModelClass> property) {
        this.property = property;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.property_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final PropertyModelClass propertyModelClass = property.get(position);

        //Populating the text fields with the data inserted
        holder.textViewID.setText(Integer.toString(propertyModelClass.getId()));
        holder.editText_Name_Number.setText(propertyModelClass.getNameNumber());
        holder.editText_Location.setText(propertyModelClass.getLocation());
        holder.editText_Size.setText(propertyModelClass.getSize());
        holder.editText_Price.setText(propertyModelClass.getPrice());
        holder.editText_Property_Type.setText(propertyModelClass.getPropertyType());
        holder.editText_Lease_Type.setText(propertyModelClass.getLeaseType());
        holder.editText_Construction_Year.setText(propertyModelClass.getConstructionYear());
        holder.editText_Floors_Number.setText(propertyModelClass.getFloorsNumber());
        holder.editText_Description.setText(propertyModelClass.getDescription());
        holder.editText_Local_Amenities.setText(propertyModelClass.getLocalAmenities());
        holder.editText_Bedrooms_Number.setText(propertyModelClass.getBedroomsNumber());
        holder.editText_Bathrooms_Number.setText(propertyModelClass.getBathroomsNumber());

        //Calling updateProperty method when pressing the edit button
        holder.button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringNameNumber = holder.editText_Name_Number.getText().toString();
                String stringLocation = holder.editText_Location.getText().toString();
                String stringSize = holder.editText_Size.getText().toString();
                String stringPrice = holder.editText_Price.getText().toString();
                String stringPropertyType = holder.editText_Property_Type.getText().toString();
                String stringLeaseType = holder.editText_Lease_Type.getText().toString();
                String stringConstructionYear = holder.editText_Construction_Year.getText().toString();
                String stringFloorsNumber = holder.editText_Floors_Number.getText().toString();
                String stringDescription = holder.editText_Description.getText().toString();
                String stringLocalAmenities = holder.editText_Local_Amenities.getText().toString();
                String stringBedroomsNumber = holder.editText_Bedrooms_Number.getText().toString();
                String stringBathroomsNumber = holder.editText_Bathrooms_Number.getText().toString();

                databaseHelperClass.updateProperty(new PropertyModelClass(propertyModelClass.getId(), stringNameNumber,
                        stringLocation, stringSize, stringPrice, stringPropertyType, stringLeaseType, stringConstructionYear,
                        stringFloorsNumber, stringDescription, stringLocalAmenities, stringBedroomsNumber, stringBathroomsNumber));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        //Calling deleteProperty method when pressing the delete button
        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelperClass.deleteProperty(propertyModelClass.getId());
                property.remove(position);
                databaseHelperClass.deleteReport(propertyModelClass.getId());
                notifyDataSetChanged();
            }
        });

        //Pressing the Add Report lets the user to add a report for the property, and automatically get ID
        holder.btn_add_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textViewID = holder.textViewID.getText().toString();
                Intent intent = new Intent(context, AddReportActivity.class);
                intent.putExtra(AddReportActivity.NAME, textViewID);
                context.startActivity(intent);
            }
        });

        //Pressing the View Report button opens the reviews for the property
        holder.btn_view_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowReportActivity.class);
                intent.putExtra("ID", Integer.parseInt(holder.textViewID.getText().toString()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return property.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewID;
        EditText editText_Name_Number;
        EditText editText_Location;
        EditText editText_Size;
        EditText editText_Price;
        EditText editText_Property_Type;
        EditText editText_Lease_Type;
        EditText editText_Construction_Year;
        EditText editText_Floors_Number;
        EditText editText_Description;
        EditText editText_Local_Amenities;
        EditText editText_Bedrooms_Number;
        EditText editText_Bathrooms_Number;
        Button button_edit;
        Button button_delete;
        Button btn_add_report;
        Button btn_view_report;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.view_text_id);
            editText_Name_Number = itemView.findViewById(R.id.edit_text_name_number);
            editText_Location = itemView.findViewById(R.id.edit_text_location);
            editText_Size = itemView.findViewById(R.id.edit_text_size);
            editText_Price = itemView.findViewById(R.id.edit_text_price);
            editText_Property_Type = itemView.findViewById(R.id.edit_text_property_type);
            editText_Lease_Type = itemView.findViewById(R.id.edit_text_lease_type);
            editText_Construction_Year = itemView.findViewById(R.id.edit_text_construction_year);
            editText_Floors_Number = itemView.findViewById(R.id.edit_text_floors_number);
            editText_Description = itemView.findViewById(R.id.edit_text_description);
            editText_Local_Amenities = itemView.findViewById(R.id.edit_text_local_amenities);
            editText_Bedrooms_Number = itemView.findViewById(R.id.edit_text_bedrooms_number);
            editText_Bathrooms_Number = itemView.findViewById(R.id.edit_text_bathrooms_number);
            button_edit = itemView.findViewById(R.id.button_edit);
            button_delete = itemView.findViewById(R.id.button_delete);
            btn_add_report = itemView.findViewById(R.id.button_add_report);
            btn_view_report = itemView.findViewById(R.id.button_view_report);
        }
    }
}
