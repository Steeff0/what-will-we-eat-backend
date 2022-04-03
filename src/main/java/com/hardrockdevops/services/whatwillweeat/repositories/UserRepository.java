package com.hardrockdevops.services.whatwillweeat.repositories;

import com.hardrockdevops.services.whatwillweeat.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}