public class B_Arbre{
	Noeud racine;
	int t; // degré minimal
	int capacite, size;

	//Constructeur
	B_Arbre(int _t){
		size=0;
		capacite=1000000;
		racine = null;
		t = _t;
	}

	public int capacity(){
		return capacite;
	}

	public int size(){
		return size;
	}

	//Fonction qui parcourt l'arbre
	public void parcoursArbre(){
		if (racine != null)
			racine.parcoursNoeud();

	}

	
	//Fonction qui recherche une clé dans un arbre
	public Noeud rechercheCleArbre(int k){
		return (racine == null) ? null : racine.rechercheCle(k);
	}

	//Fonction qui insère une nouvelle clé dans un b-arbre
	public void insertionCleArbre(int k){
		 boolean memory_allocation = false;
		if (racine == null){//Si l'arbre est vide
			//Allocation mémoire pour la racine
			racine = new Noeud(t, true);
			racine.tabCles[0]=k; //On insère la clé à l'indice 0 de la racine
			racine.n=1; //On met à jour le nombre de clés dans la racine
			size++;
		}
		else{//Si l'arbre n'est pas vide
			
			if (racine.n == 2*t-1){ //Si la racine est pleine, alors la hauteur de l'arbre est grande
				//Allocation mémoire pour la nouvelle racine
				Noeud s=new Noeud(t, false);

				//L'ancienne racine devient enfant de la nouvelle racine
				s.tabEnfants[0]=racine;

				//On divise l'ancienne racine puis on déplace une clé dans la nouvelle racine
				s.diviserNoeud(0, racine);

				//La nouvelle racine a 2 enfants maintenants. On choisit celui vers lequel ira la nouvelle clé
				int i=0;
				if (s.tabCles[0]<k)
					i++;
				s.tabEnfants[i].insertionNoeudNonPlein(k);

				//On change la racine
				racine = s;
				memory_allocation = true;
				size++;

			}else{ // Si la racine n'est pas pleine, on appelle directement insertionNoeudNonPlein pour la racine
				racine.insertionNoeudNonPlein(k);
				size++;
			}
		}
		//return memory_allocation ;
	}

	public void supprimerCleArbre(int k){
		//if (racine==null){

		//}
			//break;
			//System.err.println("L'arbre est vide");
			//return;
			//continue;
		//}
		//if (racine !=null)
			racine.supprimerCle(k);
		//appel de la fonction "supprimerCle" dans le noeud
		

		//Si la racine n'a pas de clé, on fait de son premier enfant
		//la nouvelle racine si cette dernière a un enfant
		//Sinon on met la racine à NULL
		/*if (racine.n==0){
			Noeud tmp = racine;
			//Si la racine est une feuille
			if (racine.feuille)
				racine = null;
			else
				racine = racine.tabEnfants[0];
			tmp=null;

		}*/
		//return;
	}

	public boolean delete(int k){
		//Noeud x=racine;
		return racine.delete(k);
	}

	
}