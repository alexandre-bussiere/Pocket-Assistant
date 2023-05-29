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

        button_Add_Contact=binding.buttonAddContact;
        name_Edit=binding.nameEdit;
        phone_Edit= binding.phoneEdit;
        name_View=CreateTabContactName();
        phone_View=CreateTabContactPhone();
        context=getActivity();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        nbContact=preferences.getInt("nbContact",0);
        for (int i=0; i<10;i++){
            name_View[i].setText(preferences.getString("name"+i,""));
            phone_View[i].setText(preferences.getString("phone"+i,""));
        }
        button_Add_Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_Contact();
            }
        });
        return root;
    }
    public void Add_Contact(){
        String name= name_Edit.getText().toString();
        String phone= phone_Edit.getText().toString();
        if(!name.equals("") && !phone.equals("") && nbContact!=10){
            name_View[nbContact].setText(name);
            phone_View[nbContact].setText(phone);
            nbContact++;
            name_Edit.setText("");
            phone_Edit.setText("");
            saveData();
        }
    }
    public void Delete_Contact(int numContact){
        String name,phone;
        for(int i=numContact; i<nbContact-1;i++){
            name=name_View[i+1].getText().toString();
            phone=phone_View[i+1].getText().toString();
            name_View[i].setText(name);
            phone_View[i].setText(phone);
        }
        if (numContact<nbContact){
            name_View[nbContact-1].setText("");
            phone_View[nbContact-1].setText("");
            nbContact--;
        }
        saveData();
    }
    public void Reset(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        for(int i=0;i<10;i++){
            editor.putString("name"+i,"");
            editor.putString("phone"+i,"");
        }
        editor.putInt("nbContact",0);
        editor.apply();
    }
    public void saveData(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        String name,phone;
        for(int i=0;i<10;i++){
            name=name_View[i].getText().toString();
            phone=phone_View[i].getText().toString();
            editor.putString("name"+i,name);
            editor.putString("phone"+i,phone);
        }
        editor.putInt("nbContact",nbContact);
        editor.apply();
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