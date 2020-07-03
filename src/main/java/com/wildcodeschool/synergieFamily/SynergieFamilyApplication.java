package com.wildcodeschool.synergieFamily;

import com.wildcodeschool.synergieFamily.entity.Role;
import com.wildcodeschool.synergieFamily.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SynergieFamilyApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SynergieFamilyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (!roleRepository.findByName("ROLE_COORDINATEUR").isPresent()) {
			roleRepository.save(new Role("ROLE_COORDINATEUR"));
		}
		if (!roleRepository.findByName("ROLE_ADMIN").isPresent()) {
			roleRepository.save(new Role("ROLE_ADMIN"));
		}
	}

}
