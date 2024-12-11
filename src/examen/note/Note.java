package examen.note;

import java.util.HashMap;
import java.util.Map;

import inscription.etudiant.Etudiant;
import examen.matieres.Matiere;

@SuppressWarnings("unused")
public class Note {
    private Map<Matiere, Double> notes = new HashMap<>();

    public void ajouterNote(Matiere matiere, double note) {
        notes.put(matiere, note);
    }

    public double getNoteParMatiere(Matiere matiere) {
        return notes.getOrDefault(matiere, 0.0);
    }
}
