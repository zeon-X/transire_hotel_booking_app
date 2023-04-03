package aleeha.com.example.transire.ui.rooms;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

import aleeha.com.example.transire.LoginActivity;
import aleeha.com.example.transire.R;
import aleeha.com.example.transire.SplashActivity;


public class BookingFragment extends Fragment {

    private static final String ARG_RoomName = "RoomName";
    private static final String ARG_RoomPrice = "RoomPrice";

    FirebaseAuth mAuth;
    FirebaseUser user;

    // TODO: Rename and change types of parameters
    private String RoomName;
    private int RoomPrice;

    String[] adultChileNumber;
    private AutoCompleteTextView autoCompleteTextViewAdult, autoCompleteTextViewChild;
    ArrayAdapter<String>adapterItems;
    TextView tv_room_booking_price;
    Button btn_submit_room_book;
    TextInputEditText et_arrivalDate,et_departureDate;

    TextInputEditText et_customerName, et_customerPhone, et_customerEmail;

    String CusName, CusEmail, CusPhone, CusNoAdult, CusNoChild, CusArrivalDate, CusDepartureDate;
    public BookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        adultChileNumber = getResources().getStringArray(R.array.adult_child_booking_dropdown);
        autoCompleteTextViewAdult = (AutoCompleteTextView) view.findViewById(R.id.act_booking_adult_dropdown);
        autoCompleteTextViewChild = (AutoCompleteTextView) view.findViewById(R.id.act_booking_child_dropdown);
        tv_room_booking_price = (TextView) view.findViewById(R.id.tv_room_booking_price);
        btn_submit_room_book = (Button) view.findViewById(R.id.btn_submit_room_book);
        et_arrivalDate = (TextInputEditText) view.findViewById(R.id.et_arrivalDate);
        et_departureDate = (TextInputEditText) view.findViewById(R.id.et_departureDate);
        et_customerName = (TextInputEditText) view.findViewById(R.id.et_customerName);
        et_customerEmail = (TextInputEditText) view.findViewById(R.id.et_customerEmail);
        et_customerPhone = (TextInputEditText) view.findViewById(R.id.et_customerPhone);

        if(user!=null){
            et_customerEmail.setText(user.getEmail());
            //Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent iLogin = new Intent(getActivity(), LoginActivity.class);
            startActivity(iLogin);
            getActivity().finish();
        }

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        et_arrivalDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        String date = dayOfMonth+"/"+month+"/"+year;
                        CusArrivalDate = date;
                        et_arrivalDate.setText(date);
                    }
                },year, month,day);
                dialog.show();
            }
        });
        et_departureDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        String date = dayOfMonth+"/"+month+"/"+year;
                        CusDepartureDate = date;
                        et_departureDate.setText(date);
                    }
                },year, month,day);
                dialog.show();
            }
        });

        adapterItems = new ArrayAdapter<String>(getActivity(),R.layout.dropdown_list_item,adultChileNumber);

        autoCompleteTextViewAdult.setAdapter(adapterItems);
        autoCompleteTextViewChild.setAdapter(adapterItems);

        autoCompleteTextViewAdult.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CusNoAdult = adapterView.getItemAtPosition(i).toString();
            }
        });

        autoCompleteTextViewChild.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CusNoChild = adapterView.getItemAtPosition(i).toString();
            }
        });

        Bundle args = getArguments();
        if (args != null) {
            RoomName = getArguments().getString(ARG_RoomName);
            RoomPrice = getArguments().getInt(ARG_RoomPrice);
            // Do something with the data
            tv_room_booking_price.setText("PHP "+Integer.toString(RoomPrice)); // price
        }

        btn_submit_room_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CusName = et_customerName.getText().toString();
                CusEmail = et_customerEmail.getText().toString();
                CusPhone = et_customerPhone.getText().toString();
//                Toast.makeText(getActivity(), CusName+" "+CusEmail+" "+CusPhone+" "+CusNoAdult+" "+CusNoChild+" "+CusArrivalDate+" " +CusDepartureDate, Toast.LENGTH_LONG).show();
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