package aleeha.com.example.transire.ui.rooms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

import aleeha.com.example.transire.R;

public class MyBookingAdapter extends RecyclerView.Adapter<MyBookingAdapter.MyBookingViewHolder> {

    Context context;
    ArrayList<String> roomName,arrivalDate, departureDate, price;

    public MyBookingAdapter(Context context, ArrayList<String> roomName, ArrayList<String> arrivalDate, ArrayList<String> departureDate, ArrayList<String> price) {
        this.context = context;
        this.roomName = roomName;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.price = price;
    }

    @NonNull
    @Override
    public MyBookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_my_booking,parent,false);

        return new MyBookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBookingViewHolder holder, int i) {
        holder.tv_rn.setText(roomName.get(i));
        holder.tv_ad.setText("Arrival Date: "+arrivalDate.get(i));
        holder.tv_dd.setText("Departure Date: "+departureDate.get(i));
        holder.tv_tc.setText("Total Cost: "+price.get(i));
    }

    @Override
    public int getItemCount() {
        return roomName.size();
    }

    public class MyBookingViewHolder extends RecyclerView.ViewHolder {
        TextView tv_rn, tv_ad, tv_dd, tv_tc;
        public MyBookingViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_rn = itemView.findViewById(R.id.tv_rn);
            tv_ad = itemView.findViewById(R.id.tv_ad);
            tv_dd = itemView.findViewById(R.id.tv_dd);
            tv_tc = itemView.findViewById(R.id.tv_tc);
        }
    }
}
