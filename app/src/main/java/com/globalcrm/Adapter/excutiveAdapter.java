package com.globalcrm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.icu.text.RelativeDateTimeFormatter;
import android.net.Uri;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.globalcrm.ExecutiveMonthHistory;
import com.globalcrm.Model.Executivelist;
import com.globalcrm.Model.TodayApoList;
import com.globalcrm.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class excutiveAdapter extends RecyclerView.Adapter<excutiveAdapter.ViewHolder> implements Filterable {

    public Context context;
    private static final int SWIPE_MIN_DISTANCE = 150;
    private static final int SWIPE_THRESHOLD_VELOCITY = 250;
    private GestureDetector detector;
    TextView b_co_name;
    List<Executivelist> todaySales;
    LayoutInflater inflater;
    protected List<Executivelist> origList;

    public excutiveAdapter(Context ctx, List<Executivelist> objects) {
        this.context      = ctx;
        this.todaySales  = objects;
        //this.itemList = objects;
        this.origList = objects;
        this.inflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view= inflater.inflate(R.layout.row_excutive_month,parent,false);
         return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position){

        Executivelist item= todaySales.get(position);
        holder.co_name.setText(item.getCoName());
        holder.c_mobile.setText(item.getCoPhone());

        holder.c_mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Executivelist item= todaySales.get(position);
                if(item.getCoPhone()!=null) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", item.getCoPhone(), null));
                    context.startActivity(intent);

                }
            }
        });


        holder.click_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Executivelist item= todaySales.get(position);
                Intent i= new Intent(context, ExecutiveMonthHistory.class);
                i.putExtra("id",item.getId());
                i.putExtra("name",item.getCoName());
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return todaySales.size();
    }

    public long Daybetween(String date1, String date2, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        Date Date1 = null,Date2 = null;
        try{
            Date1 = sdf.parse(date1);
            Date2 = sdf.parse(date2);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return (Date2.getTime() - Date1.getTime())/(24*60*60*1000);
    }

   public class ViewHolder extends RecyclerView.ViewHolder {
     CardView click_cardview;
     TextView co_name,c_mobile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            click_cardview=(CardView)itemView.findViewById(R.id.click_cardview);
            co_name=(TextView)itemView.findViewById(R.id.co_name);
            c_mobile=(TextView)itemView.findViewById(R.id.c_mobile);

        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<Executivelist> results = new ArrayList<>();
                if (origList == null)
                    origList = new ArrayList<>(todaySales);
                if (constraint != null && constraint.length() > 0) {
                    if (origList != null && origList.size() > 0) {
                        for (final Executivelist cd : origList) {
                            if (cd.getCoName().toLowerCase().contains(constraint.toString().toLowerCase())||cd.getCoName().toLowerCase().contains(constraint.toString().toLowerCase()))
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
            protected void publishResults(final CharSequence constraint,
                                          FilterResults results) {
                todaySales = new ArrayList<>((ArrayList<Executivelist>) results.values);
                // FIXME: 8/16/2017 implement Comparable with sort below
                ///Collections.sort(itemList);
                notifyDataSetChanged();
            }
        };
    }
}
