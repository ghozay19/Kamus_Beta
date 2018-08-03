package com.ghozay19.kamus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ghozay19.kamus.Adapter.BhsEngAdapter;
import com.ghozay19.kamus.Database.BhsEngHelper;
import com.ghozay19.kamus.Model.BhsEngModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BhsEngFragment extends Fragment {

    RecyclerView recyclerView;
    BhsEngAdapter dictionaryAdapter;
    BhsEngHelper bhsEngHelper;
    SearchView svWord;
    View rootView;

    public BhsEngFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_indo_eng, container, false);

        svWord = (SearchView) view.findViewById(R.id.svWordBhs);
        svWord.setFocusable(true);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvBhsEng);
        rootView = view.findViewById(R.id.rootlayoutBhs);


        dictionaryAdapter = new BhsEngAdapter(getContext());
        recyclerView.setAdapter(dictionaryAdapter);
        bhsEngHelper = new BhsEngHelper(getContext());
        bhsEngHelper.open();

        dictionaryAdapter = new BhsEngAdapter(getContext());
        recyclerView.setAdapter(dictionaryAdapter);

        bhsEngHelper = new BhsEngHelper(getContext());
        bhsEngHelper.open();


        svWord.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                bhsEngHelper = new BhsEngHelper(getContext());
                dictionaryAdapter = new BhsEngAdapter(getContext());

                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(dictionaryAdapter);

                bhsEngHelper.open();

                ArrayList<BhsEngModel> bhsEngModels = bhsEngHelper.getDataByWord(newText);

                bhsEngHelper.closeBhs();
                dictionaryAdapter.addItem(bhsEngModels);


                return false;
            }
        });
        return view;
    }


    public void onResume() {
        super.onResume();
        svWord.setQuery("", false);
        rootView.requestFocus();
    }
}
