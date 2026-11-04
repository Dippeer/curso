package curso.services;

import java.util.List;

import curso.dao.AlumnoDAO;
import curso.dao.CursoDAO;
import curso.dao.File.AlumnoDAOImplJSON;
import curso.dao.File.CursoDAOImplCSV;
import curso.entity.Alumno;
import curso.exception.AlumnoInClassException;
import curso.exception.AlumnoNotFoundException;
import curso.exception.DAOException;
import curso.exception.DuplicateAlumnoException;

public class AlumnoService {
	private final AlumnoDAO alumnos;
	private final CursoDAO cursos;

	public AlumnoService(String pathname) {
		super();
		this.alumnos = new AlumnoDAOImplJSON(pathname);
		this.cursos = new CursoDAOImplCSV(pathname);
	}

	public void addAlumno(Alumno element) throws DAOException, DuplicateAlumnoException {
		if (this.alumnos.get(element.getDni()) == null) {
			throw new DuplicateAlumnoException(element.getDni());
		}
		this.alumnos.add(element);
	}

	public Alumno getbydni(String dni) throws DAOException, AlumnoNotFoundException {
		if (this.alumnos.get(dni) == null) {
			throw new AlumnoNotFoundException(dni);
		}
		return this.alumnos.get(dni);
	}

	public List<Alumno> getAllAlumnos() throws DAOException {
		return this.alumnos.getAll();
	}

	public void updateAlumno(Alumno elememt) throws DAOException, AlumnoNotFoundException {
		if (this.alumnos.get(elememt.getDni()) == null) {
			throw new AlumnoNotFoundException(elememt.getDni());
		}
		this.alumnos.uptade(elememt);
	}

	public boolean removeAlumno(String dni) throws DAOException, AlumnoNotFoundException, AlumnoInClassException {
		if (this.alumnos.get(dni) == null) {
			throw new AlumnoNotFoundException(dni);
		}
		if (this.cursos.getAll().stream().anyMatch(z -> z.getDniAlumnos().contains(dni))) {
			throw new AlumnoInClassException(dni);

		}this.alumnos.remove(dni);
		
		
		return true;
	}
}
