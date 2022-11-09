package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

	private final String immatriculation;
	private final List<Stationnement> myStationnements = new LinkedList<>();

	public Voiture(String i) {
		if (null == i) {
			throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
		}

		immatriculation = i;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * Fait rentrer la voiture au garage
	 * Précondition : la voiture ne doit pas être déjà dans un garage
	 *
	 * @param g le garage où la voiture va stationner
	 * @throws java.lang.Exception Si déjà dans un garage
	 */
	public void entreAuGarage(Garage g) throws Exception {
		// Et si la voiture est déjà dans un garage ?
		Stationnement s = new Stationnement(this, g);
		if(myStationnements.isEmpty()){
			myStationnements.add(s);
		} else {
			Stationnement stationnement = myStationnements.get(myStationnements.size() - 1);
			if(stationnement.estEnCours()){
				throw new Exception("Une voiture ne peut pas entrer deux fois de suite dans le même garage");
			}
		}
	}

	/**
	 * Fait sortir la voiture du garage
	 * Précondition : la voiture doit être dans un garage
	 *
	 * @throws java.lang.Exception si la voiture n'est pas dans un garage
	 */
	public void sortDuGarage() throws Exception {
		if (!myStationnements.isEmpty()) {
			Stationnement dernierStationnement = myStationnements.get(myStationnements.size() - 1);
			if (dernierStationnement.estEnCours()) {
				dernierStationnement.terminer();
			} else {
				throw new Exception("La voiture ne peut pas avoir de doublons de sortie");
			}
		}else {
			throw new Exception("La voiture n'a pas encore effectué de stationnement");
		}
	}

	/**
	 * @return l'ensemble des garages visités par cette voiture
	 */
	public Set<Garage> garagesVisites() {
		Set<Garage> garagesVisites = new HashSet<Garage>();
		for(Stationnement station : myStationnements) {
			garagesVisites.add(station.getGarage());
		}
		return garagesVisites;
	}

	/**
	 * @return vrai si la voiture est dans un garage, faux sinon
	 */
	public boolean estDansUnGarage() {
		// Vrai si le dernier stationnement est en cours
		if(myStationnements.isEmpty()){
			return false;
		}else {
			return myStationnements.get(myStationnements.size() - 1).estEnCours();
		}
	}

	/**
	 * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste des
	 * dates d'entrée / sortie dans ce garage
	 * <br>
	 * Exemple :
	 * 
	 * <pre>
	 * Garage Castres:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 *		Stationnement{ entree=28/01/2019, en cours }
	 *  Garage Albi:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 * </pre>
	 *
	 * @param out l'endroit où imprimer (ex: System.out)
	 */
	public void imprimeStationnements(PrintStream out) {
		for(Garage garage : garagesVisites()){
			out.println(garage);
			for(Stationnement station : myStationnements) {
				if(station.getGarage().equals(garage)) {
					out.println("\t"+station);
				}
			}
		}
	}

}
