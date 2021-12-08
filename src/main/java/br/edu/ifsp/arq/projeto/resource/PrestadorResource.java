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

import br.edu.ifsp.arq.projeto.domain.model.Prestador;
import br.edu.ifsp.arq.projeto.repository.PrestadorRepository;
import br.edu.ifsp.arq.projeto.service.PrestadorService;

@RestController
@RequestMapping("prestadores")
public class PrestadorResource {
	
	@Autowired
	private PrestadorRepository prestadorRepository;
	
	@Autowired
	private PrestadorService prestadorService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PRESTADOR') and #oauth2.hasScope('read')")
	public List<Prestador> listar(){
		return prestadorRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PRESTADOR') and #oauth2.hasScope('write')")
	public Prestador criar(@Valid @RequestBody Prestador prestador, HttpServletResponse response) {
		return prestadorRepository.save(prestador);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PRESTADOR') and #oauth2.hasScope('read')")
	public ResponseEntity<Prestador> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Prestador> prestador = prestadorRepository.findById(codigo);
		if(prestador.isPresent()) {
			return ResponseEntity.ok(prestador.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PRESTADOR') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		prestadorRepository.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PRESTADOR') and #oauth2.hasScope('write')")
	public ResponseEntity<Prestador> atualizar(@PathVariable Long codigo, @Valid @RequestBody Prestador prestador){
		Prestador prestadorSalvo = prestadorService.atualizar(codigo, prestador);
		return ResponseEntity.ok(prestadorSalvo);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PRESTADOR') and #oauth2.hasScope('write')")
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo){
		prestadorService.atualizarPropriedadeAtivo(codigo, ativo);
	}
}
