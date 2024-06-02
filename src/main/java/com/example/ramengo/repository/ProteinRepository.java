package com.example.ramengo.repository;

import com.example.ramengo.entities.Protein;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProteinRepository extends JpaRepository<Protein,String> {
}
