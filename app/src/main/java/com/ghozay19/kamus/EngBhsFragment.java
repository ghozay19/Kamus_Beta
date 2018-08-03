package com.ghozay19.kamus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ghozay19.kamus.Adapter.EngBhsAdapter;
import com.ghozay19.kamus.Database.EngBhsHelper;
import com.ghozay19.kamus.Model.EngBhsModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class EngBhsFragment extends Fragment {

    RecyclerView recyclerView;
    EngBhsAdapter dictionaryAdapter;
    EngBhsHelper engBhsHelper;
    SearchView svWord;
    View rootView;

    public EngBhsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_eng_indo, container, false);

        svWord = (SearchView) view.findViewById(R.id.svWord);
        svWord.setFocusable(true);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvEngBhs);
        rootView = view.findViewById(R.id.rootlayoutEng);

        svWord.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                engBhsHelper = new EngBhsHelper(getContext());
                dictionaryAdapter = new EngBhsAdapter(getContext());

                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(dictionaryAdapter);

                engBhsHelper.open();

                ArrayList<EngBhsModel> engBhsModels = engBhsHelper.getDataByWord(newText);

                engBhsHelper.closeEng();
                dictionaryAdapter.addItem(engBhsModels);


                return false;
            }
        });
        return view;
    }

    public void onResume(){
        super.onResume();
        svWord.setQuery("",false);
        rootView.requestFocus();
    }
}
