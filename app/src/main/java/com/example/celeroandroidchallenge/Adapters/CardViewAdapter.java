package com.example.celeroandroidchallenge.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.celeroandroidchallenge.Models.Customer;
import com.example.celeroandroidchallenge.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {
    private ArrayList<String> item;
    private Context context;
    private CardView cardView;

    public CardViewAdapter(Context context, ArrayList<String> item, CardView cardView) {
        Log.i("autolog", "RecyclerViewAdapter");
        this.item = item;
        this.context = context;
        this.cardView = cardView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("autolog", "onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.images_view, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.i("autolog", "onBindViewHolder");
        Log.d("onBindViewHolderPic", item.get(0));
        Picasso.get().load(item.get(position)).error(R.drawable.ic_baseline_person_24).placeholder(R.drawable.ic_baseline_person_24).into(holder.problemPicture);
    }

    @Override
    public int getItemCount() {
        Log.i("autolog", "getItemCount");
        return item.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView problemPicture;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            Log.i("autolog", "ViewHolder");

            problemPicture = itemView.findViewById(R.id.images_image);
        }

        @Override
        public void onClick(View view) {


        }
    }
}