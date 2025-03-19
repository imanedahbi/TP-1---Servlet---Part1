import java.io.IOException;

import Imc.Imc;
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

    // Constructeur
    public CalculDeMonImc() {
        super();
    }

    // Gestion de la méthode GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Rediriger vers POST (si nécessaire)
        doPost(request, response);
    }

    // Gestion de la méthode POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Récupération des paramètres (poids et taille)
        String poidsStr = request.getParameter("poids");
        String tailleStr = request.getParameter("taille");

        double poids = 0, taille = 0;
        boolean erreur = false;

        // Vérification et conversion des valeurs
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

        // Affichage du résultat ou message d'erreur
        response.getWriter().println("<html><body>");
        if (!erreur && taille > 0 && poids > 0) {
            // Création de l'objet Imc et calcul de l'IMC
            Imc imc = new Imc(taille, poids);
            double resultatImc = imc.calcul();

            // Affichage de l'IMC calculé
            response.getWriter().println("<h2>Votre IMC est : " + String.format("%.2f", resultatImc) + "</h2>");
        } else {
            // Message d'erreur si les entrées sont invalides
            response.getWriter().println("<h2>Erreur : veuillez entrer des valeurs valides pour le poids et la taille.</h2>");
        }
        response.getWriter().println("</body></html>");
    }
}
