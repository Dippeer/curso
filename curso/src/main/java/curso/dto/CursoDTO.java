package curso.dto;

import java.time.LocalDate;
import java.util.List;

import curso.entity.Curso;

public class CursoDTO {
	private String titulo;
	private int duracionDeHoras;
	private LocalDate fechaDeInicio;
	private String profesorResposanble;

	public CursoDTO(String titulo, int duracionDeHoras, LocalDate fechaDeInicio, String profesorResposanble) {
		super();
		this.titulo = titulo;
		this.duracionDeHoras = duracionDeHoras;
		this.fechaDeInicio = fechaDeInicio;
		this.profesorResposanble = profesorResposanble;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracionDeHoras() {
		return duracionDeHoras;
	}

	public void setDuracionDeHoras(int duracionDeHoras) {
		this.duracionDeHoras = duracionDeHoras;
	}

	public LocalDate getFechaDeInicio() {
		return fechaDeInicio;
	}

	public void setFechaDeInicio(LocalDate fechaDeInicio) {
		this.fechaDeInicio = fechaDeInicio;
	}

	public String getProfesorResposanble() {
		return profesorResposanble;
	}

	public void setProfesorResposanble(String profesorResposanble) {
		this.profesorResposanble = profesorResposanble;
	}

	@Override
	public String toString() {
		return "CursoDTO [titulo=" + titulo + ", duracionDeHoras=" + duracionDeHoras + ", fechaDeInicio="
				+ fechaDeInicio + ", profesorResposanble=" + profesorResposanble + "]";
	}

}
