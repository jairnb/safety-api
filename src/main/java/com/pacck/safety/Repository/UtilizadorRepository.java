package com.pacck.safety.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pacck.safety.model.Utilizador;

@Repository
public interface UtilizadorRepository extends JpaRepository<Utilizador, Integer> {

}
