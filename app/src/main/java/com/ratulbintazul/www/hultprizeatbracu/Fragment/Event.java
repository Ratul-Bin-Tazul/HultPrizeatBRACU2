package com.ratulbintazul.www.hultprizeatbracu.Fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.gson.Gson;
import com.ratulbintazul.www.hultprizeatbracu.Adapter.EventAdapter;
import com.ratulbintazul.www.hultprizeatbracu.Adapter.EventAdapter;
import com.ratulbintazul.www.hultprizeatbracu.DataClasses.EventData;
import com.ratulbintazul.www.hultprizeatbracu.DataClasses.EventClass;
import com.ratulbintazul.www.hultprizeatbracu.DataClasses.EventClass;
import com.ratulbintazul.www.hultprizeatbracu.DataClasses.Example;
import com.ratulbintazul.www.hultprizeatbracu.R;
import com.ratulbintazul.www.hultprizeatbracu.Util.Codes;
import com.ratulbintazul.www.hultprizeatbracu.Util.TinyDB;

import java.net.URL;
import java.util.ArrayList;

public class Event extends Fragment {

    public RecyclerView eventRecycleView;
    public RecyclerView.Adapter eventAdapter;
    public RecyclerView.LayoutManager eventLayoutManager;

    public ArrayList<EventData> eventArrayList = new ArrayList<>();

    public Event() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        eventRecycleView = (RecyclerView)view.findViewById(R.id.eventRecyclerview);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        eventRecycleView.setHasFixedSize(true);


        // use a linear layout manager
        eventLayoutManager = new LinearLayoutManager(getContext());
        eventRecycleView.setLayoutManager(eventLayoutManager);

        // specify an adapter
        eventAdapter = new EventAdapter(eventArrayList,getContext());
        eventRecycleView.setAdapter(eventAdapter);

        eventAdapter.notifyDataSetChanged();

        TinyDB tinyDB = new TinyDB(getContext());
        String res = tinyDB.getString("event_response");

        //https://graph.facebook.com/PAGE_ID/events

        Gson gson = new Gson();
        EventClass event = gson.fromJson(res, EventClass.class);

        if(event!=null) {

            eventArrayList.addAll(event.getData());
        }else{

            try {
                final EventClass[] e = {null};
                GraphRequest request = GraphRequest.newGraphPathRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/" + Codes.hultPageCode + "/events",
                        new GraphRequest.Callback() {
                            @Override
                            public void onCompleted(GraphResponse response) {
                                // Insert your code here
                                Gson gson = new Gson();
                                e[0] = gson.fromJson(response.getRawResponse(), EventClass.class);
                                //eventArrayList.addAll(e.getData());

                                if (e[0] != null) {
                                    TinyDB tinyDB = new TinyDB(getContext());
                                    String res = tinyDB.getString("event_response");

                                    for (int i = 0; i < e[0].getData().size(); i++) {

                                        eventArrayList.add(e[0].getData().get(i));

                                    }


                                    tinyDB.putString("event_response", response.getRawResponse());

                                    eventRecycleView.swapAdapter(eventAdapter, false);
                                    eventAdapter.notifyDataSetChanged();

                                }
                                //Log.e("token",AccessToken.getCurrentAccessToken().getToken());
                                //Log.e("res",response.getRawResponse());
                                //Log.e("res",e.toString());

                                eventRecycleView.swapAdapter(eventAdapter, false);
                                eventAdapter.notifyDataSetChanged();


                            }
                        });

                request.executeAsync();

            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        new getPostTask().execute();
        return view;
    }
    private class getPostTask extends AsyncTask<URL, Integer, Example> {
        protected Example doInBackground(URL... urls) {

            try {
                final EventClass[] e = {null};
                GraphRequest request = GraphRequest.newGraphPathRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/" + Codes.hultPageCode + "/events",
                        new GraphRequest.Callback() {
                            @Override
                            public void onCompleted(GraphResponse response) {
                                // Insert your code here
                                Gson gson = new Gson();
                                e[0] = gson.fromJson(response.getRawResponse(), EventClass.class);
                                //eventArrayList.addAll(e.getData());

                                if (e[0] != null) {
                                    TinyDB tinyDB = new TinyDB(getContext());
                                    String res = tinyDB.getString("event_response");

                                    for (int i = 0; i < e[0].getData().size(); i++) {

                                        eventArrayList.add(e[0].getData().get(i));

                                    }


                                    tinyDB.putString("event_response", response.getRawResponse());

                                    eventRecycleView.swapAdapter(eventAdapter, false);
                                    eventAdapter.notifyDataSetChanged();

                                }
                                //Log.e("token",AccessToken.getCurrentAccessToken().getToken());
                                //Log.e("res",response.getRawResponse());
                                //Log.e("res",e.toString());

                                eventRecycleView.swapAdapter(eventAdapter, false);
                                eventAdapter.notifyDataSetChanged();


                            }
                        });

                request.executeAsync();

            }catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onProgressUpdate(Integer... progress) {

        }

        protected void onPostExecute(Example result) {

            eventRecycleView.swapAdapter(eventAdapter, false);
            eventAdapter.notifyDataSetChanged();
        }
    }


}

