package com.example.projectfrontend.classCaseStudy;

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


public class adapterRecycleCs extends RecyclerView.Adapter<adapterRecycleCs.ViewHolder> {
    private final Context context;
    private final ArrayList  cs, problem,worsening, improving, solution;
    private final RecyclerViewInterfaceCs recyclerViewInterfacecs;

    adapterRecycleCs(Context context,  ArrayList cs, ArrayList problem, ArrayList worsening, ArrayList improving, ArrayList solution, RecyclerViewInterfaceCs recyclerViewInterface){
        this.context=context;
        this.cs=cs;
        this.problem=problem;
        this.worsening=worsening;
        this.improving=improving;
        this.solution=solution;
        this.recyclerViewInterfacecs=recyclerViewInterface;
    }

    @NonNull
    @Override
    public adapterRecycleCs.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_itemstudycase, parent, false);
        return new adapterRecycleCs.ViewHolder(view, recyclerViewInterfacecs);

    }

    @Override
    public void onBindViewHolder(@NonNull adapterRecycleCs.ViewHolder holder, int position) {
        holder.title_cs.setText(String.valueOf(cs.get(position)));
        holder.problem_cs = String.valueOf(problem.get(position));
        holder.solution_cs = String.valueOf(solution.get(position));
        holder.worsening_cs=String.valueOf(worsening.get(position));
        holder.improving_cs=String.valueOf(improving.get(position));
    }

    @Override
    public int getItemCount(){
        return cs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imkotak_cs, arrow_cs;
        TextView title_cs;
        String problem_cs,worsening_cs, improving_cs, solution_cs;

        public ViewHolder(@NonNull View itemView, RecyclerViewInterfaceCs recyclerViewInterface){
            super(itemView);
            title_cs = itemView.findViewById(R.id.tv_cs);
            imkotak_cs = itemView.findViewById(R.id.ivstudycase);
            arrow_cs = itemView.findViewById(R.id.ivarrowcs);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos!=RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick( title_cs.getText().toString(), problem_cs,worsening_cs,improving_cs, solution_cs);
                        }
                    }
                }
            });
        }
    }
}
