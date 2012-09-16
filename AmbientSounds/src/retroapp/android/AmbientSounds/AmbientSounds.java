package retroapp.android.AmbientSounds;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * Clase Principal
 * @author AlisBlack
 * @date 13/09/2012
 * @version 0.1.1
 */
public class AmbientSounds extends Activity 
{
	//Constantes
	public final int KMAX = 15;
		
		
	//Variables	
	private ListButtons ambientalSounds = new ListButtons();  	
	private ButtonSound[] buttons = new ButtonSound[KMAX];	

		
	OnClickListener buttonClick;

	SoundManager snd;
	
    OnSeekBarChangeListener barChangeMaster;
    
		
	//TextView seleccionado;
	ListView lstOpciones;
		
	//Métodos de la clase	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        AdaptadorButtons adaptador = new AdaptadorButtons(this);
        
        lstOpciones = (ListView)findViewById(R.id.LstOpciones);
        //seleccionado = (TextView)findViewById(R.id.seleccionado);
        
        snd = new SoundManager(getApplicationContext());
        
        lstOpciones.setAdapter(adaptador); 
           
        
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        
        
        ambientalSounds.setButtons();       
        
        for(int i = 0; i < KMAX; i++)
        {
        	buttons[i] = ambientalSounds.getPosition(i);
        	buttons[i].setId(snd.setLoad(ambientalSounds.getId(i)));
        }
        
        lstOpciones.setTextFilterEnabled(true);
        
        barChangeMaster = new OnSeekBarChangeListener()
        {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {	}
 
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {  }
 
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				//Para no confundir con el resto de las SeekBar
				switch (seekBar.getId())
				{
				 case R.id.VolBar1:					 
				  snd.setVolume((float)progress/100);
					 break;
				}
			}
		};
		
		SeekBar sb;
        sb = (SeekBar) findViewById(R.id.VolBar1);
        sb.setOnSeekBarChangeListener(barChangeMaster);
        
        buttonClick = new OnClickListener()
        {

			@Override
			public void onClick(View v) 
			{

				if(v.getId() == findViewById(R.id.StopButton).getId())
				{
					for(int j = 0; j < KMAX; j++)
					{
						if(buttons[j].getPlay() == true)
						{
							buttons[j].isStopFuncion(true);
						}
					}
					
					snd.pauseAll();
					
					//Si se pausan los sonidos, booleano a false
					for(int i = 0; i < KMAX; i++ )
					{
						buttons[i].isPlaySong(false);
					}
				}
				
				if(v.getId() == findViewById(R.id.ResumeButton).getId())
				{
					
					for(int j = 0; j < KMAX; j++)
					{
						if(buttons[j].getStopFuncion() == true)
						{
							buttons[j].isPlaySong(true); //Para poder parar el sonido en modo Resume
						}
					}

							snd.resumeAll();

				}
			}
        	
        };
        
        //Boton de Stop
        Button stopButton = (Button) findViewById(R.id.StopButton);
        Button resumeButton = (Button) findViewById(R.id.ResumeButton);
        
        stopButton.setOnClickListener(buttonClick);
        resumeButton.setOnClickListener(buttonClick);
        
        
        
    }
    
    /**
     * Clase que nos guarda IDs de layouts para ahorrar memoria
     */
    static class ViewHolder 
    {
        Button titlesound;
        SeekBar volume;
    }
    
    /**
     * Adaptador para Incorporar los botones de Clase ButtonSound
     */
    class AdaptadorButtons extends ArrayAdapter  implements OnClickListener
    {
    	//AudioManager soundManager;
    	 
        Activity context;
        private int idPlay = -100; //Valor inicial de streamId
        
        
        //RingtoneManager ringTone = new RingtoneManager(context);
        
        	/**
        	 * Constructor de la clase
        	 * @param context Contexto donde vamos a crear el constructor
        	 */
            @SuppressWarnings("unchecked")
			AdaptadorButtons(Activity context) 
            {
            	super(context, R.layout.buttonsound, buttons);
                this.context = context;
            }
     
            //Métodos
            
            /**
             * Metodo getView utilizado para mostrar la lista
             */
            public View getView(int position, View convertView, ViewGroup parent)
            {
                View item = convertView;
                ViewHolder holder;
             
                //Con esto ahorramos CPU (no hay que generar los items que no aparecen en la pantalla
                if(item == null)
                {
                    LayoutInflater inflater = context.getLayoutInflater();
                    item = inflater.inflate(R.layout.buttonsound, null);
             
                    //Creamos el ViewHolder y guardamos los ID del Objeto
                    holder = new ViewHolder();
                    holder.titlesound = (Button)item.findViewById(R.id.LblTitulo);
                    holder.volume = (SeekBar)item.findViewById(R.id.seekBVolume);
                    item.setTag(holder);
                }
                else
                {
                    holder = (ViewHolder)item.getTag();
                }
             
                //Metodos set de la clase Titulo (Se utilizan para rellenar los botones)
                holder.titlesound.setText(buttons[position].getTitleSound());                       
               	holder.titlesound.setTag(position);
                holder.titlesound.setOnClickListener(this);
                holder.volume.setProgress(100);
                holder.volume.setTag(position);
                
                holder.volume.setOnSeekBarChangeListener(new OnSeekBarChangeListener() 
                {
					
					@Override
					public void onStopTrackingTouch(SeekBar seekBar) 
					{
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onStartTrackingTouch(SeekBar seekBar) 
					{
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) 
					{
						//buttons[(Integer) seekBar.getTag()].setVolume((float)progress/100);
						snd.setVolumeSong(buttons[(Integer) seekBar.getTag()].getId(), (float)progress/100);
					}
				});
                
                
                return(item);
            }
            
            /**
             * Metodo onClick para seleccionar el sonido
             * del arrayAdapter
             */
            public void onClick(View v) 
            {                    	

            	int position = (Integer)v.getTag(); 
            	//int idPlay;
            	
            	//seleccionado.setText("Has seleccionado: \n" + buttons[position].getTitleSound()); 
            	
            	if(buttons[position].getPlay() == false)
            	{
            		idPlay = snd.setPlay(buttons[position].getId()); 
            		buttons[position].setStreamId(idPlay); //Guardamos la Stream para saber dirigir las funciones play / stop
            		buttons[position].isPlaySong(true);
            	}
            	else
            	{
            		snd.setStop(buttons[position].getStreamId());
            		buttons[position].isPlaySong(false);
            	}
            	
            	//snd.setLoop(idPlay, -1);
           		
            }
                       
    }
    
    /**
     * Metodo para liberar memoria y parar la aplicacion
     * cuando utilizamos el boton "Back"
     * @param keyCode Codigo del boton
     * @param event Evento que se quiere hacer
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
        	snd.unloadAll(); //Eliminamos de la memoria todas las canciones
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    
    //cuando pasa a segundo plano la aplicacion
    @Override
    protected void onPause() 
    {
    	snd.unloadAll(); //Eliminamos de la memoria todas las canciones
        finish();
    	super.onPause();
    }
    //cuando se destruye la aplicacion
    @Override
    protected void onStop()
    {
    	snd.unloadAll(); //Eliminamos de la memoria todas las canciones
        finish();
    	super.onStop();
    }
}