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
import co.edu.ufps.habilitacion.dao.UsuarioDAO;
import co.edu.ufps.habilitacion.entidades.Connectiontoken;
import co.edu.ufps.habilitacion.entidades.Typedb;
import co.edu.ufps.habilitacion.entidades.Usuario;
import co.edu.ufps.habilitacion.util.Mail;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet({ "/user/signup/", "/user/signup/procesar", "/user/activar", "/user/reportes", "/user/reportes/tokens",
		"/user/reportes/tokens/agregar", "/user/reportes/seguimientos"})
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO uDAO;
	private RolDAO rDAO;
	private ReporteDAO reDAO;
	private ConnectiontokenDAO conDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioServlet() {
		super();
		uDAO = new UsuarioDAO();
		rDAO = new RolDAO();
		reDAO = new ReporteDAO();
		conDAO = new ConnectiontokenDAO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		
		Integer reporte = 0;
		
		if(path.contains("/user")) {
			path = path.replace("/user", "");
			switchUsuario(request, response, path);
		}else if(path.contains("/admin")) {
			
		}
	}
	
	protected void switchUsuario(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		
		int reporte = 0;
		switch (path) {
		case "/registro":
			request.getRequestDispatcher("registro.jsp").forward(request, response);
			break;
		case "/reportes":
			int id = ((Usuario) request.getSession().getAttribute("usuario")).getId();
			request.setAttribute("reportes", reDAO.listReportes(id + ""));
			request.getRequestDispatcher("listaReportes.jsp").forward(request, response);
			break;
		case "/reportes/tokens":
			reporte = Integer.parseInt(request.getParameter("reporte"));
			request.setAttribute("tokens", reDAO.find(reporte).getConnectiontoken());
			request.getRequestDispatcher("listaReportes.jsp").forward(request, response);
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
		case "/reportes/tokens/agregar":
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
			String url = "http://localhost:8080/SistemaReporte/user/activar?usuario=" + us.getUsuario();
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
			request.setAttribute("mensajeError", "no existe el usuario");
		}

		request.getRequestDispatcher("/login.jsp").forward(request, response);
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
