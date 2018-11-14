package classes;

public interface PieceInterface {

	void initialiserPiece();
	boolean verification(String nomPiece);
	void ajoutIncompatibilite();
	void suppressionIncompatibilite();
	void ajoutNecessite();
	void suppressionNecessite();
}
