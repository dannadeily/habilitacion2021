package co.edu.ufps.habilitacion.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import co.edu.ufps.habilitacion.entidades.*;
import co.edu.ufps.habilitacion.util.*;

public class UsuarioDAO extends Conexion<Usuario> implements GenericDAO<Usuario>{
	public UsuarioDAO() {
		super(Usuario.class);
	}

	public Usuario findByUser(String user) {
		Usuario t = null;
		try {
			Query q = super.getEm().createNamedQuery(Usuario.class.getSimpleName() + ".findByUser", Usuario.class)
					.setParameter("user", user);
			Object o = q.getSingleResult();
			t = (Usuario) o;
		} catch (NoResultException ne) {
			t = null;
		}
		return t;
	}
}
