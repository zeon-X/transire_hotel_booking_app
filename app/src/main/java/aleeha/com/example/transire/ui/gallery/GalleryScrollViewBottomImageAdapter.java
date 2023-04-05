package aleeha.com.example.transire.ui.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import aleeha.com.example.transire.R;

public class GalleryScrollViewBottomImageAdapter extends RecyclerView.Adapter<GalleryScrollViewBottomImageAdapter.ImageViewHolder> {

    private static ClickListener clicklistener;
    Context context;
    int images [];

    public GalleryScrollViewBottomImageAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.gallery_bottom_scroll_image_card, parent,false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.v_gallery_image.setBackgroundResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View v_gallery_image;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            v_gallery_image = itemView.findViewById(R.id.v_gallery_image);
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
        clicklistener =  clickListener;
    }
}
