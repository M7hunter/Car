package com.m7awas.car;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.ViewHolder> {

    private List<Car> carList;

    public CarsAdapter(List<Car> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(carList.get(position));
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivCarImage;
        TextView tvBrand, tvConstructionYear, tvIsUsed;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivCarImage = itemView.findViewById(R.id.iv_car_image);
            tvBrand = itemView.findViewById(R.id.tv_brand);
            tvConstructionYear = itemView.findViewById(R.id.tv_construction_year);
            tvIsUsed = itemView.findViewById(R.id.tv_is_used);
        }

        void bind(Car car) {
            Picasso.with(itemView.getContext())
                    .load((car.getImageUrl() == null || car.getImageUrl().isEmpty()) ? "invalid image url" : car.getImageUrl())
                    .into(ivCarImage);

            tvBrand.setText(car.getBrand());
            tvConstructionYear.setText(car.getConstructionYear());
            tvIsUsed.setText(car.isUsed() ? "used" : "new");
        }
    }
}
