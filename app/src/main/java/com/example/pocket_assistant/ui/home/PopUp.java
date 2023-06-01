package com.example.pocket_assistant.ui.home;

import android.app.Activity;
import android.app.Dialog;
import android.media.MediaPlayer;
import android.widget.Button;
import android.widget.TextView;

import com.example.pocket_assistant.R;

public class PopUp extends Dialog {

    private String title;
    private Button stopButton;
    private TextView titleView, subTitleView;

    public MediaPlayer mediaPlayer;


    public PopUp(Activity activity){
        super(activity, androidx.appcompat.R.style.Theme_AppCompat_DayNight_DarkActionBar);
        setContentView(R.layout.pop_up);
        this.title = "Etes-vous tombez ?";
        this.stopButton = findViewById(R.id.stopAlert);
        this.titleView = findViewById(R.id.titlePopUp);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Button getStopButton() {
        return stopButton;
    }

    public void build(){
        show();
        titleView.setText(title);

        this.mediaPlayer= MediaPlayer.create(getContext().getApplicationContext(), R.raw.et_oui_alarme);
    }
}
