package co.edu.ufps.habilitacion.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.protobuf.TextFormat.ParseException;

import co.edu.ufps.habilitacion.dao.ConnectiontokenDAO;
import co.edu.ufps.habilitacion.dao.ReporteDAO;
import co.edu.ufps.habilitacion.dao.RolDAO;
import co.edu.ufps.habilitacion.dao.TypedbDAO;
import co.edu.ufps.habilitacion.dao.UsuarioDAO;
import co.edu.ufps.habilitacion.entidades.Connectiontoken;
import co.edu.ufps.habilitacion.entidades.Typedb;
import co.edu.ufps.habilitacion.entidades.Usuario;
import co.edu.ufps.habilitacion.util.Mail;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet({
		// usuario
		"/user", "/user/signup/", "/user/signup/procesar", "/user/activar", "/user/reportes",
		"/user/reportes/registrar", "/user/reportes/tokens", "/user/reportes/seguimientos",

		// admin
		"/administrador", "/administrador/registroBases", "/administrador/registroBases/enviar",

		// tokens
		"/user/reportes/tokens/registrar","/user/tokens/listar"
})

public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO uDAO;
	private RolDAO rDAO;
	private ReporteDAO reDAO;
	private ConnectiontokenDAO conDAO;
	private TypedbDAO tdbDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioServlet() {
		super();
		uDAO = new UsuarioDAO();
		rDAO = new RolDAO();
		reDAO = new ReporteDAO();
		conDAO = new ConnectiontokenDAO();
		tdbDAO = new TypedbDAO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();

		if (request.getSession().getAttribute("usuario") != null) {
			if (path.contains("/user")) {
				path = path.replace("/user", "");
				switchUsuario(request, response, path);
			} else if (path.contains("/admin")) {
				path = path.replace("/administrador", "");
				switchAdmin(request, response, path);
			}
		}else {
			request.getRequestDispatcher("/Login").forward(request, response);
		}
	}

	protected void switchUsuario(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		int id=0;
		int reporte = 0;
		switch (path) {
		case "":
			id = ((Usuario)request.getSession().getAttribute("usuario")).getId();
			request.setAttribute("reportes", reDAO.listReportes(id+""));
			request.setAttribute("tokens", conDAO.listTokens(id+""));
			request.getRequestDispatcher("inicio.jsp").forward(request, response);
			break;
		case "/registro":
			request.getRequestDispatcher("registro.jsp").forward(request, response);
			break;
		case "/reportes":
			id = ((Usuario) request.getSession().getAttribute("usuario")).getId();
			request.setAttribute("reportes", reDAO.listReportes(id + ""));
			request.getRequestDispatcher("listaReportes.jsp").forward(request, response);
			break;
		case "/reportes/registrar":
			request.getRequestDispatcher("registrarReporte.jsp").forward(request, response);
			break;
		case "/reportes/seguimientos":
			reporte = Integer.parseInt(request.getParameter("reporte"));
			request.setAttribute("seguimientos", reDAO.find(reporte).getSeguimientos());
			request.getRequestDispatcher("listaReportes.jsp").forward(request, response);
			break;
		case "/signup/procesar":
			registrar(request, response);
			break;
		case "/activar":
			activar(request, response);
			break;
		default:
			if(path.contains("/tokens")) {
				switchTokens(request, response, path);
			}
			break;
		}
	}

	protected void registrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String usuario = request.getParameter("usuario");
		if (uDAO.findByUser(usuario) != null) {
			String email = request.getParameter("email");
			String rol = request.getParameter("rol");
			String pass = request.getParameter("pass");

			Usuario us = new Usuario(usuario, email, pass, (short) 0, rDAO.find(Integer.parseInt(rol)));
			uDAO.insert(us);
			Mail mail = new Mail();
			String url = "http://localhost:8080/habilitacion2021/user/activar?usuario=" + us.getUsuario();
			mail.enviarEmail(us.getEmail(), "Activación", url);
		} else {
			System.out.println("Error");
		}

		request.getRequestDispatcher("/registroUsuario.jsp").forward(request, response);
	}

	protected void activar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String usuario = request.getParameter("usuario");
		Usuario us = uDAO.findByUser(usuario);
		if (us != null) {
			us.setState((short) 1);
			uDAO.update(us);
		} else {
			System.out.println("no existe");
		}

		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	

	protected void switchAdmin(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		
		switch (path) {
		case "":
			break;
		case "/registroBases":
			request.getRequestDispatcher("/registroBases.jsp").forward(request, response);
			break;
		case "/registroBases/enviar":
			procesarBases(request, response);
			break;
		default:
			break;
		}
	}

	protected void procesarBases(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String description = request.getParameter("description");
		String driver = request.getParameter("driver");
		String aditional = request.getParameter("aditional");

		if (description != null && driver != null && aditional != null) {
			Typedb tbd = new Typedb(aditional, description, driver);
			tdbDAO.insert(tbd);
		} else {
			System.out.println("no se pudo");
		}

		request.getRequestDispatcher("/registroBases.jsp").forward(request, response);
	}

	protected void switchTokens(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {

		int reporte = 0;
		switch (path) {
		case "/reportes/tokens":
			reporte = Integer.parseInt(request.getParameter("reporte"));
			request.setAttribute("tokens", reDAO.find(reporte).getConnectiontoken());
			request.getRequestDispatcher("listaReportes.jsp").forward(request, response);
			break;
		case "/reportes/tokens/listar":
			try {
				int id= ((Usuario)request.getSession().getAttribute("usuario")).getId();
				request.setAttribute("tokens", conDAO.listTokens(id+""));
				request.getRequestDispatcher("listaTokens.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			break;
		case "/reportes/tokens/registrar":
			try {
				request.getRequestDispatcher("registroTokens.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			break;
		case "/reportes/tokens/registrar/enviar":
			try {
				registroTokens(request, response);
			} catch (ServletException | SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
	
	protected void registroTokens(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException, ParseException {
		String db = request.getParameter("db");
		String host = request.getParameter("host");
		String pass = request.getParameter("pass");
		short port = Short.parseShort(request.getParameter("port"));
		short state = Short.parseShort(request.getParameter("state"));
		String userdb = request.getParameter("userdb");
		String tokens = request.getParameter("tokens");
		String aditional = request.getParameter("aditional");
		String description = request.getParameter("description");
		String driver = request.getParameter("driver");

		Typedb td = new Typedb(aditional, description, driver);

		Connectiontoken cn = new Connectiontoken(db, host, pass, port, state, tokens, userdb, td);

		conDAO.insert(cn);
		request.getRequestDispatcher("/user/reportes").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
