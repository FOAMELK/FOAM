package android.example.foamlayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link BeerAdapter} is an {@link android.widget.ArrayAdapter} that can provide the
 * layout for each list item based on a data source, which is a list of {@link Beer}
 * objects.
 */
public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.MyViewHolder> {
    List<Beer> data = new ArrayList<>();
    private int pos = -1;
    private static final String TAG = "BeerAdapter";

    public BeerAdapter(List<Beer> data) {
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_beer, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //pos = position;
        holder.name.setText(data.get(position).getName());
        holder.alcoholPercentage.setText(data.get(position).getAlcoholPercentage());
        holder.rating.setRating(data.get(position).getRating());

        //Load image from DB with Glide, into recyclerView imagePlaceholders
        Glide.with(holder.image.getContext()).load(data.get(position).getURL()).into(holder.image);

        //Old code
        //holder.image.setImageResource(data.get(position).getImageResourceId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, alcoholPercentage;
        ImageView image;
        RatingBar rating;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_text_view);
            alcoholPercentage = itemView.findViewById(R.id.alcohol_pc_text_view);
            rating = itemView.findViewById(R.id.rating_bar);
            image = itemView.findViewById(R.id.beer_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    int position = getAdapterPosition();
                    Beer beer = data.get(position);
                    Log.d(TAG, "onClick detected: position " + position);
                    Intent intent = new Intent(context, BeerActivity.class);
                    intent.putExtra("this_beer_name", beer.getName());
                    intent.putExtra("this_beer_rating", beer.getRating());
                    intent.putExtra("this_beer_image", beer.getImageResourceId());
                    intent.putExtra("DBref", beer.getmRef());
                    intent.putExtra("URLLE", beer.getURL());
                    intent.putExtra("HUMLE", beer.getHumle());
                    intent.putExtra("FLOWER", beer.getFlower());
                    intent.putExtra("SPICE", beer.getSpice());
                    intent.putExtra("MALT", beer.getMalt());
                    intent.putExtra("DESC", beer.getDesc());

                    context.startActivity(intent);

                }
            });
        }
    }
}

