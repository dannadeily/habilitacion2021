package co.edu.ufps.habilitacion.dao;

import co.edu.ufps.habilitacion.entidades.Rol;
import co.edu.ufps.habilitacion.util.Conexion;

public class RolDAO extends Conexion<Rol> implements GenericDAO<Rol> {
	public RolDAO() {
		super(Rol.class);
	}

}
