package com.example.anita.assignment2;

/**
 * Created by anita on 2016-09-21.
 */
public class StaticData {
    private static StaticData instance = null;
    private boolean isInitialized;

    //Private constructor to make a singleton
    private StaticData()
    {
        isInitialized = false;
    };

    public static StaticData getInstance()
    {
        if (instance == null)
            instance = new StaticData();
        return instance;
    }

    public boolean getIsInitialized()
    {
        return isInitialized;
    };
    public void setIsInitialized(boolean bool)
    {
        isInitialized = bool;
    }
}
