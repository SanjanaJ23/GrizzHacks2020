package com.example.firebasetrial;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private static final int SIGN_IN_REQUEST_CODE = 123;

    public static final String USERPROFCOLL = "com.example.firebasetrial.USERPROFILES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            // Start sign in/sign up activity
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .build(),
                    SIGN_IN_REQUEST_CODE
            );
        } else {
            // User is already signed in. Therefore, display
            // a welcome Toast
            Toast.makeText(this,
                    "Welcome " + FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getDisplayName(),
                    Toast.LENGTH_LONG)
                    .show();

            FetchUserProfile(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SIGN_IN_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(this,
                        "Successfully signed in. Welcome!",
                        Toast.LENGTH_LONG)
                        .show();
                FetchUserProfile(FirebaseAuth.getInstance().getCurrentUser().getEmail());
            } else {
                Toast.makeText(this,
                        "We couldn't sign you in. Please try again later.",
                        Toast.LENGTH_LONG)
                        .show();

                // Close the app
                //finish();
            }
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_sign_out) {
            AuthUI.getInstance().signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(MainActivity.this,
                                    "You have been signed out.",
                                    Toast.LENGTH_LONG)
                                    .show();

                            // Close activity
                            finish();
                        }
                    });
        }
        return true;
    }
    /** Called when the user taps the Update button */
    public void sendMessage(View view) {
        EditText profile_name = (EditText) findViewById(R.id.profile_name);
        EditText profile_university = (EditText) findViewById(R.id.profile_university);
        EditText profile_year = (EditText) findViewById(R.id.profile_year);
        EditText profile_major = (EditText) findViewById(R.id.profile_major);
        CheckBox profile_reading = (CheckBox) findViewById(R.id.reading);
        CheckBox profile_movies = (CheckBox) findViewById(R.id.movies);
        CheckBox profile_music = (CheckBox) findViewById(R.id.music);
        CheckBox profile_visual_arts = (CheckBox) findViewById(R.id.visual_arts);
        CheckBox profile_sports = (CheckBox) findViewById(R.id.sports);
        CheckBox profile_gaming = (CheckBox) findViewById(R.id.gaming);
        CheckBox profile_cooking = (CheckBox) findViewById(R.id.cooking);
        CheckBox profile_travel = (CheckBox) findViewById(R.id.travel);
        CheckBox profile_community_service = (CheckBox) findViewById(R.id.community_service);
        CheckBox profile_other = (CheckBox) findViewById(R.id.other);
        EditText profile_classes = (EditText) findViewById(R.id.profile_classes);
        EditText profile_bio = (EditText) findViewById(R.id.profile_bio);

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        String name = profile_name.getText().toString();
        String university = profile_university.getText().toString();
        String year = profile_year.getText().toString();
        String major = profile_major.getText().toString();
        String reading = profile_reading.isChecked() == true ? "true" : "false";
        String movies = profile_movies.isChecked() == true ? "true" : "false";
        String music = profile_music.isChecked() == true ? "true" : "false";
        String visual_arts = profile_visual_arts.isChecked() == true ? "true" : "false";
        String sports = profile_sports.isChecked() == true ? "true" : "false";
        String gaming = profile_gaming.isChecked() == true ? "true" : "false";
        String cooking = profile_cooking.isChecked() == true ? "true" : "false";
        String travel = profile_travel.isChecked() == true ? "true" : "false";
        String community_service = profile_community_service.isChecked() == true ? "true" : "false";
        String other = profile_other.isChecked() == true ? "true" : "false";
        String classes = profile_classes.getText().toString();
        String bio = profile_bio.getText().toString();

        FirebaseFirestore.getInstance().collection(USERPROFCOLL)
                .document(email)
                .set(new UserProfile(name, university, year, major, reading, movies, music, visual_arts,
                        sports, gaming, cooking, travel, community_service, other, classes, bio))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this,
                                "Updated profile successfully.",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,
                                "Saving to FireStore failed.",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });



    }

    public void chat(View view) {
        Intent intent = new Intent(this, ChatMainActivity.class);
        startActivity(intent);
    }

    public void FetchUserProfile(String email) {

        FirebaseFirestore.getInstance().collection(USERPROFCOLL).
                document(email).
                get().
                addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()) {
                            UserProfile uprof = documentSnapshot.toObject(UserProfile.class);
                            UpdateUserProfileUI(uprof);
                        }
                        else {
                            FirebaseFirestore.getInstance().collection(USERPROFCOLL).
                                    document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).
                                    set(new UserProfile()).
                                    addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(MainActivity.this,
                                                    "Created new account in Firestore.",
                                                    Toast.LENGTH_LONG)
                                                    .show();
                                        }
                                    }).
                                    addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(MainActivity.this,
                                                    "Saving to FireStore failed.",
                                                    Toast.LENGTH_LONG)
                                                    .show();
                                        }
                                    });
                        }
                    }
                }).
                addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,
                                "Failed in checking for document.",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
    }

    public void UpdateUserProfileUI(UserProfile u) {
        // Capture the layout's TextView and set the string as its text
        EditText profile_name = findViewById(R.id.profile_name);
        profile_name.setText(u.getName());

        EditText profile_university = findViewById(R.id.profile_university);
        profile_university.setText(u.getUniversity());

        EditText profile_year = findViewById(R.id.profile_year);
        profile_year.setText(u.getYear());

        EditText profile_major = findViewById(R.id.profile_major);
        profile_major.setText(u.getMajor());

        CheckBox profile_reading = (CheckBox) findViewById(R.id.reading);
        profile_reading.setChecked(u.getReading().equals("true") ? true : false);

        CheckBox profile_movies = (CheckBox) findViewById(R.id.movies);
        profile_movies.setChecked(u.getMovies().equals("true") ? true : false);

        CheckBox profile_music = (CheckBox) findViewById(R.id.music);
        profile_music.setChecked(u.getMusic().equals("true") ? true : false);

        CheckBox profile_visual_arts = (CheckBox) findViewById(R.id.visual_arts);
        profile_visual_arts.setChecked(u.getVisual_Arts().equals("true") ? true : false);

        CheckBox profile_sports = (CheckBox) findViewById(R.id.sports);
        profile_sports.setChecked(u.getSports().equals("true") ? true : false);

        CheckBox profile_gaming = (CheckBox) findViewById(R.id.gaming);
        profile_gaming.setChecked(u.getGaming().equals("true") ? true : false);

        CheckBox profile_cooking = (CheckBox) findViewById(R.id.cooking);
        profile_cooking.setChecked(u.getCooking().equals("true") ? true : false);

        CheckBox profile_travel = (CheckBox) findViewById(R.id.travel);
        profile_travel.setChecked(u.getTravel().equals("true") ? true : false);

        CheckBox profile_community_service = (CheckBox) findViewById(R.id.community_service);
        profile_community_service.setChecked(u.getCommunity_Service().equals("true") ? true : false);

        CheckBox profile_other = (CheckBox) findViewById(R.id.other);
        profile_other.setChecked(u.getOther().equals("true") ? true : false);

        EditText profile_classes = findViewById(R.id.profile_classes);
        profile_classes.setText(u.getClasses());

        EditText profile_bio = findViewById(R.id.profile_bio);
        profile_bio.setText(u.getBio());
    }
}