package com.example.projectfrontend;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.projectfrontend.HomeActivityNew.Home2Activity;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class IntroActivity extends AppCompatActivity {

    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private MaterialButton buttonOnboardingAction;
    public ImageButton menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_intro);

        layoutOnboardingIndicators = findViewById(R.id.layoutOnboardingIndicators);
        buttonOnboardingAction = findViewById(R.id.buttonOnboardingAction);

        setupOnboardingItems();

        ViewPager2 onboardingViewpager = findViewById(R.id.onboardingViewPager);
        onboardingViewpager.setAdapter(onboardingAdapter);

        setupOnboardingIndicators();
        setCurrentOnboardingIndicator(0);

        onboardingViewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });

        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onboardingViewpager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()) {
                    onboardingViewpager.setCurrentItem(onboardingViewpager.getCurrentItem() + 1);
                } else {
                    startActivity(new Intent(getApplicationContext(), Home2Activity.class));
                    finish();
                }
            }
        });

        ImageButton changeLang = findViewById(R.id.language_button);
        changeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLanguageDialog ();
            }
        });


    }

    private void showChangeLanguageDialog() {
        final String [] listItems = {"English", "Bahasa"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(IntroActivity.this);
        mBuilder.setTitle("Choose Language");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    setLocale ("en");
                    recreate();
                }
                else if (i == 1) {
                    setLocale ("in");
                    recreate();
                }
                dialogInterface.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    public void loadLocale () {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
    }

    private void setupOnboardingItems() {
        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem itemAboutApps = new OnboardingItem();
        itemAboutApps.setTitle(getString(R.string.onboardingTitle1));
        itemAboutApps.setDesc(getString(R.string.onboardingDesc1));
        itemAboutApps.setImage(R.drawable.intro1);

        OnboardingItem itemMainFeature = new OnboardingItem();
        itemMainFeature.setTitle(getString(R.string.onboardingTitle2));
        itemMainFeature.setDesc(getString(R.string.onboardingDesc2));
        itemMainFeature.setImage(R.drawable.intro2);

        OnboardingItem itemNewUsers = new OnboardingItem();
        itemNewUsers.setTitle(getString(R.string.onboardingTitle3));
        itemNewUsers.setDesc(getString(R.string.onboardingDesc3));
        itemNewUsers.setImage(R.drawable.intro3);

        onboardingItems.add(itemAboutApps);
        onboardingItems.add(itemMainFeature);
        onboardingItems.add(itemNewUsers);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);
    }

    private void setupOnboardingIndicators() {
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);
        }
    }

    private void setCurrentOnboardingIndicator(int index){
        int childCount = layoutOnboardingIndicators.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView)layoutOnboardingIndicators.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_active)
                );
            }else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive)
                );
            };
        }
        if (index == onboardingAdapter.getItemCount() - 1) {
            buttonOnboardingAction.setText(getString(R.string.onboardingButton2));
        } else {
            buttonOnboardingAction.setText(getString(R.string.onboardingButton1));
        }
    }
}