public class Noeud{
	int tabCles[]; // table des clés
	int t; // degré minimum
	Noeud tabEnfants[]; //tableau des enfants
	int n, size; //nombre courant de clés
	boolean feuille;
	int capacite; //maximum des clés par noeud

	//Constructeur

	public Noeud(){

	}
	// Initialisation des attributs
	public Noeud(int _t, boolean _feuille){
		t=_t;
		feuille=_feuille;
		capacite = 2*t-1;
		tabCles = new int[capacite]; //allocation mémoire pour le maximum de clés possibles par noeud
		tabEnfants = new Noeud[2*t]; //maximum d'enfants par noeuds
		//Initialisation du nombre de clés courant à 0
		n=0;
		size=0;
	}

	public int capacity(){
		return capacite;
	}

	public int size(){
		return n;
	}

	/*
	Fonction qui parcourt tous les noeuds dans un arbre en partant de la racine
	*/
	public void parcoursNoeud(){
		/*
		il y' a n clés et n+1 enfants.
		Le parcours se fait en fonction du nombre des n clés et de ses premiers n enfants
		*/
		int i;
		for (i=0; i<n; i++){
			if (feuille==false){
				/*
				Si ce n'est pas une feuille, on parcourt le sous arbre avant d'afficher la clé
				*/
				tabEnfants[i].parcoursNoeud();
				
			}
			System.err.print(" | "+tabCles[i]);
		}
			//On affiche le sous arbre de la racine avec le dernier enfant
			if (feuille==false)
				tabEnfants[i].parcoursNoeud();

	}

	/*
	Fonction pour rechercher une clé k dans un sous-arbre enraciné avec ce noeud
	*/
	public Noeud rechercheCle(int k){
		//On trouve la première clé la plus grande ou égale à k
		int i=0;
		while (i<n && k>tabCles[i])
			i++;

		//Si la clé trouvé est égale à k, on retourne ce noeud
		if (tabCles[i]==k)
			return this;

		//si la clé n'est pas trouvée dans ce noeud et c'est une feuille
		if (feuille==true)
			return null;

		//On va sur l'enfant approprié
		return tabEnfants[i].rechercheCle(k);
	}

	/*
	Fonction pour insérer une nouvelle clé dans ce noeud.
	La condition est que le noeud doit être non-plein quand la fonction est appelée
	*/
	public void insertionNoeudNonPlein(int k){
		//On initialise l'index avec l'index de l'élément le plus à droite
		int i=n-1;

		//Si c'est une feuille
		if (feuille == true){
			/*
			La boucle suivante fait 2 choses :
			a) elle cherche la position de la nouvelle clé à insérer
			b) elle déplace les clés de plus grande valeur en tête du tableau
			*/
			while (i>=0 && tabCles[i]>k){
				tabCles[i+1]=tabCles[i];
				i--;
			}

			// On insère la nouvelle clé à la position trouvée
			tabCles[i+1] = k;
			n=n+1;
		}else{ //Si ce noeud n'est pas une feuille
			//On trouve l'enfant qui recevoir la nouvelle clé
			while (i>=0 && tabCles[i]>k)
				i--;

			//on  vérifie si l'enfant trouvé est plain, si c'est le cas on le divise
			if (tabEnfants[i+1].n == 2*t-1){
				diviserNoeud(i+1, tabEnfants[i+1]);

				/*Après avoir diviser le noeud Enfant, la clé du milieu du noeud enfant tabEnfants[i] 
				monte et tabEnfants[i] est divisé en 2. On regarde lequel des 2 noeuds obtenus, après division,
				qui va recevoir la nouvelle clé	
				*/
				if (tabCles[i+1]<k)
					i++;
			}
			tabEnfants[i+1].insertionNoeudNonPlein(k);
		
		}
	}

