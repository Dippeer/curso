package curso.entity;

import java.io.Serializable;
import java.util.Objects;

public class Profesor implements Serializable{
	private String Dni;
	private String nombre;
	private String apellidos;
	private String email;
	private String especialidad;

	public Profesor(String dni, String nombre, String apellidos, String email, String especialidad) {
		super();
		Dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.especialidad = especialidad;
	}

	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	@Override
	public String toString() {
		return "Profesor [Dni=" + Dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ ", especialidad=" + especialidad + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(Dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		return Objects.equals(Dni, other.Dni);
	}

}
