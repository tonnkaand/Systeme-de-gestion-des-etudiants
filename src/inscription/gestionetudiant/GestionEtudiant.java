package inscription.gestionetudiant;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import inscription.etudiant.Etudiant;
import examen.matieres.Matiere;

@SuppressWarnings("unused")
public class GestionEtudiant {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            List<Etudiant> etudiants = new ArrayList<>();
            List<Matiere> matieres = new ArrayList<>();
            System.out.println(); 
            // Ajouter des matières
            System.out.println("Combien de matieres ?");
            int nbMatieres = scanner.nextInt();
            scanner.nextLine(); // Consommer le retour à la ligne
            for (int i = 0; i < nbMatieres; i++) {
                System.out.print("Nom de la matiere " + (i + 1) + ": ");
                String nomMatiere = scanner.nextLine();
                matieres.add(new Matiere(nomMatiere));
            }
            System.out.println(); 
            // Ajouter les étudiants
            System.out.println("Combien d'etudiants ?");
            int nbEtudiants = scanner.nextInt();
            scanner.nextLine();
            System.out.println(); 
            for (int i = 0; i < nbEtudiants; i++) {
                System.out.println("Etudiant -" + (i + 1));
                System.out.print("Nom: ");
                String nom = scanner.nextLine();
                System.out.print("Prenom: ");
                String prenom = scanner.nextLine();
                System.out.print("Age: ");
                int age = scanner.nextInt();
                scanner.nextLine(); // Consommer le retour à la ligne
                System.out.print("Matricule: ");
                String matricule = scanner.nextLine();

                Etudiant etudiant = new Etudiant(nom, prenom, age, matricule);
                List<Double> notes = new ArrayList<>();
                for (Matiere matiere : matieres) {
                    System.out.print("Note En " + matiere.getNom() + ": ");
                    notes.add(scanner.nextDouble());
                }
                scanner.nextLine(); // Consommer le retour à la ligne
                System.out.println(); 
                etudiant.setNotes(notes);
                etudiants.add(etudiant);
            }
            System.out.println(); 
            System.out.println(); 
            int choix;
            do {
                System.out.println("\n--- Menu ---");
                System.out.println("1. Affichage des resultats par ordre de merite");
                System.out.println("2. Afficher le premier et le dernier de la classe");
                System.out.println("3. afficher la moyenne de la classe");
                System.out.println("4. Afficher la liste des admis (moyenne >= 10)");
                System.out.println("5. Afficher les Etudiants ayant une moyenne >= moyenne de la classe");
                System.out.println("0. Quitter");
                System.out.print("Veuillez entrer votre choix: ");
                choix = scanner.nextInt();
                scanner.nextLine(); // Consommer le retour à la ligne

                switch (choix) {
                    case 1:
                         System.out.println(); 
                         System.out.println(); 
                        etudiants.sort((e1, e2) -> Double.compare(e2.calculerMoyenne(), e1.calculerMoyenne()));
                        System.out.println("Resultats par ordre de merite:");
                        for (Etudiant e : etudiants) {
                            System.out.println(e.getNomComplet() + " - Moyenne: " + e.calculerMoyenne());
                        }
                        break;
                    case 2:
                        System.out.println(); 
                        System.out.println(); 
                        if (!etudiants.isEmpty()) {
                            System.out.println("Premier: " + etudiants.get(0).getNomComplet());
                            System.out.println("Dernier: " + etudiants.get(etudiants.size() - 1).getNomComplet());
                        } else {
                            System.out.println("Aucun étudiant enregistre.");
                        }
                        break;
                    case 3:
                        System.out.println(); 
                        System.out.println(); 
                        double moyenneClasse = etudiants.stream()
                                .mapToDouble(Etudiant::calculerMoyenne)
                                .average()
                                .orElse(0.0);
                        System.out.println("Moyenne de la classe: " + moyenneClasse);
                        break;
                    case 4:
                        System.out.println(); 
                        System.out.println("Admis (moyenne >= 10):");
                        etudiants.stream()
                                .filter(e -> e.calculerMoyenne() >= 10)
                                .forEach(e -> System.out.println(e.getNomComplet()));
                        break;
                    case 5:
                        System.out.println();
                        System.out.println();  
                        double moyenne = etudiants.stream()
                                .mapToDouble(Etudiant::calculerMoyenne)
                                .average()
                                .orElse(0.0);
                        System.out.println("Etudiants ayant une moyenne >= moyenne de la classe est :");
                        etudiants.stream()
                                .filter(e -> e.calculerMoyenne() >= moyenne)
                                .forEach(e -> System.out.println(e.getNomComplet()));
                               
                              
                        break;
                    case 0:
                        System.out.println(); 
                        System.out.println(); 
                        System.out.println("Merci , Au-revoir!");
                        break;
                    default:
                        System.out.println("Choix indisponible. Veuillez reessayer.");
                }
            } while (choix != 0);
        }
    }
}
