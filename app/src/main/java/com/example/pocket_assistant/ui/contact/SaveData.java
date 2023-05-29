package com.example.pocket_assistant.ui.contact;

import android.graphics.Bitmap;

public interface SaveData {
    int REQUEST_MEDIA_READ=1000;
    int REQUEST_MEDIA_WRITE=1001;
    void onDataLoad(Bitmap bitmap);
    Bitmap getDataToSave();
}
