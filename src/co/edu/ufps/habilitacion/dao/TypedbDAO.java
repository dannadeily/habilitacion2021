package co.edu.ufps.habilitacion.dao;

import co.edu.ufps.habilitacion.entidades.*;
import co.edu.ufps.habilitacion.util.*;

public class TypedbDAO extends Conexion<Typedb> implements GenericDAO<Typedb>{
	public TypedbDAO() {
		super(Typedb.class);
	}

}
