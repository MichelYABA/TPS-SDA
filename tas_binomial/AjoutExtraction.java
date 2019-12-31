import java.util.Random;

public class AjoutExtraction {

    public static void main(String[] args) {
        int i, insertion = 0, extraction = 0;
        // Tableau dynamique.
        //ArrayList<Integer> a = new ArrayList<Integer>();
	    //TasBinaire tas=new TasBinaire(1000000);
        BinomialTasMin tas=new BinomialTasMin();

        // Analyse du temps pris par les opérations.
        Analyzer time_analysis = new Analyzer();
        // Analyse du nombre de copies faites par les opérations.
        Analyzer lien_analysis = new Analyzer();
	    // Analyse de l'espace mémoire inutilisé.
	    Analyzer memory_analysis = new Analyzer();
        long before, after;
        // Booléen permettant de savoir si une allocation a été effectuée.
        int lien;

        Random rand= new Random();

        double test, p=0.5;
           

        for(i = 0; i < 100000 ; i++){
            test=rand.nextDouble();
            if (test<=p){
                //System.err.println("Pour "+i+" insertion");
                 // Ajout d'un élément et mesure du temps pris par l'opération.
                before = System.nanoTime();
                lien = tas.ajouterCle(i);
                after = System.nanoTime();
                insertion++;
                 // Enregistrement du temps pris par l'opération
            time_analysis.append(after - before);
            // Enregistrement du nombre de copies efféctuées par l'opération.
            // S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
            lien_analysis.append( (lien != 0)? lien : 0);
           // Enregistrement de l'espace mémoire non-utilisé.
           memory_analysis.append(tas.capacity() - tas.size());
            }else{
                //System.err.println("Pour "+i+" extraction");

                 // Ajout d'un élément et mesure du temps pris par l'opération.
                before = System.nanoTime();
                lien = tas.extractMin();
                after = System.nanoTime();
                extraction++;

                 // Enregistrement du temps pris par l'opération
            time_analysis.append(after - before);
            // Enregistrement du nombre de copies efféctuées par l'opération.
            // S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
            lien_analysis.append( (lien != 0)? lien : 0);
           // Enregistrement de l'espace mémoire non-utilisé.
           memory_analysis.append(tas.capacity() - tas.size());
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
        time_analysis.save_values("plot/tas_binomial_time_java.plot");
        lien_analysis.save_values("plot/tas_binomial_lien_java.plot");
	    memory_analysis.save_values("plot/tas_binomial_memory_java.plot");
    }
}
