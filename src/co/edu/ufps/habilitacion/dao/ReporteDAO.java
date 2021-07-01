package co.edu.ufps.habilitacion.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Query;

import co.edu.ufps.habilitacion.entidades.*;
import co.edu.ufps.habilitacion.util.*;

public class ReporteDAO extends Conexion<Reporte> implements GenericDAO<Reporte> {
	public ReporteDAO() {
		super(Reporte.class);
	}

	public List<Reporte> listReportes(String usuario) {
		Query consulta = getEm().createNativeQuery(
				"select reporte.id, u.id as usuario from usuario u inner join connectiontoken c on c.user=u.id inner join reporte on reporte.conexion=c.id where u.id=:usuario")
				.setParameter("usuario", usuario);
		List<Object[]> lista = consulta.getResultList();
		List<Reporte> listReportes =null;
		if (lista != null) {
			listReportes = new ArrayList<>();
			for (Object[] s : lista) {
				Reporte r = new Reporte();
				r = this.find(Integer.parseInt(s[0].toString()));
				listReportes.add(r);
			}
		}
		return listReportes;
	}

}
