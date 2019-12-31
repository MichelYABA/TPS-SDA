import java.util.Random;

public class AjoutCleAleatoire {

    public static void main(String[] args) {
        int i, time_ind = 0, pop_ind = 0;
        // Tableau dynamique.
        //ArrayList<Integer> a = new ArrayList<Integer>();
	    //TasBinaire tas=new TasBinaire(1000000);
        TasBinaire_Dynamic tas=new TasBinaire_Dynamic(4);
        // Analyse du temps pris par les opérations.
        Analyzer time_analysis = new Analyzer();
        // Analyse du nombre de copies faites par les opérations.
        Analyzer permutation_analysis = new Analyzer();
	    // Analyse de l'espace mémoire inutilisé.
	    Analyzer memory_analysis = new Analyzer();
        long before, after;
        // Booléen permettant de savoir si une allocation a été effectuée.
        int permutation;

        Random rand= new Random();
        int max=1000;
        int min=0;
        int nombreAleatoire; 


        for(i = 0; i <1000000 ; i++){
            // Ajout d'un élément et mesure du temps pris par l'opération.
            nombreAleatoire=rand.nextInt(max-min +1)+min;
            before = System.nanoTime();
            permutation = tas.insert(nombreAleatoire);
            after = System.nanoTime();

            // Enregistrement du temps pris par l'opération
            time_analysis.append(after - before);
            // Enregistrement du nombre de copies efféctuées par l'opération.
            // S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
            permutation_analysis.append( (permutation !=0) ? permutation : 0);
        // Enregistrement de l'espace mémoire non-utilisé.
           memory_analysis.append(tas.capacite() - tas.size());
           //System.err.println("permutation de "+i+" : "+permutation);
        }
        //System.out.println();


        //System.out.println("Tableau après : ");
        //tas.printArray();


        System.out.println();

        // Affichage de quelques statistiques sur l'expérience.
        System.err.println("Total cost : "+time_analysis.get_total_cost());
        System.err.println("Average cost : "+time_analysis.get_average_cost());
        System.err.println("Variance :"+time_analysis.get_variance());
        System.err.println("Standard deviation :"+time_analysis.get_standard_deviation());
        

        // Sauvegarde les données de l'expérience: temps et nombre de copies effectuées par opération.
        time_analysis.save_values("../plot/tas_binaire_time_java.plot");
        permutation_analysis.save_values("../plot/tas_binaire_permutation_java.plot");
        memory_analysis.save_values("../plot/tas_binaire_memory_java.plot");
    }
}
