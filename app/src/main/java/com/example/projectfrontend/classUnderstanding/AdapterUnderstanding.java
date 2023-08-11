package com.example.projectfrontend.classUnderstanding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfrontend.R;

import java.util.ArrayList;

public class AdapterUnderstanding extends RecyclerView.Adapter<AdapterUnderstanding.ViewHolder> {
    private final RecyclerInterface recyclerInterface;
    private ArrayList<ItemModel> dataItem;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textTitle;
        ImageView iconItem;
        public ViewHolder(@NonNull View itemView, RecyclerInterface recyclerInterface) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.title);
            iconItem = itemView.findViewById(R.id.iconItem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos !=RecyclerView.NO_POSITION) {
                            recyclerInterface.onItemClick(pos, dataItem.get(pos));
                        }
                    }
                }
            });
        }
    }

    public AdapterUnderstanding(Context context, ArrayList<ItemModel> data,
                                RecyclerInterface recyclerInterface) {
        this.dataItem = data;
        this.context = context;
        this.recyclerInterface = recyclerInterface;
    }

    @NonNull
    @Override
    public AdapterUnderstanding.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.understanding_item, parent, false);
        return new ViewHolder(view, recyclerInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUnderstanding.ViewHolder holder, int position) {

        TextView text_title = holder.textTitle;
        ImageView icon_image = holder.iconItem;



        text_title.setText(context.getString(dataItem.get(position).getTitle()));
        icon_image.setImageResource(dataItem.get(position).getIcon());


    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }


}
