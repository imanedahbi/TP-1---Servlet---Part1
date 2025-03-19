

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculDeMonImc
 */
@WebServlet("/CalculDeMonImc")
public class CalculDeMonImc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculDeMonImc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//
//        double poids = Double.parseDouble(request.getParameter("poids"));
//        double taille = Double.parseDouble(request.getParameter("taille"));
//        double imc = poids / (taille * taille);
//
//        PrintWriter out = response.getWriter();
//        out.println("<html><head><title>Résultat IMC</title></head><body>");
//        out.println("<h2>Votre IMC est : " + imc + "</h2>");
//        out.println("</body></html>");
//    }
   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Récupération des paramètres envoyés via POST
        String poidsStr = request.getParameter("poids");
        String tailleStr = request.getParameter("taille");

        double poids = 0, taille = 0;
        boolean erreur = false;
        
        // Vérification et conversion des paramètres
        try {
            if (poidsStr != null && tailleStr != null && !poidsStr.isEmpty() && !tailleStr.isEmpty()) {
                poids = Double.parseDouble(poidsStr.trim());
                taille = Double.parseDouble(tailleStr.trim());
            } else {
                erreur = true;
            }
        } catch (NumberFormatException e) {
            erreur = true;
        }

        // Génération de la réponse HTML
        response.getWriter().println("<html><body>");
        if (!erreur && taille > 0) {
            double imc = poids / (taille * taille);
            response.getWriter().println("<h2>Votre IMC est : " + String.format("%.2f", imc) + "</h2>");
        } else {
            response.getWriter().println("<h2>Erreur : veuillez entrer des valeurs valides pour le poids et la taille.</h2>");
        }
        response.getWriter().println("</body></html>");
    }


	    // Pour éviter une erreur si la servlet est appelée en GET, rediriger vers POST
	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        doPost(request, response);
	    }


}
