package com.example.pocket_assistant.ui.parameter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pocket_assistant.databinding.FragmentParameterBinding;

public class ParameterFragment extends Fragment {

    private FragmentParameterBinding binding;
    final static int Nbmessage=8;
    Context context;
    Button valider;
    EditText[] messages= new EditText[Nbmessage];

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ParameterViewModel parameterViewModel =
                new ViewModelProvider(this).get(ParameterViewModel.class);

        binding = FragmentParameterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context=getActivity();
        valider=binding.valide;
        messages=CreateTabMessage();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        for(int i=0;i<Nbmessage;i++){
            messages[i].setText(preferences.getString("msg"+i,""));
        }
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();
            }
        });



        return root;
    }
    public EditText[]CreateTabMessage(){
        EditText[]messages=new EditText[Nbmessage];
        messages[0]=binding.message;
        messages[1]=binding.message2;
        messages[2]=binding.message25;
        messages[3]=binding.message3;
        messages[4]=binding.message4;
        messages[5]=binding.message5;
        messages[6]=binding.message6;
        messages[7]=binding.message7;
        return messages;

    }
    public void SaveData(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        String message;
        for (int i=0; i<Nbmessage;i++){
            message=messages[i].getText().toString();
            editor.putString("msg"+i,message);
        }
        editor.apply();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}