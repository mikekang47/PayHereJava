package com.sihoo.me.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sihoo.me.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
