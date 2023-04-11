package com.github.wesleyav.adopet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.wesleyav.adopet.entities.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

	@Query("select a from Animal a where a.adotado = false")
	List<Animal> findAllNaoAdotados();

}
