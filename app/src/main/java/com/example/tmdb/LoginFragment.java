package com.example.tmdb;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private BottomNavigationView navBar;;
    private com.google.firebase.database.DatabaseReference mDatabase,mDatabase2;
    EditText username_ET;
    EditText password_ET;

    public LoginFragment() {
        // Required empty public constructor
    }

    public LoginFragment(BottomNavigationView navView) {
        navBar = navView;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(BottomNavigationView param1) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        username_ET = view.findViewById(R.id.Username_ET);
        password_ET = view.findViewById(R.id.password_ET);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");

        Button next_fragment = view.findViewById(R.id.NextPage_BT);
        Button regiszter_fragment = view.findViewById(R.id.regiszter_BT);

        next_fragment.setOnClickListener(new View.OnClickListener() {
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

                    //User user = new User(username,password);

                    mDatabase.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for(DataSnapshot iterator : dataSnapshot.getChildren())
                            {
                                User user = iterator.getValue(User.class);

                                if(!user.getName().equals(username) || !user.getPassword().equals(password)) {
                                    Toast.makeText(getContext(),"Create an account",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    navBar.setVisibility(View.VISIBLE);

                                    HomeFragment homeFragment = new HomeFragment(user);

                                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.fg_placeholder, homeFragment, "HomeFragment");
                                    fragmentTransaction.addToBackStack(null);
                                    fragmentTransaction.commit();
                                }

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

        regiszter_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RegiszterFragment regiszterFragment = new RegiszterFragment(navBar);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fg_placeholder, regiszterFragment, "RegiszterFragment");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        return view;
    }

}
