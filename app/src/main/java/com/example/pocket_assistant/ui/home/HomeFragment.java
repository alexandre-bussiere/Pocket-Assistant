package com.example.pocket_assistant.ui.home;

import static com.google.android.material.transition.platform.MaterialSharedAxis.Z;

import android.annotation.SuppressLint;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ComponentActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pocket_assistant.MainActivity;
import com.example.pocket_assistant.R;
import com.example.pocket_assistant.databinding.FragmentHomeBinding;



public class HomeFragment extends Fragment {

    public Switch OnOffSwitch;
    public static boolean Onoffstate;
    private TextView textView2 = null;
    private TextView textView3 = null;
    private TextView textView4 = null;
    private TextView textView5 = null;
    private TextView textView6 = null;
    private FragmentHomeBinding binding;
    MainActivity tae = new MainActivity();
    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;

        textView2 = binding.textView2;
        textView3 = binding.textView3;
        textView4 = binding.textView4;
        textView5 = binding.textView5;
        textView6 = binding.textView6;

        OnOffSwitch = binding.switch1;
        OnOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Onoffstate = OnOffSwitch.isChecked();
            }

        });

        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}