package com.example.pocket_assistant.ui.contact;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pocket_assistant.R;
import com.example.pocket_assistant.databinding.FragmentContactBinding;

public class ContactFragment extends Fragment {

    private FragmentContactBinding binding;
    EditText name_Edit, phone_Edit;
    Button button_Add_Contact;
    TextView[] name_View= new TextView[10],phone_View= new TextView[10];

    static int nbContact=0;

    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ContactViewModel contactViewModel =
                new ViewModelProvider(this).get(ContactViewModel.class);

        binding = FragmentContactBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textContact;
        contactViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        button_Add_Contact=(Button) binding.buttonAddContact;
        name_Edit=(EditText) binding.nameEdit;
        phone_Edit=(EditText) binding.phoneEdit;
        name_View=CreateTabContactName();
        phone_View=CreateTabContactPhone();
        context=getActivity();


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        name_View[nbContact].setText(preferences.getString("name",""));
        phone_View[nbContact].setText(preferences.getString("phone",""));
        button_Add_Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= name_Edit.getText().toString();
                String phone= phone_Edit.getText().toString();
                if(!name.equals("") && !phone.equals("") && nbContact!=10){
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("name",name);
                    editor.putString("phone",phone);
                    editor.apply();
                    name_View[nbContact].setText(preferences.getString("name",""));
                    phone_View[nbContact].setText(preferences.getString("phone",""));
                    name_Edit.setText("");
                    phone_Edit.setText("");
                    nbContact++;
                    return;
                }

            }
        });
        return root;
    }
    public TextView[] CreateTabContactName(){
        TextView[] name_View= new TextView[10];
        name_View[0]=binding.nameView0;
        name_View[1]=binding.nameView1;
        name_View[2]=binding.nameView2;
        name_View[3]=binding.nameView3;
        name_View[4]=binding.nameView4;
        name_View[5]=binding.nameView5;
        name_View[6]=binding.nameView6;
        name_View[7]=binding.nameView7;
        name_View[8]=binding.nameView8;
        name_View[9]=binding.nameView8;
        return name_View;

    }
    public TextView[] CreateTabContactPhone(){
        TextView[] phone_View= new TextView[10];
        phone_View[0]=binding.phoneView0;
        phone_View[1]=binding.phoneView1;
        phone_View[2]=binding.phoneView2;
        phone_View[3]=binding.phoneView3;
        phone_View[4]=binding.phoneView4;
        phone_View[5]=binding.phoneView5;
        phone_View[6]=binding.phoneView6;
        phone_View[7]=binding.phoneView7;
        phone_View[8]=binding.phoneView8;
        phone_View[9]=binding.phoneView9;
        return phone_View;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}