package com.example.projectfrontend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder> {

    private List<OnboardingItem> OnboardingItems;

    public OnboardingAdapter(List<OnboardingItem> onboardingItems) {
        OnboardingItems = onboardingItems;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.onboarding_item, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        holder.setOnboardingData(OnboardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return OnboardingItems.size();
    }

    class OnboardingViewHolder extends RecyclerView.ViewHolder{

        private TextView textTitle;
        private TextView textDesc;
        private ImageView imageOnboarding;

        OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textDesc = itemView.findViewById(R.id.textDesc);
            imageOnboarding = itemView.findViewById(R.id.imageOnboarding);

        }

        void setOnboardingData(OnboardingItem onboardingItem) {
            textTitle.setText(onboardingItem.getTitle());
            textDesc.setText(onboardingItem.getDesc());
            imageOnboarding.setImageResource(onboardingItem.getImage());
        }
    }
}
