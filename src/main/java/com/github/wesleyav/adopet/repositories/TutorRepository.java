package com.github.wesleyav.adopet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.wesleyav.adopet.entities.Tutor;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Integer> {

}
