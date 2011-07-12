package com.statichiss.AuctionNotifier;

import java.util.ArrayList;
import java.util.Collections;

public class SearchResult {

    public final static int RESULT_SUCCESS = 0;
    public final static int RESULT_ERROR = 1;
    private int resultCode;
    private Exception error;
    private ArrayList<Listing> listings;

    public SearchResult() {
        listings = new ArrayList<Listing>();
    }

    public void append(SearchResult toAppend) {
        listings.addAll(toAppend.getListings());
    }

    public void sort() {
        Collections.sort(listings);
    }

    public ArrayList<Listing> getListings() {
        return listings;
    }
}
