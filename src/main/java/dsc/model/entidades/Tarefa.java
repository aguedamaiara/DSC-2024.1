package dsc.model.entidades;

import java.util.Objects;

public class Tarefa {
	private Integer id;
	private String titulo;
	private String descricao;
	private StatusTarefa status; // Enum: A_FAZER, FAZENDO, FEITO

	private Usuario responsavel; // Responsável pela Tarefa

	// Getters e Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusTarefa getStatus() {
		return status;
	}

	public void setStatus(StatusTarefa status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Tarefa tarefa = (Tarefa) obj;
		return id != null && id.equals(tarefa.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}
}
