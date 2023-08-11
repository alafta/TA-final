package com.example.projectfrontend.HomeActivityNew;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfrontend.R;
import com.example.projectfrontend.classPrinciple.PenjelasanPrinsipalSolusi2;
import com.example.projectfrontend.classPrinciple.PrinsipalSolusi2;

import java.util.ArrayList;

public class AdapterCm2 extends RecyclerView.Adapter<AdapterCm2.ViewHolder> {
    private final Context context;
    private final ArrayList<PrinsipalSolusi2> listPrinsipalSolusi2;
    private final ArrayList<PenjelasanPrinsipalSolusi2> listPenjelasanPrinsipalSolusi2;
    private final RecyclerViewInterfaceCm2 recyclerViewInterfaceCm2;


    AdapterCm2(Context context, ArrayList<PrinsipalSolusi2> listPrinsipalSolusi2, ArrayList<PenjelasanPrinsipalSolusi2> penjelasan_ps, RecyclerViewInterfaceCm2 recyclerViewInterfaceCm2) {
        this.context = context;
        this.listPrinsipalSolusi2 = listPrinsipalSolusi2;
        this.listPenjelasanPrinsipalSolusi2 = penjelasan_ps;
        this.recyclerViewInterfaceCm2 = recyclerViewInterfaceCm2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_cm1, parent, false);
        return new ViewHolder(view, listPrinsipalSolusi2, listPenjelasanPrinsipalSolusi2, recyclerViewInterfaceCm2);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id_ps_txt.setText(String.valueOf(listPrinsipalSolusi2.get(position).getId()));
        holder.nama_ps_txt.setText((listPrinsipalSolusi2.get(position).getNamaIngPs()));
    }

    @Override
    public int getItemCount() {
        return listPrinsipalSolusi2.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imbulet, imMinus;
        TextView id_ps_txt, nama_ps_txt, penjelasan_ps_txt;
        Button ilutrasi_ps;
        RelativeLayout containerPenjelasan;


        public ViewHolder(@NonNull View itemView, ArrayList<PrinsipalSolusi2> prinsipalSolusi2, ArrayList<PenjelasanPrinsipalSolusi2> listPenjelasan, RecyclerViewInterfaceCm2 recyclerViewInterfaceCm2) {
            super(itemView);
            imbulet = itemView.findViewById(R.id.circleplus);
            imMinus = itemView.findViewById(R.id.circleminus);
            id_ps_txt = itemView.findViewById(R.id.id_solusiCm);
            nama_ps_txt = itemView.findViewById(R.id.nama_solusiCm);
            penjelasan_ps_txt = itemView.findViewById(R.id.isi_penjelasanSolusi);
            ilutrasi_ps = itemView.findViewById(R.id.button_ilustrasiCm);
            containerPenjelasan = itemView.findViewById(R.id.containerPenjelasan);

            nama_ps_txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = "";
                    if (containerPenjelasan.getVisibility() == View.GONE) {
                        containerPenjelasan.setVisibility(View.VISIBLE);
                        if (imMinus.getVisibility()== View.GONE){
                            imMinus.setVisibility(View.VISIBLE);
                        }

                        PrinsipalSolusi2 ps = prinsipalSolusi2.get(getAdapterPosition());
                        for (PenjelasanPrinsipalSolusi2 penjelasan: listPenjelasan) {
                            if (ps.getId() == penjelasan.getIdPs()) {
                                text += penjelasan.getPenjelasanIng()+"\n";
                            }
                        }
                        penjelasan_ps_txt.setText(text);
                    } else {
                        containerPenjelasan.setVisibility(View.GONE);
                        imMinus.setVisibility(View.GONE);
                    }
                }
            });

            imbulet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = "";
                    if (containerPenjelasan.getVisibility() == View.GONE) {
                        containerPenjelasan.setVisibility(View.VISIBLE);
                        if (imMinus.getVisibility()== View.GONE){
                            imMinus.setVisibility(View.VISIBLE);
                        }

                        PrinsipalSolusi2 ps = prinsipalSolusi2.get(getAdapterPosition());
                        for (PenjelasanPrinsipalSolusi2 penjelasan: listPenjelasan) {
                            if (ps.getId() == penjelasan.getIdPs()) {
                                text += penjelasan.getPenjelasanIng()+"\n";
                            }
                        }
                        penjelasan_ps_txt.setText(text);
                    } else {
                        containerPenjelasan.setVisibility(View.GONE);
                        imMinus.setVisibility(View.GONE);
                    }
                }
            });



            ilutrasi_ps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("tombol ilustrasi","berhasil diklik");
                    if (recyclerViewInterfaceCm2 != null){
                        int pos = getAdapterPosition();

                        int id = Integer.parseInt(id_ps_txt.getText().toString());
                        if(pos!=RecyclerView.NO_POSITION){
                            recyclerViewInterfaceCm2.onItemClick(id, nama_ps_txt.getText().toString());
                        }
                    }                }
            });
        }
    }
}
