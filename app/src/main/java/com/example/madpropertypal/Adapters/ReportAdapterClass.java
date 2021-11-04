package com.example.madpropertypal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madpropertypal.Constructors.ReportModelClass;
import com.example.madpropertypal.Databases.DatabaseHelperClass;
import com.example.madpropertypal.R;

import java.util.List;

public class ReportAdapterClass extends RecyclerView.Adapter<ReportAdapterClass.ViewHolder> {

    List<ReportModelClass> report;
    Context context;
    DatabaseHelperClass databaseHelperClass;

    public ReportAdapterClass(List<ReportModelClass> report, Context context) {
        this.report = report;
        this.context = context;
        databaseHelperClass = new DatabaseHelperClass(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.report_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final ReportModelClass reportModelClass = report.get(position);

        holder.editText_ID.setText(reportModelClass.getPropertyID());
        holder.textView_ViewingDate.setText(reportModelClass.getViewingDate());
        holder.editText_ViewingComments.setText(reportModelClass.getViewingComments());
        holder.editText_OfferExpiryDate.setText(reportModelClass.getOfferExpiryDate());
        holder.editText_OfferConditions.setText(reportModelClass.getOfferConditions());
        holder.editText_OfferPrice.setText(reportModelClass.getOfferPrice());
        holder.editText_Interest.setText(reportModelClass.getInterest());
    }

    @Override
    public int getItemCount() {
        return report.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView editText_ID;
        TextView editText_Interest;
        TextView editText_OfferPrice;
        TextView editText_OfferConditions;
        TextView editText_OfferExpiryDate;
        TextView editText_ViewingComments;
        TextView textView_ViewingDate;

        public ViewHolder(View itemView) {
            super(itemView);
            editText_ID = itemView.findViewById(R.id.report_id_final);
            editText_Interest = itemView.findViewById(R.id.report_view_interest_final);
            editText_OfferPrice = itemView.findViewById(R.id.report_offer_price_final);
            editText_OfferConditions = itemView.findViewById(R.id.report_offer_conditions_final);
            editText_OfferExpiryDate = itemView.findViewById(R.id.report_expiry_date_final);
            editText_ViewingComments = itemView.findViewById(R.id.report_view_comments_final);
            textView_ViewingDate = itemView.findViewById(R.id.report_text_viewing_date_final);
        }
    }
}