	//Fonction qui divise le noeud enfant y 
	public void diviserNoeud(int i, Noeud y){
		//On crée un nouveau noeud qui va stocker (t-1) clés de y
		Noeud z= new Noeud(y.t, y.feuille);
		z.n = t-1;

		//On copie les dernières (t-1) clés de y dans z
		for (int j=0; j<t-1; j++)
			z.tabCles[j] = y.tabCles[j+t];

		//On copie les derniers t enfants de y dans z
		if (y.feuille == false){
			for (int j=0; j<t; j++)
				z.tabEnfants[j] = y.tabEnfants[j+t];
		}

		//On reduit le nombre de clés dans y
		y.n = t-1;

		//A partir du noeud qui va recevoir le nouvel enfant, on réserve de l'espace pour le nouvel enfant;
		for (int j=n; j>=i+1; j--)
			tabEnfants[j+1]=tabEnfants[j];

		//On lie le nouvel enfant à ce noeud
		tabEnfants[i+1] = z;

		/*
		Une clé de y se déplacera dans ce noeud.
		On trouve la position de la nouvelle clé et on déplace les clés les plus grandes en tête
		*/
		for (int j=n-1; j>=i; j--)
			tabCles[j+1] = tabCles[j];

		//On copie la clé du milieu de y dans ce noeud
		tabCles[i] = y.tabCles[t-1];

		//On incrémente le nombre clés dans ce noeud
		n=n+1;
	}


/*
Les méthodes ci-dessous concernent la suppression d'une clé dans un b-arbre
*/
	public int trouverCle(int k){
		int idx=0;
		while(idx<n && tabCles[idx]<k)
			++idx;
		return idx;
	}

	

	public boolean verifCle(int k){
		//int idx=0;
		
		boolean val=false;
		/*while (idx<n){
			if (tabCles[idx]==k)
				return true;
			idx++;
		}
		return val;*/
		//while(idx<n && tabCles[idx] !=k)
		//	++idx;
		int idx=trouverCle(k);

		if (tabCles[idx]==k){
			val = true;

		}

		

		return val;
	}

	/*
	Cette fonction supprime une clé k d'un sous-arbre en partant de la racine de ce noeud
	*/
	public void supprimerCle(int k){
		int idx=trouverCle(k);

		//Si la clé à supprimer est présente dans ce noeud
		if (idx <= n && tabCles[idx]==k){

		// Si le noeud est une feuille, la fonction deplacerFeuille est appelé
		// Sinon la fonction deplacerNonFeuille est appelé
			if (feuille)
				deplacerDeLaFeuille(idx);
			else
				deplacerDuNonFeuille(idx);

		}else{ //Si ce noeud est une feuille et la clé n'est pas présente dans l'arbre

			/*if (feuille){
				System.out.println("La clé "+ k +" ne se trouve pas dans larbre");
				//return;
			}*/

			//Si la clé à supprimer est présente dans le sous-arbre enraciné avec ce noeud
			//boolean
			
			boolean flag=(idx==n) ? true : false;

			if (tabEnfants[idx].n <t)
				remplir(idx);

			if (flag && idx >n)
				tabEnfants[idx - 1].supprimerCle(k);
			else
				tabEnfants[idx].supprimerCle(k);
		}
		//return;

	}

	public boolean delete(int value) {
        // look up for a key k that will be deleted
        int i = 1;
        // find the smallest index i such that k<= x.keyi, or else set i to x.n ++
        while (i <= n && value > tabCles[i - 1]) {
            //increment i
            i++;
        }
        
        if (feuille) {
            // recursive call to search in the subtree of the node x
            // check if the key k is found
            if (i <= n && value == tabCles[i - 1]) {
                // return true if the key k is found in the B Tree
                tabCles[i - 1] = 0;
                // Deleting and sifting
                for(int j = i - 1; j < n - 1; j++){
                    tabCles[j] = tabCles[j+1];
                    if(j+1 == n - 1)
                        n--;                    
                }
                size--;
                return true;
            }
        } else {
            return this.tabEnfants[i-1].delete(value);
        }
        // return false if the key k is not found in the B Tree
        return false;
    }




	public void deplacerDeLaFeuille(int idx){
		for (int i=idx+1; i<n; ++i)
			tabCles[i-1]=tabCles[i];

		n--;

		//return;
	}

