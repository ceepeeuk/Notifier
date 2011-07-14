package com.statichiss.AuctionNotifier;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EbayInvoke {
    private static String appID;
    private static String ebayURL;
    private static String maxNumOfResults;
    private static String globalId;

    private Resources resources;
    private static final String TAG = "com.statichiss.AuctionNotifier.EbayInvoke";

    public EbayInvoke(Context context) {
        this.resources = context.getResources();

        if (Build.PRODUCT.toLowerCase().contains("sdk")) {
            /*
            the sandbox URLs are pretty useless as they only return a success code but no results
            if you really want to use them then swap out the next two lines
            appID=this.resources.getString(R.string.ebay_appid_sandbox);
            ebayURL=this.resources.getString(R.string.ebay_wsurl_sandbox);
            */
            appID = this.resources.getString(R.string.ebay_appid_production);
            ebayURL = this.resources.getString(R.string.ebay_wsurl_production);
            // TODO: pull this from prefs too
            maxNumOfResults = this.resources.getString(R.string.ebay_max_number_of_results);
            globalId = this.resources.getString(R.string.ebay_global_id);
        } else {
            appID = this.resources.getString(R.string.ebay_appid_production);
            ebayURL = this.resources.getString(R.string.ebay_wsurl_production);
            // TODO: pull this from prefs too
            maxNumOfResults = this.resources.getString(R.string.ebay_max_number_of_results);
            globalId = this.resources.getString(R.string.ebay_global_id);
        }
    }

    public String search(String keyword) throws Exception {
        String jsonResponse = null;
        jsonResponse = invokeEbayRest(keyword);
        if ((jsonResponse == null) || (jsonResponse.length() < 1)) {
            throw (new Exception("No result received from invokeEbayRest(" + keyword + ")"));
        }
        return (jsonResponse);
    }

    private String getRequestURL(String keyword) {
        CharSequence requestURL = TextUtils.expandTemplate(this.resources.getString(R.string.ebay_request_template), ebayURL, appID, keyword, maxNumOfResults, globalId);
        return (requestURL.toString());
    }

    private String invokeEbayRest(String keyword) throws Exception {
        String result = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(this.getRequestURL(keyword));
        Log.d(TAG, this.getRequestURL(keyword));
        // Set eBay locale here, TODO: pull from preferences.
        //httpGet.setHeader("X-EBAY-SOA-GLOBAL-ID", "EBAY-GB");
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity httpEntity = response.getEntity();

        if (httpEntity != null) {
            InputStream in = httpEntity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder temp = new StringBuilder();
            String currentLine = null;
            while ((currentLine = reader.readLine()) != null) {
                temp.append(currentLine);
            }
            result = temp.toString();
            in.close();
        }

        return (result);
    }
}