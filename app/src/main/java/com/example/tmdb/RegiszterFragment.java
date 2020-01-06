package com.example.tmdb;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;


public class RegiszterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private com.google.firebase.database.DatabaseReference mDatabase;
    private BottomNavigationView navBar;
    EditText username_ET;
    EditText password_ET;


    public RegiszterFragment() {
        // Required empty public constructor
    }

    public RegiszterFragment(BottomNavigationView navBar){
        this.navBar = navBar;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegiszterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegiszterFragment newInstance(String param1, String param2) {
        RegiszterFragment fragment = new RegiszterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_regiszter, container, false);

        username_ET = view.findViewById(R.id.username_ET_regiszter);
        password_ET = view.findViewById(R.id.password_ET2_regiszter);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Button home_fragment = view.findViewById(R.id.regiszter_BT_fragment);

        home_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String username = username_ET.getText().toString();
                final String password = password_ET.getText().toString();

                if(username.equals(""))
                {
                    Toast.makeText(getContext(),"please add username",Toast.LENGTH_SHORT).show();
                }
                else if(password.equals(""))
                {
                    Toast.makeText(getContext(),"please add password",Toast.LENGTH_SHORT).show();
                }
                else {
                    navBar.setVisibility(View.VISIBLE);
                    User user = new User(username,password);

                    mDatabase.child("users").push().setValue(user);

                    HomeFragment homeFragment = new HomeFragment(user);

                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fg_placeholder, homeFragment, "HomeFragment");
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                }

            });

        return view;

    }

}
