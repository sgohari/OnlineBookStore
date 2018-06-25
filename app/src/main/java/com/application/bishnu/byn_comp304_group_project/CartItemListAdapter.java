package com.application.bishnu.byn_comp304_group_project;
/**
 * Author Yayun Yang
 * date: 27/04/2018
 * subject: COMP304-003
 * Project: online book store.
 */
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CartItemListAdapter extends ArrayAdapter<Books> {
    Context context;
    List<Books> booklist;
    int resource;

    public CartItemListAdapter(Context context, int resource, List<Books> booklist) {
        super(context, resource, booklist);
        this.context = context;
        this.resource = resource;
        this.booklist = booklist;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =  LayoutInflater.from(context);
        View view = inflater.inflate(resource,null);

        ImageView bookPic = view.findViewById(R.id.ivCartPic);
        final TextView bookName = view.findViewById(R.id.tvCartBookName);
        TextView price = view.findViewById(R.id.tvCartPrice);

        Books book = booklist.get(position);
        bookPic.setImageDrawable(context.getResources().getDrawable(book.getImage()));
        bookName.setText(book.getName());
        price.setText(book.getPrice());
        view.findViewById(R.id.ibtnRemove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Books b: CartActivity.cartList){
                    if(b.getName() == bookName.getText()){
                        CartActivity.cartList.remove(CartActivity.cartList.indexOf(b));
                        notifyDataSetChanged();
                        return;
                    }
                }
            }
        });

    return view;
    }

}
