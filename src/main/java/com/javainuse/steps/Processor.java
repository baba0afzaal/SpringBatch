package com.javainuse.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.javainuse.model.User;

public class Processor implements ItemProcessor<User, User> {
	
	private static final Logger log = LoggerFactory.getLogger(Processor.class);

	@Override
	public User process(User user) throws Exception {

	  
		return user.toBuilder()
				.name(user.getName().toLowerCase())
				.dept(user.getDept().toUpperCase())
				.build();
	}
}
