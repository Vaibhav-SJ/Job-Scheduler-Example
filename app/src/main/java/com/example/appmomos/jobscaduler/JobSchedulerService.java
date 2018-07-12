package com.example.appmomos.jobscaduler;

import android.annotation.SuppressLint;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.widget.Toast;

@SuppressLint("Registered")
public class JobSchedulerService extends JobService
{

    private MJobExecutor mJobExecutor;

    @SuppressLint("StaticFieldLeak")
    @Override
    public boolean onStartJob(final JobParameters params)
    {

        mJobExecutor = new MJobExecutor()
        {
            @Override
            protected void onPostExecute(String s)
            {
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                jobFinished(params,true);
            }
        };

        mJobExecutor.execute();

        return true;

        /*If Return type True : Job  takes more time so system should wait , and to indicate job is finished we have to use jobFinished() method
        If Return type False : Job wont take much time and system will wait and no need to call jobFinished() method because statem will waita nd it will come to know that job finished*/

    }




    @Override
    public boolean onStopJob(JobParameters params)
    {
        mJobExecutor.cancel(true);
        return false;
    }
    // this method is called when user cancels the job i.e system clls this methods to cancel pending tasks.
}

/*
Note :
* Job Scaduler runs in main thread to avoid performance issue use separate thread or async tasks in it.
* If onStartJob returns true and jobFinished(0 method is not used them system assumes job is still running and even though job is finished
  system  won't call new job because according to system job is still running.
* jobFinished() method user two argument 1. parameters to pass onStartJob(0 method and 2. boolean value indicating syatem should call onstartJob method or not.
  If jobFinished() method return true : system will call on start method once . if False syatem won't call and job will not execute again and again.

*/