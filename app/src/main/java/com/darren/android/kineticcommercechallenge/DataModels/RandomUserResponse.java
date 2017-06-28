package com.darren.android.kineticcommercechallenge.DataModels;

/**
 * Created by Darren on 6/26/2017.
 */

public class RandomUserResponse {

    private Result[] results;

    private Info info;

    public Result[] getResults()
    {
        return results;
    }

    public void setResults(Result[] results)
    {
        this.results = results;
    }

    public Info getInfo ()
    {
        return info;
    }

    public void setInfo (Info info)
    {
        this.info = info;
    }
}
