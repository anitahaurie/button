package com.example.anita.assignment2;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by anita on 2016-09-23.
 */
public class EditFragment extends Fragment {

    public final static String TAG = "EditFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EditText mEdit = (EditText)getActivity().findViewById(R.id.editTextName);
        mEdit.setEnabled(true);

        Button mSaveButton = (Button)getActivity().findViewById(R.id.button_save);
        mSaveButton.setVisibility(View.VISIBLE);

        Log.d(TAG, "OnCreate");
    }
}
