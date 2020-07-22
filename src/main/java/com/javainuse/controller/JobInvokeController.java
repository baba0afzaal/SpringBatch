package com.javainuse.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobInvokeController {
	
	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	Job processJob;
	
	@RequestMapping
	("/invokejob")	public String handler() throws Exception {
		System.out.println("executed the job ");
		JobParameters jobParameters =
				new JobParametersBuilder().addLong("time",System.currentTimeMillis()).toJobParameters();
		jobLauncher.run(processJob, jobParameters);
		return "BATCH HAS BEEN INVOKED";

		
		
	}
	/*@RequestMapping("/invokejob1")
	public void handler1()throws Exception{
		System.out.println("executed the job 1 ");
		jobLauncher.run(processJob, new JobParameters());
		System.out.println("BATCH HAS BEEN INVOKED of job 2 ");
		
	}*/
}
