import java.util.Random;

public class AjoutCleCroissant {

    public static void main(String[] args) {
        int i, time_ind = 0, pop_ind = 0;
       
        BinomialTasMin tas=new BinomialTasMin();
        // Analyse du temps pris par les opérations.
        Analyzer time_analysis = new Analyzer();
        // Analyse du nombre de copies faites par les opérations.
        Analyzer lien_analysis = new Analyzer();
	    // Analyse de l'espace mémoire inutilisé.
	    Analyzer memory_analysis = new Analyzer();
        long before, after;
        // Booléen permettant de savoir si une permutation a été effectuée
        int lien, p=0;

            // Ajout d'un élément et mesure du temps pris par l'opération.

        
        for(i = 0; i <100000 ; i++){
        	
            // Ajout d'un élément et mesure du temps pris par l'opération.
            before = System.nanoTime();
            lien = tas.ajouterCle(i);
            after = System.nanoTime();

            // Enregistrement du temps pris par l'opération
            time_analysis.append(after - before);
            // Enregistrement du nombre de copies efféctuées par l'opération.
            // S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
            lien_analysis.append( (lien !=0) ? lien : 0);
	    // Enregistrement de l'espace mémoire non-utilisé.
	       memory_analysis.append(tas.capacity() - tas.size());
          // System.err.println("permutation de "+tableau_2[i]+" : "+permutation);
        }
        /*System.out.println();


        System.out.println("Tableau après : ");
        tas.printArray();


        System.out.println();*/

        // Affichage de quelques statistiques sur l'expérience.
        System.err.println("Total cost : "+time_analysis.get_total_cost());
        System.err.println("Average cost : "+time_analysis.get_average_cost());
        System.err.println("Variance :"+time_analysis.get_variance());
        System.err.println("Standard deviation :"+time_analysis.get_standard_deviation());
        

        // Sauvegarde les données de l'expérience: temps et nombre de copies effectuées par opération.
        time_analysis.save_values("plot/tas_binomial_time_java.plot");
        lien_analysis.save_values("plot/tas_binomial_lien_java.plot");
	    memory_analysis.save_values("plot/tas_binomial_memory_java.plot");
    }
}