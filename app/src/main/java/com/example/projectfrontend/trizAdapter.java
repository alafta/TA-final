package com.example.projectfrontend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class trizAdapter extends PagerAdapter {

    private List<trizModel> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public trizAdapter(List<trizModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.triz_item, container, false);

        ImageView imageView;
        TextView text;

        imageView = view.findViewById(R.id.illu);
        text = view.findViewById(R.id.textUnd);

        imageView.setImageResource(models.get(position).getImage());
        text.setText(models.get(position).getText());

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
