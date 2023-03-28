package aleeha.com.example.transire.ui.rooms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import aleeha.com.example.transire.R;

public class RoomCardContainerAdapter extends RecyclerView.Adapter<RoomCardContainerAdapter.MyViewHolder> {

    private static ClickListener clicklistener;
    Context context;
    String [] roomName;
    int [] roomPrice, roomRatingCount, roomRating;
    int [] roomImage;

    public RoomCardContainerAdapter(Context context, String[] roomName, int[] roomPrice, int[] roomRatingCount, int[] roomRating, int[] roomImage) {
        this.context = context;
        this.roomName = roomName;
        this.roomPrice = roomPrice;
        this.roomRatingCount = roomRatingCount;
        this.roomRating = roomRating;
        this.roomImage = roomImage;
    }

    @NonNull
    @Override
    public RoomCardContainerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.room_card,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomCardContainerAdapter.MyViewHolder holder, int i) {
        holder.tv_roomName.setText(roomName[i]);
        holder.tv_roomPrice.setText(Integer.toString(roomPrice[i]));
        holder.tv_roomRatingCount.setText("("+Integer.toString(roomRatingCount[i])+")");

        holder.rb_roomRating.setRating(roomRating[i]);

        holder.v_roomImage.setBackgroundResource(roomImage[i]);
    }

    @Override
    public int getItemCount() {
        return roomName.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_roomName, tv_roomPrice, tv_roomRatingCount;
        RatingBar rb_roomRating;
        View v_roomImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_roomName = itemView.findViewById(R.id.tv_room_name);
            tv_roomPrice = itemView.findViewById(R.id.tv_room_price);
            tv_roomRatingCount = itemView.findViewById(R.id.tv_rating_count);
            rb_roomRating = itemView.findViewById(R.id.rb_room_rating);
            v_roomImage = itemView.findViewById(R.id.view_room_card_img);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clicklistener.onItemClick(getAdapterPosition(),view);
        }
    }

    // implementing interface
    public interface ClickListener{
        void onItemClick(int position, View view);
    }

    public void setOnItemClickListener(ClickListener clickListener){
        RoomCardContainerAdapter.clicklistener = clickListener;
    }
}
