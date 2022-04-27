package com.wipro.projetofinal;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wipro.projetofinal.entities.Manager;
import com.wipro.projetofinal.service.ManageService;

public class ManagerServiceTest {

	@Autowired
	private ManageService managerService;

	@Test(expected = Exception.class)
	public void managerService_save_manager_test() throws Exception {
		Manager manager = new Manager("Maria Joaquina", "70281281050", "jjoao@gmail.com", "1232345", "12abc456");		
		managerService.saveManager(manager);
	}

}
