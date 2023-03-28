package aleeha.com.example.transire.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import aleeha.com.example.transire.MainActivity;
import aleeha.com.example.transire.R;
import aleeha.com.example.transire.databinding.FragmentHomeBinding;
import aleeha.com.example.transire.ui.aboutus.AboutUsFragment;
import aleeha.com.example.transire.ui.contactus.ContactUsFragment;
import aleeha.com.example.transire.ui.facilities.FacilitiesFragment;
import aleeha.com.example.transire.ui.gallery.GalleryFragment;
import aleeha.com.example.transire.ui.location.LocationFragment;
import aleeha.com.example.transire.ui.rooms.RoomsFragment;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    LinearLayout BTN_rooms, BTN_location, BTN_facilities, BTN_gallery, BTN_contactUs, BTN_aboutUs;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_home, container, false);
        BTN_rooms = view.findViewById(R.id.ll_btn_rooms);
        BTN_location = view.findViewById(R.id.ll_btn_location);
        BTN_facilities = view.findViewById(R.id.ll_btn_facilities);
        BTN_gallery = view.findViewById(R.id.ll_btn_gallery);
        BTN_contactUs = view.findViewById(R.id.ll_btn_contact_us);
        BTN_aboutUs = view.findViewById(R.id.ll_btn_about_us);

        BTN_rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToFragment(R.id.nav_rooms);
            }
        });

        BTN_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToFragment(R.id.nav_location);
            }
        });

        BTN_facilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToFragment(R.id.nav_facilities);
            }
        });

        BTN_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToFragment(R.id.nav_gallery);
            }
        });

        BTN_contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToFragment(R.id.nav_contact_us);
            }
        });

        BTN_aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToFragment(R.id.nav_about_us);
            }
        });
        return view;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



    private void navigateToFragment(int destinationId) {
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
        NavController navController = navHostFragment.getNavController();
        navController.navigate(destinationId);
    }


}