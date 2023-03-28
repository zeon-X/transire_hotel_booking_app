package aleeha.com.example.transire.ui.rooms;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import aleeha.com.example.transire.R;
import aleeha.com.example.transire.ui.location.LocationFragment;

public class RoomsFragment extends Fragment {

    private static final String ARG_PARAM1 = "roomName";
    private static final String ARG_PARAM2 = "roomIntro";

    private static final String ARG_PARAM3 = "roomPrice";
    private static final String ARG_PARAM4 = "roomRating";
    private static final String ARG_PARAM5 = "roomRatingCount";
    private static final String ARG_PARAM6 = "roomImage";

    RecyclerView RV_RoomCardContainer;
    String[] roomName,roomIntro;
    int[] roomPrice = {9000,4000,2000,1000};
    int[] roomRating = {5,4,4,3};
    int[] roomRatingCount = {10,40,20,11};
    int[] roomImage = {
        R.drawable.room_container_1,R.drawable.room_container_2, R.drawable.room_container_3, R.drawable.room_container_4
    };


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
    public RoomsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RoomsFragment newInstance(String param1, String param2) {
        RoomsFragment fragment = new RoomsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rooms, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        roomName = getResources().getStringArray(R.array.room_names);
        roomIntro = getResources().getStringArray(R.array.room_details_intro);
        RV_RoomCardContainer = (RecyclerView) view.findViewById(R.id.rv_room_card_container);

        RoomCardContainerAdapter roomCardContainerAdapter = new RoomCardContainerAdapter(
                getActivity().getApplicationContext(),
                roomName,
                roomPrice,
                roomRatingCount,
                roomRating,
                roomImage);

        RV_RoomCardContainer.setAdapter(roomCardContainerAdapter);
        RV_RoomCardContainer.setLayoutManager(new LinearLayoutManager(getContext()));
        
        roomCardContainerAdapter.setOnItemClickListener(new RoomCardContainerAdapter.ClickListener() {
            @Override
            public void onItemClick(int i, View view) {

                Bundle bundle = getRoomDetailsBundle(roomName[i], roomIntro[i],roomPrice[i],roomRating[i],roomRatingCount[i],roomImage[i]);
                navigateToRoomDetailsWithData(getView(),bundle);
            }
        });
    }


    public void navigateToRoomDetailsWithData(View view, Bundle data) {
        Navigation.findNavController(view).navigate(R.id.nav_room_details,data);
    }

    public Bundle getRoomDetailsBundle(String param1, String param2, int param3, int param4, int param5, int param6 ){
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);

        args.putInt(ARG_PARAM3, param3);
        args.putInt(ARG_PARAM4, param4);
        args.putInt(ARG_PARAM5, param5);
        args.putInt(ARG_PARAM6, param6);
        return args;
    }

}