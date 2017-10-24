package com.ratulbintazul.www.hultprizeatbracu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.Profile;
import com.ratulbintazul.www.hultprizeatbracu.DataClasses.Datum;
import com.ratulbintazul.www.hultprizeatbracu.R;
import com.ratulbintazul.www.hultprizeatbracu.Util.Codes;

import java.util.ArrayList;


//import static com.ratulbintazul.www.hultprizeatbracu.R.styleable.RecyclerView;

/**
 * Created by SAMSUNG on 10/19/2017.
 */
public class HultNewsAdapter extends RecyclerView.Adapter<HultNewsAdapter.BookHolder>{
    private ArrayList<Datum> arrayList;
    private Context context;

    public HultNewsAdapter(ArrayList<Datum> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.context = ctx;
    }

    @Override
    public HultNewsAdapter.BookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hult_news_item_layout,parent,false);

        HultNewsAdapter.BookHolder BookHolder = new HultNewsAdapter.BookHolder(view);
        return BookHolder;
    }

    @Override
    public void onBindViewHolder(final HultNewsAdapter.BookHolder holder, final int position) {

        final Datum data = arrayList.get(position);

        //holder.message.setText(BooksDataProvider.getMessage());
        //holder.messageSent.setText(BooksDataProvider.getMessage());

        String s = data.getMessage();
        if(s!=null)
            holder.message.setText(s.length() >80? s.substring(0,80)+"..." : s);
        else
            holder.message.setText(data.getStory());

        String date = data.getCreatedTime();
        String[] split = date.split("T");
        holder.date.setText(split[0]+ " at "+split[1].substring(0,5));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    //context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + data.getId())));
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://post/"+data.getId()+"?owner="+ Codes.hultPageCode)));
                } catch (Exception e) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + data.getId())));
                }

            }
        });


    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class BookHolder extends RecyclerView.ViewHolder {

        TextView message,date;
        CardView cardView;
        public BookHolder(final View itemView) {
            super(itemView);

            message = (TextView) itemView.findViewById(R.id.postMessage);
            date = (TextView) itemView.findViewById(R.id.hultNewsDate);

            cardView = (CardView) itemView.findViewById(R.id.hultNewsCard);

        }


    }
}
