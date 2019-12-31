import java.util.Iterator;

public class Essai{
	
	public static void main(String[] args){
	int[] tableau={8,6,4,2,1,9,5,10,3,7};

		BinomialTasMin tas=new BinomialTasMin();

		for (int i=0; i<10; i++){
			tas.ajouterCle(tableau[i]);
		}

		tas.afficherTas();

		int minimum = tas.extractMin();

		System.err.println(minimum);

		//for (int i=0; i<10; i++)
			//System.err.println(tas.tete.cle);

		/*for (int i=0; i<10; i++){
			tas.ajouterCle(i);
		}*/
		
		/*Noeud x = new Noeud();
			x.cle =4 ;
			x.degre = 0;
			TasBinomialMin t = new TasBinomialMin(); //The Comparator oh the H heap is not used
			t.tete = x;
			this.tete = this.unionTasBinomiaux(tas).tete;
			size++;*/

		//tas.printTas();

		/*Iterator it=tas.iterator();

        while(it.hasNext()){
  			System.err.println(it.next());
  		}*/

  		//for (TasBinomialMin x : tas)
  		//	System.err.println(x.tete.cle);

  		/*List listObjets = new ArrayList();

  		for (int i=0; i<10; i++){
  			listObjets.add(i);
  		}


		Iterator it = listObjects.iterator();

		while(it.hasNext())
		{
			int tmpObject = it.next();
			System.out.println(tmpObject);
		}*/
	}
}