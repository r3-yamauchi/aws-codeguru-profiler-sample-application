/*
 * Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: MIT-0
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.company.sample.application;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.codeguruprofilerjavaagent.Profiler;
import java.util.Calendar;
import java.util.Date;

public class App {

    public static void main(String[] args) {
        // start the profiler
        Profiler.builder().profilingGroupName("profgr1")
            .awsCredentialsProvider(DefaultCredentialsProvider.create())
            .build()
            .start();

        final App app = new App();

        while(true){
            app.load();
            app.load1();
            app.load2();
        }

        final Date date1 = new Date();
        final Date date2 = new Date();
        if(date1 == null && date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        //logic to compare the dates and return boolean.
        final Calendar cal1 = Calendar.getInstance();
        final Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        final boolean bl1 = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        final boolean bl2 = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        if (bl1 != bl2) {
            throw new IllegalArgumentException("Not equal");
        }

    }

    private void load() {
        for (int i =0; i< 1 << 20; ++i){
            computeShort();
        }
    }

    private void load1(){
        for (int i =0; i< 1 << 20; ++i){
            computeMedium();
        }
    }

    private void load2(){
        for (int i =0; i< 1 << 20; ++i){
            computeLong();
        }
    }

    private void computeShort(){
        long x = 0;
        for (int i =0; i< 1 << 15; ++i){
            x += i;
        }
    }

    private void computeMedium(){
        long x = 0;
        for (int i =0; i< 1 << 17; ++i){
            x += i;
        }
    }

    private void computeLong(){
        long x = 0;
        for (int i =0; i< 1 << 18; ++i){
            x += i;
        }
    }
}

