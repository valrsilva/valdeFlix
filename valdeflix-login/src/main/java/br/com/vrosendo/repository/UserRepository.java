package br.com.vrosendo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vrosendo.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findOneByUsernameAndPassword(String username, String password);
	
}
