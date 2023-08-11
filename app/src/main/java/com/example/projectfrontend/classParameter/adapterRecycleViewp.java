package com.example.projectfrontend.classParameter;

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

public class adapterRecycleViewp extends RecyclerView.Adapter<adapterRecycleViewp.ViewHolder> {
    private final Context context;
    private  ArrayList<String> id_p, nama_p, meaningP, saeP;
    private final RecyclerViewInterfaceP recyclerViewInterface;

    adapterRecycleViewp(Context context, ArrayList id_p, ArrayList nama_p, ArrayList meaningP, ArrayList saeP, RecyclerViewInterfaceP recyclerViewInterface) {
        this.context = context;
        this.id_p = id_p;
        this.nama_p = nama_p;
        this.meaningP = meaningP;
        this.saeP = saeP;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_itemp, parent, false);
        return new ViewHolder(view, recyclerViewInterface);

    }

    @Override
    public void onBindViewHolder(@NonNull adapterRecycleViewp.ViewHolder holder, int position) {
        holder.id_p_txt.setText(String.valueOf(id_p.get(position)));
        holder.nama_p_txt.setText(String.valueOf(nama_p.get(position)));
        holder.meaning = String.valueOf(meaningP.get(position));
        holder.sae = String.valueOf(saeP.get(position));
    }

    @Override
    public int getItemCount() {
        return id_p.size();
    }

    public void filterList(ArrayList<String> filteredList, ArrayList<String> filterId, ArrayList<String> filterM, ArrayList<String> filterS) {
        nama_p =  filteredList;
        id_p = filterId;
        meaningP = filterM;
        saeP = filterS;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imkotak, imbulet;
        TextView id_p_txt, nama_p_txt;
        String meaning, sae;

        public ViewHolder(@NonNull View itemView, RecyclerViewInterfaceP recyclerViewInterface) {
            super(itemView);
            id_p_txt = itemView.findViewById(R.id.tvnop1);
            nama_p_txt = itemView.findViewById(R.id.tvp1);
            nama_p_txt.setTextSize(12F);
            imkotak = itemView.findViewById(R.id.imp1);
            imbulet = itemView.findViewById(R.id.buletp1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        int id = Integer.parseInt(id_p_txt.getText().toString());
                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(id, nama_p_txt.getText().toString(), meaning, sae);
                        }
                    }
                }
            });
        }
    }
}
