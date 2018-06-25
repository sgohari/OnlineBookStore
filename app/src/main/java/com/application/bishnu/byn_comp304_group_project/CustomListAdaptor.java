package com.application.bishnu.byn_comp304_group_project;
/**
 * Author Bishnu Khanal
 * date: 27/04/2018
 * subject: COMP304-003
 * Project: online book store.
 */
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CustomListAdaptor extends ArrayAdapter<Books>
{
    Context mContext;
    List<Books> booksList;
    int resource;

    public CustomListAdaptor(Context mContext, int resource, List<Books> booksList)
    {
        super(mContext, resource, booksList);

        this.mContext = mContext;
        this.resource = resource;
        this.booksList = booksList;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(resource,null); //in place of resource we can pass R.layout.my_list_items

        //referencing /initializing the textview and imageview
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewAuthor = view.findViewById(R.id.textViewAuthor);
        TextView textViewDescription = view.findViewById(R.id.textViewDescription);
        TextView textViewPrice = view.findViewById(R.id.textViewPrice);
        ImageView imageView = view.findViewById(R.id.myImageView);

        final Books books = booksList.get(position); //initializing Books to get appropriate position

        textViewName.setText(books.getName()); //setting data to text/image view
        textViewAuthor.setText(books.getAuthor());
        textViewDescription.setText(books.getDescription());
        textViewPrice.setText(books.getPrice());
        imageView.setImageDrawable(mContext.getResources().getDrawable(books.getImage()));

        view.findViewById(R.id.addSelect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //selectItem(position);
                CartActivity.cartList.add(books);
                //mContext.startActivity(new Intent(mContext, CartActivity.class));
                Toast.makeText(mContext,"Book added!",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
   /* public void selectItem(int position) //method to select items
    {
        //setting alert before purchase
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext); //takes context parameter
        builder.setTitle("Are you sure you want to purchase this book?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //booksList.remove(position); //need to add some add functions
                //notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = builder.create(); //creates alert dialogue
        alertDialog.show(); //shows the alert dialogue
    }*/
}
