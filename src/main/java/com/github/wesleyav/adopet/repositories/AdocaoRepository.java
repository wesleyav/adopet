package com.github.wesleyav.adopet.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.wesleyav.adopet.entities.Adocao;

@Repository
public interface AdocaoRepository extends JpaRepository<Adocao, UUID> {

}
