package com.example.projectfrontend.classPrinciple;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfrontend.R;

import java.util.ArrayList;

public class adapterlist2 extends RecyclerView.Adapter<adapterlist2.ViewHolder> {
    private ArrayList penjelasaning;
    private LayoutInflater minflater;

    adapterlist2(Context context, ArrayList penjelasanIng){
        this.minflater = LayoutInflater.from(context);
        this.penjelasaning = penjelasanIng;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = minflater.inflate(R.layout.principles_desc_list, parent, false);
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
            txtpenjelasan = itemView.findViewById(R.id.recyclerDescprinc);
        }
    }
}
