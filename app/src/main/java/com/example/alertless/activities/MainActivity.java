package com.example.alertless.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alertless.R;
import com.example.alertless.entities.ProfileDetailsEntity;
import com.example.alertless.utils.Constants;
import com.example.alertless.view.adapters.ProfileListAdapter;
import com.example.alertless.view.models.ProfileViewModel;

import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName() + Constants.TAG_SUFFIX;
    private ProfileViewModel profileViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProfileListAdapter profileListAdapter = getProfileListAdapter();

        // Init Profile view model
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        profileViewModel.getAllProfileDetailsEntities().observe(this, profileDetailsEntities -> {

            Collections.sort(profileDetailsEntities, Comparator.comparing(ProfileDetailsEntity::getName));
            profileListAdapter.setProfileDetails(profileDetailsEntities);
        });
    }


    private ProfileListAdapter getProfileListAdapter() {
        RecyclerView recyclerView = findViewById(R.id.profileRecyclerview);
        final ProfileListAdapter adapter = new ProfileListAdapter(this, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        return adapter;
    }

    public void editProfile(View view) {
        Intent intent = new Intent(this, ProfileEditActivity.class);
        startActivity(intent);
    }
}
