package aleeha.com.example.transire.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import aleeha.com.example.transire.R;
import aleeha.com.example.transire.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

//    private FragmentGalleryBinding binding;

    String[] galleryCardNames;
    int[] galleryCardImages={
            R.drawable.room_gallery_1,R.drawable.room_gallery_2,R.drawable.room_gallery_3,
            R.drawable.room_gallery_4,R.drawable.room_gallery_5,R.drawable.room_gallery_6
    };
    RecyclerView RV_gallery_card_container;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_gallery, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        galleryCardNames = getResources().getStringArray(R.array.gallery_card_names);
        RV_gallery_card_container = view.findViewById(R.id.rv_gallery_card_container);

        GalleryCardAdapter galleryCardAdapter = new GalleryCardAdapter(
                getActivity().getApplicationContext(),
                galleryCardNames,
                galleryCardImages
        );
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(),2,GridLayoutManager.VERTICAL,false);
        RV_gallery_card_container.setLayoutManager(gridLayoutManager);
        RV_gallery_card_container.setAdapter(galleryCardAdapter);
    }

    //    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }

}