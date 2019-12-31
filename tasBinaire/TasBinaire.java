class TasBinaire{
	private int[] tabCle; // Tableau contenant les différentes clés
	private int capacite; //capacité du tableau
	private int nb_elt, perm; //taille courante du tableau
	private int pt=0;
	
	//Initialise le tas binaire avec la capacité donnée en entrée
	public TasBinaire(int c){
		nb_elt=0;
		//perm=0;
		capacite=c;
		tabCle = new int[c];
		
	}

	//Initialise la file de priorité avec une taille de 1
	public TasBinaire(){
		this(1);
	}


	public int nb_permutation(){
		return perm;
	}

	//retourne le nombre de clés dans le tas
	public int size(){
		return nb_elt;
	}

	public int capacite(){
		return capacite;
	}

	//renvoie l'indice du pere d'un noeud
	public int pere(int i){
		return (i-1)/2;
	}

	//renvoie l'indice du fils gauche
	public int gauche(int i){
		return ((2*i)+1);
	}	

	//renvoie l'indice du fils droit
	public int droit(int i){
		return ((2*i) + 2);
	}

	//retourne la plus petite clé du tas
	// Complexité : O(1)
	public int min(){
		return tabCle[0];
	}
	
	/*
	Cette procédure permet de faire monter la valeur de TabCle[i]
	dans un tas min, de sorte que le sous-arbre enraciné au noeud i
	devienne un tas min.
	Temps d'exécution : O(log nb_elt)
	*/
	public int entasser(int i){
		int g = gauche(i);
		int d = droit(i);
		int min, p=0;

		if (g<=nb_elt && tabCle[g] < tabCle[i])
			min = g;
		else
			min = i;

		if (d<=nb_elt && tabCle[d] < tabCle[i])
			min = d;

		if (min != i){
			int temp = tabCle[i];
			tabCle[i]=tabCle[min];
			tabCle[min]=temp;
			p++;
			p+=entasser(min);
		}

		return p;

	}

	/*public void construireTas(){
		//int moitie=this.tabCle.length/2;
		for (int i=tabCle.length/2; i=0; i--){
			this.entasser(i);
		}
	}*/


	/*Extraire l'élément  de priorité minimale
	Complexité : O(log n_elt) car elle n'effectue qu'un volume 
	constant de travail en plus du temps O(log n_elt) de entasser*/
	public int extraireMin(){
		//int permutation=true;
		int min;
		//int nb_elt=tabCle.size;
		if (nb_elt <=0)
			return 0;
			//throw new RuntimeException("La file est vide");
		//else{
			min = tabCle[0];
			tabCle[0] = tabCle[nb_elt-1];
			nb_elt--;
		//}

		/*if (nb_elt == 1){
			nb_elt--;
			//memory_allocation=false;
			//return tabCle[0];
			//return memory_allocation;
		}*/

		
		

		//return racine;
		return entasser(0);
	}

	/*
	Cette fonction diminuer la valeur d'une clé donnée dans le tas
	Complexixté : O(log n_elt) car le chemin reliant le noeud modifié à la racine
	a la longueur O(log n_elt)
	*/
	public int diminuerCle(int i, int cle){
		//int per=0;
		int permutation=0;
		/*if (k>tabCle[i])
			throw new RuntimeException("La nouvelle valeur est plus grande que la valeur actuelle !!!!");*/
		//int x=nb_elt - 1;

		if (cle > tabCle[i])
			throw new RuntimeException("La nouvelle valeur est plus grande que la valeur actuelle !!!!");
			
		

		tabCle[i]=cle;
		//Première insertion d'une clé dans une table
		/*int ins=nb_elt-1;
		tabCle[ins]=cle;*/
		
		//tabCle[i]=k;
		int p=pere(i);

		//Parcours du chemin reliant un noeud à la racine en comparant
		// un élément de son parent, permute les clés puis continue si 
		//la clé de l'élément est plus petit
		//Pour trouver la place idéale de la valeur du noeud modifié
		while (i>0 && tabCle[p]>tabCle[i]){
			int temp = tabCle[i];
			tabCle[i] = tabCle[p];
			tabCle[p]=temp;
			i = pere(i);
			permutation++;
		}

		return permutation;
	}


	/*
	Fonction pour insérer une clé dans un tas

	*/
	public int inserer(int cle){
		//On vérifie si le tableau est plein
		
		//if (nb_elt==capacite)
		//	throw new RuntimeException("Ne peut pas ajouter une clé");

		//On fait de la place à l'élément qu'on veut insérer
		//en incrémentant le compteur du nombre d'élement dans le tas
		nb_elt++;
		
		return diminuerCle(nb_elt, cle);

	}

	/*void echanger(int x, int y) { 
    	int temp = x; 
    	x = y; 
    	y = temp; 
	} */

	/*int insererCle(int k) { 
		
		boolean permutation=false;
		//int permut;
    // First insert the new key at the end 
		if (nb_elt==capacite)
			throw new RuntimeException("Ne peut pas ajouter une clé"); 

    	nb_elt++; 
    	int i = nb_elt - 1; 
    	tabCle[i] = k; 

    	int p=pere(i);
  		
  		//for (int j=0; j<nb_elt; j++){
  		int permut=0;	
		    // Fix the min heap property if it is violated 
		    while (i > 0 && tabCle[p] > tabCle[i] )
		    { 
		      //echanger(tabCle[i] , tabCle[p]); 
		        int temp = tabCle[i];
		    	int x, y;
		    	x= tabCle[p];
		    	y=tabCle[i];
	    		tabCle[i] = x; 
	    		tabCle[p] = y; 
		       i = pere(i); 

		       permutation=true;
		       permut=permut+1;
		    }
		    return permut;
		//}

		//return 0;
	    
	} 	*/

	public int insert(int value) {
			int permute=0;

            if (nb_elt == tabCle.length)
                  throw new RuntimeException("La file est pleine");
            else {
                  nb_elt++;
                  int i=nb_elt - 1;
                  tabCle[i] = value;
                  permute=this.siftUp(i);     
            }
            return permute;
      }  

	private int siftUp(int nodeIndex) {
        int parentIndex, tmp, a=0;
        parentIndex = pere(nodeIndex);
        boolean permt=false;	
         
    //if (nodeIndex>1){
    	//for (int k=nb_elt; k==0; k--){
        	while ((nodeIndex != 0) && (tabCle[parentIndex] > tabCle[nodeIndex])) {
	            permt=true;
	            tmp = tabCle[parentIndex];
	            tabCle[parentIndex] = tabCle[nodeIndex];
	            tabCle[nodeIndex] = tmp;
	            parentIndex=pere(parentIndex);          
	           
	            if (permt)
	            	a++;
	           
	            a+=this.siftUp(parentIndex);
        	}
       //}
       return a;
                
    }

    /*  private void siftUp_2(int nodeIndex) {
        int parentIndex, tmp, a=0;
        parentIndex = pere(nodeIndex);
        boolean per=false;	
         
        for (int k=0; k<nb_elt; k++){
        	while (tabCle[parentIndex] > tabCle[nodeIndex]) {
	            per=true;
	            tmp = tabCle[parentIndex];
	            tabCle[parentIndex] = tabCle[nodeIndex];
	            tabCle[nodeIndex] = tmp;
	                       
	            this.siftUp(parentIndex);

        	}
        }         
               
                  
      }*/

      /*private boolean pereGrand(nodeIndex){
      	int parentIndex = pere(nodeIndex);
        return (tabCle[parentIndex] > tabCle[nodeIndex]);
      }*/

      
      // A utility function to print array of size n 
void printArray() 
{ 
    for (int i=0; i<nb_elt; ++i) 
    	System.out.print("|"+tabCle[i]);

} 

	/*
    int  ajouter(int x) {
            
            int c=0;
            nb_permutation=0;
               
                
                int i = nTas;
                tas.add(x);
                while (i > 0 && tas.get(pere(i)) > x) {
                    echanger(pere(i),i);
                    i = pere(i);
                    c++;
                }
            //tas[i] = x;
            nTas++;
           
           return c;
            }

	*/

    

} 