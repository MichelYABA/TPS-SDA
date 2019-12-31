import java.util.Random;

public class AjoutExtraction {

    public static void main(String[] args) {
        int i, insertion = 0, extraction = 0;
        // Tableau dynamique.
        //ArrayList<Integer> a = new ArrayList<Integer>();
	    //TasBinaire tas=new TasBinaire(1000000);
        AVLTree avl=new AVLTree();

        // Analyse du temps pris par les opérations.
        Analyzer time_analysis = new Analyzer();
        // Analyse du nombre de copies faites par les opérations.
       
	    // Analyse de l'espace mémoire inutilisé.
	    Analyzer memory_analysis = new Analyzer();
        long before, after;
        // Booléen permettant de savoir si une allocation a été effectuée.
        

        Random rand= new Random();

        double test, p=0.8;
        int n_aleat; 

        for(i = 0; i < 1000000 ; i++){
            n_aleat = (int)(Math.random()*2);

            before = System.nanoTime();
            //j = (int)(Math.random() * 2);
            if(n_aleat==1){
               avl.root=avl.insert(avl.root, i);
               insertion++;
            }
            else {
                avl.root=avl.deleteNode(avl.root, i);
                extraction++;
            }
            after = System.nanoTime();
//memoire_allocation=true;
            time_analysis.append(after - before);
            memory_analysis.append(avl.getCapacity() - avl.getSize() );
            
        }

        // Affichage de quelques statistiques sur l'expérience.
        System.err.println("Nombre d'insertions : "+insertion);
        System.err.println("Nombre d'extraction : "+extraction);
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