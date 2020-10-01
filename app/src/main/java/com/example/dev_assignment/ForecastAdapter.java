package com.example.dev_assignment;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>
{


    private Context mCtx;
    private List<Forecast> forecastList;
    String address;

    public ForecastAdapter(Context mCtx, List<Forecast> forecastList, String address) {
        this.mCtx = mCtx;
        this.forecastList = forecastList;
        this.address=address;
    }


    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.forecast_list, null);

        return new ForecastViewHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ForecastViewHolder holder, final int position) {
        Forecast forecast = forecastList.get(position);
        String date=new SimpleDateFormat("MM/dd/yyyy").format(new Date(forecast.getDate() * 1000L));
        holder.textViewDate.setText(date);
        holder.textViewPlace.setText(address);
        holder.textViewPressure.setText("Pressure:"+forecast.getPressure().toString());
        holder.textViewHumidity.setText(forecast.getHumidity() + "°C");

    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }

    static class ForecastViewHolder extends RecyclerView.ViewHolder {

        TextView textViewDate, textViewPlace, textViewPressure,textViewHumidity;

        public Button report;

        public ForecastViewHolder(View itemView) {
            super(itemView);

            textViewDate = itemView.findViewById(R.id.date);
            textViewPlace = itemView.findViewById(R.id.place);
            textViewPressure = itemView.findViewById(R.id.pressure);
//            textViewRoast = itemView.findViewById(R.id.textViewVillage1);
            textViewHumidity = itemView.findViewById(R.id.humidity);

        }
    }

}