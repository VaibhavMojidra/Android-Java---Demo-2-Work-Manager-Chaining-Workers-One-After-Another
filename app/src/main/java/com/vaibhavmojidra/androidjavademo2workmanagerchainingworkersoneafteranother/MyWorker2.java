package com.vaibhavmojidra.androidjavademo2workmanagerchainingworkersoneafteranother;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker2 extends Worker {

    public MyWorker2(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            Thread.sleep(10000);
            return Result.success();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
