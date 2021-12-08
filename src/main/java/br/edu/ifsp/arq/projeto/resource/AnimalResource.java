package br.edu.ifsp.arq.projeto.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.arq.projeto.domain.model.Animal;
import br.edu.ifsp.arq.projeto.repository.AnimalRepository;
import br.edu.ifsp.arq.projeto.service.AnimalService;

@RestController
@RequestMapping("animais")
public class AnimalResource {
	
	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private AnimalService animalService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ANIMAL') and #oauth2.hasScope('read')")
	public List<Animal> listar(){
		return animalRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ANIMAL') and #oauth2.hasScope('write')")
	public Animal criar(@Valid @RequestBody Animal animal, HttpServletResponse response) {
		return animalRepository.save(animal);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ANIMAL') and #oauth2.hasScope('read')")
	public ResponseEntity<Animal> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Animal> animal = animalRepository.findById(codigo);
		if(animal.isPresent()) {
			return ResponseEntity.ok(animal.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_ANIMAL') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		animalRepository.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ANIMAL') and #oauth2.hasScope('write')")
	public ResponseEntity<Animal> atualizar(@PathVariable Long codigo, @Valid @RequestBody Animal animal){
		Animal animalSalvo = animalService.atualizar(codigo, animal);
		return ResponseEntity.ok(animalSalvo);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ANIMAL') and #oauth2.hasScope('write')")
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo){
		animalService.atualizarPropriedadeAtivo(codigo, ativo);
	}
}
