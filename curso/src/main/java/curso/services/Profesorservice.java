package curso.services;

import java.util.List;

import curso.dao.CursoDAO;
import curso.dao.ProfesorDAO;
import curso.dao.File.CursoDAOImplCSV;
import curso.dao.File.ProfesorDAOImplBinario;
import curso.entity.Profesor;
import curso.exception.DAOException;
import curso.exception.DuplicateProffesorException;
import curso.exception.ProffesorEmploiedExeption;
import curso.exception.ProffesorNotfoundException;

public class Profesorservice {
	private final ProfesorDAO profesores;
private final CursoDAO cursos;
	public Profesorservice(String filename) {
		super();
		this.profesores = new ProfesorDAOImplBinario(filename);
	this.cursos= new CursoDAOImplCSV(filename);
	}

	public void addProfesor(Profesor p) throws DAOException, DuplicateProffesorException {

		if (this.profesores.get(p.getDni()) != null) {
			throw new DuplicateProffesorException(p.getDni());
		}

		this.profesores.add(p);

	}

	public Profesor getProfesor(String dni) throws DAOException, ProffesorNotfoundException {
		if (this.profesores.get(dni) == null) {
			throw new ProffesorNotfoundException(dni);
		}
		return this.profesores.get(dni);
	}

	public List<Profesor> getallProfesor() throws DAOException {
		return this.profesores.getAll();
	}

	public void updateProfesor(Profesor p) throws DAOException, ProffesorNotfoundException {
		if (this.profesores.get(p.getDni()) == null) {
			throw new ProffesorNotfoundException(p.getDni());
		} else {
			this.profesores.uptade(p);
		}
	}

	public boolean remonveProfesor(String dni) throws DAOException, ProffesorNotfoundException, ProffesorEmploiedExeption {
		if(this.profesores.get(dni)==null) {
			throw new ProffesorNotfoundException(dni);
		}
		if(this.cursos.getAll().stream().anyMatch(z->z.getProfesorResposanble().equals(dni))) {
			throw new ProffesorEmploiedExeption(dni);
		}
		
		
		
		return this.profesores.remove(dni);
	}
}