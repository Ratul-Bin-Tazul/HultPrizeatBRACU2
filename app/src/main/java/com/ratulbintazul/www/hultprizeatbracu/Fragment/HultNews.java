package com.ratulbintazul.www.hultprizeatbracu.Fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;
import com.ratulbintazul.www.hultprizeatbracu.Adapter.HultNewsAdapter;
import com.ratulbintazul.www.hultprizeatbracu.DataClasses.Datum;
import com.ratulbintazul.www.hultprizeatbracu.DataClasses.Example;
import com.ratulbintazul.www.hultprizeatbracu.R;
import com.ratulbintazul.www.hultprizeatbracu.Util.Codes;
import com.ratulbintazul.www.hultprizeatbracu.Util.TinyDB;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HultNews extends Fragment {


    public RecyclerView hultNewsRecycleView;
    public RecyclerView.Adapter hultNewsAdapter;
    public RecyclerView.LayoutManager hultNewsLayoutManager;

    public ArrayList<Datum> hultNewsArrayList = new ArrayList<>();

    ViewPager newsContainer;
    public HultNews() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hult_news, container, false);

        //newsContainer = (ViewPager) view.findViewById(R.id.);
        hultNewsRecycleView = (RecyclerView)view.findViewById(R.id.hultNewsRecyclerview);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        hultNewsRecycleView.setHasFixedSize(true);


        // use a linear layout manager
        hultNewsLayoutManager = new LinearLayoutManager(getContext());
        hultNewsRecycleView.setLayoutManager(hultNewsLayoutManager);

        // specify an adapter
        hultNewsAdapter = new HultNewsAdapter(hultNewsArrayList,getContext());
        hultNewsRecycleView.setAdapter(hultNewsAdapter);

        hultNewsAdapter.notifyDataSetChanged();

        TinyDB tinyDB = new TinyDB(getContext());
        String res = tinyDB.getString("response");

        Gson gson = new Gson();
        Example exp = gson.fromJson(res, Example.class);

        if(exp!=null) {

            hultNewsArrayList.addAll(exp.getData());
        }else{
            try {
                final Example[] e = {null};
                GraphRequest request = GraphRequest.newGraphPathRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/" + Codes.hultPageCode + "/feed",
                        new GraphRequest.Callback() {
                            @Override
                            public void onCompleted(GraphResponse response) {
                                // Insert your code here
                                Gson gson = new Gson();
                                e[0] = gson.fromJson(response.getRawResponse(), Example.class);
                                //hultNewsArrayList.addAll(e.getData());

                                if (e[0] != null) {
                                    TinyDB tinyDB = new TinyDB(getContext());
                                    String res = tinyDB.getString("response");

                                    for (int i = 0; i < e[0].getData().size(); i++) {
                                        if (!res.contains(e[0].getData().get(i).getCreatedTime())) {
                                            hultNewsArrayList.add(e[0].getData().get(i));
                                        }
                                    }


                                    tinyDB.putString("response", response.getRawResponse());

                                    hultNewsRecycleView.swapAdapter(hultNewsAdapter, false);
                                    hultNewsAdapter.notifyDataSetChanged();

                                }
                                //Log.e("token",AccessToken.getCurrentAccessToken().getToken());
                                if (response == null)
                                    Log.e("res", "null");
                                else {
                                    Log.e("res", "not null " + response.toString());
                                }
                                //Log.e("res",response.getRawResponse());
                                //Log.e("res",e.toString());

                                hultNewsRecycleView.swapAdapter(hultNewsAdapter, false);
                                hultNewsAdapter.notifyDataSetChanged();


                            }
                        });

                request.executeAsync();

            }catch (Exception e) {
                e.printStackTrace();
            }
        }


//        newsContainer.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                return true;
//            }
//        });


        new getPostTask().execute();

        return view;
    }

    private class getPostTask extends AsyncTask<URL, Integer, Example> {
        protected Example doInBackground(URL... urls) {

            try {
            final Example[] e = {null};
            GraphRequest request = GraphRequest.newGraphPathRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/433900623415047/feed",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            // Insert your code here
                            Gson gson = new Gson();
                            e[0] = gson.fromJson(response.getRawResponse(), Example.class);
                            //hultNewsArrayList.addAll(e.getData());

                            if(e[0]!=null) {
                                TinyDB tinyDB = new TinyDB(getContext());
                                String res = tinyDB.getString("response");


                                    for (int i = 0; i < e[0].getData().size(); i++) {
                                        if (!res.contains(e[0].getData().get(i).getCreatedTime())) {
                                            hultNewsArrayList.add(e[0].getData().get(i));
                                        }
                                    }



                                    tinyDB.putString("response", response.getRawResponse());

                                    hultNewsRecycleView.swapAdapter(hultNewsAdapter, false);
                                    hultNewsAdapter.notifyDataSetChanged();



                            }
                            //Log.e("token",AccessToken.getCurrentAccessToken().getToken());
                            if(response == null)
                                Log.e("res","null");
                            else {
                                Log.e("res", "not null "+response.toString() );
                            }
                            //Log.e("res",response.getRawResponse());
                            //Log.e("res",e.toString());

                            hultNewsRecycleView.swapAdapter(hultNewsAdapter,false);
                            hultNewsAdapter.notifyDataSetChanged();

                        }
                    });

            request.executeAsync();

            return e[0];
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("error", e.toString());
            }
            return null;
        }

        protected void onProgressUpdate(Integer... progress) {

        }

        protected void onPostExecute(Example result) {

            hultNewsAdapter.notifyDataSetChanged();
            hultNewsRecycleView.swapAdapter(hultNewsAdapter,false);
        }
    }

}