	public void deplacerDuNonFeuille(int idx){
		int k=tabCles[idx];

		if (tabEnfants[idx].n >= t){
			int pred = getPrecedent(idx);
			tabCles[idx] = pred;
			tabEnfants[idx].supprimerCle(pred);
		}

		else if (tabEnfants[idx].n >= t){
			int succ = getSuccesseur(idx);
			tabCles[idx] = succ;
			tabEnfants[idx+1].supprimerCle(succ);
		}

		else{
			fusionner(idx);
			tabEnfants[idx].supprimerCle(k);
		}
		//return;
	}

	//Cette fonction permet d'obtenir la clé précédent une clé donnée
	public int getPrecedent(int idx){
		Noeud courant = tabEnfants[idx];
		while (!courant.feuille)
			courant = courant.tabEnfants[courant.n];

		return courant.tabCles[courant.n-1];
	}

	public int getSuccesseur(int idx){
		Noeud courant = tabEnfants[idx + 1];

		while (!courant.feuille)
			courant = courant.tabEnfants[0];

		return courant.tabCles[0];
	}

	public void remplir(int idx){
		//SI l'enfant précédent a plus de t-1 clés, on emprunte une clé de cet enfant
		if (idx !=0 && tabEnfants[idx - 1].n >=t)
			emprunterDuPrecedent(idx);

		else if (idx !=n && tabEnfants[idx +1].n >= t)
			emprunterDuProchain(idx);
		else{
			if (idx !=n)
				fusionner(idx);
			else
				fusionner(idx - 1);
		}
		//return;

	}


	public void emprunterDuPrecedent(int idx){
		Noeud enfant=tabEnfants[idx];
		Noeud frere=tabEnfants[idx-1];

		for (int i=enfant.n - 1; i>=0; --i)
			enfant.tabCles[i+1]= enfant.tabCles[i];

		if (!enfant.feuille){
			for (int i=enfant.n; i>=0; --i)
				enfant.tabEnfants[i+1] = enfant.tabEnfants[i];
		}

		enfant.tabCles[0] = tabCles[idx - 1];

		if (!enfant.feuille)
			enfant.tabEnfants[0] = frere.tabEnfants[frere.n];

		tabCles[idx - 1] = frere.tabCles[frere.n-1];

		enfant.n +=1;
		frere.n -=1;

		//return;
	}

	public void emprunterDuProchain(int idx){
		Noeud enfant=tabEnfants[idx];
		Noeud frere=tabEnfants[idx+1];

		enfant.tabCles[enfant.n] = tabCles[idx];

		if (!enfant.feuille)
			enfant.tabEnfants[enfant.n+1] = frere.tabEnfants[0];

		tabCles[idx] = frere.tabCles[0];


		for (int i=1; i<frere.n; ++i)
			frere.tabCles[i-1]= frere.tabCles[i];

		if (!frere.feuille){
			for (int i=1; i<=frere.n; ++i)
				frere.tabEnfants[i-1] = frere.tabEnfants[i];
		}		

		enfant.n +=1;
		frere.n -=1;

		//return;
	}

	public void fusionner(int idx){
		Noeud enfant = tabEnfants[idx];
		Noeud cible = tabEnfants[idx + 1];

		enfant.tabCles[t - 1]= tabCles[idx];

		for (int i=0; i<cible.n; ++i)
			enfant.tabCles[i+t]=cible.tabCles[i];

		if (!enfant.feuille){
			for (int i=0; i<=cible.n; ++i)
				enfant.tabCles[i+t] = cible.tabCles[i];
		}

		for (int i=idx+1; i<n; ++i)
			tabCles[i-1] = tabCles[i];

		for (int i=idx+2; i<=n; ++i)
			tabEnfants[i-1]=tabEnfants[i];

		enfant.n +=cible.n+1;

		n--;

		//On  libère la mémoire occupée par la cible
		//delete(cible);
		cible=null;
		//return;
	}
}