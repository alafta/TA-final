package com.example.projectfrontend.classBAM;

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

public class bamAdapter extends PagerAdapter {
    private List<bamModel> models;
    private LayoutInflater layoutInflater;
    private Context context;


    public bamAdapter (List<bamModel> models, Context context) {
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
        View view = layoutInflater.inflate(R.layout.bam_item, container, false);

        ImageView imageView;
        TextView title;
        TextView subtitle;

        imageView = view.findViewById(R.id.illu);
        title = view.findViewById(R.id.descTitle);
        subtitle = view.findViewById(R.id.descSub);

        imageView.setImageResource(models.get(position).getImage());
        title.setText(models.get(position).getTitle());
        subtitle.setText(models.get(position).getSubtitle());

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }


}
