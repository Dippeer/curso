package curso.services;

import java.util.ArrayList;
import java.util.List;

import curso.dao.AlumnoDAO;
import curso.dao.CursoDAO;
import curso.dao.ProfesorDAO;
import curso.dao.File.AlumnoDAOImplJSON;
import curso.dao.File.CursoDAOImplCSV;
import curso.dao.File.ProfesorDAOImplBinario;
import curso.dto.CursoDTO;
import curso.entity.Curso;
import curso.entity.Profesor;
import curso.exception.AlumnoAlreadyExixtsExcetption;
import curso.exception.AlumnoNotFoundException;
import curso.exception.CursoActiveException;
import curso.exception.CursoNotFoundException;
import curso.exception.DAOException;
import curso.exception.DuplicateCursoException;
import curso.exception.ProffesorNotfoundException;

public class CursoService {
	private final CursoDAO cursos;
	private final ProfesorDAO profesores;
	private final AlumnoDAO alumnos;

	public CursoService(String pathnameCurso, String pathnameProfesor, String pathNameAlumno) {

		super();
		this.cursos = new CursoDAOImplCSV(pathnameCurso);
		this.profesores = new ProfesorDAOImplBinario(pathnameProfesor);
		this.alumnos = new AlumnoDAOImplJSON(pathNameAlumno);
	}

	public void addcurso(CursoDTO curso) throws DAOException, DuplicateCursoException, ProffesorNotfoundException {
		if (this.profesores.get(curso.getProfesorResposanble()) == null) {
			throw new ProffesorNotfoundException(curso.getProfesorResposanble());
		}
		if (!(this.cursos.get(curso.generarid()) == null)) {
			throw new DuplicateCursoException(Long.toString(curso.generarid()));
		}
		this.cursos.add(new Curso(curso.generarid(), curso.getTitulo(), curso.getDuracionDeHoras(),
				curso.getFechaDeInicio(), curso.getProfesorResposanble()));
	}

	public void getCurso(Long id)
			throws DAOException, DuplicateCursoException, ProffesorNotfoundException, CursoNotFoundException {
		if (this.cursos.get(id) == null) {
			throw new CursoNotFoundException(Long.toString(id));
		}
	}

	public List<Curso> getAllCursos() throws DAOException, DuplicateCursoException, ProffesorNotfoundException {
		return this.cursos.getAll();
	}

	public List<Curso> getallCursosfromProfesor(String dni)
			throws DAOException, DuplicateCursoException, ProffesorNotfoundException {
		if (this.cursos.getAll().stream().filter(c -> c.getProfesorResposanble().equals(dni)) == null) {
			throw new ProffesorNotfoundException(dni);
		}
		return this.cursos.getAll().stream().filter(c -> c.getProfesorResposanble().equals(dni)).toList();

	}

	public void updateprofesor(String profesor, Curso curso)
			throws DAOException, DuplicateCursoException, ProffesorNotfoundException {
		if (this.cursos.get(curso.getId()) == null) {
			throw new DuplicateCursoException(Long.toString(curso.getId()));

		}
		if (this.profesores.get(profesor) == null) {
			throw new ProffesorNotfoundException(profesor);

		}
		this.cursos.uptade(curso);
	}

	public boolean removecurso(Long id) throws DAOException, CursoNotFoundException, CursoActiveException {
		if (this.cursos.get(id) == null) {
			throw new CursoNotFoundException(Long.toString(id));

		}
		if (this.cursos.get(id).getDniAlumnos().size() > 0) {
			throw new CursoActiveException(Long.toString(id));
		}
		return this.cursos.remove(id);
	}

//MATRUCICULAS
	public void matricualarAlumno(String dni, Long id)
			throws DAOException, AlumnoNotFoundException, CursoNotFoundException, AlumnoAlreadyExixtsExcetption {
		if (this.alumnos.get(dni) == null) {
			throw new AlumnoNotFoundException(dni);
		}
		if (this.cursos.get(id) == null) {
			throw new CursoNotFoundException(Long.toString(id));

		}
		if (this.cursos.get(id).getDniAlumnos().contains(dni)) {
			throw new AlumnoAlreadyExixtsExcetption(dni);
		}
		this.cursos.get(id).getDniAlumnos().add(dni);
	}

	public void desmatricualarAlumno(String dni, Long id)
			throws DAOException, AlumnoNotFoundException, CursoNotFoundException {
		if (this.alumnos.get(dni) == null) {
			throw new AlumnoNotFoundException(dni);
		}
		if (this.cursos.get(id) == null) {
			throw new CursoNotFoundException(Long.toString(id));

		}
		if (!this.cursos.get(id).getDniAlumnos().contains(dni)) {
			throw new AlumnoNotFoundException(dni);

		}
		this.cursos.get(id).getDniAlumnos().remove(dni);
	}

	public List<String> listaAlumnos(Long id) throws DAOException, CursoNotFoundException {
		if (this.cursos.get(id) == null) {
			throw new CursoNotFoundException(Long.toString(id));

		}
		if (this.cursos.get(id).getDniAlumnos().isEmpty()) {
			List<String> Alumnos = new ArrayList<String>();
			return Alumnos;
		}
		return this.cursos.get(id).getDniAlumnos();
	}

	public List<Curso> listaCursos(String dni) throws DAOException, CursoNotFoundException, AlumnoNotFoundException,
			DuplicateCursoException, ProffesorNotfoundException {
		if (this.alumnos.get(dni) == null) {
			throw new AlumnoNotFoundException(dni);

		}
		List<Curso> cursosDelAlumno = this.getAllCursos().stream().filter(c -> c.getDniAlumnos().contains(dni))
				.toList();
		if (cursosDelAlumno.isEmpty()) {
			return new ArrayList<Curso>();
		}
		return cursosDelAlumno;
	}
public boolean Alumnoexiste(String dni, Long id) throws DAOException, AlumnoNotFoundException, CursoNotFoundException {
	if (this.alumnos.get(dni) == null) {
		throw new AlumnoNotFoundException(dni);
	}
	if (this.cursos.get(id) == null) {
		throw new CursoNotFoundException(Long.toString(id));

	}
	return this.cursos.get(id).getDniAlumnos().contains(dni);
}
}
