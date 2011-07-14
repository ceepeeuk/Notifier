package com.statichiss.AuctionNotifier;

import java.util.Date;

public class Listing implements Comparable<Listing> {

    private String id;
    private String title;
    private String imageUrl;
    private String listingUrl;
    private String location;
    private String shippingCost;
    private String currentPrice;
    private String auctionSource;
    private Date startTime;
    private Date endTime;
    private boolean auction;
    private boolean buyItNow;

    public int compareTo(Listing another) {
        return (another.startTime.compareTo(this.startTime));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append("\n\n")
                .append("Title: ")
                .append(getTitle())
                .append("\n")
                .append("Current Price: ")
                .append(getCurrentPrice())
                .append("\n")
                .append("Start time: ")
                .append(getStartTime())
                .append("\n")
                .append("End time:")
                .append(getEndTime())
                .append("\n\n");
        return sb.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getListingUrl() {
        return listingUrl;
    }

    public void setListingUrl(String listingUrl) {
        this.listingUrl = listingUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(String shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getAuctionSource() {
        return auctionSource;
    }

    public void setAuctionSource(String auctionSource) {
        this.auctionSource = auctionSource;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean isAuction() {
        return auction;
    }

    public void setAuction(boolean auction) {
        this.auction = auction;
    }

    public boolean isBuyItNow() {
        return buyItNow;
    }

    public void setBuyItNow(boolean buyItNow) {
        this.buyItNow = buyItNow;
    }
}