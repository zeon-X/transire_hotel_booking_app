package aleeha.com.example.transire.ui.contactus;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import aleeha.com.example.transire.R;


public class ContactUsFragment extends Fragment {

   String[] contactUsDropDown;
   private AutoCompleteTextView autoCompleteTextView;

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


        contactUsDropDown = getResources().getStringArray(R.array.contact_us_dropdown);
        autoCompleteTextView = (AutoCompleteTextView) getView().findViewById(R.id.act_contact_us_dropdown);

        adapterItems = new ArrayAdapter<String>(getActivity(),R.layout.list_item,contactUsDropDown);

        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getActivity(), "Item: "+item, Toast.LENGTH_SHORT).show();
            }
        });

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_us, container, false);
    }
}