package com.example.pocket_assistant.ui.home;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Button;
import android.widget.TextView;

import com.example.pocket_assistant.R;

public class PopUp extends Dialog {

    private String title;
    private String subtitle;
    private Button stopButton;
    private TextView titleView, subTitleView;

    public PopUp(Activity activity){
        super(activity, androidx.appcompat.R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.pop_up);
        this.title = "Vous êtes tomber";
        this.subtitle = "Chrono";
        this.stopButton = findViewById(R.id.stopAlert);
        this.titleView = findViewById(R.id.titlePopUp);
        this.subTitleView = findViewById(R.id.chronoPopUp);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Button getStopButton() {
        return stopButton;
    }

    public void build(){
        show();
        titleView.setText(title);
        subTitleView.setText(subtitle);
    }
}
