package com.twitter.next.fatcat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twitter.next.fatcat.entities.User;

//@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

}
