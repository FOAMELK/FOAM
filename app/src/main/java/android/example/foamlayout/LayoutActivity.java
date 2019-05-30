package android.example.foamlayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);


        //Get data from main activity
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("collection");
        ArrayList<BeerCollection> categories = (ArrayList<BeerCollection>) args.getSerializable("ARRAYLIST");


        //Start layout
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        MainAdapter adapter = new MainAdapter(this, categories);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

}
