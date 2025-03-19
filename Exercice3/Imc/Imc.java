package Imc;

public class Imc {
    private double taille;
    private double poids;

    // Constructeur
    public Imc(double taille, double poids) {
        this.taille = taille;
        this.poids = poids;
    }

    // Méthode de calcul de l'IMC
    public double calcul() {
        if (taille > 0) {
            return poids / (taille * taille);
        }
        return 0; // Valeur de sécurité en cas d'erreur
    }
}
