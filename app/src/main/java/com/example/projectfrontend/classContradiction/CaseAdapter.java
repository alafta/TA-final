package com.example.projectfrontend.classContradiction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfrontend.R;

import java.util.ArrayList;

public class CaseAdapter extends RecyclerView.Adapter<CaseAdapter.ViewHolder> {
    private final contRecyclerInterface recyclerInterface;
    private ArrayList <CaseItemModel> dataItem;
    private Context context;
    
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textNumber, textCase;
        public ViewHolder(@NonNull View itemView, contRecyclerInterface recyclerInterface) {
            super(itemView);
            
            textNumber = itemView.findViewById(R.id.numbCase);
            textCase = itemView.findViewById(R.id.caseText);
            
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (CaseAdapter.this.recyclerInterface !=null) {
                        int pos = getAdapterPosition();
                        
                        if (pos !=RecyclerView.NO_POSITION) {
                            CaseAdapter.this.recyclerInterface.onItemClick(pos, dataItem.get(pos));
                        }
                    }
                }
            });
            
        }
    }

    public CaseAdapter(contRecyclerInterface recyclerInterface, ArrayList<CaseItemModel> dataItem, Context context) {
        this.recyclerInterface = recyclerInterface;
        this.dataItem = dataItem;
        this.context = context;
    }

    CaseAdapter(contRecyclerInterface recyclerInterface, ArrayList<CaseItemModel> data) {
        this.recyclerInterface = recyclerInterface;
        this.dataItem = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.case_item, parent, false);
        return new ViewHolder(view, recyclerInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView text_number = holder.textNumber;
        TextView text_case = holder.textCase;
        
        text_number.setText(dataItem.get(position).getNumber());
        text_case.setText(context.getString(dataItem.get(position).getCaseStudy()));

    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }


}
