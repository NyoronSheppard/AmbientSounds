package retroapp.android.AmbientSounds;


/**
* Clase List Button
* @author AlisBlack
* @date 13/09/2012
* @version 0.0.4
*/
public class ListButtons 
{
	//Constantes
	private final int KMAX = 15;


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

		buttonlist[0] = new ButtonSound("Canary Trills", "canarytrills");
		buttonlist[0].setId(R.raw.canarytrills);

		buttonlist[1] = new ButtonSound("Earthquake", "earthquake");
		buttonlist[1].setId(R.raw.earthquake);

		buttonlist[2] = new ButtonSound("Jungle", "jungle");
		buttonlist[2].setId(R.raw.jungle);

		buttonlist[3] = new ButtonSound("Peak Cock", "peakcock");
		buttonlist[3].setId(R.raw.peakcock);

		buttonlist[4] = new ButtonSound("Pinch", "pinch");
		buttonlist[4].setId(R.raw.pinch);
		
		buttonlist[5] = new ButtonSound("Rain", "rain");
		buttonlist[5].setId(R.raw.rain);
		
		buttonlist[6] = new ButtonSound("Rattle Snake", "rattlesnake");
		buttonlist[6].setId(R.raw.rattlesnake);

		buttonlist[7] = new ButtonSound("Rocks", "rocks");
		buttonlist[7].setId(R.raw.rocks);

		buttonlist[8] = new ButtonSound("Tidal Wave", "tidalwave");
		buttonlist[8].setId(R.raw.tidalwave);

		buttonlist[9] = new ButtonSound("Walking Water", "walkingwater");
		buttonlist[9].setId(R.raw.walkingwater);

		buttonlist[10] = new ButtonSound("Water Drain", "waterdrain");
		buttonlist[10].setId(R.raw.waterdrain);
		
		buttonlist[11] = new ButtonSound("Water Rice", "waterice");
		buttonlist[11].setId(R.raw.waterice);
		
		buttonlist[12] = new ButtonSound("Water Pump Siphon", "waterpumpsiphon");
		buttonlist[12].setId(R.raw.waterpumpsiphon);

		buttonlist[13] = new ButtonSound("Wind", "wind");
		buttonlist[13].setId(R.raw.wind);

		buttonlist[14] = new ButtonSound("Wood Pecker", "woodpecker");
		buttonlist[14].setId(R.raw.woodpecker);

	}

}
