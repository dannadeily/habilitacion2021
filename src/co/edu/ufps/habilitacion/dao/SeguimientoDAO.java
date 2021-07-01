package co.edu.ufps.habilitacion.dao;

import co.edu.ufps.habilitacion.entidades.*;
import co.edu.ufps.habilitacion.util.*;

public class SeguimientoDAO extends Conexion<Seguimiento> implements GenericDAO<Seguimiento>{
	public SeguimientoDAO() {
		super(Seguimiento.class);
	}

}
