package com.kmlz.optcredit.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kmlz.optcredit.R;

import java.util.List;

public class ExpencesAdapter extends RecyclerView.Adapter<ExpencesAdapter.ViewHolder> {



    Context context;

    public ExpencesAdapter(){

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_expence,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class  ViewHolder extends RecyclerView.ViewHolder{

        TextView name, amount, id, type;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.id_text_view_expence_name);
            amount = itemView.findViewById(R.id.id_text_view_expende_amount);
            type = itemView.findViewById(R.id.id_text_view_expence_type);
            id = itemView.findViewById(R.id.id_text_view_exp_id);
        }
    }
}
