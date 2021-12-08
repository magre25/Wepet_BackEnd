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

import br.edu.ifsp.arq.projeto.domain.model.Pedido;
import br.edu.ifsp.arq.projeto.repository.ServicoRepository;
import br.edu.ifsp.arq.projeto.service.ServicoService;

@RestController
@RequestMapping("pedidos")
public class ServicoResource {
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	@Autowired
	private ServicoService servicoService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PEDIDO') and #oauth2.hasScope('read')")
	public List<Pedido> listar(){
		return servicoRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PEDIDO') and #oauth2.hasScope('write')")
	public Pedido criar(@Valid @RequestBody Pedido pedido, HttpServletResponse response) {
		return servicoRepository.save(pedido);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PEDIDO') and #oauth2.hasScope('read')")
	public ResponseEntity<Pedido> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Pedido> pedido = servicoRepository.findById(codigo);
		if(pedido.isPresent()) {
			return ResponseEntity.ok(pedido.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PEDIDO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		servicoRepository.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PEDIDO') and #oauth2.hasScope('write')")
	public ResponseEntity<Pedido> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pedido pedido){
		Pedido servicoSalvo = servicoService.atualizar(codigo, pedido);
		return ResponseEntity.ok(servicoSalvo);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PEDIDO') and #oauth2.hasScope('write')")
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo){
		servicoService.atualizarPropriedadeAtivo(codigo, ativo);
	}
}
