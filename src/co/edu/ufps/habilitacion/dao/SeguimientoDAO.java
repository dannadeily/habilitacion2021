package co.edu.ufps.habilitacion.dao;

import co.edu.ufps.habilitacion.entidades.Seguimiento;
import co.edu.ufps.habilitacion.util.Conexion;

public class SeguimientoDAO extends Conexion<Seguimiento> implements GenericDAO<Seguimiento> {
	public SeguimientoDAO() {
		super(Seguimiento.class);
	}

}
