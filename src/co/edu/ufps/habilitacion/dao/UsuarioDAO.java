package co.edu.ufps.habilitacion.dao;

import javax.persistence.NoResultException;

import co.edu.ufps.habilitacion.entidades.Usuario;
import co.edu.ufps.habilitacion.util.Conexion;

public class UsuarioDAO extends Conexion<Usuario> implements GenericDAO<Usuario> {
	public UsuarioDAO() {
		super(Usuario.class);
	}

	public Usuario findByUser(String user) {
		Usuario t = null;
		try {
			t = (Usuario) super.getEm().createNamedQuery(Usuario.class.getSimpleName() + ".findByUser", Usuario.class)
					.setParameter("user", user).getSingleResult();
		} catch (NoResultException ne) {
			t = null;
		}
		return t;
	}
}