import java.util.NoSuchElementException;

public class BinomialTasMin{

	private Noeud tetes;
	private int size;
	private int capacite;

	//Constructeur
	public BinomialTasMin(){
		tetes = null;
		size = 0;
		capacite = 100000;
		//this(1);
	}

	public BinomialTasMin(int c){
		tetes = null;
		size = 0;
		capacite = c;
	}

	public BinomialTasMin(Noeud n){
		tetes=n;
	}


	//Vérifie s'il n'y a pas de noeuds de têtes
	public boolean estVide(){
		return tetes==null;
	}

	//permet d'obtenir la capacite du noeud
	public int capacity(){
		return capacite;
	}

	//permet d'obtenir le nombre d'élements courant dans le noeud
	public int size(){
		return size;
	}

	//supprime les noeuds du tas et les clés qui y sont
	public void makeVide(){
		tetes = null;
		size =0;
	}

	//Insère une clé dans un tas
	/*public int insererCle(int val){
		int l=0;
		if (val > 0){
			Noeud temp = new Noeud(val);

			if (tetes == null){
				tetes = temp;
				size = 1;
			}else{
				l+=unionTasBinomiaux(temp);
				size++;
			}
		}
		return l;
	}*/

	public int ajouterCle(int valeur) {
		int lien=0;
		if (valeur>0){
			Noeud x = new Noeud();
			x.cle =valeur ;
			x.degre = 0;
			if (tetes==null){
				tetes=x;
				size=1;
			}else{
				BinomialTasMin tas = new BinomialTasMin(); 
				tas.tetes = x;
				lien+=unionTas(tas);
				size++;
			}	
		}
		return lien;
	}


	//Fusionne 2 racines des arbres qui forment le tas
	/*private void fusionner(Noeud n){
		Noeud temp1=tetes, temp2=n;

		while ((temp1 != null) && (temp2 !=null)){
			if (temp1.degre == temp2.degre){
				Noeud tmp = temp2;
				temp2 = temp2.frere;
				tmp.frere = temp1.frere;
				temp1.frere=tmp;
				temp1=tmp.frere;
			}else{
				if (temp1.degre < temp2.degre){
					if ((temp1.frere == null) || (temp1.frere.degre > temp2.degre)){
						Noeud tmp = temp2;
						temp2 = temp2.frere;
						tmp.frere = temp1.frere;
						temp1.frere = tmp;
						temp1 = tmp.frere;
					}else{
						temp1 = temp1.frere;
					}
				}else{
					Noeud tmp = temp1;
					temp1 = temp2;
					temp2 = temp2.frere;
					temp1.frere = tmp;
					if (tmp == tetes){
						tetes = temp1;
					}
				}
			}
		}
		if (temp1 == null){
			temp1 = tetes;
			while (temp1.frere != null){
				temp1 = temp1.frere;
			}
			temp1.frere = temp2;
		}
	}*/

	//Supposons que le noeud noud1 est plus grand que le noeud root2
	// noeud2 devient la nouvelle racine
	private void lierNoeud(Noeud noeud1, Noeud noeud2){
		noeud1.pere = noeud2;
		noeud1.frere = noeud2.enfant;
		noeud2.enfant = noeud1;
		noeud1.degre++;
	}

	//Union des noeuds
	/*private int unionTasBinomiaux(Noeud nod){
		int lien=0;
		fusionner(nod);

		Noeud avant_x = null, x= tetes, apres_x = tetes.frere;

		while (apres_x != null){
			if ((x.degre != apres_x.degre) || ((apres_x.frere != null) && (apres_x.frere.degre == x.degre))){
				avant_x = x;
				x = apres_x;
			}else{
				if (x.cle <= apres_x.cle){
					x.frere = apres_x.frere;

					lierNoeud(apres_x, x);
					lien++;
					
				}else{
					if (avant_x == null)
						tetes = apres_x;
					else
						avant_x.frere = apres_x;

					lierNoeud(x, apres_x);
					
					lien++;
					x = apres_x;
				}
			}
			apres_x = x.frere;
		}

		return lien;
	}*/


//FOnction qui retourne la clé minimum
	public int getMinimum(){
		return tetes.trouverNoeudMinimum().cle;
	}

	private Noeud fusionnerArbre(Noeud h, Noeud x, Noeud y) {
      	//Noeud h= new Noeud();
        	if (x == null && y == null) 
            	return h;
            else if (x == null){
            	//h=y;
            	h.frere = fusionnerArbre(y, null, y.frere);
            } 	
            else if (y == null) {
            	//h=x;
            	h.frere = fusionnerArbre(x, x.frere, null);
            }
            else if (x.degre < y.degre) {
            	//h=x;
            	h.frere = fusionnerArbre(x, x.frere, y);
            }
            else   {
            	//h=y;
            	h.frere = fusionnerArbre(y, x, y.frere);
            }                     
            	

            return h;
	}



