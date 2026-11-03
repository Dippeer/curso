package curso.dao.File;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import curso.dao.ProfesorDAO;
import curso.entity.Profesor;
import curso.exception.DAOException;

public class ProfesorDAOImplBinario implements ProfesorDAO {
	private File profesorfile;

	public ProfesorDAOImplBinario(String profesorfilenombre) {
		this.profesorfile = new File(profesorfilenombre);
		if (!this.profesorfile.exists()) {
			try {
				this.profesorfile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void add(Profesor element) throws DAOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(this.profesorfile.getName())))) {

			oos.writeObject(element);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new DAOException(String.format(DAOException.ERROR_MESSAGE_FILE_NOT_FOUND));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new DAOException(String.format(DAOException.ERROR_MESSAGE_IO));
		}

	}

	@Override
	public Profesor get(String key) throws DAOException {
		Profesor p = null;
		try (ObjectInputStream ois = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(this.profesorfile.getName())))) {
			Boolean encontrado = false;
			while (!encontrado) {
				p = (Profesor) ois.readObject();
				if (p.getDni().equals(key)) {
					encontrado = true;

				}

			}
			return p;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new DAOException(String.format(DAOException.ERROR_MESSAGE_FILE_NOT_FOUND));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new DAOException(String.format(DAOException.ERROR_MESSAGE_IO));

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new DAOException(String.format(DAOException.ERROR_MESSAGE_BAD_FORMAT));

		}

	}

	@Override
	public List<Profesor> getAll() throws DAOException {
		// TODO Auto-generated method stub
		List<Profesor> profesores = new ArrayList<Profesor>();
		Profesor p = null;
		try (ObjectInputStream ois = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(this.profesorfile.getName())))) {
			Boolean encontrado = false;
			while (!encontrado) {
				p = (Profesor) ois.readObject();
				profesores.add(p);
			}
			return profesores;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new DAOException(String.format(DAOException.ERROR_MESSAGE_FILE_NOT_FOUND));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new DAOException(String.format(DAOException.ERROR_MESSAGE_IO));

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new DAOException(String.format(DAOException.ERROR_MESSAGE_BAD_FORMAT));

		}

	}

	@Override
	public void uptade(Profesor element) throws DAOException {
		// TODO Auto-generated method stub
		Profesor p = null;
		try (ObjectInputStream ois = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(this.profesorfile.getName()))); ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(this.profesorfile.getName())))) {
			Boolean encontrado = false;
			while (!encontrado) {
				p = (Profesor) ois.readObject();
				if (p.getDni().equals(element.getDni())) {
					encontrado = true;
					oos.writeObject(element);
				}

			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new DAOException(String.format(DAOException.ERROR_MESSAGE_FILE_NOT_FOUND));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new DAOException(String.format(DAOException.ERROR_MESSAGE_IO));

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new DAOException(String.format(DAOException.ERROR_MESSAGE_BAD_FORMAT));

		}

	}

	@Override
	public boolean remove(String key) throws DAOException {
		// TODO Auto-generated method stub
		Profesor p = null;
		try (ObjectInputStream ois = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(this.profesorfile.getName()))); ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(this.profesorfile.getName())))) {
			
			while(true) {
				p = (Profesor) ois.readObject();
				if (!p.getDni().equals(key)) {
					
					oos.writeObject(p);
				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new DAOException(String.format(DAOException.ERROR_MESSAGE_FILE_NOT_FOUND));
		} catch (IOException e) {
			// TODO Auto-generated catch block

			return true;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new DAOException(String.format(DAOException.ERROR_MESSAGE_BAD_FORMAT));

		}
	}

}
