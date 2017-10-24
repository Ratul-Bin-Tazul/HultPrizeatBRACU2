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

import com.ratulbintazul.www.hultprizeatbracu.DataClasses.EventData;
import com.ratulbintazul.www.hultprizeatbracu.R;
import com.ratulbintazul.www.hultprizeatbracu.Util.Codes;

import java.util.ArrayList;

/**
 * Created by SAMSUNG on 10/23/2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.eventHolder>{
    private ArrayList<EventData> arrayList;
    private Context context;

    public EventAdapter(ArrayList<EventData> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.context = ctx;
    }

    @Override
    public EventAdapter.eventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item_layout,parent,false);

        EventAdapter.eventHolder eventHolder = new EventAdapter.eventHolder(view);
        return eventHolder;
    }

    @Override
    public void onBindViewHolder(final EventAdapter.eventHolder holder, final int position) {

        final EventData data = arrayList.get(position);

        //holder.message.setText(BooksDataProvider.getMessage());
        //holder.messageSent.setText(BooksDataProvider.getMessage());

        String s = data.getName();

        holder.name.setText(s.length() >80? s.substring(0,80)+"..." : s);


        holder.place.setText(data.getPlace().getName());

        String time = data.getStartTime();
        String[] split = time.split("T");
        String[] date = split[0].split("-");

        holder.month.setText(Codes.Month[Integer.parseInt(date[1])-1]);
        holder.date.setText(date[2]);

        int timeInt = Integer.parseInt(split[1].substring(0,2));
        String timeString="";
        if(timeInt>12) {
            timeInt = timeInt - 12;
            holder.time.setText(timeInt+split[1].substring(2,5) + " PM");
        }
        else
            holder.time.setText(split[1].substring(0,5) + " AM");


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    //context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + data.getId())));
                    //context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://post/"+data.getId()+"?owner="+ Codes.icePageCode)));
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://event/"+data.getId())));
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

    public class eventHolder extends RecyclerView.ViewHolder {

        TextView month,date,name,place,time;
        CardView cardView;
        public eventHolder(final View itemView) {
            super(itemView);

            month = (TextView) itemView.findViewById(R.id.eventMonth);
            date = (TextView) itemView.findViewById(R.id.eventDate);
            name = (TextView) itemView.findViewById(R.id.eventName);
            place = (TextView) itemView.findViewById(R.id.eventLocation);
            time = (TextView) itemView.findViewById(R.id.eventTime);

            cardView = (CardView) itemView.findViewById(R.id.eventCard);

        }


    }
}
