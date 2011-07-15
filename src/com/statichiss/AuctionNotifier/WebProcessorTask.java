package com.statichiss.AuctionNotifier;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.net.URLEncoder;
import java.util.ArrayList;

public class WebProcessorTask extends AsyncTask<Long, Void, ArrayList> {

    private String TAG = getClass().getName();
    private Context context;
    ProgressDialog waitSpinner;

    public WebProcessorTask(Context context) {
        this.context = context;
    }

    @Override
    protected ArrayList doInBackground(Long... searchId) {

        ArrayList<Listing> listings;

        EbayInvoke ebayInvoke = new EbayInvoke(context);
        EbayParser ebayParser = new EbayParser(context);
        DatabaseHelper databaseHelper = new DatabaseHelper(context);

        publishProgress(null);

        try {

            String searchTerm = databaseHelper.getSearch(searchId[0]);
            databaseHelper.close();

            Log.d(TAG, "Calling eBay");
            String response = ebayInvoke.search(URLEncoder.encode(searchTerm));
            Log.d(TAG, "Processing eBay response");

            listings = ebayParser.parseListings(response);
            Log.d(TAG, "Found " + listings.size() + " auctions");

            NotificationHelper.showNotification(this.context, searchId[0].intValue(), listings.size(), searchTerm);

            for (Listing listing : listings) {
                Log.d(TAG, listing.toString());
            }

        } catch (Exception e) {
            Log.e(TAG, "Exception occurred calling ebay:", e);
            return null;
        }

        return listings;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        waitSpinner = ProgressDialog.show(this.context, this.context.getString(R.string.app_name), "Talking to eBay...", true);
    }

    @Override
    protected void onPostExecute(ArrayList arrayList) {
        super.onPostExecute(arrayList);
        waitSpinner.cancel();
    }
}
