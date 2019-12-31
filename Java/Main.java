import analysis.Analyzer;
import structures.ArrayListProxy;
//import structures.ArrayList;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int i, time_ind = 0, pop_ind = 0;
        // Tableau dynamique.
        //ArrayListProxy<Integer> a = new ArrayListProxy<Integer>();
        ArrayList<Integer> a = new ArrayList<Integer>();
        // Analyse du temps pris par les opérations.
        Analyzer time_analysis = new Analyzer();
        // Analyse du nombre de copies faites par les opérations.
        Analyzer copy_analysis = new Analyzer();
	    // Analyse de l'espace mémoire inutilisé.
	    Analyzer memory_analysis = new Analyzer();
        long before, after, avant, apres;
        // Booléen permettant de savoir si une allocation a été effectuée.
        boolean memory_allocation;
        Random random_generator = new Random();
        double test, p=0.9;

        for(i = 0; i < 1000000 ; i++){
        	test=random_generator.nextDouble();
            if(test<=p) {
                // Ajout d'un élément et mesure du temps pris par l'opération.
                before = System.nanoTime();
                memory_allocation = a.append(i);
                after = System.nanoTime();
                time_ind++;
            }
            else {

            	//if (a.size()>0){
            		before = System.nanoTime();
                	memory_allocation = a.pop_back();
                	after = System.nanoTime();
                	pop_ind++;
                //}else if (a.size()==0){
            	//	System.err.println("on ne fait rien ");

            	//}
            	
               
            }

            // Enregistrement du temps pris par l'opération
            time_analysis.append(after - before);
            // Enregistrement du nombre de copies efféctuées par l'opération.
            // S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
            copy_analysis.append( (memory_allocation == true)? a.size(): 1);
	        // Enregistrement de l'espace mémoire non-utilisé.
	        memory_analysis.append( a.capacity() - a.size() );
        }

        // Affichage de quelques statistiques sur l'expérience.
        System.err.println("Nombre d'insertion : "+time_ind);
        System.err.println("Nombre de suppression : "+pop_ind);
        System.err.println("Total cost : "+time_analysis.get_total_cost());
        System.err.println("Average cost : "+time_analysis.get_average_cost());
        System.err.println("Variance :"+time_analysis.get_variance());
        System.err.println("Standard deviation :"+time_analysis.get_standard_deviation());

        // Sauvegarde les données de l'expérience: temps et nombre de copies effectuées par opération.
        time_analysis.save_values("../plots/dynamic_array_add_and_delete_sqrt_time_java.plot");
        copy_analysis.save_values("../plots/dynamic_array_add_and_delete_sqrt_copy_java.plot");
	    memory_analysis.save_values("../plots/dynamic_array_add_and_delete_sqrt_memory_java.plot");
    }
}
