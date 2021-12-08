package br.edu.ifsp.arq.projeto.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.projeto.domain.model.Cliente;
import br.edu.ifsp.arq.projeto.domain.model.Prestador;
import br.edu.ifsp.arq.projeto.repository.PrestadorRepository;

@Service
public class PrestadorService {
	
	@Autowired
	private PrestadorRepository prestadorRepository;

	public Prestador atualizar(Long codigo, Prestador prestador) {
		Prestador clienteSalva = buscarPessoaPeloCodigo(codigo);
		BeanUtils.copyProperties(prestador, clienteSalva, "codigo");
		return prestadorRepository.save(clienteSalva);
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Prestador prestadorSalva = buscarPessoaPeloCodigo(codigo);
		prestadorSalva.setAtivo(ativo);
		prestadorRepository.save(prestadorSalva);
	}
	
	private Prestador buscarPessoaPeloCodigo(Long codigo) {
		Prestador prestadorSalva = prestadorRepository.findById(codigo)
				 .orElseThrow(()-> new EmptyResultDataAccessException(1));
		return prestadorSalva;
	}

}
