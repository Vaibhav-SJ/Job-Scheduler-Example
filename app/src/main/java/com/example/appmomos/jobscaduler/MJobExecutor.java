package com.example.appmomos.jobscaduler;

import android.os.AsyncTask;

public class MJobExecutor extends AsyncTask<Void,Void,String>
{



    @Override
    protected String doInBackground(Void... voids)
    {
        return "BackGround Long running task running..";
    }
}
