package br.edu.ifsp.arq.projeto.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.projeto.domain.model.Cliente;
import br.edu.ifsp.arq.projeto.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente atualizar(Long codigo, Cliente cliente) {
		Cliente clienteSalva = buscarPessoaPeloCodigo(codigo);
		BeanUtils.copyProperties(cliente, clienteSalva, "codigo");
		return clienteRepository.save(clienteSalva);
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Cliente clienteSalva = buscarPessoaPeloCodigo(codigo);
		clienteSalva.setAtivo(ativo);
		clienteRepository.save(clienteSalva);
	}
	
	private Cliente buscarPessoaPeloCodigo(Long codigo) {
		Cliente clienteSalva = clienteRepository.findById(codigo)
				.orElseThrow(()-> new EmptyResultDataAccessException(1));
		return clienteSalva;
	}

}
