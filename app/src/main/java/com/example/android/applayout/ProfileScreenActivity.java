package com.example.android.applayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileScreenActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private String userID;
    private TextView Name, Year, Department, Nssid, Servons;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);


        //add Firebase Database stuff


        Name = (TextView) findViewById(R.id.Name);
        Year = (TextView) findViewById(R.id.Year);
        Department = (TextView) findViewById(R.id.Department);
        Nssid = (TextView) findViewById(R.id.Nssid);
        Servons = (TextView) findViewById(R.id.Servons);


        // mListView = (ListView) findViewById(R.id.listview);

        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be useable.
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    // Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    //toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    //Log.d(TAG, "onAuthStateChanged:signed_out");
                    //toastMessage("Successfully signed out.");
                }
                // ...
            }
        };


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showData(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            UserInformation uInfo = new UserInformation();
            uInfo.setName(ds.child(userID).getValue(UserInformation.class).getName());
            uInfo.setDepartment(ds.child(userID).getValue(UserInformation.class).getDepartment());
            uInfo.setYear(ds.child(userID).getValue(UserInformation.class).getYear());
            uInfo.setNssid(ds.child(userID).getValue(UserInformation.class).getNssid());
            uInfo.setServons(ds.child(userID).getValue(UserInformation.class).getServons());

            Name.setText(uInfo.getName());
            Department.setText(uInfo.getDepartment());
            Year.setText(uInfo.getYear());
            Nssid.setText(uInfo.getNssid());
            Servons.setText(uInfo.getServons());
            //display all the information
            // Log.d(TAG, "showData: name: " + uInfo.getName());
            //Log.d(TAG, "showData: email: " + uInfo.getEmail());
            //Log.d(TAG, "showData: phone_num: " + uInfo.getPhone_num());

            // ArrayList<String> array  = new ArrayList<>();
            //array.add(uInfo.getName());
            //array.add(uInfo.getEmail());
            //array.add(uInfo.getPhone_num());
            //ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
            //mListView.setAdapter(adapter);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}



            /**
             * customizable toast
             * @param message
             */
           /* private void toastMessage(String message){
                Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
            }
        }

*/

