package android.example.foamlayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BeerActivity extends AppCompatActivity {

    private static final String TAG = "BeerActivity";

    DatabaseReference mDatabase;
    String DBKey;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);
        Log.d(TAG, "onCreate: started.");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        getIncomingIntent();
    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: Checking for incoming intents...");
        if (getIntent().hasExtra("this_beer_name") && getIntent().hasExtra("this_beer_rating") && getIntent().hasExtra("this_beer_image")) {
            Log.d(TAG, "getInComingIntent: found intent extras.");
            String this_beer_name = getIntent().getStringExtra("this_beer_name");
            int this_beer_rating = getIntent().getIntExtra("this_beer_rating", 1);
            int this_beer_image = getIntent().getIntExtra("this_beer_image", R.drawable.carlsberg_pilsner_daase);

            //Image URL for glide
            String mURL = getIntent().getStringExtra("URLLE");
            //Key to DB for ratingbar
            DBKey = getIntent().getStringExtra("DBref");
            //Useless for now
            //mDatabase.child(DBKey).child("rating").setValue(4.0);

            //Update humle, flower, spices & malt
            int huml = getIntent().getIntExtra("HUMLE", 1);
            int flow = getIntent().getIntExtra("FLOWER",1);
            int spic = getIntent().getIntExtra("SPICE",1);
            int malt = getIntent().getIntExtra("MALT",1);

            String desc = getIntent().getStringExtra("DESC");
            //Set values
            setBeer(this_beer_name, this_beer_rating, this_beer_image, mURL, huml, flow, spic, malt, desc);
        }
    }

    private void setBeer(String name, int rating, int imageId, String mURL, int hum, int flo, int spi, int mal, String Desc) {
        Log.d(TAG, "setImage: setting the correct beer to widgets");

        TextView beerName = findViewById(R.id.this_beer_name);
        beerName.setText(name);

        ImageView beerImage = findViewById(R.id.this_beer_image);   //beerImage.setImageResource(imageId); -Old stuff
        //Loading of image from DB with glide
        Glide.with(this).load(mURL).into(beerImage);

        RatingBar beerRating = findViewById(R.id.this_beer_rating_bar);
        beerRating.setRating(rating);

        ProgressBar Humle = findViewById(R.id.this_beer_taste1);
        Humle.setProgress(hum);

        ProgressBar Flower = findViewById(R.id.this_beer_taste2);
        Flower.setProgress(flo);

        ProgressBar Spices = findViewById(R.id.this_beer_taste3);
        Spices.setProgress(spi);

        ProgressBar Malt = findViewById(R.id.this_beer_taste4);
        Malt.setProgress(mal);

        TextView Description = findViewById(R.id.this_beer_description);
        Description.setText(Desc);
    }

    //Kill activity on back button press
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}