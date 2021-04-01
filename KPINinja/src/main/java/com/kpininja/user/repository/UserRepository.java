package com.kpininja.user.repository;

import org.springframework.stereotype.Repository;
import com.kpininja.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
