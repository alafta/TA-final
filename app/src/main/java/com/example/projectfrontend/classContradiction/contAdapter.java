package com.example.projectfrontend.classContradiction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.projectfrontend.R;

import java.util.List;

public class contAdapter extends PagerAdapter {
    private List<contModel> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public contAdapter (List<contModel> models, Context context) {
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
        View view = layoutInflater.inflate(R.layout.cont_item, container, false);

        ImageView imageView;
        TextView desc;

        imageView = view.findViewById(R.id.illu);
        desc = view.findViewById(R.id.desc);


        imageView.setImageResource(models.get(position).getImage());
        desc.setText(models.get(position).getDesc());


        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
