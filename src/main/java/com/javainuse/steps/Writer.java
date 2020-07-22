package com.javainuse.steps;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.javainuse.model.User;
import com.javainuse.repository.Repository;

public class Writer implements ItemWriter<User> {

	@Autowired 
	private Repository repository;
	
	@Override
	public void write(List<? extends User> users) throws Exception {
			/*for (String msg : messages) {
				System.out.println("writing the data "+ msg);
			}*/
		
		repository.saveAll(users);
		System.out.println("data saved ");
	}

}
