package br.edu.ifsp.arq.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.arq.projeto.domain.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long>{

}
