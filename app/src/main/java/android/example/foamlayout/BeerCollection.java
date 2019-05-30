package android.example.foamlayout;

import java.io.Serializable;
import java.util.List;

public class BeerCollection implements Serializable {

    private List<Beer> mBeerList;
    private String mType;

    public BeerCollection(List<Beer> beerList, String type) {
        mBeerList = beerList;
        mType = type;
    }

    public List<Beer> getBeerList() {
        return mBeerList;
    }

    public String getType() {
        return mType;
    }
}
