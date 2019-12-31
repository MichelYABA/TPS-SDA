import java.util.Random;

public class FusionTas {

    public static void main(String[] args) {
        int i,j;
        // Tableau dynamique.
        //ArrayList<Integer> a = new ArrayList<Integer>();
	    //TasBinaire tas=new TasBinaire(1000000);
        BinomialTasMin tas_1=new BinomialTasMin(100000);
         BinomialTasMin tas_2=new BinomialTasMin(100000);
         BinomialTasMin tas = new BinomialTasMin();


        // Analyse du temps pris par les opérations.
        Analyzer time_analysis = new Analyzer();
      

        // Analyse du nombre de copies faites par les opérations.
        Analyzer lien_analysis = new Analyzer();
      

	    // Analyse de l'espace mémoire inutilisé.
	    Analyzer memory_analysis = new Analyzer();
        long before, after;
        // Booléen permettant de savoir si une allocation a été effectuée.
        int lien;
           

        for(i = 0; i < 100000 ; i++){
           
                //System.err.println("Pour "+i+" insertion");
                 // Ajout d'un élément et mesure du temps pris par l'opération.
                
                tas_1.ajouterCle(i);
               
           
            }
           
        

        for(j = 100000; j >0 ; j--){
            
                //System.err.println("Pour "+i+" insertion");
                 // Ajout d'un élément et mesure du temps pris par l'opération.
               
                tas_2.ajouterCle(j);
               
                 // Enregistrement du temps pris par l'opération
           
            }

        before = System.nanoTime();
        lien = tas_1.unionTas(tas_2);
        after = System.nanoTime();

         time_analysis.append(after - before);
            // Enregistrement du nombre de copies efféctuées par l'opération.
            // S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
            lien_analysis.append( (lien != 0)? lien : 0);
           // Enregistrement de l'espace mémoire non-utilisé.
           memory_analysis.append(tas.capacity() - tas.size());
           
           
        

        // Affichage de quelques statistiques sur l'expérience.
        
        
        System.err.println("Total cost : "+time_analysis.get_total_cost());
        System.err.println("Average cost : "+time_analysis.get_average_cost());
        System.err.println("Variance :"+time_analysis.get_variance());
        System.err.println("Standard deviation :"+time_analysis.get_standard_deviation());

       

        time_analysis.save_values("plot/tas_binomial_time_java.plot");
        lien_analysis.save_values("plot/tas_binomial_lien_java.plot");
        memory_analysis.save_values("plot/tas_binomial_memory_java.plot");
   
}
}
