package com.vaibhavmojidra.androidjavademo2workmanagerchainingworkersoneafteranother;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;

import com.vaibhavmojidra.androidjavademo2workmanagerchainingworkersoneafteranother.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        OneTimeWorkRequest oneTimeWorkRequestForWorker1=new OneTimeWorkRequest.Builder(MyWorker1.class).build();
        OneTimeWorkRequest oneTimeWorkRequestForWorker2=new OneTimeWorkRequest.Builder(MyWorker2.class).build();
        OneTimeWorkRequest oneTimeWorkRequestForWorker3=new OneTimeWorkRequest.Builder(MyWorker3.class).build();

        WorkManager workManager=WorkManager.getInstance(this);

        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequestForWorker1.getId()).observe(this, workInfo -> {
            binding.work1TextView.setText(workInfo.getState().name());
        });

        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequestForWorker2.getId()).observe(this, workInfo -> {
            binding.work2TextView.setText(workInfo.getState().name());
        });

        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequestForWorker3.getId()).observe(this, workInfo -> {
            binding.work3TextView.setText(workInfo.getState().name());
        });

        binding.startWorksButton.setOnClickListener(view -> {
            workManager
                    .beginWith(oneTimeWorkRequestForWorker1)
                    .then(oneTimeWorkRequestForWorker2)
                    .then(oneTimeWorkRequestForWorker3)
                    .enqueue();
        });
    }
}