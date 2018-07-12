package com.example.appmomos.jobscaduler;

import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
{

    private JobScheduler jobScheduler ;
    private JobInfo jobInfo;
    private static final int JOB_ID = 1234; //Job id to indicate job info builder while unbinding it
    private JobInfo.Builder builder;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*ComponentName componentName = new ComponentName(this,JobSchedulerService.class);
        builder = new JobInfo.Builder(JOB_ID,componentName);
        builder.setPeriodic(3000); // job scheduler will be called at 3 secs.
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY); // any type of network (wifi or mobile data) is must top run this job scheduler
        builder.setPersisted(true); // setPersisted true means job scheduler will start after restarting the mobile or false means it won't start after swich off and restart*/


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {

            ComponentName componentName = new ComponentName(this,JobSchedulerService.class);
            builder = new JobInfo.Builder(JOB_ID,componentName);
           // builder.setMinimumLatency(3000); // job scheduler will be called at 3 secs.
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY); // any type of network (wifi or mobile data) is must top run this job scheduler
            builder.setPersisted(true);
            builder.setBackoffCriteria(0,JobInfo.BACKOFF_POLICY_LINEAR);





        } else {
            ComponentName componentName = new ComponentName(this,JobSchedulerService.class);
            builder = new JobInfo.Builder(JOB_ID,componentName);
            builder.setPeriodic(3000); // job scheduler will be called at 3 secs.
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY); // any type of network (wifi or mobile data) is must top run this job scheduler
            builder.setPersisted(true);
        }


        jobInfo = builder.build();
        jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
    }



    public void startJobScaduler(View view)
    {
        jobScheduler.schedule(jobInfo);
        Toast.makeText(this,"Job Scheduled",Toast.LENGTH_SHORT).show();
    }

    public void stopJobScaduler(View view)
    {
        jobScheduler.cancel(JOB_ID);
         Toast.makeText(this,"Job Cancelled",Toast.LENGTH_SHORT).show();
    }



    /*
        Note :
        * Here to add JobSchedulerClasee we need to create Job scheduler object.
        *
    */


}
