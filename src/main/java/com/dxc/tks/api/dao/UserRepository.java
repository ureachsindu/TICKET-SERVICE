package com.dxc.tks.api.dao;

import com.dxc.tks.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 
 * @author mkhan339
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByEmailAddress(String emailAddress);
}
