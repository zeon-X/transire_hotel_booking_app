package aleeha.com.example.transire.ui.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import aleeha.com.example.transire.R;
import aleeha.com.example.transire.ui.rooms.RoomCardContainerAdapter;

public class GalleryCardAdapter extends RecyclerView.Adapter<GalleryCardAdapter.MyViewHolder> {

    Context context;
    String[] galleryCardNames;
    int[] galleryCardImages;

    public GalleryCardAdapter(Context context, String[] galleryCardNames, int[] galleryCardImages) {
        this.context = context;
        this.galleryCardNames = galleryCardNames;
        this.galleryCardImages = galleryCardImages;
    }

    @NonNull
    @Override
    public GalleryCardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.gallery_card, parent,false);

        return new GalleryCardAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryCardAdapter.MyViewHolder holder, int i) {
        holder.tv_roomName.setText(galleryCardNames[i]);
        holder.v_roomImage.setBackgroundResource(galleryCardImages[i]);
    }

    @Override
    public int getItemCount() {
        return galleryCardNames.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_roomName;
        View v_roomImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_roomName = itemView.findViewById(R.id.tv_gallery_card_name);
            v_roomImage = itemView.findViewById(R.id.v_gallery_card_img);
        }
    }
}
