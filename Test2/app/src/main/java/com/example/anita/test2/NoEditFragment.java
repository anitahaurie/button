package com.example.anita.test2;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by anita on 2016-09-21.
 */
public class NoEditFragment extends Fragment {

    public final static String TAG = "NoEditFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide Save button
        Button mSaveButton = (Button)getActivity().findViewById(R.id.save_button);
        mSaveButton.setVisibility(View.INVISIBLE);

        //Disable text editing
        EditText mEdit = (EditText)getActivity().findViewById(R.id.editTextName);
        mEdit.setEnabled(false);

        //Set values in text boxes. Default if not set, otherwise, values from sharedPreferences
        SharedPreferences sharedPref = getActivity().getSharedPreferences("Profile_Preferences", 0);
        mEdit.setText(sharedPref.getString(getString(R.string.saved_name), getString(R.string.empty_default)));

        Log.d(TAG, "OnCreate");

    }
}
