package com.example.pocket_assistant.ui.parameter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import com.example.pocket_assistant.databinding.FragmentParameterBinding;

public class ParameterFragment extends Fragment {

    private FragmentParameterBinding binding;
    private Button validButton;
    private EditText message, message2;

    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ParameterViewModel parameterViewModel =
                new ViewModelProvider(this).get(ParameterViewModel.class);

        binding = FragmentParameterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        validButton = binding.valide;
        message = binding.message;
        message2 = binding.editTextText;
        context = getActivity();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        validButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("test", message.getText().toString());
                editor.apply();

                message2.setText(preferences.getString("test42", "NOpe"));
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}