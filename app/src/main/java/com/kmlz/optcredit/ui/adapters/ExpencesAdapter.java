package com.kmlz.optcredit.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kmlz.optcredit.R;
import com.kmlz.optcredit.network.responses.ExpenseRes;

import java.util.List;

public class ExpencesAdapter extends RecyclerView.Adapter<ExpencesAdapter.ViewHolder> {

    Context context;
    List<ExpenseRes> expenseRes;

    public ExpencesAdapter(Context context, List<ExpenseRes> expenseRes) {
        this.expenseRes = expenseRes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_expence, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.type.setText(expenseRes.get(i).getCAT_NAME());
        viewHolder.name.setText(expenseRes.get(i).getEXPENSE_NAME());
        viewHolder.amount.setText(expenseRes.get(i).getEXPENSE_AMOUNT());
        Log.e("here in lis","in list");
    }

    @Override
    public int getItemCount() {
        return expenseRes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

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
