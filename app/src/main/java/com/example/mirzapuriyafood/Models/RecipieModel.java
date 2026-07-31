package com.example.mirzapuriyafood.Models;

public class RecipieModel {
    private int pic;        // image resource
    private int price;      // price in rupees
    private String text;    // dish name
    private double rating;  // rating (decimal allowed)

    public RecipieModel(int pic, int price, String text, double rating) {
        this.pic = pic;
        this.price = price;
        this.text = text;
        this.rating = rating;
    }

    public int getPic() { return pic; }
    public void setPic(int pic) { this.pic = pic; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
}