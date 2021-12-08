package br.edu.ifsp.arq.projeto.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.projeto.domain.model.Servico;
import br.edu.ifsp.arq.projeto.repository.HospedagemRepository;

@Service
public class HospedagemService {
	
	@Autowired
	private HospedagemRepository hospedagemRepository;

	public Servico atualizar(Long codigo, Servico servico) {
		Servico hospedagemSalva = buscarPessoaPeloCodigo(codigo);
		BeanUtils.copyProperties(servico, hospedagemSalva, "codigo");
		return hospedagemRepository.save(hospedagemSalva);
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Servico hospedagemSalva = buscarPessoaPeloCodigo(codigo);
		hospedagemSalva.setAtivo(ativo);
		hospedagemRepository.save(hospedagemSalva);
	}
	
	private Servico buscarPessoaPeloCodigo(Long codigo) {
		Servico hospedagemSalva = hospedagemRepository.findById(codigo)
				.orElseThrow(()-> new EmptyResultDataAccessException(1));
		return hospedagemSalva;
	}

}
