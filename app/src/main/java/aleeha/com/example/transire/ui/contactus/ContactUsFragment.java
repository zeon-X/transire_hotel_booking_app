package aleeha.com.example.transire.ui.contactus;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import aleeha.com.example.transire.LoginActivity;
import aleeha.com.example.transire.R;


public class ContactUsFragment extends Fragment {

   String[] contactUsDropDown;
   private AutoCompleteTextView autoCompleteTextView;
   Button BTN_submit_form;
   TextView et_message, et_email,et_phone,et_name;
   LinearLayout ll_contact_us_page;
   ProgressBar progressBar_contact_us;

   FirebaseAuth mAuth;
   FirebaseUser user;

   ArrayAdapter<String> adapterItems;

   String Name, Phone, Email, Message, Cause;

    public ContactUsFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        contactUsDropDown = getResources().getStringArray(R.array.contact_us_dropdown);
        autoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.act_contact_us_dropdown);
        BTN_submit_form = (Button) view.findViewById(R.id.btn_submit_contact_us);
        et_email = (TextView) view.findViewById(R.id.et_email);
        et_phone = (TextView) view.findViewById(R.id.et_phone);
        et_name = (TextView) view.findViewById(R.id.et_name);
        et_message = (TextView) view.findViewById(R.id.et_message);
        ll_contact_us_page = (LinearLayout) view.findViewById(R.id.ll_contact_us_page);
        progressBar_contact_us = (ProgressBar) view.findViewById(R.id.progressBar_contact_us);

        if(user!=null){
            et_email.setText(user.getEmail());
        }
        else{
            Toast.makeText(getActivity(), "Login Required", Toast.LENGTH_SHORT).show();
            Intent iLogin = new Intent(getActivity(), LoginActivity.class);
            startActivity(iLogin);
            getActivity().finish();
        }

        adapterItems = new ArrayAdapter<String>(getActivity(),R.layout.dropdown_list_item,contactUsDropDown);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cause = adapterView.getItemAtPosition(i).toString();
            }
        });

        BTN_submit_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ll_contact_us_page.setVisibility(View.GONE);
                progressBar_contact_us.setVisibility(View.VISIBLE);

                Name = et_name.getText().toString();
                Phone = et_phone.getText().toString();
                Email = et_email.getText().toString();
                Message = et_message.getText().toString();

                Map<String, Object> contact_data = new HashMap<>();
                contact_data.put("name", Name);
                contact_data.put("email", Email);
                contact_data.put("phone", Phone);
                contact_data.put("cause", Cause);
                contact_data.put("message", Message);

                Date date = new Date();
                String documentId = Long.toString(date.getTime());

                db.collection("transient_contact_data").document(documentId)
                        .set(contact_data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //Log.d(TAG, "DocumentSnapshot successfully written!");
                                Toast.makeText(getContext(), "Your Message Submitted", Toast.LENGTH_SHORT).show();
                                navigateToFragment(R.id.nav_home);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("onFailureBooking", "Error writing document", e);
                                Toast.makeText(getActivity(), "Message failed", Toast.LENGTH_SHORT).show();
                                ll_contact_us_page.setVisibility(View.VISIBLE);
                                progressBar_contact_us.setVisibility(View.GONE);
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