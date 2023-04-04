package com.github.wesleyav.adopet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.wesleyav.adopet.entities.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

}
