package android.example.foamlayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.List;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progDiag;    //For loading screen
    FirebaseDatabase mdatabase;
    ArrayList<BeerCollection> categories;
    Intent intent;
    Bundle args;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This is the Loading screen
        progDiag = new ProgressDialog(this);

        //Data between activities
        intent = new Intent(this, LayoutActivity.class);
        args = new Bundle();

        //Database read
        mdatabase = FirebaseDatabase.getInstance();
        dataread();
    }

    public void dataread(){
        //ProgressThingi
        progDiag.setTitle("Serving beer");
        progDiag.setMessage(null);
        progDiag.show();


        DatabaseReference mRef = mdatabase.getReference("/");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //Create object used in layout
                categories = new ArrayList<>();

                //Define placeholders
                String name;
                String alcpct;
                double rat;
                String DBKey;
                String URL;
                double spice1;
                double spice2;
                double spice3;
                double spice4;
                String descrip;

                //Iterates beer types
                for (DataSnapshot groupName : dataSnapshot.getChildren()) {

                    //Creates the necessary beer list for each beer type
                    ArrayList<Beer> beers = new ArrayList<>();

                    //Iterates beer names
                    for (DataSnapshot beerName : dataSnapshot.child(groupName.getKey()).getChildren()) {

                        //Reads data for each beer
                        name = beerName.child("name").getValue(String.class);
                        alcpct = beerName.child("alcpct").getValue(String.class);
                        rat = beerName.child("rating").getValue(Double.class);
                        URL = beerName.child("URL").getValue(String.class);

                        spice1 = beerName.child("Humle").getValue(Double.class);
                        spice2 = beerName.child("Flower").getValue(Double.class);
                        spice3 = beerName.child("Spice").getValue(Double.class);
                        spice4 = beerName.child("Malt").getValue(Double.class);
                        descrip = beerName.child("Description").getValue(String.class);

                        //Problemet ligger i denne KEY!!!
                        DBKey = dataSnapshot.child(groupName.child(name).getKey()).getKey();



                        //Adds a beer to the beer list
                        beers.add(new Beer(name, alcpct, (int)rat, R.drawable.carlsberg_pilsner, DBKey, URL, (int)spice1, (int)spice2, (int)spice3, (int)spice4, descrip));

                    }

                    //Creates a collection of a type of beer
                    BeerCollection temp = new BeerCollection(beers, groupName.getKey());

                    //Adds the collection of beers to the object that the layout uses
                    categories.add(temp);
                }


                //Transmits the object to Layout activity
                args.putSerializable("ARRAYLIST", (Serializable) categories);
                intent.putExtra("collection", args);
                progDiag.dismiss(); //Closes the loading screen
                startActivity(intent);
                finish();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                TextView mtextview = findViewById(R.id.mTextView);
                mtextview.setText("Database read cancelled by user");
            }
        });
    }
}