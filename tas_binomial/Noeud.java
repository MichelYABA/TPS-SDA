class Noeud {
		int cle;						//clé contenu dans un noeud
		int degre;						//le degré d'un arbre binomial commençant par ce noeud
		Noeud enfant, frere, pere;			//enfant and frere de ce noeud

		
		public Noeud(){

		}

		public Noeud(int c){
			cle=c;
			degre=0;
			pere=enfant=frere=null;
		}

		public Noeud reverse(Noeud f){
			Noeud n;
			if (frere != null)
				n=frere.reverse(this);
			else
				n = this.
			frere = f;
			return n;
		}

		//Fonction pour trouver le noeud minimum
		public Noeud trouverNoeudMinimum(){
			Noeud x= this, y=this;
			int min = x.cle;

			while (x!=null){
				if (x.cle < min){
					y = x;
					min = x.cle;
				}
				x=x.frere;
			}
			return y;
		}

		//Fonction pour trouver le noeud d'une clé donnée
		public Noeud trouverNoeudAvecCle(int valeur){
			Noeud temp=this, noeud = null;

			while (temp !=null){
				if (temp.cle == valeur){
					noeud = temp;
					break;
				}

				if (temp.enfant == null)
					temp=temp.frere;
				else{
					noeud = temp.enfant.trouverNoeudAvecCle(valeur);
					if (noeud == null)
						temp = temp.frere;
					else
						break;
				}
			}
			return noeud;
		}

		//Fonction pour obtenir la taille d'un noeud
		public int getSize(){
			return (1+((enfant == null) ? 0 : enfant.getSize()) + ((frere == null) ? 0 : frere.getSize()));
		}

}