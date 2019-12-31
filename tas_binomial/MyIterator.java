import java.util.Iterator;
import java.util.NoSuchElementException;


 class MyIterator implements Iterator<Integer> {
		TasBinomialMin data;
		
		//Constructor clones recursively the elements in the queue
		//It takes linear time
		public MyIterator(TasBinomialMin tas) {
			//data = new TasBinomialMin<T>(comp);
			data = new TasBinomialMin();
			data.tete = clone(tas.tete, null);
		}
		
		private Noeud clone(Noeud x, Noeud parent) {
			if (x == null) 
				return null;
			Noeud noeud = new Noeud();
			noeud.cle = x.cle;
			noeud.frere = clone(x.frere, parent);
			noeud.enfant = clone(x.enfant, noeud);
			return noeud;
		}
		
		public boolean hasNext() {
			return !data.estVide();
		}
		
		public Integer next() {
            if (!hasNext()) 
            	throw new NoSuchElementException();
			return data.delMin();
		}
	
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}