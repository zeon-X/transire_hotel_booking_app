package aleeha.com.example.transire.ui.contactus;

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
import android.widget.Toast;

import aleeha.com.example.transire.R;


public class ContactUsFragment extends Fragment {

   String[] contactUsDropDown;
   private AutoCompleteTextView autoCompleteTextView;
   Button BTN_submit_form;

   ArrayAdapter<String> adapterItems;

    public ContactUsFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        contactUsDropDown = getResources().getStringArray(R.array.contact_us_dropdown);
        autoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.act_contact_us_dropdown);
        BTN_submit_form = (Button) view.findViewById(R.id.btn_submit_contact_us);

        adapterItems = new ArrayAdapter<String>(getActivity(),R.layout.dropdown_list_item,contactUsDropDown);

        autoCompleteTextView.setAdapter(adapterItems);
//        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String item = adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(getActivity(), "Item: "+item, Toast.LENGTH_SHORT).show();
//            }
//        });

        BTN_submit_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Contact Us Form Submitted", Toast.LENGTH_SHORT).show();
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