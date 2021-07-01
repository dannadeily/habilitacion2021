package co.edu.ufps.habilitacion.dao;

import co.edu.ufps.habilitacion.entidades.*;
import co.edu.ufps.habilitacion.util.*;

public class RolDAO extends Conexion<Rol> implements GenericDAO<Rol>{
	public RolDAO() {
		super(Rol.class);
	}

}
