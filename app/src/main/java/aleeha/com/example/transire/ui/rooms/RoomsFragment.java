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

    private static final String ARG_CheckIn = "roomCheckIn";
    private static final String ARG_CheckOut = "roomCheckOut";
    private static final String ARG_LuggageStorage = "roomLuggageStorage";
    private static final String ARG_CancellationAndPrePayment = "roomCancellationAndPrePayment";
    private static final String ARG_ChildrenAndExtraBed = "roomChildrenAndExtraBed";
    private static final String ARG_AdditionalInfo = "roomAdditionalInfo";

    RecyclerView RV_RoomCardContainer;
    String[] roomName,roomIntro;
    int[] roomPrice = {9000,4000,2000,1000};
    int[] roomRating = {5,4,4,3};
    int[] roomRatingCount = {10,40,20,11};
    int[] roomImage = {
        R.drawable.room_container_1,R.drawable.room_container_2, R.drawable.room_container_3, R.drawable.room_container_4
    };

    String[] roomCheckIn={"14:00-22:00","14:00-22:00","14:00-22:00","14:00-22:00"},
            roomCheckOut={"14:00-22:00","14:00-22:00","14:00-22:00","14:00-22:00"},
            roomLuggageStorage={"14:00-22:00","14:00-22:00","14:00-22:00","14:00-22:00"},
            roomCancellationAndPrePayment=
                    {
                            "•\tGuests must provide a minimum of 14 days notice for cancellation in order to receive a full refund.\n" +
                                    "•\tAny cancellations made within 14 days of the reservation date will result in a forfeiture of the prepayment.\n" +
                                    "•\tPrepayment is required to secure the reservation.\n",
                            "•\tCancellation and prepayment policies may vary depending on the booking conditions.\n" +
                                    "•\tPlease refer to the booking confirmation email or contact our customer service team for more information on the cancellation and prepayment policies.\n",
                            "•\tTo receive a full refund, guests must cancel their reservation at least 14 days prior to the reservation date.\n" +
                                    "•\tIf a reservation is cancelled within 14 days of the reservation date, the prepayment will be forfeited.\n" +
                                    "•\tA prepayment is required to confirm and hold the reservation.\n",
                            "a. Guests may cancel their reservation free of charge up to 24 hours before arrival.\n" +
                                    " b. Guests will be charged the full amount of the reservation if they cancel within 24 hours of arrival. \n" +
                                    "c. Prepayment is required for all reservations.\n"
                    },
            roomChildrenAndExtraBed=
                    {
                            "•\tChildren under the age of 12 stay for free.\n" +
                                    "•\tA maximum of two extra beds can be added to a room for an additional fee of 1000 peso per night.\n" +
                                    "•\tAny additional guests beyond the maximum occupancy listed for each room will not be allowed.\n",
                            "•\tChildren are welcome to stay in our exclusive transient living room.\n" +
                                    "•\tExtra beds can be arranged upon request, subject to availability.\n" +
                                    "•\tThere may be an additional charge for extra beds.\n",
                            "•\tChildren under 12 years of age can stay for free.\n" +
                                    "•\tUp to two extra beds can be added to a room for an additional charge of 1000 peso per night.\n" +
                                    "•\tOnly the maximum number of guests allowed in each room can stay. Additional guests will not be permitted.\n",
                            "a. Children under the age of 12 stay free of charge when using existing beds.\n" +
                                    " b. One extra bed can be provided upon request for an additional fee. \n" +
                                    "c. Baby cots are also available upon request.\n"
                    },
            roomAdditionalInfo=
                    {
                            "•Smoking is not permitted anywhere on the property.\n" +
                                    "•\tPets are not allowed.\n" +
                                    "•\tGuests are responsible for any damages or excessive cleaning required during their stay.\n" +
                                    "•\tCheck-in is available between 3:00pm and 9:00pm. Late check-ins must be arranged in advance.\n" +
                                    "•\tCheck-out is at 11:00am.\n•" +
                                    "\tThe use of candles or any open flames is strictly prohibited.\n" +
                                    "•\tQuiet hours are from 10:00pm to 7:00am.\n" +
                                    "•\tAny violations of these rules may result in a forfeiture of the security deposit.\n",
                            "Our exclusive transient living room is designed to provide a comfortable and luxurious experience for our guests. Here are some additional details you should know before your stay:\n" +
                                    "•\tThe living room is equipped with a king-size bed, a private bathroom, a sitting area, and a kitchenette.\n" +
                                    "•\tComplimentary high-speed Wi-Fi is available throughout the room.\n" +
                                    "•\tThe room is air-conditioned to ensure your comfort during your stay.\n" +
                                    "•\tComplimentary breakfast is provided every morning during your stay.\n" +
                                    "•\tOur customer service team is available 24/7 to assist you with any inquiries or requests you may have.\n",
                            "•Smoking and pets are not allowed on the property." +
                                    "•\tGuests will be held responsible for any damages or excessive cleaning required during their stay.\n" +
                                    "•\tLate check-ins must be arranged in advance, and check-in time is between 3:00pm and 9:00pm.\n" +
                                    "•\tCheck-out time is at 11:00am.\n" +
                                    "•\tThe use of candles or open flames is strictly prohibited.\n" +
                                    "•\tQuiet hours are in effect from 10:00pm to 7:00am.\n" +
                                    "•\tAny violation of these rules may result in forfeiture of the security deposit.\n",
                            "a. Check-in time is from 2:00 PM to 12:00 AM. \n" +
                                    "b. Check-out time is before 12:00 PM. \n" +
                                    "c. Smoking is not allowed in the rooms or in any public areas of the hotel.\n" +
                                    " d. Pets are not allowed in the rooms or in any public areas of the hotel.\n" +
                                    "e. Guests are responsible for any damages caused to the room during their stay. \n" +
                                    "f. Room service is available from 7:00 AM to 10:00 PM. \n" +
                                    "g. The Transient House owners is not responsible for any lost or stolen items.\n" +
                                    " h. Guests are required to show a valid photo ID and credit card upon check-in. \n" +
                                    "i. The Transient House reserves the right to refuse service to anyone for any reason. \n" +
                                    "j. Any violations of these rules may result in eviction from the hotel without a refund.\n" +
                                    "We hope that these rules will ensure a comfortable and enjoyable stay for all guests. " +
                                    "Please feel free to contact us if you have any questions or concerns.\n"
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

                Bundle bundle = getRoomDetailsBundle
                        (roomName[i], roomIntro[i],roomPrice[i],roomRating[i],roomRatingCount[i],roomImage[i],
                        roomCheckIn[i],roomCheckOut[i],roomLuggageStorage[i],roomCancellationAndPrePayment[i],roomChildrenAndExtraBed[i],roomAdditionalInfo[i] );
                navigateToRoomDetailsWithData(getView(),bundle);
            }
        });
    }


    public void navigateToRoomDetailsWithData(View view, Bundle data) {
        Navigation.findNavController(view).navigate(R.id.nav_room_details,data);
    }

    public Bundle getRoomDetailsBundle
            (String param1, String param2, int param3, int param4, int param5, int param6,
             String param7,String param8,String param9,String param10,String param11,String param12  ){

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);

        args.putInt(ARG_PARAM3, param3);
        args.putInt(ARG_PARAM4, param4);
        args.putInt(ARG_PARAM5, param5);
        args.putInt(ARG_PARAM6, param6);

        args.putString(ARG_CheckIn, param7);
        args.putString(ARG_CheckOut, param8);
        args.putString(ARG_LuggageStorage, param9);
        args.putString(ARG_CancellationAndPrePayment, param10);
        args.putString(ARG_ChildrenAndExtraBed, param11);
        args.putString(ARG_AdditionalInfo, param12);

        return args;
    }

}