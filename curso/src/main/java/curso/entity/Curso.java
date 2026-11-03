package curso.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Curso {
	private long id;
	private String titulo;
	private int duracionDeHoras;
	private LocalDate fechaDeInicio;
	private String profesorResposanble;
	private List<String> dniAlumnos;

	

	public Curso(String titulo, int duracionDeHoras, LocalDate fechaDeInicio, String profesorResposanble,
			List<String> dniAlumnos) {
		super();
		this.id = hashCode();
		this.titulo = titulo;
		this.duracionDeHoras = duracionDeHoras;
		this.fechaDeInicio = fechaDeInicio;
		this.profesorResposanble = profesorResposanble;
		this.dniAlumnos = new ArrayList<String>();
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

	public List<String> getDniAlumnos() {
		return dniAlumnos;
	}

	public void setDniAlumnos(List<String> dniAlumnos) {
		this.dniAlumnos = dniAlumnos;
	}

	public long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Integer.valueOf(duracionDeHoras), fechaDeInicio, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return String.format("%d;%s;%d;%s;%s;%s\n",this.id,this.titulo,this.duracionDeHoras,this.profesorResposanble,this.dniAlumnos.toString().replaceAll(",", ";"));
	}

}
