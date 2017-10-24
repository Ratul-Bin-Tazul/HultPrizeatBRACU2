package com.ratulbintazul.www.hultprizeatbracu.Fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
import com.ratulbintazul.www.hultprizeatbracu.Adapter.IceNewsAdapter;
import com.ratulbintazul.www.hultprizeatbracu.DataClasses.Datum;
import com.ratulbintazul.www.hultprizeatbracu.DataClasses.Example;
import com.ratulbintazul.www.hultprizeatbracu.R;
import com.ratulbintazul.www.hultprizeatbracu.Util.Codes;
import com.ratulbintazul.www.hultprizeatbracu.Util.TinyDB;

import java.net.URL;
import java.util.ArrayList;


public class IceNews extends Fragment {


    public RecyclerView iceNewsRecycleView;
    public RecyclerView.Adapter iceNewsAdapter;
    public RecyclerView.LayoutManager iceNewsLayoutManager;

    public ArrayList<Datum> iceNewsArrayList = new ArrayList<>();

    
    public IceNews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ice_news, container, false);


        iceNewsRecycleView = (RecyclerView)view.findViewById(R.id.iceNewsRecyclerview);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        iceNewsRecycleView.setHasFixedSize(true);


        // use a linear layout manager
        iceNewsLayoutManager = new LinearLayoutManager(getContext());
        iceNewsRecycleView.setLayoutManager(iceNewsLayoutManager);

        // specify an adapter
        iceNewsAdapter = new IceNewsAdapter(iceNewsArrayList,getContext());
        iceNewsRecycleView.setAdapter(iceNewsAdapter);

        iceNewsAdapter.notifyDataSetChanged();

        TinyDB tinyDB = new TinyDB(getContext());
        String res = tinyDB.getString("ice_response");


        Gson gson = new Gson();
        Example exp = gson.fromJson(res, Example.class);

        if(exp!=null) {

            iceNewsArrayList.addAll(exp.getData());
        }else{

            try {
                final Example[] e = {null};
                GraphRequest request = GraphRequest.newGraphPathRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/" + Codes.icePageCode + "/feed",
                        new GraphRequest.Callback() {
                            @Override
                            public void onCompleted(GraphResponse response) {
                                // Insert your code here
                                Gson gson = new Gson();
                                e[0] = gson.fromJson(response.getRawResponse(), Example.class);
                                //iceNewsArrayList.addAll(e.getData());

                                if (e[0] != null) {
                                    TinyDB tinyDB = new TinyDB(getContext());
                                    String res = tinyDB.getString("ice_response");

                                    for (int i = 0; i < e[0].getData().size(); i++) {
                                        if (!res.contains(e[0].getData().get(i).getCreatedTime())) {
                                            iceNewsArrayList.add(e[0].getData().get(i));
                                        }
                                    }


                                    tinyDB.putString("ice_response", response.getRawResponse());

                                    iceNewsRecycleView.swapAdapter(iceNewsAdapter, false);
                                    iceNewsAdapter.notifyDataSetChanged();

                                }
                                //Log.e("token",AccessToken.getCurrentAccessToken().getToken());
                                //Log.e("res",response.getRawResponse());
                                //Log.e("res",e.toString());

                                iceNewsRecycleView.swapAdapter(iceNewsAdapter, false);
                                iceNewsAdapter.notifyDataSetChanged();


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
                        "/"+ Codes.icePageCode+"/feed",
                        new GraphRequest.Callback() {
                            @Override
                            public void onCompleted(GraphResponse response) {
                                // Insert your code here
                                Gson gson = new Gson();
                                e[0] = gson.fromJson(response.getRawResponse(), Example.class);
                                //iceNewsArrayList.addAll(e.getData());

                                if(e[0]!=null) {
                                    TinyDB tinyDB = new TinyDB(getContext());
                                    String res = tinyDB.getString("ice_response");


                                    for (int i = 0; i < e[0].getData().size(); i++) {
                                        if (!res.contains(e[0].getData().get(i).getCreatedTime())) {
                                            iceNewsArrayList.add(e[0].getData().get(i));
                                        }
                                    }



                                    tinyDB.putString("ice_response", response.getRawResponse());

                                    iceNewsRecycleView.swapAdapter(iceNewsAdapter, false);
                                    iceNewsAdapter.notifyDataSetChanged();



                                }
                                //Log.e("token",AccessToken.getCurrentAccessToken().getToken());

                                //Log.e("res",response.getRawResponse());
                                //Log.e("res",e.toString());

                                iceNewsRecycleView.swapAdapter(iceNewsAdapter,false);
                                iceNewsAdapter.notifyDataSetChanged();


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

            iceNewsAdapter.notifyDataSetChanged();
            iceNewsRecycleView.swapAdapter(iceNewsAdapter,false);
        }
    }


}
