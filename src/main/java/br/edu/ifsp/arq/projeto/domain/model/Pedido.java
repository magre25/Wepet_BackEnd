package br.edu.ifsp.arq.projeto.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_animal")
	private Animal animal;	
	
	@NotNull
	@Column(name = "dt_inicio")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataInicio;
	
	@Column(name = "dt_fim")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFim;	
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_servico")
	private Servico hospesagem;
	 
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_prestador")
	private Prestador prestador;
	
	@NotNull
	private Boolean status;			

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Prestador getPrestador() {
		return prestador;
	}

	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}

	public Servico getHospesagem() {
		return hospesagem;
	}

	public void setHospesagem(Servico hospesagem) {
		this.hospesagem = hospesagem;
	}


	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public Boolean getAtivo() {
		return status;
	}

	public void setAtivo(Boolean status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}
