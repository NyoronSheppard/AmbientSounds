package retroapp.android.AmbientSounds;

/**
* Clase Button
* @author AlisBlack
* @date 13/09/2012
* @version 0.0.6
*/
public class ButtonSound 
{
	//Variables

	private String titlesound;
	private String namesound;
	private int id;
	private boolean isPlay;


	//Constructor

	/**
	 * Constructor parametizado
	 * @param titlesound Titulo del sonido
	 */
	public ButtonSound(String titlesound, String namesound)
	{
		this.titlesound = new String(titlesound);
		this.namesound = new String(namesound);
		this.id = -1;
		this.isPlay = false;
	}


	/**
	 * Metodo get de la Clase
	 * @return titlesound String con el titulo del sonido
	 */
	public String getTitleSound()
	{
		return (titlesound);
	}

	/**
	 * Metodo para obtener el nombre del sonido
	 * @return namesound Nombre del Sonido
	 */
	public String getNameSound()
	{
		return (namesound);
	}

	/**
	 * Metodo para obtener la Id
	 * @return id Identificador
	 */
	public int getId()
	{
		return (id);
	}
	
	/**
	 * Metodo para obtener si esta sonando o no
	 * @return isPlay Booleano
	 */
	public boolean getPlay()
	{
		return (isPlay);
	}

	/**
	 * Metodo para cambiar la id
	 * @param id Identificador
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * Metodo para cambiar el nombre del sounid
	 * @param namesound Nombre del sonido
	 */
	public void setNameSound(String namesound)
	{
		this.namesound = namesound;
	}
	
	/**
	 * Metodo para cambiar si el sonido esta ejecutandose o no
	 * @param isPlay Booleano
	 */
	public void isPlaySong(boolean isPlay)
	{
		this.isPlay = isPlay;
	}

}
