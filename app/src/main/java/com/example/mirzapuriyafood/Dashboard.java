package com.example.mirzapuriyafood;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mirzapuriyafood.Adapter.RecipieAdapter;
import com.example.mirzapuriyafood.Classes.RecyclerItemClickListener;
import com.example.mirzapuriyafood.Models.RecipieModel;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/9214589741"; // Test Banner ID
    RecyclerView recyclerView;
    private ViewGroup adContainerView;
    private AdView adView;
    ImageView logout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        // Initialize Mobile Ads SDK
        MobileAds.initialize(this, initializationStatus -> {});

        // Find ad container from XML
        adContainerView = findViewById(R.id.ad_view_container);

        // Create a new AdView
        adView = new AdView(this);
        adView.setAdUnitId(AD_UNIT_ID);

        // Calculate adaptive banner size after layout is ready
        // Inside onCreate after finding adContainerView
        adContainerView.post(() -> {
            // Get actual width of container in pixels
            int adWidth = adContainerView.getWidth();

            // Convert to dp for AdMob size calculation
            float density = getResources().getDisplayMetrics().density;
            int adWidthInDp = (int) (adWidth / density);

            // Get adaptive size
            AdSize adSize = AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidthInDp);
            adView.setAdSize(adSize);

            // Add adView to container
            adContainerView.removeAllViews();
            adContainerView.addView(adView);

            // Load ad
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        });

        // Ad listener
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                Toast.makeText(Dashboard.this, "Ad failed: " + adError.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdLoaded() {
                Toast.makeText(Dashboard.this, "Ad loaded successfully", Toast.LENGTH_SHORT).show();
            }
        });

        // RecyclerView initialization
        recyclerView = findViewById(R.id.restaurant_list);
        recyclerView.setHasFixedSize(true);
        logout = findViewById(R.id.logout_icon);

        logout.setOnClickListener(v -> {
            getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
                    .edit()
                    .clear()
                    .apply();

            Toast.makeText(Dashboard.this, "Logged out successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Dashboard.this, SignIn_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        // Sample data list
        ArrayList<RecipieModel> list = new ArrayList<>();
        list.add(new RecipieModel(R.drawable.cryspymomo, 100, "Crispy Momo", 4.5));
        list.add(new RecipieModel(R.drawable.panir, 140, "Paneer Tikka", 4.0));
        list.add(new RecipieModel(R.drawable.sahipanir, 120, "Sahi Paneer", 3.5));
        list.add(new RecipieModel(R.drawable.burger, 80, "Burger", 4.2));
        list.add(new RecipieModel(R.drawable.somosa, 40, "Samosa", 3.8));
        list.add(new RecipieModel(R.drawable.finger, 50, "Finger Chips", 4.1));
        list.add(new RecipieModel(R.drawable.dish, 100, "Dish", 4.5));

        // Adapter setup
        RecipieAdapter adapter = new RecipieAdapter(list, this);
        recyclerView.setAdapter(adapter);

        StaggeredGridLayoutManager staggered = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggered);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 0) {
                    startActivity(new Intent(Dashboard.this, ScrollingActivity.class));
                }
            }

            @Override
            public void onLongItemClick(View view, int position) {}
        }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyBanner();
    }

    private void destroyBanner() {
        if (adView != null) {
            View parentView = (View) adView.getParent();
            if (parentView instanceof ViewGroup) {
                ((ViewGroup) parentView).removeView(adView);
            }
            adView.destroy();
            adView = null;
        }
    }
}