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

import br.edu.ifsp.arq.projeto.domain.model.Servico;
import br.edu.ifsp.arq.projeto.repository.HospedagemRepository;
import br.edu.ifsp.arq.projeto.service.HospedagemService;

@RestController
@RequestMapping("servicos")
public class HospedagemResource {
	
	@Autowired
	private HospedagemRepository hospedagemRepository;
	
	@Autowired
	private HospedagemService hospedagemService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_SERVICO') and #oauth2.hasScope('read')")
	public List<Servico> listar(){
		return hospedagemRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_SERVICO') and #oauth2.hasScope('write')")
	public Servico criar(@Valid @RequestBody Servico servico, HttpServletResponse response) {
		return hospedagemRepository.save(servico);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_SERVICO') and #oauth2.hasScope('read')")
	public ResponseEntity<Servico> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Servico> servico = hospedagemRepository.findById(codigo);
		if(servico.isPresent()) {
			return ResponseEntity.ok(servico.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_SERVICO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		hospedagemRepository.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_SERVICO') and #oauth2.hasScope('write')")
	public ResponseEntity<Servico> atualizar(@PathVariable Long codigo, @Valid @RequestBody Servico servico){
		Servico hospedagemSalvo = hospedagemService.atualizar(codigo, servico);
		return ResponseEntity.ok(hospedagemSalvo);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_SERVICO') and #oauth2.hasScope('write')")
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo){
		hospedagemService.atualizarPropriedadeAtivo(codigo, ativo);
	}
}