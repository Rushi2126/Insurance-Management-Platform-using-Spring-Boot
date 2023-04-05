package com.rushi.insurance.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.rushi.insurance.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
