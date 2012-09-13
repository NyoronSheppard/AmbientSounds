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
	private final int KMAX = 5;


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

		buttonlist[0] = new ButtonSound("Bus Interior", "businterior");
		buttonlist[0].setId(R.raw.businterior);

		buttonlist[1] = new ButtonSound("Lake Waves", "lakewaves");
		buttonlist[1].setId(R.raw.lakewaves);

		buttonlist[2] = new ButtonSound("Ocean Wave", "oceanwave");
		buttonlist[2].setId(R.raw.oceanwave);

		buttonlist[3] = new ButtonSound("Water Dripping", "waterdripping");
		buttonlist[3].setId(R.raw.waterdripping);

		buttonlist[4] = new ButtonSound("Wood Logs", "woodlogs");
		buttonlist[4].setId(R.raw.woodlogs);

	}

}
