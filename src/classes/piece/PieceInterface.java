package classes.piece;

public interface PieceInterface {

	public void initialiserPiece () ;
	public boolean verification (String nomPiece) ;
	public void ajoutIncompatibilite();
	public void suppressionIncompatibilite();
	public void ajoutNecessite();
	public void suppressionNecessite();
	
}
