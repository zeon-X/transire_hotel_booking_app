package aleeha.com.example.transire.ui.rooms;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import aleeha.com.example.transire.R;


public class RoomDetailsFragment extends Fragment {

    Button BTN_book_now;
    TextView tv_roomName, tv_roomPrice, tv_roomRatingCount, tv_roomIntro;
    RatingBar rb_roomRating;
    View v_roomImage;
    ListView lv_room_amenities;
    ImageView iv_btn_review_dialog;

    TextView tv_check_in, tv_check_out, tv_luggage_in,tv_cp,tv_ceb,tv_ai;
    Button btn_submit_review;

    ArrayAdapter<String> adapterItems;
    String[] roomAmenities;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "roomName";
    private static final String ARG_PARAM2 = "roomIntro";
    private static final String ARG_PARAM3 = "roomPrice";
    private static final String ARG_PARAM4 = "roomRating";
    private static final String ARG_PARAM5 = "roomRatingCount";
    private static final String ARG_PARAM6 = "roomImage";

    private static final String ARG_CheckIn = "roomCheckIn";
    private static final String ARG_CheckOut = "roomCheckOut";
    private static final String ARG_LuggageStorage = "roomLuggageStorage";
    private static final String ARG_CancellationAndPrePayment = "roomCancellationAndPrePayment";
    private static final String ARG_ChildrenAndExtraBed = "roomChildrenAndExtraBed";
    private static final String ARG_AdditionalInfo = "roomAdditionalInfo";


    private static final String ARG_RoomName = "RoomName";
    private static final String ARG_RoomPrice = "RoomPrice";

    // TODO: Rename and change types of parameters
    private String mParam1, mParam2;
    private int mParam3, mParam4, mParam5, mParam6;
    private String CheckIn,CheckOut,LuggageStorage,CancellationAndPrePayment,ChildrenAndExtraBed,AdditionalInfo;

    public RoomDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_room_details, container, false);
        BTN_book_now =(Button) view.findViewById(R.id.btn_book_now);
        tv_roomName =(TextView) view.findViewById(R.id.tv_room_details_name);
        tv_roomIntro =(TextView) view.findViewById(R.id.tv_room_details_intro);
        tv_roomPrice =(TextView) view.findViewById(R.id.tv_room_details_price);
        tv_roomRatingCount =(TextView) view.findViewById(R.id.tv_tv_room_details_rating_count);
        rb_roomRating =(RatingBar) view.findViewById(R.id.rb_room_details_rating);
        v_roomImage =(View) view.findViewById(R.id.view_room_details_image);
        iv_btn_review_dialog = (ImageView) view.findViewById(R.id.iv_btn_review_dialog);

        tv_check_in = (TextView) view.findViewById(R.id.tv_check_in);
        tv_check_out =(TextView) view.findViewById(R.id.tv_check_out);
        tv_luggage_in =(TextView) view.findViewById(R.id.tv_luggage_in);
        tv_cp =(TextView) view.findViewById(R.id.tv_cp);
        tv_ceb =(TextView) view.findViewById(R.id.tv_ceb);
        tv_ai =(TextView) view.findViewById(R.id.tv_ai);

        lv_room_amenities = (ListView) view.findViewById(R.id.lv_room_amenities);

        BTN_book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = getRoomBookingDetailsBundle(mParam1,mParam3);
                navigateToRoomBookingWithData(getView(),bundle);
            }
        });
        // Get the data from the arguments
        Bundle args = getArguments();
        if (args != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getInt(ARG_PARAM3);
            mParam4 = getArguments().getInt(ARG_PARAM4);
            mParam5 = getArguments().getInt(ARG_PARAM5);
            mParam6 = getArguments().getInt(ARG_PARAM6);

            CheckIn = getArguments().getString(ARG_CheckIn);
            CheckOut = getArguments().getString(ARG_CheckOut);
            CancellationAndPrePayment = getArguments().getString(ARG_CancellationAndPrePayment);
            ChildrenAndExtraBed = getArguments().getString(ARG_ChildrenAndExtraBed);
            LuggageStorage = getArguments().getString(ARG_LuggageStorage);
            AdditionalInfo = getArguments().getString(ARG_AdditionalInfo);

            // Do something with the data
            tv_roomName.setText(mParam1); // name
            tv_roomIntro.setText(mParam2); //room intro
            tv_roomPrice.setText(Integer.toString(mParam3)); // price
            rb_roomRating.setRating(mParam4); // rating
            tv_roomRatingCount.setText("("+Integer.toString(mParam5)+")"); //rating count
            v_roomImage.setBackgroundResource(mParam6); //image

            tv_check_in.setText(CheckIn);
            tv_check_out.setText(CheckOut);
            tv_luggage_in.setText(LuggageStorage);
            tv_cp.setText(CancellationAndPrePayment);
            tv_ceb.setText(ChildrenAndExtraBed);
            tv_ai.setText(AdditionalInfo);
        }

        roomAmenities = getResources().getStringArray(R.array.room_amenities_points);

        ArrayList<RoomAmenitiesPoints> roomAmenitiesPoints = new ArrayList<RoomAmenitiesPoints>();
        for(int i=0; i<roomAmenities.length; ++i){
            roomAmenitiesPoints.add(new RoomAmenitiesPoints( roomAmenities[i]));
        }
        RoomAmenitiesAdapter roomAmenitiesAdapter = new RoomAmenitiesAdapter(getActivity(),roomAmenitiesPoints);
        lv_room_amenities.setAdapter(roomAmenitiesAdapter);

        //DIALOG
        Dialog reviewDialog = new Dialog(getActivity());
        reviewDialog.setContentView(R.layout.review_dialog);
        reviewDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        btn_submit_review = reviewDialog.findViewById(R.id.btn_submit_review);


        iv_btn_review_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reviewDialog.show();
            }
        });

        btn_submit_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Review Added Successfully", Toast.LENGTH_SHORT).show();
                reviewDialog.dismiss();
            }
        });

        return view;
    }

    public void navigateToRoomBookingWithData(View view, Bundle data) {
        Navigation.findNavController(view).navigate(R.id.nav_room_booking,data);
    }
    public Bundle getRoomBookingDetailsBundle(String param1, int param2){
        Bundle args = new Bundle();
        args.putString(ARG_RoomName, param1);
        args.putInt(ARG_RoomPrice, param2);
        return args;
    }

}