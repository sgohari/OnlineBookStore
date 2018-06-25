package com.application.bishnu.byn_comp304_group_project;
/**
 * Author Bishnu Khanal
 * date: 27/04/2018
 * subject: COMP304-003
 * Project: online book store.
 */
public class Books {

    //properies/instance variables
    int image;
    String name, author, description, price;

    public Books(int image, String name, String author, String description, String price)
    {
        this.image = image;
        this.name = name;
        this.author = author;
        this.description = description;
        this.price = price;
    }

    public int getImage()
    {
        return image;
    }

    public String getName()
    {
        return name;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getDescription()
    {
        return description;
    }

    public String getPrice()
    {
        return price;
    }
}
