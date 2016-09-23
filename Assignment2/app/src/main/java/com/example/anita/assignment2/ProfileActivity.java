package com.example.anita.assignment2;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        Fragment mNoEditFragment = new NoEditFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.my_fragment_container, mNoEditFragment, "NoEditFragment");
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();


    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_edit:
                //Allow user to edit text fields
                Fragment mEditFragment = new EditFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.my_fragment_container, mEditFragment, "EditFragment");
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
                /**EditText mEdit = (EditText)findViewById(R.id.editTextName);
                 mEdit.setEnabled(true);*/
                return true;

            case android.R.id.home:
                //If info isn't saved or if in edit mode, don't allow back
                Fragment page = getFragmentManager().findFragmentByTag("NoEditFragment");
                if (canSaveAndExit() && page!=null)
                {
                    finish();
                }
                else
                {
                    //Toast saying can't save
                    CharSequence text = "Please fill all fields and save before exiting.";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(this, text, duration);
                    toast.show();
                    return true;
                }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void SaveInfo(View view)
    {
        //Save information in sharedPreferences
        SharedPreferences sharedPref = this.getSharedPreferences("Profile_Preferences", 0);
        EditText mEdit = (EditText)this.findViewById(R.id.editTextName);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.saved_name), mEdit.getText().toString());
        editor.commit();

        if (canSaveAndExit())
        {
            //Create fragment transaction
            Fragment mNoEditFragment = new NoEditFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.replace(R.id.my_fragment_container, mNoEditFragment, "NoEditFragment");
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
        else
        {
            //Toast saying can't save
            CharSequence text = "Please fill all fields to save.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }


        //Set bool
        //StaticData.getInstance().setIsInitialized(true);
    }

    public boolean canSaveAndExit()
    {
        boolean canSave = true;
        EditText mEdit = (EditText)this.findViewById(R.id.editTextName);

        if (mEdit.getText().toString().isEmpty())
        {
            canSave = false;
        }

        StaticData.getInstance().setIsInitialized(canSave);
        return canSave;
    }

}
