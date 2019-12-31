//import java.util.ArrayList;

class TasBinaire_Dynamic{

	ArrayListProxy<Integer> tabCle; // Tableau contenant les différentes clés
	int nb_elt; //taille courante du tableau
	
	public TasBinaire_Dynamic(int capacite){
		nb_elt=0;
		tabCle = new ArrayListProxy<Integer>(capacite);
		
	}

	public int size(){
		return nb_elt;
		//return 
	}

	public int capacite(){
		return tabCle.capacity();
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

		if (g<=nb_elt && tabCle.get(g) < tabCle.get(i))
			min = g;
		else
			min = i;

		if (d<=nb_elt && tabCle.get(d) < tabCle.get(i))
			min = d;

		if (min != i){
			int temp = tabCle.get(i);

			tabCle.set(i, tabCle.get(min));
			tabCle.set(min, temp);
			p++;
			p+=entasser(min);
		}

		return p;

	}

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
			min = tabCle.get(0);
			tabCle.set(0, tabCle.get(nb_elt-1));
			//tabCle[0] = tabCle[nb_elt-1];
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




	public void diminuer(int i, int k){
		int ins=nb_elt-1;
		int x=tabCle.get(ins);
		//x.add(k);
		//int ttabCle.get(ins)
		//x.append(k);
		
		//tabCle[i]=k;
		int p=pere(i);
		int t_p=tabCle.get(p);
		int t_i=tabCle.get(i);
		while (i!=0 && t_p>t_i){
			int temp = tabCle.get(i);
			t_i= tabCle.get(p);
			t_p=temp;
			i = pere(i);
		}

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

		if (cle > tabCle.get(i))
			throw new RuntimeException("La nouvelle valeur est plus grande que la valeur actuelle !!!!");
			
		//tabCle[i]=cle;
		tabCle.set(i, cle);
		
		//tabCle[i]=k;
		int p=pere(i);

		//Parcours du chemin reliant un noeud à la racine en comparant
		// un élément de son parent, permute les clés puis continue si 
		//la clé de l'élément est plus petit
		//Pour trouver la place idéale de la valeur du noeud modifié
		while (i>0 && tabCle.get(p)>tabCle.get(i)){
			int temp = tabCle.get(i);
			tabCle.set(i, tabCle.get(p));
			tabCle.set(p, temp);
			i = pere(i);
			permutation++;
		}

		return permutation;
	}


	
	public int insert(int value) {
			int permute=0;
			boolean allocation;

            nb_elt++;
            int i=nb_elt - 1;

            allocation=tabCle.append(value);
            //tabCle.set(i, value);
            
            permute=this.siftUp(i);     
           
            return permute;
      }  

	private int siftUp(int nodeIndex) {
        int parentIndex, tmp, a=0;
        parentIndex = pere(nodeIndex);
        boolean permt=false;	
         
    //if (nodeIndex>1){
    	//for (int k=nb_elt; k==0; k--){
        	while ((nodeIndex != 0) && (tabCle.get(parentIndex) > tabCle.get(nodeIndex))) {
	            permt=true;
	            tmp = tabCle.get(parentIndex);
	            tabCle.set(parentIndex, tabCle.get(nodeIndex));
	            tabCle.set(nodeIndex, tmp);
	            parentIndex=pere(parentIndex);          
	           
	            if (permt)
	            	a++;
	           
	            a+=this.siftUp(parentIndex);
        	}
       //}
       return a;
                
    }

    void printArray() 
{ 
    for (int i=0; i<nb_elt; ++i) 
    	System.out.print("|"+tabCle.get(i));

} 

	


} 