	public int unionTas(BinomialTasMin tas) {
		int l=0;
		
		if (tas == null) 
			throw new IllegalArgumentException("Ne peut pas fusionner 2 tas null");
		
		//Noeud fusion=fusionnerArbre(this.tete, tas.tete);
		tetes = fusionnerArbre(new Noeud(), tetes, tas.tetes).frere;

		/*if (this.tete == null)
			return this;*/
		
		Noeud x = this.tetes;
		Noeud avant_x = null;
		Noeud apres_x = x.frere;
		
		while (apres_x != null) {
			if ((x.degre != apres_x.degre) ||
			   ((apres_x.frere != null) && (apres_x.frere.degre == x.degre))) {
				avant_x = x; 
				x = apres_x;
			} else if (x.cle<=apres_x.cle) {
				x.frere = apres_x.frere;
				lierNoeud(apres_x, x);
				l++;
			} else {
				if (avant_x == null) { 
					this.tetes = apres_x; 
				}
				else { 
					avant_x.frere = apres_x; 
				}
				lierNoeud(x, apres_x);
				x = apres_x;
				l++;
			}
			apres_x = x.frere;
		}
		return l;
	}


	public BinomialTasMin unionTas_2(BinomialTasMin tas) {
		int l=0;
		
		if (tas == null) 
			throw new IllegalArgumentException("Ne peut pas fusionner 2 tas null");
		
		//Noeud fusion=fusionnerArbre(this.tete, tas.tete);
		tetes = fusionnerArbre(new Noeud(), tetes, tas.tetes).frere;

		/*if (this.tete == null)
			return this;*/
		
		Noeud x = this.tetes;
		Noeud avant_x = null;
		Noeud apres_x = x.frere;
		
		while (apres_x != null) {
			if ((x.degre != apres_x.degre) ||
			   ((apres_x.frere != null) && (apres_x.frere.degre == x.degre))) {
				avant_x = x; 
				x = apres_x;
			} else if (x.cle<=apres_x.cle) {
				x.frere = apres_x.frere;
				lierNoeud(apres_x, x);
				l++;
			} else {
				if (avant_x == null) { 
					this.tetes = apres_x; 
				}
				else { 
					avant_x.frere = apres_x; 
				}
				lierNoeud(x, apres_x);
				x = apres_x;
				l++;
			}
			apres_x = x.frere;
		}
		return this;
	}


//Supprime un élement donné du tas
	/*public void supprimerElement(int val){
		if ((tetes != null) && (tetes.trouverNoeudAvecCle(val) != null)){
			diminuerCle(val, getMinimum() - 1);
			extraireMin();
		}
	}*/

//Diminuer la clé avec une valeur donnée
	public void diminuerCle(int ancien, int nouvelle){
		Noeud temp = tetes.trouverNoeudAvecCle(ancien);
		if (temp == null)
			return;
		temp.cle = nouvelle;
		Noeud tempPere= temp.pere;

		while ((tempPere != null) && (temp.cle < tempPere.cle)){
			int z = temp.cle;
			temp.cle = tempPere.cle;
			tempPere.cle = z;

			temp = tempPere;
			tempPere = tempPere.pere;
		}
	}


	//Fonction pour afficher le tas
	public void afficherTas(){
		System.err.println("Tas : ");
		afficherNoeud(tetes);
		System.err.println();
	}

	//Affiche le noeud
	private void afficherNoeud(Noeud n){
		if (n != null){
			afficherNoeud(n.enfant);
			System.err.print(" | "+n.cle);
			afficherNoeud(n.frere);
		}
	}


	public Noeud extraireNoeud() {
		
		Noeud precedent = tetes;
		Noeud courant = tetes;
		int min = courant.cle;
		while (courant != null) {
			if (courant.cle < min) {
				precedent = courant;
				min = courant.cle;
			}
			courant = courant.frere;
		}
		/*precedent.frere = min.frere;
		if (min == tetes) 
			tetes = min.frere;*/
		return precedent; 
	}


	public int extraireMin() {
		int lien=0;
		/*if(estVide()) 
			throw new NoSuchElementException("le tas est vide");*/
		Noeud min = extraireNoeud();
		Noeud x = (min.enfant == null) ? min : min.enfant;
		if (min.enfant != null) {
			min.enfant = null;
			Noeud avant_x = null, apres_x = x.frere;
			while (apres_x != null) {
				x.frere = avant_x;
				avant_x = x;
				x = apres_x;
				apres_x = apres_x.frere;
			}
			x.frere = avant_x;
			BinomialTasMin tas= new BinomialTasMin();
			tas.tetes = x;
			lien+=unionTas(tas);
			//tetes=this.tetes;
		}
		//return min.cle;
		return lien;
	}

	int extractMin(){
		if (tetes == null)
			return 0;

		Noeud min = tetes;
		Noeud minPrev = null;
		Noeud next = min.frere;
		Noeud nextPrev = min;

		while (next != null){
			if (next.cle < min.cle){
				min = next;
				minPrev = nextPrev;
			}
			nextPrev = next;
			next = next.frere;
		}
		return removeTreeRoot(min, minPrev);
        //return min.cle;
	}

	int removeTreeRoot(Noeud root, Noeud prev) {
		int li=0;
        // Remove root from the heap
        if (root == tetes) {
            tetes = root.frere;
        } else {
            prev.frere = root.frere;
        }

        // Reverse the order of root's children and make a new heap
        Noeud newHead = null;
        Noeud child = root.enfant;
        while (child != null) {
            Noeud next = child.frere;
            child.frere = newHead;
            child.pere = null;
            newHead = child;
            child = next;
        }
        BinomialTasMin newHeap = new BinomialTasMin(newHead);

        // Union the heaps and set its head as this.head
        li+=unionTas(newHeap);
        return li;

    }



	

}