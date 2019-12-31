import java.util.Iterator;
import java.util.Comparator;
import java.util.NoSuchElementException;


public class TasBinomialMin implements Iterable<Integer> {
	public Noeud tete;   //tête de la liste des racines
	//private Comparator<T> comp;	//Compare les clés
	private int size;
	private int capacite;
	//Représente un noeud de l'arbre binomial
	
	
	public TasBinomialMin() {
		tete=null;
		size=0;
		//capacite=10;
	}

	public TasBinomialMin(int c){
		tete=null;
		size=0;
		capacite = c;
	}

	public TasBinomialMin(int[] a) {
		//comp = new MyComparator();
		for (int k : a) 
			ajouterCle(k);
	}

	public int capacity(){
		return capacite;
	}

	public int getSize(){
		return size;
	}
	
	
	/*public TasBinomialMin(Comparator<T> C) {
		comp = C;
	}*/
	
	
	
	
	
	/*public TasBinomialMin(Comparator<T> C, T[] a) {
		comp = C;
		for (T k : a) 
			ajouterCle(k);
	}*/

	//Vérifie si la file de priorité est vide
	public boolean estVide() {
		return tete == null;
	}

	// Nombre d'éléments courants dans la file de priorité
	// Complexité : O(log(n))
	// Une exception est renvoyée s'il y a plus que 2E63-1 éléments dans la file
	// retourne le nombre d'éléments dans la file de priorité
	public int size() {
		int result = 0, tmp;
		for (Noeud noeud = tete; noeud != null; noeud = noeud.frere) {
			if (noeud.degre > 30) { 
				throw new ArithmeticException("Le nombre d'éléments ne peut être évalué, but the priority queue is still valid."); 
			}
			tmp = 1 << noeud.degre;
			result |= tmp;
		}
		return result;
	}


	//Compare 2 clés
	/*private boolean plusGrand(T n, T m) {
		if (n == null) return false;
		if (m == null) return true;
		return comp.compare(n, m)>0;

		//return (n>m);

	}*/

	
	
	//permet d'obtenir la clé minimale courante dans la file
	// Complexité : O(log(n))
	// Retourne la clé minimale courante dans la file de priorité
	public int minTCle() {
		if (estVide()) 
			throw new NoSuchElementException("Le tas est vide");
		Noeud min = this.tete;
		Noeud courant = this.tete;
		while (courant.frere != null) {
			min = (courant.frere.cle < min.cle) ? courant : min;
			courant = courant.frere;
		}
		return min.cle;
	}


	//Trouver clé minimale
	/*public int minimumTasBinomial(){
		Noeud y=null;
		Noeud x=this.tete;

		Integer min = Integer.MAX_VALUE;

		while (x != null){
			if (x.cle < min){
				min=x.cle;
				y=x;
			}
			x=x.frere;
		}
		return y.cle;
	}*/

	//Supprime et extrait le noeud contenant la clé minimale
	private Noeud extraireMin() {
		Noeud min = this.tete;
		Noeud precedent = null;
		Noeud courant = this.tete;
		while (courant.frere != null) {
			if (courant.frere.cle < min.cle) {
				precedent = courant;
				min = courant.frere;
			}
			courant = courant.frere;
		}
		precedent.frere = min.frere;
		if (min == tete) 
			tete = min.frere;
		return min; 
	}

	//Supprimes la clé minimale d'un tas
	public int delMin() {
		if(estVide()) 
			throw new NoSuchElementException("le tas est vide");
		Noeud min = extraireMin();
		Noeud x = (min.enfant == null) ? min : min.enfant;
		if (min.enfant != null) {
			min.enfant = null;
			Noeud avant_x = null, apres_x = x.frere;
			while (apres_x != null) {
				x.frere = avant_x;
				avant_x = x;
				x = apres_x;apres_x = apres_x.frere;
			}
			x.frere = avant_x;
			TasBinomialMin H = new TasBinomialMin();
			H.tete = x;
			tete = unionTasBinomiaux(H).tete;
		}
		return min.cle;
	}

