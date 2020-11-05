package com.example.celeroandroidchallenge;
import android.content.Context;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.celeroandroidchallenge.Activities.MainActivity;
import com.example.celeroandroidchallenge.Adapters.CardViewAdapter;
import com.example.celeroandroidchallenge.Models.Customer;
import com.example.celeroandroidchallenge.Models.ProfilePictures;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private List<Customer> item;
    private Context context;
    private CardView cardView;


    public RecyclerViewAdapter(Context context, List<Customer> item, CardView cardView )
    {
        Log.i("autolog", "RecyclerViewAdapter");
        this.item = item;
        this.context = context;
        this.cardView = cardView;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("autolog", "onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_row, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Log.i("autolog", "onBindViewHolder");
        holder.name.setText(item.get(position).getname());
        holder.phoneNumber.setText(item.get(position).getPhoneNumber());



        Picasso.get().load(item.get(position).getprofilePictures().getLarge()).error(R.drawable.ic_baseline_person_24).placeholder(R.drawable.ic_baseline_person_24).into(holder.profilePicture);
    }

    @Override
    public int getItemCount() {
        Log.i("autolog", "getItemCount");
        return item.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        /* ViewHolder UI */
        public TextView name;
        public TextView phoneNumber;
        public ImageView profilePicture;

        /* CardView UI */
        private LinearLayoutManager layoutManager;
        private RecyclerView cardViewRecyclerView;
        private ImageView cardViewProfilePicture;
        private TextView cardViewName;
        private TextView cardViewId;
        private TextView cardViewVisitOrder;
        private TextView cardViewPhoneNumber;
        private TextView cardViewAddress;
        private TextView cardViewServiceReason;
        private ImageView cardViewProblemPictures;

        public ViewHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            Log.i("autolog", "ViewHolder");

            /* ViewHolder UI References */
            name = (TextView) itemView.findViewById(R.id.name);
            phoneNumber = (TextView) itemView.findViewById(R.id.rv_tv_phoneNumber);
            profilePicture = itemView.findViewById(R.id.rv_iv_profilePicture);

            /* CardView UI References */
            cardViewRecyclerView = itemView.findViewById(R.id.cardViewRecyclerView);
            cardViewProfilePicture = cardView.findViewById(R.id.cardView_iv_profilePicture);
            cardViewName = cardView.findViewById(R.id.cardView_tv_name);
            cardViewId = cardView.findViewById(R.id.cardView_tv_id);
            cardViewPhoneNumber = cardView.findViewById(R.id.cardView_tv_phoneNumber);
            cardViewServiceReason = cardView.findViewById(R.id.cardView_tv_serviceDesc);

            layoutManager = new LinearLayoutManager(context,
                    LinearLayoutManager.HORIZONTAL,
                    false);
        }

        @Override
        public void onClick(View view)
        {
            int mPosition = getLayoutPosition();
            Log.d("positionName", item.get(mPosition).getname());

            //Picasso.get().load(item.get(mPosition).getprofilePictures().getMedium()).error(R.drawable.ic_baseline_person_24).placeholder(R.drawable.ic_baseline_person_24).into(cardViewProfilePicture);
            Picasso.get().load(item.get(mPosition).getprofilePictures().getLarge()).error(R.drawable.ic_baseline_person_24).placeholder(R.drawable.ic_baseline_person_24).into(cardViewProfilePicture);
            cardViewName.setText(item.get(mPosition).getname());
            cardViewId.setText(item.get(mPosition).getIdentifier());
            cardViewPhoneNumber.setText(item.get(mPosition).getPhoneNumber());
            cardViewServiceReason.setText(item.get(mPosition).getServiceReason());

            handleCardAdapter(item.get(mPosition).getProblemPictures());

            slideUp(cardView);
            cardView.setVisibility(View.VISIBLE);
        }

        public void handleCardAdapter(ArrayList<String> problemPictures) {
            RecyclerView cardViewRecycler = cardView.findViewById(R.id.cardViewRecyclerView);
            cardViewRecycler.setLayoutManager(layoutManager);
            CardViewAdapter cardViewAdapter = new CardViewAdapter(context, problemPictures, cardView);
            cardViewRecycler.setAdapter(cardViewAdapter);
        }

        /* Animation to show card view once user had clicked on a Customer ViewHolder */
        public void slideUp(CardView cardView)
        {
            cardView.setVisibility(View.VISIBLE);
            TranslateAnimation animation = new TranslateAnimation(0, 0, cardView.getHeight(), 0);
            animation.setDuration(250);
            cardView.startAnimation(animation);
        }
    }

    // TODO Update list of customers once the server changes
    // TODO Setup Room and SQLLight to store local objects
}