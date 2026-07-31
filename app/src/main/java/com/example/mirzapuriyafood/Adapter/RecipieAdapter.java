package com.example.mirzapuriyafood.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mirzapuriyafood.Models.RecipieModel;
import com.example.mirzapuriyafood.R;

import java.util.ArrayList;

public class RecipieAdapter extends RecyclerView.Adapter<RecipieAdapter.ViewHolder> {

    private ArrayList<RecipieModel> list;
    private Context context;

    public RecipieAdapter(ArrayList<RecipieModel> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_restaurant_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecipieModel model = list.get(position);

        holder.itemDish.setImageResource(model.getPic());
        holder.itemText.setText(model.getText());
        holder.itemPrice.setText("₹" + model.getPrice());
        holder.itemRating.setText(String.valueOf(model.getRating())); // "4.5"


        switch (position){
            case 0:
                holder.itemDish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "Image One is clicked", Toast.LENGTH_SHORT).show();
                    }
                });


                holder.itemText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "Text is clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case 1:

                holder.itemDish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "Image Second is clicked", Toast.LENGTH_SHORT).show();
                    }
                });


                holder.itemText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "Text is clicked", Toast.LENGTH_SHORT).show();
                    }
                });


                break;
            default:
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemDish;
        TextView itemText;
        TextView itemPrice;
        TextView itemRating;

        @SuppressLint("WrongViewCast")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemDish = itemView.findViewById(R.id.itemimage);
            itemText = itemView.findViewById(R.id.itemtext);
            itemPrice = itemView.findViewById(R.id.itemprice);
            itemRating = itemView.findViewById(R.id.itemrating);
        }
    }
}