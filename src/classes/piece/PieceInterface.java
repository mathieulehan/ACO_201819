package classes.piece;

public interface PieceInterface {

	public void initialiserPiece () ;
//	public boolean verification (String nomPiece) ;
	public void ajoutIncompatibilite(String nomPiece, String incompatibilité);
	public void suppressionIncompatibilite(String nomPiece, String incompatibilite);
	public void ajoutNecessite(String nomPiece, String necessite);
	public void suppressionNecessite(String nomPiece, String necessite);
	
}
