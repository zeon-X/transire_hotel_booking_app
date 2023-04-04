package aleeha.com.example.transire.ui.rooms;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import aleeha.com.example.transire.LoginActivity;
import aleeha.com.example.transire.R;
import io.grpc.okhttp.internal.Util;


public class MyBookingFragment extends Fragment {

    RecyclerView rv_my_booking;
    ProgressBar progressBar;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth;
    FirebaseUser user;

    ArrayList<String>
            roomName = new ArrayList<String>(),
            arrivalDate = new ArrayList<String>(),
            departureDate = new ArrayList<String>(),
            price = new ArrayList<String>();


    public MyBookingFragment() {}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_booking, container, false);
        rv_my_booking = (RecyclerView) view.findViewById(R.id.rv_my_booking);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);
        rv_my_booking.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        String user_email = "";
        if(user!=null){
            user_email = user.getEmail();
        }
        else{
            Toast.makeText(getActivity(), "Login Required", Toast.LENGTH_SHORT).show();
            Intent iLogin = new Intent(getActivity(), LoginActivity.class);
            startActivity(iLogin);
            getActivity().finish();
        }

        if(TextUtils.isEmpty(user_email)){
            user_email="emailNotFound";
            Toast.makeText(getActivity(), "Login Again Please", Toast.LENGTH_SHORT).show();
            Intent iLogin = new Intent(getActivity(), LoginActivity.class);
            startActivity(iLogin);
            getActivity().finish();
        }

        db.collection("transient_user_data").document(user_email).collection("booking")
                .get(Source.CACHE)
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                roomName.add(document.get("room_name").toString());
                                arrivalDate.add(document.get("arrival").toString());
                                departureDate.add(document.get("departure").toString());
                                price.add(document.get("total_price").toString());
                            }
                            progressBar.setVisibility(View.GONE);
                            rv_my_booking.setVisibility(View.VISIBLE);
                        } else {
                            Log.d("my_booking_data_error", "Error getting documents: ", task.getException());
                            Toast.makeText(getActivity(), "Error getting documents", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            rv_my_booking.setVisibility(View.VISIBLE);
                        }
                    }
                });

        MyBookingAdapter myBookingAdapter = new MyBookingAdapter(getActivity(),roomName,arrivalDate,departureDate,price);
        rv_my_booking.setAdapter(myBookingAdapter);
        rv_my_booking.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}