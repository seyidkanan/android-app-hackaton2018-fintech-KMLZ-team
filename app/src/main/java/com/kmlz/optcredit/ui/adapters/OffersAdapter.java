package com.kmlz.optcredit.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kmlz.optcredit.R;
import com.kmlz.optcredit.models.Offer;

import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ViewHolder> {

    private List<Offer> offers;
    Context context;

    public OffersAdapter(List<Offer> offers, Context context){
        this.context = context;
        this.offers = offers;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item_offer,viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.offerName.setText(offers.get(i).getOfferName());
        holder.bankName.setText(offers.get(i).getBankName());
        // we'll use bankIcon in future
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView bankIcon;
        TextView bankName, offerName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bankIcon = itemView.findViewById(R.id.image_view_bank_icon);
            bankName = itemView.findViewById(R.id.id_text_view_credit_type);
            offerName = itemView.findViewById(R.id.id_text_view_credit_type);
        }
    }
}
