package aleeha.com.example.transire.ui.rooms;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import aleeha.com.example.transire.R;


public class BookingFragment extends Fragment {

    private static final String ARG_RoomName = "RoomName";
    private static final String ARG_RoomPrice = "RoomPrice";

    // TODO: Rename and change types of parameters
    private String RoomName;
    private int RoomPrice;

    String[] adultChileNumber;
    private AutoCompleteTextView autoCompleteTextViewAdult, autoCompleteTextViewChild;
    ArrayAdapter<String>adapterItems;
    TextView tv_room_booking_price;
    Button BTN_submit_contact_us;

    public BookingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        adultChileNumber = getResources().getStringArray(R.array.adult_child_booking_dropdown);
        autoCompleteTextViewAdult = (AutoCompleteTextView) view.findViewById(R.id.act_booking_adult_dropdown);
        autoCompleteTextViewChild = (AutoCompleteTextView) view.findViewById(R.id.act_booking_child_dropdown);
        tv_room_booking_price = (TextView) view.findViewById(R.id.tv_room_booking_price);
        BTN_submit_contact_us = (Button) view.findViewById(R.id.btn_submit_contact_us);

        adapterItems = new ArrayAdapter<String>(getActivity(),R.layout.dropdown_list_item,adultChileNumber);

        autoCompleteTextViewAdult.setAdapter(adapterItems);
        autoCompleteTextViewChild.setAdapter(adapterItems);

        autoCompleteTextViewAdult.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        autoCompleteTextViewChild.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });

        Bundle args = getArguments();
        if (args != null) {
            RoomName = getArguments().getString(ARG_RoomName);
            RoomPrice = getArguments().getInt(ARG_RoomPrice);
            // Do something with the data
            tv_room_booking_price.setText("PHP "+Integer.toString(RoomPrice)); // price
        }

        BTN_submit_contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Booking Done", Toast.LENGTH_SHORT).show();
                navigateToFragment(R.id.nav_home);
            }
        });


        return view;
    }

    private void navigateToFragment(int destinationId) {
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
        NavController navController = navHostFragment.getNavController();
        navController.navigate(destinationId);
    }
}