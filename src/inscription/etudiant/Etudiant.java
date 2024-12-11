package inscription.etudiant;

import java.util.List;

public class Etudiant {
    private String nom;
    private String prenom;
    @SuppressWarnings("unused")
    private int age;
    private String matricule;
    private List<Double> notes;

    public Etudiant(String nom, String prenom, int age, String matricule) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.matricule = matricule;
    }

    public void setNotes(List<Double> notes) {
        this.notes = notes;
    }

    public List<Double> getNotes() {
        return notes;
    }

    public double calculerMoyenne() {
        return notes.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public String getNomComplet() {
        return this.prenom + " " + this.nom;
    }

    public String getMatricule() {
        return matricule;
    }
}
