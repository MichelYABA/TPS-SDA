import java.util.Random;

public class AjoutCleAleatoire {

    public static void main(String[] args) {
        int i, time_ind = 0, pop_ind = 0;
        // Tableau dynamique.
        //ArrayList<Integer> a = new ArrayList<Integer>();
	    AVLTree avl=new AVLTree();
        // Analyse du temps pris par les opérations.
        Analyzer time_analysis = new Analyzer();
        // Analyse du nombre de copies faites par les opérations.
        //Analyzer copy_analysis = new Analyzer();
	    // Analyse de l'espace mémoire inutilisé.
	    Analyzer memory_analysis = new Analyzer();
        long before, after;
        // Booléen permettant de savoir si une allocation a été effectuée.
        boolean memory_allocation;

        Random rand= new Random();
        int max=1000;
        int min=0;
        int nombreAleatoire; 


        for(i = 0; i < 1000000 ; i++){

            nombreAleatoire=rand.nextInt(max-min +1)+min;
            // Ajout d'un élément et mesure du temps pris par l'opération.
            before = System.nanoTime();
            avl.insert(avl.root, i);
            after = System.nanoTime();

            // Enregistrement du temps pris par l'opération
            time_analysis.append(after - before);
            // Enregistrement du nombre de copies efféctuées par l'opération.
            // S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
            //copy_analysis.append( (memory_allocation == true)? i: 1);
	    // Enregistrement de l'espace mémoire non-utilisé.
	      memory_analysis.append(avl.getCapacity() - avl.getSize() );
        }

        // Affichage de quelques statistiques sur l'expérience.
        System.err.println("Total cost : "+time_analysis.get_total_cost());
        System.err.println("Average cost : "+time_analysis.get_average_cost());
        System.err.println("Variance :"+time_analysis.get_variance());
        System.err.println("Standard deviation :"+time_analysis.get_standard_deviation());

        // Sauvegarde les données de l'expérience: temps et nombre de copies effectuées par opération.
        time_analysis.save_values("plots/avl_time_java.plot");
        //copy_analysis.save_values("plots/b_arbre_copy_java.plot");
	    memory_analysis.save_values("plots/avl_memory_java.plot");
    }
}
