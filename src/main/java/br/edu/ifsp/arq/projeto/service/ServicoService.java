package br.edu.ifsp.arq.projeto.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.projeto.domain.model.Pedido;
import br.edu.ifsp.arq.projeto.repository.ServicoRepository;

@Service
public class ServicoService {
	
	@Autowired
	private ServicoRepository servicoRepository;

	public Pedido atualizar(Long codigo, Pedido pedido) {
		Pedido servicoSalva = buscarPessoaPeloCodigo(codigo);
		BeanUtils.copyProperties(pedido, servicoSalva, "codigo");
		return servicoRepository.save(servicoSalva);
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Pedido servicoSalva = buscarPessoaPeloCodigo(codigo);
		servicoSalva.setAtivo(ativo);
		servicoRepository.save(servicoSalva);
	}
	
	private Pedido buscarPessoaPeloCodigo(Long codigo) {
		Pedido servicoSalva = servicoRepository.findById(codigo)
			   .orElseThrow(()-> new EmptyResultDataAccessException(1));
		return servicoSalva;
	}

}
