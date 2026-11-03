package curso.dao.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

import curso.dao.AlumnoDAO;
import curso.entity.Alumno;
import curso.exception.DAOException;

public class AlumnoDAOImplJSON implements AlumnoDAO {
	private Path pathjson;
	private ObjectMapper mapper;

	public AlumnoDAOImplJSON(String pathjson) {
		super();

		this.pathjson = Paths.get(pathjson);
		this.mapper = new ObjectMapper();
		this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
		this.mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		if (!Files.exists(this.pathjson)) {
			try {
				ObjectNode root = this.mapper.createObjectNode();
				this.mapper.writeValue(this.pathjson.toFile(), root);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private Map<String, Alumno> read() throws DAOException {

		Map<String, Alumno> alumnmos;
		try {
			alumnmos = this.mapper.readValue(this.pathjson.toFile(), new TypeReference<Map<String, Alumno>>() {
			});
			return alumnmos;

		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			throw new DAOException(DAOException.ERROR_MESSAGE_BAD_FORMAT);
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			throw new DAOException(DAOException.ERROR_MESSAGE_BAD_FORMAT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new DAOException(DAOException.ERROR_MESSAGE_IO);
		}

	}

	private void write(Map<String,Alumno> a) throws DAOException {
		try {
			this.mapper.writeValue(this.pathjson.toFile(), a);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new DAOException(DAOException.ERROR_MESSAGE_IO);
		}
	}

	@Override
	public void add(Alumno element) throws DAOException {
		// TODO Auto-generated method stub
Map<String,Alumno> alumnos=this.read();
alumnos.put(element.getDni(), element);
	this.write(alumnos);
	}

	@Override
	public Alumno get(String key) throws DAOException {
		// TODO Auto-generated method stub
		return this.read().get(key);
	}

	@Override
	public List<Alumno> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return new ArrayList<Alumno>(this.read().values());
	}

	@Override
	public void uptade(Alumno element) throws DAOException {
		Map <String,Alumno> alumnos= this.read();
		if (alumnos.containsKey(element.getDni())) {
			alumnos.put(element.getDni(), element);
			this.write(alumnos);
		}
		
	}

	@Override
	public boolean remove(String key) throws DAOException {
		Map <String,Alumno> alumnos= this.read();
	boolean borrado=false;
		if (alumnos.containsKey(key)) {
		return borrado=alumnos.remove(key) != null;	
		
		}
		return borrado;
	}

}
