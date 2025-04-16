package org.example.dearmyany.repository;

import org.example.dearmyany.entity.AuthKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthKeyRepository extends JpaRepository<AuthKey, Long> {

    Optional<AuthKey> findByAuthKeyAndUsedFalse(String authKey);

}
