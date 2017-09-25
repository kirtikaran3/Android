package com.example.kirti.multiplefragmentdata;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Bundle bundle = getArguments();

        if(bundle !=null){

            String name = bundle.getString("Name");
            String id = bundle.getString("ID");

            Toast.makeText(getContext(), name,Toast.LENGTH_SHORT).show();
            Toast.makeText(getContext(),id,Toast.LENGTH_SHORT).show();

        }

        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

}
