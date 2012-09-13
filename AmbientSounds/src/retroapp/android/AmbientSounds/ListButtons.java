package retroapp.android.AmbientSounds;


/**
* Clase List Button
* @author AlisBlack
* @date 13/09/2012
* @version 0.0.3
*/
public class ListButtons 
{
	//Constantes
	private final int KMAX = 7;


	//Variables
	private ButtonSound[] buttonlist;


	//Constructor

	/**
	 * Constructor de la clase
	 */
	public ListButtons()
	{
		this.buttonlist = new ButtonSound[KMAX];
	}


	/**
	 * Metodo que dada una posicion, te devuelve el objeto
	 * @param position Posicion en la lista de frases
	 * @return
	 */
	public ButtonSound getPosition(int position)
	{	
		return (buttonlist[position]);
	}

	/**
	 * Metodo para pasarle la id que cargaremos en load
	 * @param position Posicion en la lista
	 * @return id Valor del identificador
	 */
	public int getId(int position)
	{
		return (buttonlist[position].getId());
	}

	/**
	 * Metodo que devuelve el tamaño maximo de la lista
	 * @return KMAX
	 */
	public int getSize()
	{
		return (KMAX);
	}


	/**
	 * Metodo que añade los nombres de los sonidos y el Id de los ficheros.raw
	 */
	public void setButtons()
	{

		buttonlist[0] = new ButtonSound("Restaurant", "restaurant");
		buttonlist[0].setId(R.raw.restaurant);

		buttonlist[1] = new ButtonSound("Spring Weather", "springweather");
		buttonlist[1].setId(R.raw.springweather);

		buttonlist[2] = new ButtonSound("Street Alley Ambience", "streetalleyambience");
		buttonlist[2].setId(R.raw.streetalleyambience);

		buttonlist[3] = new ButtonSound("Street Traffic", "streettraffic");
		buttonlist[3].setId(R.raw.streettraffic);

		buttonlist[4] = new ButtonSound("Train Interior", "traininterior");
		buttonlist[4].setId(R.raw.traininterior);

		buttonlist[5] = new ButtonSound("Train Station Hall", "trainstationhall");
		buttonlist[5].setId(R.raw.trainstationhall);
		
		buttonlist[6] = new ButtonSound("No Petes", "yyy");
		buttonlist[6].setId(R.raw.yyy);

	}

}
