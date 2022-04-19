package com.globalcrm.Adapter;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.globalcrm.Model.TodayApoList;
import com.globalcrm.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TodayAppoitmentAdapter extends RecyclerView.Adapter<TodayAppoitmentAdapter.ViewHolder> implements Filterable {
    public Context context;
    private static final int SWIPE_MIN_DISTANCE = 150;
    private static final int SWIPE_THRESHOLD_VELOCITY = 250;
    private GestureDetector detector;
    TextView b_co_name;
    List<TodayApoList> todaySales;
    LayoutInflater inflater;
    protected List<TodayApoList> itemList;
    protected List<TodayApoList> origList;

    public TodayAppoitmentAdapter(Context ctx, List<TodayApoList> objects) {
        this.context      = ctx;
        this.todaySales  = objects;
        this.itemList = objects;
        this.origList = objects;
        this.inflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.row_all_appoitment,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position){

        TodayApoList item= todaySales.get(position);
        holder.appointment_time.setText(item.getCurrTime());
        holder.appinment_compny_name.setText(item.getCompanyName());
        holder.appinment_client_name.setText(item.getClientName());
        holder.appinment_client_number.setText(item.getMobileNo());
        holder.appinment_address.setText(item.getAddress());
        holder.appinment_product.setText(item.getDisFor());
        holder.appinment_tm_name.setText(item.getTmName());
        holder.appinment_ex_name.setText(item.getExName());
        holder.appinment_client_mobile.setText(item.getMobileNo());
        holder.appinment_client_mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TodayApoList item= todaySales.get(position);
                if(item.getMobileNo()!=null) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", item.getMobileNo(), null));
                   context.startActivity(intent);
                }
            }
        });
        if(item.getTotal().equals("0")){
            if(item.getLeadStatus().equals("4")){
                holder.appinment_status_close.setVisibility(View.VISIBLE);
            }else {
                holder.appinment_status_close.setVisibility(View.GONE);
            }
        }else {
            holder.appinment_status_close.setVisibility(View.GONE);
        }
        if(item.getLeadStatus().equals("1")){

            holder.appinment_feedback_status.setText(item.getLead_name());
         // holder.appinment_feedback_status.setText("Client Not Available");
            holder.appinment_feedback_status.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            holder.appinment_feedback.setText(item.getNiRes());

        }else if(item.getLeadStatus().equals("2")){

            holder.appinment_feedback_status.setText(item.getLead_name());
           // holder.appinment_feedback_status.setText("Client Not Interested");
            holder.appinment_feedback_status.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            holder.appinment_feedback.setText(item.getNiRes());

        }else if(item.getLeadStatus().equals("3")){

            holder.appinment_feedback_status.setText(item.getLead_name());
            //holder.appinment_feedback_status.setText("Followup");
            holder.appinment_feedback_status.setTextColor(context.getResources().getColor(R.color.green));
            holder.appinment_feedback.setText(item.getFuReson());

        }else if(item.getLeadStatus().equals("4")){

            holder.appinment_feedback_status.setText("Close : "+item.getTotal());
            holder.appinment_feedback_status.setTextColor(context.getResources().getColor(R.color.blue_home));
            holder.appinment_feedback.setText(item.getTotal());

        }if(item.getLeadStatus().equals("0")){
            holder.appinment_feedback_status.setText(item.getLead_name());
//            holder.appinment_feedback_status.setText("Pending");
            holder.appinment_feedback_status.setTextColor(context.getResources().getColor(R.color.black));
            holder.appinment_feedback.setText("No Add");

        }

//        if(!item.getReachTime().isEmpty()) {
//            if (!item.getFeedbackTime().isEmpty()) {
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa", Locale.getDefault());
//                Date feedback_date = null;
//                Date reach_time = null;
//                long min = 0;
//                try {
//                    feedback_date = dateFormat.parse(item.getFeedbackTime());
//                    reach_time = dateFormat.parse(item.getReachTime());
//                    long difference = Math.abs(reach_time.getTime() - feedback_date.getTime());
//                    Log.d("mytag", "diff" + difference);
//                    long minuteFor = ((difference / 1000) / 60);
//                    holder.appinment_duration.setText(String.valueOf(minuteFor)+" Min");
//                    //  Toast.makeText(context, "diff"+minuteFor, Toast.LENGTH_SHORT).show();
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

//        holder.appinment_status_close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TodayApoList item= todaySales.get(position);
//               // A_id = item.getLead_id();
//                //   Toast.makeText(ctx, "" + A_id, Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(context, New_Appinment_Accpet_Activity.class);
//                Datastorage.WritePreference("Ap_id", item.getLeadId(), context);
//                context.startActivity(i);
//            }
//        });

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
        TextView appointment_time,appinment_compny_name,appinment_client_name,appinment_client_number,cu_tm_name,reach_time,appinment_duration;
        TextView appinment_address,appinment_product,appinment_feedback,tely_caller_name,appinment_status_close,appinment_tm_name;
        TextView appinment_ex_name,appinment_feedback_status,appinment_client_mobile;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            appointment_time=(TextView)itemView.findViewById(R.id.appointment_time);
            appinment_compny_name=(TextView)itemView.findViewById(R.id.appinment_compny_name);
            appinment_client_name=(TextView)itemView.findViewById(R.id.appinment_client_name);
            appinment_client_mobile=(TextView)itemView.findViewById(R.id.appinment_client_mobile);
            appinment_client_number=(TextView)itemView.findViewById(R.id.appinment_client_number);
            appinment_address=(TextView)itemView.findViewById(R.id.appinment_address);
            appinment_product=(TextView)itemView.findViewById(R.id.appinment_product);
            appinment_feedback=(TextView)itemView.findViewById(R.id.appinment_feedback);
            appinment_feedback_status=(TextView)itemView.findViewById(R.id.appinment_feedback_status);
            appinment_tm_name=(TextView)itemView.findViewById(R.id.appinment_tm_name);
            appinment_ex_name=(TextView)itemView.findViewById(R.id.appinment_ex_name);
            reach_time=(TextView)itemView.findViewById(R.id.reach_time);
            appinment_duration=(TextView)itemView.findViewById(R.id.appinment_duration);
            //tely_caller_name=(TextView)itemView.findViewById(R.id.tely_caller_name);
            appinment_status_close=(TextView)itemView.findViewById(R.id.appinment_status_close);
        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<TodayApoList> results = new ArrayList<>();
                if (origList == null)
                    origList = new ArrayList<>(todaySales);
                if (constraint != null && constraint.length() > 0) {
                    if (origList != null && origList.size() > 0) {
                        for (final TodayApoList cd : origList) {
                            if (cd.getTmName().toLowerCase().contains(constraint.toString().toLowerCase())||cd.getExName().toLowerCase().contains(constraint.toString().toLowerCase())||cd.getLead_name().toLowerCase().contains(constraint.toString().toLowerCase()))
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
                todaySales = new ArrayList<>((ArrayList<TodayApoList>) results.values);
                // FIXME: 8/16/2017 implement Comparable with sort below
                ///Collections.sort(itemList);
                notifyDataSetChanged();
            }
        };
    }
}
