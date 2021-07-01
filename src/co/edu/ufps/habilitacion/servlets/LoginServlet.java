package co.edu.ufps.habilitacion.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.ufps.habilitacion.dao.RolDAO;
import co.edu.ufps.habilitacion.dao.UsuarioDAO;
import co.edu.ufps.habilitacion.entidades.Usuario;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet({ "/Login", "/Login/enviar","/CerrarSesion" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO uDAO;
	private RolDAO rDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		uDAO = new UsuarioDAO();
		rDAO = new RolDAO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();

		switch (path) {
		case "/Login":
			request.getRequestDispatcher("login.jsp").forward(request, response);
			break;
		case "/Login/enviar":
			loguear(request, response);
			break;
		case "/CerrarSesion":
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath()+"/Login");
			break;
		}

	}

	protected void loguear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getSession().getAttribute("usuario")!=null) {
			swtichInicio(request,response,(Usuario)request.getSession().getAttribute("usuario"));
		}
		
		String usuario = request.getParameter("usuario");
		String pass = request.getParameter("pass");

		if (usuario != null || pass != null) {
			Usuario us = uDAO.findByUser(usuario);

			if (us != null && us.getState()==1) {
				request.getSession().setAttribute("usuario", usuario);
				swtichInicio(request,response,us);
			}else {
				response.sendRedirect(request.getContextPath()+"/Login");
			}
		}

	}
	
	private void swtichInicio(HttpServletRequest request,HttpServletResponse response, Usuario us)
			throws ServletException, IOException {
		switch (us.getRolBean().getDescription()) {
		case "Usuario":
			request.getRequestDispatcher("/user").forward(request, response);
			break;
		case "Administrador":
			request.getRequestDispatcher("/administrador").forward(request, response);
			break;
		default:
			break;
		}
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
