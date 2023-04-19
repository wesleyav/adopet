package com.github.wesleyav.adopet.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.wesleyav.adopet.entities.Adocao;

public interface AdocaoRepository extends JpaRepository<Adocao, UUID> {

}
