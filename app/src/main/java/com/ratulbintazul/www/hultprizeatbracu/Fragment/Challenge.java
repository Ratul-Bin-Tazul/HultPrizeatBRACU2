package com.ratulbintazul.www.hultprizeatbracu.Fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.ratulbintazul.www.hultprizeatbracu.R;


public class Challenge extends Fragment {

    PDFView pdfView;

    public Challenge() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_challenge, container, false);

        pdfView = (PDFView)view.findViewById(R.id.pdfView);
        //pdfView.fromUri(Uri.parse("android.resource://" + getContext().getPackageName() + "/" + R.raw.hult_prize_challenge_2018_compressed)).load();
        pdfView.fromAsset("pdf.pdf")
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {

                    }
                })
                .enableAnnotationRendering(true)
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {

                    }
                })
                .scrollHandle(new DefaultScrollHandle(getContext()))
                .spacing(10) // in dp
                .load();
        return view;
    }

}
