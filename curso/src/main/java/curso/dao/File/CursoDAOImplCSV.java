package curso.dao.File;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import curso.dao.CursoDAO;
import curso.entity.Curso;
import curso.exception.DAOException;

public class CursoDAOImplCSV implements CursoDAO {
	private Path crusoPath;

	public CursoDAOImplCSV(String path) {
		super();
		this.crusoPath = Paths.get(path);
		if (Files.notExists(crusoPath)) {
			try {
				Files.createFile(crusoPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void add(Curso element) throws DAOException {
		// TODO Auto-generated method stub
		try {
			Files.writeString(this.crusoPath, element.toString(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (FileNotFoundException e) {
			throw new DAOException(String.format(DAOException.ERROR_MESSAGE_FILE_NOT_FOUND));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new DAOException(String.format(DAOException.ERROR_MESSAGE_IO));
		}

	}

	@Override
	public Curso get(Long key) throws DAOException {
		// TODO Auto-generated method stub
		try (Stream<String> lineas =Files.lines(this.crusoPath)){
			Iterator<String> iteraror= lineas.iterator();
			while(iteraror.hasNext()) {
				String linea=iteraror.next();
				String[] partes=linea.split(";");
				if(Long.valueOf(partes[0]).equals(key)) {
					
					return new Curso(partes[0],Integer.parseInt(partes[1]), LocalDate.parse(partes[2]), partes[3],Arrays.asList(partes[4]));
				}

			}
			return null;
		} 
		catch (FileNotFoundException e) {
			throw new DAOException(String.format(DAOException.ERROR_MESSAGE_FILE_NOT_FOUND));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new DAOException(String.format(DAOException.ERROR_MESSAGE_IO));
		}

		
	}

	@Override
	public List<Curso> getAll() throws DAOException {
		try (Stream<String> lineas =Files.lines(this.crusoPath)){
			Iterator<String> iteraror= lineas.iterator();
			List<Curso> cursos= new ArrayList<Curso>();
			while(iteraror.hasNext()) {
				String linea=iteraror.next();
				String[] partes=linea.split(";");
				
					cursos.add(new Curso(partes[0],Integer.parseInt(partes[1]), LocalDate.parse(partes[2]), partes[3],Arrays.asList(partes[4])));
			}
			return cursos;

		}
		catch (FileNotFoundException e) {
			throw new DAOException(String.format(DAOException.ERROR_MESSAGE_FILE_NOT_FOUND));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new DAOException(String.format(DAOException.ERROR_MESSAGE_IO));
	}
		}

	@Override
	public void uptade(Curso element) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean remove(Long key) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	
	}

}
