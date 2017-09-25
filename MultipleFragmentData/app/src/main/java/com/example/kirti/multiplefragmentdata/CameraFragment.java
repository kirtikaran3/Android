package com.example.kirti.multiplefragmentdata;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class CameraFragment extends Fragment {


    public CameraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_camera, container, false);

        Button button = (Button) v.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BlankFragment blankFragment = new BlankFragment();

                Bundle bundle = new Bundle();
                bundle.putString("Name","Kirti Karan");
                bundle.putString("ID","12345");
                blankFragment.setArguments(bundle);

               FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.relativeLayoutID,blankFragment).commit();



            }
        });


        return v;
    }

}
