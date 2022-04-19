package com.globalcrm.Adapter;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.globalcrm.Model.TodaySales;
import com.globalcrm.R;
import java.util.ArrayList;
import java.util.List;

public class CurreuntMonthSalesAdapter extends RecyclerView.Adapter<CurreuntMonthSalesAdapter.ViewHolder> implements Filterable {

   public Context context;
   private GestureDetector detector;
   TextView b_co_name;
   List<TodaySales> todaySales;
   LayoutInflater inflater;
   protected List<TodaySales> origList;

    public CurreuntMonthSalesAdapter(Context ctx, List<TodaySales> objects) {
        this.context      = ctx;
        this.todaySales  = objects;
        this.origList  = objects;
        this.inflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.row_current_month,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        TodaySales item= todaySales.get(position);

        holder.cu_co_name.setText(item.getCompanyName());
        holder.cu_tm_name.setText(item.getTmName());
        holder.b_amount.setText(item.getPro_amount());
        holder.b_product.setText(item.getProduct_id());
        holder.cu_client_name.setText(item.getClientName());
        holder.cu_client_mo.setText(item.getMobileNo());
        holder.t_amount.setText(item.getTotal());
        holder.date.setText(item.getCur_date());
    }

    @Override
    public int getItemCount() {
        return todaySales.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cu_co_name,b_product,b_amount,cu_tm_name,cu_client_name,cu_client_mo,t_amount,date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cu_co_name=(TextView)itemView.findViewById(R.id.cu_co_name);
            b_product=(TextView)itemView.findViewById(R.id.b_product);
            b_amount=(TextView)itemView.findViewById(R.id.b_amount);
            cu_tm_name=(TextView)itemView.findViewById(R.id.cu_tm_name);
            cu_client_name=(TextView)itemView.findViewById(R.id.cu_client_name);
            cu_client_mo=(TextView)itemView.findViewById(R.id.cu_client_mo);
            t_amount=(TextView)itemView.findViewById(R.id.t_amount);
            date=(TextView)itemView.findViewById(R.id.date);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<TodaySales> results = new ArrayList<>();
                if (origList == null)
                    origList = new ArrayList<>(todaySales);

                if (constraint != null && constraint.length() > 0) {
                    if (origList != null && origList.size() > 0){
                        for (final TodaySales cd : origList) {
                            if (cd.getCompanyName().toLowerCase().contains(constraint.toString().toLowerCase())||cd.getTmName().toLowerCase().contains(constraint.toString().toLowerCase())||cd.getCur_date().toLowerCase().contains(constraint.toString().toLowerCase()))
                                results.add(cd);
                        }
                    }

                    oReturn.values = results;
                    oReturn.count = results.size();//newly Aded by ZA
                } else {
                    oReturn.values = origList;
                    oReturn.count = origList.size();//newly added by ZA
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(final CharSequence constraint, FilterResults results){
                todaySales = new ArrayList<>((ArrayList<TodaySales>) results.values);
                // FIXME: 8/16/2017 implement Comparable with sort below
                ///Collections.sort(itemList);
                notifyDataSetChanged();
            }
        };
    }
}
