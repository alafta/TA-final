package com.example.projectfrontend.classPrinciple;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfrontend.R;

import java.util.ArrayList;

public class adapterRecycleViewps2 extends RecyclerView.Adapter<adapterRecycleViewps2.ViewHolder> {
    private Context context;
    private ArrayList<String> id_ps, nama_ps;
    private final RecyclerViewInterfacePs2 recyclerViewInterface;

    adapterRecycleViewps2(Context context, ArrayList id_ps, ArrayList nama_ps, RecyclerViewInterfacePs2 recyclerViewInterface){
        this.context=context;
        this.id_ps=id_ps;
        this.nama_ps=nama_ps;
        this.recyclerViewInterface=recyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_principles, parent, false);
        return new ViewHolder(view, recyclerViewInterface);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id_ps_txt.setText(String.valueOf(id_ps.get(position)));
        holder.nama_ps_txt.setText(String.valueOf(nama_ps.get(position)));
    }

    @Override
    public int getItemCount(){
        return nama_ps.size();
    }
    public void filterList(ArrayList<String> filteredList, ArrayList<String> filterId) {
        nama_ps =  filteredList;
        id_ps = filterId;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imkotak, imbulet;
        TextView id_ps_txt, nama_ps_txt;

        public ViewHolder(@NonNull View itemView, RecyclerViewInterfacePs2 recyclerViewInterface){
            super(itemView);
            id_ps_txt = itemView.findViewById(R.id.bgnomps);
            nama_ps_txt = itemView.findViewById(R.id.txtprinc);
            imbulet = itemView.findViewById(R.id.buletps1);
            cardView = itemView.findViewById(R.id.cardps);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        int id = Integer.parseInt(id_ps_txt.getText().toString());
                        if(pos!=RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(id, nama_ps_txt.getText().toString());
                        }
                    }
                }
            });
        }
    }
}
