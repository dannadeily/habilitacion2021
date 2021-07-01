package co.edu.ufps.habilitacion.dao;

import co.edu.ufps.habilitacion.entidades.Typedb;
import co.edu.ufps.habilitacion.util.Conexion;

public class TypedbDAO extends Conexion<Typedb> implements GenericDAO<Typedb> {
	public TypedbDAO() {
		super(Typedb.class);
	}

}
