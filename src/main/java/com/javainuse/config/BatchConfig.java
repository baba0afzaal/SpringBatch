package com.javainuse.config;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.javainuse.listener.JobCompletionListener;
import com.javainuse.model.User;
import com.javainuse.steps.Processor;
import com.javainuse.steps.Writer;


@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	

	
	@Bean
	public Job processJob() {
		return jobBuilderFactory.get("jobProcess")
				.incrementer(new RunIdIncrementer())
				.listener(listener())
				.flow(step1()) 
				.end()
				.build();
	}
	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step 1")
				.<User, User >chunk(100)
//				.reader(new Reader())
				.reader(reader())
				.processor(new Processor())
				.writer(writer())
				.build();
	}
	@Bean
	public FlatFileItemReader<User> reader() {
		System.out.println("in reader");
		FlatFileItemReader<User> reader = new FlatFileItemReader<User>();
		reader.setResource(new ClassPathResource ("/users.csv"));
		reader.setLinesToSkip(1);
		reader.setLineMapper(new DefaultLineMapper<User>() {{
			setLineTokenizer(new DelimitedLineTokenizer() {{
				setStrict(false);
				setDelimiter(",");
				setNames(new String[] {"ID","Name","Dept","Salary"});
			}});
			setFieldSetMapper(new BeanWrapperFieldSetMapper<User>() {{
				setTargetType(User.class);
			}});
		}});
		
		return reader;
	}
	
	@Bean
	public Processor processor() {
		return new Processor();
	}
	
	@Bean
	public Writer writer() {
		return new Writer();
	}
	
	@Bean
	public JobCompletionListener listener() {
		return new JobCompletionListener();
	}

}
