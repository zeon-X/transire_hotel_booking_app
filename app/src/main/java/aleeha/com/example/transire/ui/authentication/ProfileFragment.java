package aleeha.com.example.transire.ui.authentication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import aleeha.com.example.transire.LoginActivity;
import aleeha.com.example.transire.R;
import aleeha.com.example.transire.RegistrationActivity;

public class ProfileFragment extends Fragment {

    Button btn_logout,btn_update, btn_login, btn_register;
    FirebaseAuth mAuth;
    FirebaseUser user;

    ImageView iv_user_img;
    TextView tv_user_name;
    LinearLayout ll_profile_page,ll_go_log_reg;

    public ProfileFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        btn_logout = (Button) view.findViewById(R.id.btn_logout);
        btn_update = (Button) view.findViewById(R.id.btn_update);
        btn_login = (Button) view.findViewById(R.id.btn_login);
        btn_register = (Button) view.findViewById(R.id.btn_register);
        tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);
        iv_user_img = (ImageView) view.findViewById(R.id.iv_user_img);
        ll_profile_page = (LinearLayout) view.findViewById(R.id.ll_profile_page);
        ll_go_log_reg = (LinearLayout) view.findViewById(R.id.ll_go_log_reg);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if(user!=null){
            ll_profile_page.setVisibility(View.VISIBLE);
            tv_user_name.setText(user.getEmail());
            //Toast.makeText(getActivity(), user.getDisplayName(), Toast.LENGTH_SHORT).show();
        }
        else{
            ll_profile_page.setVisibility(View.GONE);
            ll_go_log_reg.setVisibility(View.VISIBLE);
        }


        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();

                Intent iLogin = new Intent(getActivity(), LoginActivity.class);
                startActivity(iLogin);
                getActivity().finish();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToFragment(R.id.nav_home);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iLogin = new Intent(getActivity(), LoginActivity.class);
                startActivity(iLogin);
                getActivity().finish();
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iReg = new Intent(getActivity(), RegistrationActivity.class);
                startActivity(iReg);
                getActivity().finish();
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