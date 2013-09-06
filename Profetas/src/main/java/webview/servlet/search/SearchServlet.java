package webview.servlet.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.dto.Classificacao;
import persistence.dto.FontesObras;
import persistence.dto.Local;
import persistence.dto.Personagem;
import datatype.SimpleDate;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// CLASSE PARA TESTE!!! MODIFICAR TUDO DEPOIS!!!!
		String type = request.getParameter("type");
		if(type.equals("character")){
			List<Personagem> personagens = new ArrayList<Personagem>();
			for(int i = 1; i <= 20; i ++){
				personagens.add(new Personagem("nome"+i, "apelido"+i, new Local("Nome da cidade",0,0), new SimpleDate((short)1700, (short)2, (short)(i)), new Local("Nome da cidade",0,0), new SimpleDate((short)1700, (short)2, (short)(i+1)), null, "ocupacao"+i, "formacao"+i, null, null, null, null, null, null));
			}
			request.setAttribute("result", personagens);
		} else if(type.equals("bibliography")){
			List<FontesObras> obras = new ArrayList<FontesObras>();
			for(int i = 1; i <= 20; i++){
				obras.add(new FontesObras("titulo"+i, "comentarios"+i, "refverenciasirCulacaoObra"+i, "about:blank", "copiasManuscritas"+i, "traducoes"+i, new SimpleDate((short)1700, (short)11, (short)1), "editor"+i, null, new Local("algum lugar",0,0), new Classificacao("Livro"), null, null, null, null, null));
			}
			request.setAttribute("result", obras);
		}
		request.setAttribute("resultType", type);
		request.getRequestDispatcher("/jsp/result.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
