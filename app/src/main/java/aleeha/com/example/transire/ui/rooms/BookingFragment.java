package aleeha.com.example.transire.ui.rooms;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import aleeha.com.example.transire.LoginActivity;
import aleeha.com.example.transire.R;
import aleeha.com.example.transire.SplashActivity;


public class BookingFragment extends Fragment {

    private static final String ARG_RoomName = "RoomName";
    private static final String ARG_RoomPrice = "RoomPrice";

    FirebaseAuth mAuth;
    FirebaseUser user;

    Date date1,date2;
    int numOfDays = 1;

    ProgressBar progressBar;
    LinearLayout ll_booking_page;

    // TODO: Rename and change types of parameters
    String RoomName;
    int RoomPrice;

    String[] adultChileNumber;
    AutoCompleteTextView autoCompleteTextViewAdult, autoCompleteTextViewChild;
    ArrayAdapter<String>adapterItems;
    TextView tv_room_booking_price,tv_room_booking_total_price;
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
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        adultChileNumber = getResources().getStringArray(R.array.adult_child_booking_dropdown);
        autoCompleteTextViewAdult = (AutoCompleteTextView) view.findViewById(R.id.act_booking_adult_dropdown);
        autoCompleteTextViewChild = (AutoCompleteTextView) view.findViewById(R.id.act_booking_child_dropdown);
        tv_room_booking_price = (TextView) view.findViewById(R.id.tv_room_booking_price);
        tv_room_booking_total_price = (TextView) view.findViewById(R.id.tv_room_booking_total_price);
        btn_submit_room_book = (Button) view.findViewById(R.id.btn_submit_room_book);
        et_arrivalDate = (TextInputEditText) view.findViewById(R.id.et_arrivalDate);
        et_departureDate = (TextInputEditText) view.findViewById(R.id.et_departureDate);
        et_customerName = (TextInputEditText) view.findViewById(R.id.et_customerName);
        et_customerEmail = (TextInputEditText) view.findViewById(R.id.et_customerEmail);
        et_customerPhone = (TextInputEditText) view.findViewById(R.id.et_customerPhone);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        ll_booking_page = (LinearLayout) view.findViewById(R.id.ll_booking_page);

        Bundle args = getArguments();
        if (args != null) {
            RoomName = getArguments().getString(ARG_RoomName);
            RoomPrice = getArguments().getInt(ARG_RoomPrice);
            // Do something with the data
            tv_room_booking_price.setText("PHP "+Integer.toString(RoomPrice)+" Per Day"); // price
            tv_room_booking_total_price.setText(Integer.toString( RoomPrice));
        }

        if(user!=null){
            et_customerEmail.setText(user.getEmail());
            //Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent iLogin = new Intent(getActivity(), LoginActivity.class);
            startActivity(iLogin);
            getActivity().finish();
        }

        //DATE PICKING
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
                        String date = year+"/"+month+"/"+dayOfMonth;
                        CusArrivalDate = date;
                        et_arrivalDate.setText(date);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            date1 = new Date(year-1900,month-1,dayOfMonth);
                        }
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
                        String date = year+"/"+month+"/"+dayOfMonth;
                        CusDepartureDate = date;

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            date2 =  new Date(year-1900,month-1,dayOfMonth);
                        }
                        long dateBeforeInMs = date1.getTime();
                        long dateAfterInMs = date2.getTime();
                        long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);

                        numOfDays = (int) (timeDiff / (1000 * 60 * 60 * 24));
                        if(numOfDays >= 0){
                            if(numOfDays==0) numOfDays = 1;
                            et_departureDate.setText(date);
                            tv_room_booking_total_price.setText(Integer.toString(numOfDays * RoomPrice));
                        }
                        else{
                            Toast.makeText(getActivity(), "Select a date after "+ CusArrivalDate, Toast.LENGTH_SHORT).show();
                        }
                    }
                },year, month,day);
                dialog.show();
            }
        });


        //DROPDOWN SELECT ADAPTER
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


        //BUTTON CLICK
        btn_submit_room_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CusName = et_customerName.getText().toString();
                CusEmail = et_customerEmail.getText().toString();
                CusPhone = et_customerPhone.getText().toString();
                //navigateToFragment(R.id.nav_home);

                ll_booking_page.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

                String  userEmail = user.getEmail();
                if(TextUtils.isEmpty(user.getEmail())){
                    userEmail="emailNotFound";
                    Toast.makeText(getActivity(), "Login Error. Email not found", Toast.LENGTH_SHORT).show();
                    return;
                }
                Date date = new Date();
                //This method returns the time in millis
                String documentId = Long.toString(date.getTime());

                Map<String, Object> booking_data = new HashMap<>();
                booking_data.put("name", CusName);
                booking_data.put("email", CusEmail);
                booking_data.put("phone", CusPhone);
                booking_data.put("adult", Integer.parseInt(CusNoAdult));
                booking_data.put("child", Integer.parseInt(CusNoChild));

                booking_data.put("arrival", CusArrivalDate);
                booking_data.put("departure", CusDepartureDate);

                booking_data.put("room_name", RoomName);
                booking_data.put("room_price", RoomPrice);
                booking_data.put("days", numOfDays);
                booking_data.put("total_price", numOfDays*RoomPrice);

                db.collection("transient_user_data").document(userEmail).collection("booking").document(documentId)
                        .set(booking_data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //Log.d(TAG, "DocumentSnapshot successfully written!");
                                Toast.makeText(getActivity(), "Room Booked successfully", Toast.LENGTH_SHORT).show();
                                navigateToFragment(R.id.nav_home);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("onFailureBooking", "Error writing document", e);
                                Toast.makeText(getActivity(), "Booking failed", Toast.LENGTH_SHORT).show();
                                ll_booking_page.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
                            }
                        });

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