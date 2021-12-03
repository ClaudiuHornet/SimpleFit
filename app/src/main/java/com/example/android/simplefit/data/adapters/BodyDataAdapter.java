package com.example.android.simplefit.data.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.simplefit.R;
import com.example.android.simplefit.models.db.BodyData;
import com.example.android.simplefit.util.DateStringConverter;
import com.example.android.simplefit.viewmodels.BodyDataViewModel;
import com.google.android.material.card.MaterialCardView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BodyDataAdapter extends RecyclerView.Adapter<BodyDataAdapter.BodyDataViewHolder> {

    private List<BodyData> bodyDataList = new ArrayList<>();
    private BodyDataItemOnClickListener listener;

    public BodyDataAdapter(BodyDataItemOnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public BodyDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new BodyDataViewHolder(inflater.inflate(R.layout.body_data_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BodyDataAdapter.BodyDataViewHolder holder, int position) {
        BodyData currentBodyData = bodyDataList.get(position);
        String[] dateSplit = currentBodyData.getDate().split(" ");

        holder.measurementWeight.setText("Weight: " + currentBodyData.getWeight() + "kg");
        holder.measurementDate.setText(DateStringConverter.convertDbDateToUiDate(dateSplit[0]) + " " + dateSplit[1]);
        holder.cardView.setOnClickListener(view -> listener.onBodyDataItemClick(currentBodyData));

    }

    @Override
    public int getItemCount() {
        return bodyDataList.size();
    }

    public void setBodyDataList(List<BodyData> bodyDataList)
    {
        this.bodyDataList = bodyDataList;
        notifyDataSetChanged();
    }

    /*----------------------------------------------------------------*/

    public class BodyDataViewHolder extends RecyclerView.ViewHolder {

        private MaterialCardView cardView;
        private TextView measurementWeight;
        private TextView measurementDate;

        public BodyDataViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.bodyDataItemCard);
            measurementWeight = itemView.findViewById(R.id.bodyDataItemWeight);
            measurementDate = itemView.findViewById(R.id.bodyDataItemDate);
        }
    }

    /*------------------------------------------------------------------*/

    public interface BodyDataItemOnClickListener{
        void onBodyDataItemClick(BodyData bodyData);
    }
}
