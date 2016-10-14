package com.example.anita.assignment1;

/**
 * Created by anita on 2016-09-15.
 */
public class StaticData {
    private static StaticData mInstance = null;
    private boolean showSnackbar;

    private StaticData()
    {
        showSnackbar = false;
    }

    public static StaticData getInstance()
    {
        if (mInstance == null)
        {
            mInstance = new StaticData();
        }
        return mInstance;
    }

    public boolean getShowSnackbar()
    {
        return this.showSnackbar;
    }

    public void setShowSnackbar(boolean val)
    {
        showSnackbar = val;
    }
}
