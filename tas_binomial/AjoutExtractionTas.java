import java.util.Random;

public class AjoutExtractionTas {

    public static void main(String[] args) {
        int i, insertion_1 = 0, extraction_1 = 0, insertion_2=0, extraction_2=0;
        // Tableau dynamique.
        //ArrayList<Integer> a = new ArrayList<Integer>();
	    //TasBinaire tas=new TasBinaire(1000000);
        BinomialTasMin tas_1=new BinomialTasMin();
         BinomialTasMin tas_2=new BinomialTasMin();
         BinomialTasMin tas_3=new BinomialTasMin();
         BinomialTasMin tas_4=new BinomialTasMin();

        // Analyse du temps pris par les opérations.
        Analyzer time_analysis_1 = new Analyzer();
         Analyzer time_analysis_2 = new Analyzer();

        // Analyse du nombre de copies faites par les opérations.
        Analyzer lien_analysis_1 = new Analyzer();
        Analyzer lien_analysis_2 = new Analyzer();

	    // Analyse de l'espace mémoire inutilisé.
	    Analyzer memory_analysis_1 = new Analyzer();
        Analyzer memory_analysis_2 = new Analyzer();
        long before_1, before_2, after_1, after_2;
        // Booléen permettant de savoir si une allocation a été effectuée.
        int lien_1, lien_2;

        Random rand= new Random();

        double test, p=0.9;
           

        for(i = 0; i < 100000 ; i++){
            test=rand.nextDouble();
            if (test<=p){
                //System.err.println("Pour "+i+" insertion");
                 // Ajout d'un élément et mesure du temps pris par l'opération.
                before_1 = System.nanoTime();
                tas_1.ajouterCle(i);
                tas_2.ajouterCle(i);
                tas_3.ajouterCle(i);
                tas_4.ajouterCle(i);
                after_1 = System.nanoTime();
                insertion_1++;
                 // Enregistrement du temps pris par l'opération
            time_analysis_1.append(after_1 - before_1);
            // Enregistrement du nombre de copies efféctuées par l'opération.
            // S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
           // lien_analysis_1.append( (lien_1 != 0)? lien_1 : 0);
           // Enregistrement de l'espace mémoire non-utilisé.
           memory_analysis_1.append(tas_1.capacity() - tas_1.size());
            }else{
                //System.err.println("Pour "+i+" extraction");

                 // Ajout d'un élément et mesure du temps pris par l'opération.
                before_1 = System.nanoTime();
                tas_1.unionTas(tas_2);
                tas_1.unionTas(tas_3);
                tas_1.unionTas(tas_4);
               
                
                after_1 = System.nanoTime();
                extraction_1++;

                 // Enregistrement du temps pris par l'opération
            time_analysis_1.append(after_1 - before_1);
            // Enregistrement du nombre de copies efféctuées par l'opération.
            // S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
           // lien_analysis_1.append( (lien_1 != 0)? lien_1 : 0);
           // Enregistrement de l'espace mémoire non-utilisé.
           memory_analysis_1.append(tas_1.capacity() - tas_1.size());
            }
           
           
        }

        /*for(i = 0; i < 10000 ; i++){
            test=rand.nextDouble();
            //if (test<=p){
                //System.err.println("Pour "+i+" insertion");
                 // Ajout d'un élément et mesure du temps pris par l'opération.
                before_2 = System.nanoTime();
                lien_2 = tas_2.unionTas(tas_1);
                after_2 = System.nanoTime();
                insertion_2++;
                 // Enregistrement du temps pris par l'opération
            time_analysis_2.append(after_2 - before_2);
            // Enregistrement du nombre de copies efféctuées par l'opération.
            // S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
            lien_analysis_2.append( (lien_2 != 0)? lien_2 : 0);
           // Enregistrement de l'espace mémoire non-utilisé.
           memory_analysis_2.append(tas_2.capacity() - tas_2.size());
            }else{
                //System.err.println("Pour "+i+" extraction");

                 // Ajout d'un élément et mesure du temps pris par l'opération.
                before_2 = System.nanoTime();
                lien_2 = tas_2.extractMin();
                after_2 = System.nanoTime();
                extraction_2++;

                 // Enregistrement du temps pris par l'opération
            time_analysis_2.append(after_2 - before_2);
            // Enregistrement du nombre de copies efféctuées par l'opération.
            // S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
            lien_analysis_2.append( (lien_2 != 0)? lien_2 : 0);
           // Enregistrement de l'espace mémoire non-utilisé.
           memory_analysis_2.append(tas_2.capacity() - tas_2.size());
            }*/
           
           
       // }

        // Affichage de quelques statistiques sur l'expérience.
        //System.err.println("Pour le Tas 1 : ");
        System.err.println("Nombre d'insertions : "+insertion_1);
        System.err.println("Nombre d'extraction : "+extraction_1);
        System.err.println("Total cost : "+time_analysis_1.get_total_cost());
        System.err.println("Average cost : "+time_analysis_1.get_average_cost());
        System.err.println("Variance :"+time_analysis_1.get_variance());
        System.err.println("Standard deviation :"+time_analysis_1.get_standard_deviation());

       /* System.err.println("Pour le Tas 2 : ");
        System.err.println("Nombre d'insertions : "+insertion_2);
        System.err.println("Nombre d'extraction : "+extraction_2);
        System.err.println("Total cost : "+time_analysis_2.get_total_cost());
        System.err.println("Average cost : "+time_analysis_2.get_average_cost());
        System.err.println("Variance :"+time_analysis_2.get_variance());
        System.err.println("Standard deviation :"+time_analysis_2.get_standard_deviation());*/

        // Sauvegarde les données de l'expérience: temps et nombre de copies effectuées par opération.
        time_analysis_1.save_values("plot/plot1/tas_binomial_time_java.plot");
        lien_analysis_1.save_values("plot/plot1/tas_binomial_lien_java.plot");
	    memory_analysis_1.save_values("plot/plot1/tas_binomial_memory_java.plot");

        /*time_analysis_2.save_values("plot/plot2/tas_binomial_time_java.plot");
        lien_analysis_2.save_values("plot/plot2/tas_binomial_lien_java.plot");
        memory_analysis_2.save_values("plot/plot2/tas_binomial_memory_java.plot");*/
   
}
}
