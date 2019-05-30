package android.example.foamlayout;


import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;

/**
 * {@link Beer} represents an object with all the attributes to be
 * contained for each beer in the database. It contains a name,
 * alcohol percentage, brewery, rating, size in ml
 */
public class Beer implements Serializable{

    /** Name of the beer */
    private String mName;

    /** Alcohol percentage of the beer */
    private String mAlcoholPercentage;

    /** Rating of the beer */
    private int mRating;

    /** Image resource ID for the beer */
    private int mImageResourceId;

    //Database reference
    private String mRef;

    //URL to image resource
    private String mURL;

    //Ingredience variable
    private int humle;
    private int flower;
    private int spice;
    private int malt;
    private String des;

    /**
     * Create a new Beer object.
     *
     * @param name              is the name of the beer
     * @param alcoholPercentage is the alcohol volume of the beer in percents
     * @param rating            is the rating of the beer from 0 to 5
     * @param imageResourceId   is the resource ID for the image file associated with this word
     */
    public Beer(String name, String alcoholPercentage, int rating, int imageResourceId, String passOn, String URL, int Humle, int Flower, int Spice, int Malt, String descrip) {
        mName = name;
        mAlcoholPercentage = alcoholPercentage;
        mRating = rating;
        mImageResourceId = imageResourceId;
        mRef = passOn;
        mURL = URL;
        humle = Humle;
        flower = Flower;
        spice = Spice;
        malt = Malt;
        des = descrip;
    }

    /** Get the name of the beer */
    public String getName() {
        return mName;
    }

    /** get the alcohol percentage of the beer */
    public String getAlcoholPercentage() {
        return mAlcoholPercentage;
    }

    /** Get the rating of the beer */
    public int getRating() {
        return mRating;
    }

    /** Get the imageResourceId for the image associated with the beer */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    public String getmRef(){
        return mRef;
    }

    public String getURL(){
        return mURL;
    }

    public int getHumle(){
        return humle;
    }
    public int getFlower(){
        return flower;
    }
    public int getSpice(){
        return spice;
    }
    public int getMalt(){
        return malt;
    }
    public String getDesc(){
        return des;
    }
}