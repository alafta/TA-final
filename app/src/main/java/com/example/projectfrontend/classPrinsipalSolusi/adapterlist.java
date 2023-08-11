package com.example.projectfrontend.classPrinsipalSolusi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfrontend.R;

import java.util.ArrayList;

public class adapterlist extends RecyclerView.Adapter<adapterlist.ViewHolder> {
    private ArrayList penjelasaning;
    private LayoutInflater minflater;

    adapterlist(Context context, ArrayList penjelasanIng){
        this.minflater = LayoutInflater.from(context);
        this.penjelasaning = penjelasanIng;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = minflater.inflate(R.layout.list_itempenjelasanps, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        holder.txtpenjelasan.setText(String.valueOf(penjelasaning.get(position)));
    }

    @Override
    public int getItemCount(){
        return penjelasaning.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtpenjelasan;

        ViewHolder(View itemView){
            super(itemView);
            txtpenjelasan = itemView.findViewById(R.id.tvpenjelasanps);
        }
    }
}
