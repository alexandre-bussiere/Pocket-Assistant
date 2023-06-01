package com.example.pocket_assistant.ui.contact;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
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
    private final static int NbContactMax=10;
    EditText name_Edit, phone_Edit;
    Button button_Add_Contact;
    Button button_Delete_All_Coontact;

    Button[] buttons_delete_Contact =new Button[NbContactMax];
    TextView[] name_View= new TextView[NbContactMax],phone_View= new TextView[NbContactMax];
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
        button_Delete_All_Coontact=binding.buttonDeleteAllContact;
        name_Edit=binding.nameEdit;
        phone_Edit= binding.phoneEdit;
        name_View=CreateTabContactName();
        phone_View=CreateTabContactPhone();
        buttons_delete_Contact=CreateTabButtonDelete();
        context=getActivity();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        nbContact=preferences.getInt("nbContact",0);
        for (int i=0; i<NbContactMax;i++){
            name_View[i].setText(preferences.getString("name"+i,""));
            phone_View[i].setText(preferences.getString("phone"+i,""));
            buttons_delete_Contact[i].setVisibility(preferences.getInt("visibility"+i,View.INVISIBLE));
        }
        button_Add_Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_Contact();
            }
        });
        button_Delete_All_Coontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reset();
            }
        });
        for (int i=0; i<NbContactMax;i++){
            int finalI = i;
            buttons_delete_Contact[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Delete_Contact(finalI);
                }
            });
        }
        return root;
    }
    public void Add_Contact(){
        String name= name_Edit.getText().toString();
        String phone= phone_Edit.getText().toString();
        if(!name.equals("") && !phone.equals("") && nbContact<NbContactMax){
            name_View[nbContact].setText(name);
            phone_View[nbContact].setText(phone);
            buttons_delete_Contact[nbContact].setVisibility(View.VISIBLE);
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
            buttons_delete_Contact[nbContact-1].setVisibility(View.INVISIBLE);
            nbContact--;
        }
        saveData();
    }
    public void Reset(){
        for(int i=0;i<NbContactMax;i++){
            name_View[i].setText("");
            phone_View[i].setText("");;
            buttons_delete_Contact[i].setVisibility(View.INVISIBLE);
        }
        nbContact=0;
        saveData();
    }
    public void saveData(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        String name,phone;
        int visibility;
        for(int i=0;i<NbContactMax;i++){
            name=name_View[i].getText().toString();
            phone=phone_View[i].getText().toString();
            visibility= buttons_delete_Contact[i].getVisibility();
            editor.putString("name"+i,name);
            editor.putString("phone"+i,phone);
            editor.putInt("visibility"+i,visibility);
        }
        editor.putInt("nbContact",nbContact);
        editor.apply();
    }
        public TextView[] CreateTabContactName(){
        TextView[] name_View= new TextView[NbContactMax];
        name_View[0]=binding.nameView0;
        name_View[1]=binding.nameView1;
        name_View[2]=binding.nameView2;
        name_View[3]=binding.nameView3;
        name_View[4]=binding.nameView4;
        name_View[5]=binding.nameView5;
        name_View[6]=binding.nameView6;
        name_View[7]=binding.nameView7;
        name_View[8]=binding.nameView8;
        name_View[9]=binding.nameView9;
        return name_View;

    }
    public TextView[] CreateTabContactPhone(){
        TextView[] phone_View= new TextView[NbContactMax];
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
    public Button[] CreateTabButtonDelete(){
        Button[] buttons_delete= new Button[NbContactMax];
        buttons_delete[0]=binding.delelteContact0;
        buttons_delete[1]=binding.delelteContact1;
        buttons_delete[2]=binding.delelteContact2;
        buttons_delete[3]=binding.delelteContact3;
        buttons_delete[4]=binding.delelteContact4;
        buttons_delete[5]=binding.delelteContact5;
        buttons_delete[6]=binding.delelteContact6;
        buttons_delete[7]=binding.delelteContact7;
        buttons_delete[8]=binding.delelteContact8;
        buttons_delete[9]=binding.delelteContact9;
        return buttons_delete;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
