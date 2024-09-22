package dsc.model.entidades;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Tarefa {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(nullable = false)
	private String titulo;

	@Column(nullable = false)
	private String descricao;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusTarefa status; // Enum: A_FAZER, FAZENDO, FEITO

	@ManyToOne
	@JoinColumn(name = "responsavel_id", nullable = false)
	private Usuario responsavel;

	public Tarefa() { }
	
	public Tarefa( String titulo, String descricao, StatusTarefa status, Usuario responsavel) {
		super();
		// String id, this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.status = status;
		this.responsavel = responsavel;
	}

	// Getters e Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
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
	
	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
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

}
