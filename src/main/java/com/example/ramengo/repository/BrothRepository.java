package com.example.ramengo.repository;

import com.example.ramengo.entities.Broth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrothRepository extends JpaRepository<Broth,String> {
}
