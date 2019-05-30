package android.example.foamlayout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

// Change member variables to match the rest of code

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.BeerViewHolder> {

    private Context context;
    private List<BeerCollection> items;

    public MainAdapter(Context context, List<BeerCollection> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.beer_row, parent, false);
        return new BeerViewHolder(view);
    }


    @Override
    public void onBindViewHolder(BeerViewHolder beerViewHolder, int position) {
        BeerCollection currentCollection = items.get(position);
        BeerAdapter adapter = new BeerAdapter(currentCollection.getBeerList());
        beerViewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        beerViewHolder.recyclerView.setAdapter(adapter);
        beerViewHolder.type.setText(currentCollection.getType());
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class BeerViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        TextView type;

        BeerViewHolder(View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.title);
            recyclerView = itemView.findViewById(R.id.beer_recycler_view);
        }
    }
}
