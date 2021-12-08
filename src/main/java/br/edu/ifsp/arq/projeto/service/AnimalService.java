package br.edu.ifsp.arq.projeto.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.projeto.domain.model.Animal;
import br.edu.ifsp.arq.projeto.repository.AnimalRepository;

@Service
public class AnimalService {
	
	@Autowired
	private AnimalRepository animalRepository;

	public Animal atualizar(Long codigo, Animal animal) {
		Animal animalSalva = buscarPessoaPeloCodigo(codigo);
		BeanUtils.copyProperties(animal, animalSalva, "codigo");
		return animalRepository.save(animalSalva);
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Animal animalSalva = buscarPessoaPeloCodigo(codigo);
		animalSalva.setAtivo(ativo);
		animalRepository.save(animalSalva);
	}
	
	private Animal buscarPessoaPeloCodigo(Long codigo) {
		Animal animalSalva = animalRepository.findById(codigo)
			   .orElseThrow(()-> new EmptyResultDataAccessException(1));
		return animalSalva;
	}

}
