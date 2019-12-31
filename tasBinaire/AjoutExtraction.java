import java.util.Random;

public class AjoutExtraction {

    public static void main(String[] args) {
        int i, insertion = 0, extraction = 0;
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

        double test, p=0.1;
           

        for(i = 0; i < 1000000 ; i++){
            test=rand.nextDouble();
            if (test<=p){
                //System.err.println("Pour "+i+" insertion");
                 // Ajout d'un élément et mesure du temps pris par l'opération.
                before = System.nanoTime();
                permutation = tas.insert(i);
                after = System.nanoTime();
                insertion++;
                 // Enregistrement du temps pris par l'opération
            time_analysis.append(after - before);
            // Enregistrement du nombre de copies efféctuées par l'opération.
            // S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
            permutation_analysis.append( (permutation != 0)? permutation : 0);
           // Enregistrement de l'espace mémoire non-utilisé.
           memory_analysis.append(tas.capacite() - tas.size());
            }else{
                //System.err.println("Pour "+i+" extraction");

                 // Ajout d'un élément et mesure du temps pris par l'opération.
                before = System.nanoTime();
                permutation = tas.extraireMin();
                after = System.nanoTime();
                extraction++;

                 // Enregistrement du temps pris par l'opération
            time_analysis.append(after - before);
            // Enregistrement du nombre de copies efféctuées par l'opération.
            // S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
            permutation_analysis.append( (permutation != 0)? permutation : 0);
           // Enregistrement de l'espace mémoire non-utilisé.
           memory_analysis.append(tas.capacite() - tas.size());
            }
           
           
        }

        // Affichage de quelques statistiques sur l'expérience.
        System.err.println("Nombre d'insertions : "+insertion);
        System.err.println("Nombre d'extraction : "+extraction);
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