	//Mets une clé dans un tas
	// Complexité : O(log(n))
	public void ajouterCle(int valeur) {
		//if (valeur >0){

			/*Noeud x = new Noeud();
			x.cle =valeur ;
			x.degre = 0;
			TasBinomialMin tas = new TasBinomialMin(); //The Comparator oh the H heap is not used
			tas.tete = x;
			this.tete = this.unionTasBinomiaux(tas).tete;*/

			Noeud temp = new Noeud (valeur);
			if (this.tete == null){
				this.tete=temp;
				size=1;
			}else{
				
			}
			size++;
		//}
		
	}

	//fusionne 2 racines des arbres, there can be up to 2 Binomial Trees of same degre
    private Noeud fusionnerArbre(Noeud x, Noeud y) {
      	Noeud h= new Noeud();
        	if (x == null && y == null) 
            	return h;
            else if (x == null){
            	h=y;
            	h.frere = fusionnerArbre(null, y.frere);
            } 	
            else if (y == null) {
            	h=x;
            	h.frere = fusionnerArbre(x.frere, null);
            }
            else if (x.degre < y.degre) {
            	h=x;
            	h.frere = fusionnerArbre(x.frere, y);
            }
            else   {
            	h=y;
            	h.frere = fusionnerArbre(x, y.frere);
            }                     
            	

            return h;
	}

	//Fusion de 2 arbres
	/*private Noeud fusionner(Noeud t1, Noeud t2){
		if (t1.cle > t2.cle){
			Noeud temp=t1;
			t1=t2;
			t2=temp;
		}
		t2.pere=t1;
		t2.frere=t1.enfant;
		t1.enfant=t2;
		t1.degre++;

		return t1;
	}*/

	//Supposons que le noeud root1 est plus grand que le noeud root2
	// root2 devient la nouvelle racine
	private void lienBinomial(Noeud root1, Noeud root2) {
		root1.frere = root2.enfant;
		root2.enfant = root1;
		root2.degre++;
	}
	
	
	//Fusionne 2 tas binomiaux
	//Complexité : O(log(n))
	// Retourne les tas unis
	public TasBinomialMin unionTasBinomiaux(TasBinomialMin tas) {
		
		if (tas == null) 
			throw new IllegalArgumentException("Ne peut pas fusionner 2 tas null");
		
		Noeud fusion=fusionnerArbre(this.tete, tas.tete);
		this.tete = fusion.frere;

		if (this.tete == null)
			return this;
		
		Noeud x = this.tete;
		Noeud avant_x = null;
		Noeud apres_x = x.frere;
		
		while (apres_x != null) {
			if ((x.degre != apres_x.degre) ||
			   ((apres_x.frere != null) && (apres_x.frere.degre == x.degre))) {
				avant_x = x; 
				x = apres_x;
			} else if (x.cle<=apres_x.cle) {
				x.frere = apres_x.frere;
				lienBinomial(apres_x, x);
			} else {
				if (avant_x == null) { 
					this.tete = apres_x; 
				}
				else { 
					avant_x.frere = apres_x; 
				}
				lienBinomial(x, apres_x);
				x = apres_x;
			}
			apres_x = x.frere;
		}
		return this;
	}

	
	
	public Iterator<Integer> iterator() {
		return new MyIterator(this);
	}

	void printArbre(Noeud n){
		if (n != null){
			System.err.println(n.cle);
			this.printArbre(n.enfant);
			this.printArbre(n.frere);

			//n=n.frere;
		}
	}

	void printTas(TasBinomialMin t){
		//Iterator it=this.iterator();
		System.err.println("Tas : ");

        //while(it.hasNext()){
  			printArbre(t.tete);
  		//}
  		System.err.println();
	}


	
	

}
	
	/***************************
	 * Comparator
	 **************************/
	
	//default Comparator
	/*private class MyComparator implements Comparator<T> {
		
		public int compare(T cle_1, T cle_2) {
			return ((Comparator<T>) cle_1).compareTo(cle_2);
		}
	}*/
	

