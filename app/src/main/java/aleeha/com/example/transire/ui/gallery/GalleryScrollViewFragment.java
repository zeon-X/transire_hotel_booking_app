package aleeha.com.example.transire.ui.gallery;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import aleeha.com.example.transire.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GalleryScrollViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GalleryScrollViewFragment extends Fragment {

    int[][] images ={
            {R.drawable.h1img1,R.drawable.h1img2},
            {R.drawable.h2img1,R.drawable.h2img2,R.drawable.h2img3,},
            {R.drawable.h3img1,R.drawable.h3img2},
            {R.drawable.h4img1,R.drawable.h4img2},
            {R.drawable.h5img1,R.drawable.h5img2},
            {R.drawable.h6img1,R.drawable.h6img2},
    };
    String [] roomNames;
    ImageView iv_focus_image;
    TextView tv_gallery_room_name;
    RecyclerView rv_gallery_scroll_bottom_images;

    private static final String ARG_RoomNo = "RoomNo";
    //INDEX OF THE ROOM
    private int RoomNo;

    public GalleryScrollViewFragment() {}

    public static GalleryScrollViewFragment newInstance(int param1) {
        GalleryScrollViewFragment fragment = new GalleryScrollViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_RoomNo, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            RoomNo = getArguments().getInt(ARG_RoomNo);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery_scroll_view, container, false);
        roomNames = getResources().getStringArray(R.array.gallery_card_names);

        rv_gallery_scroll_bottom_images = (RecyclerView) view.findViewById(R.id.rv_gallery_scroll_bottom_images);
        tv_gallery_room_name = (TextView) view.findViewById(R.id.tv_gallery_room_name);
        iv_focus_image = (ImageView) view.findViewById(R.id.iv_focus_image);

        tv_gallery_room_name.setText(roomNames[RoomNo]);
        iv_focus_image.setBackgroundResource(images[RoomNo][0]);
        GalleryScrollViewBottomImageAdapter galleryScrollViewBottomImageAdapter = new GalleryScrollViewBottomImageAdapter(getActivity(),images[RoomNo]);

        rv_gallery_scroll_bottom_images.setAdapter(galleryScrollViewBottomImageAdapter);
        rv_gallery_scroll_bottom_images.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));

        galleryScrollViewBottomImageAdapter.setOnItemClickListener(new GalleryScrollViewBottomImageAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                iv_focus_image.setBackgroundResource(images[RoomNo][position]);

            }
        });
        return view;
    }
}