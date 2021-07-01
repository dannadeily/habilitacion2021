package co.edu.ufps.habilitacion.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
		@NamedQuery(name = "Usuario.findByUser", query = "SELECT u FROM Usuario u where u.usuario=:user") })
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String usuario;

	private String email;

	private int id;

	private String pass;

	private short state;

	// bi-directional many-to-one association to Connectiontoken
	@OneToMany(mappedBy = "usuario")
	private List<Connectiontoken> connectiontokens;

	// bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name = "role")
	private Rol rolBean;

	public Usuario() {
	}

	public Usuario(String usuario, String email, String pass, short state, Rol rolBean) {
		super();
		this.usuario = usuario;
		this.email = email;
		this.pass = pass;
		this.state = state;
		this.rolBean = rolBean;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public short getState() {
		return this.state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public List<Connectiontoken> getConnectiontokens() {
		return this.connectiontokens;
	}

	public void setConnectiontokens(List<Connectiontoken> connectiontokens) {
		this.connectiontokens = connectiontokens;
	}

	public Connectiontoken addConnectiontoken(Connectiontoken connectiontoken) {
		getConnectiontokens().add(connectiontoken);
		connectiontoken.setUsuario(this);

		return connectiontoken;
	}

	public Connectiontoken removeConnectiontoken(Connectiontoken connectiontoken) {
		getConnectiontokens().remove(connectiontoken);
		connectiontoken.setUsuario(null);

		return connectiontoken;
	}

	public Rol getRolBean() {
		return this.rolBean;
	}

	public void setRolBean(Rol rolBean) {
		this.rolBean = rolBean;
	}

}