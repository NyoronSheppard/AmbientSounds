package retroapp.android.AmbientSounds;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * Clase Principal
 * @author AlisBlack
 * @date 13/09/2012
 * @version 0.1.6
 */
public class AmbientSounds extends Activity 
{
	//Constantes
	public final int KMAX = 15;
	private static final int NOTIF_ALERTA_ID = 1;
		
		
	//Variables	
	private ListButtons ambientalSounds = new ListButtons();  	
	private ButtonSound[] buttons = new ButtonSound[KMAX];	

		
	OnClickListener buttonClick;

	SoundManager snd;
	AudioManager audioManager;
	
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
        
        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        
        SeekBar volControl = (SeekBar)findViewById(R.id.VolBar1);
        volControl.setMax(maxVolume);
        volControl.setProgress(curVolume);
        volControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {	}
 
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {  }
 
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
			}
		});
        
        buttonClick = new OnClickListener()
        {
			@Override
			public void onClick(View v) 
			{
				if(v.getId() == findViewById(R.id.StopButton).getId())
				{
					for(int j = 0; j < KMAX; j++)
					{
						if(buttons[j].getStreamId() != -1)
						{
							snd.setStop(buttons[j].getStreamId());
							buttons[j].isPlaySong(false);
							buttons[j].isPauseFuncion(false);
						}
					}					
				}				
				if(v.getId() == findViewById(R.id.ResumeButton).getId())
				{					
					for(int j = 0; j < KMAX; j++)
					{
						if(buttons[j].getPauseFuncion() == true)
						{
							buttons[j].isPlaySong(true); //Para poder parar el sonido en modo Resume
						}
					}
							snd.resumeAll();
				}
				if(v.getId() == findViewById(R.id.PauseButton).getId())
				{
					for(int j = 0; j < KMAX; j++)
					{
						if(buttons[j].getPlay() == true)
						{
							buttons[j].isPauseFuncion(true);
						}
					}
					
					snd.pauseAll();
					
					//Si se pausan los sonidos, booleano a false
					for(int i = 0; i < KMAX; i++ )
					{
						buttons[i].isPlaySong(false);
					}					
				}
			}       	
        };
        
        //Botones de Stop y Resume
        Button stopButton = (Button) findViewById(R.id.StopButton);
        Button resumeButton = (Button) findViewById(R.id.ResumeButton);
        Button pauseButton = (Button) findViewById(R.id.PauseButton);
        
        stopButton.setOnClickListener(buttonClick);
        resumeButton.setOnClickListener(buttonClick);
        pauseButton.setOnClickListener(buttonClick);       
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
        if((keyCode == KeyEvent.KEYCODE_BACK))
        {
        	AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        	
        	alertDialog.setTitle("¿Desea segundo plano?");
        	//alertDialog.setMessage("Alis Black y Nyoron Sheppard");
        	
        	//alertDialog.setIcon(icon)
        	
        	alertDialog.setButton2("No", new DialogInterface.OnClickListener() 
        	{
        	      public void onClick(DialogInterface dialog, int which) 
        	      {       	 
        	        	snd.unloadAll(); //Eliminamos de la memoria todas las canciones
        	            finish();
        	      } 
        	}); 
        	alertDialog.setButton("Si", new DialogInterface.OnClickListener() 
        	{
				
				@Override
				public void onClick(DialogInterface dialog, int which) 
				{
					//Obtenemos una referencia al servicio de notificaciones
					String ns = Context.NOTIFICATION_SERVICE;
					NotificationManager notManager = (NotificationManager) getSystemService(ns);
					
					//Configuramos la notificacion
					int icono = android.R.drawable.stat_sys_warning;
					CharSequence textoEstado = "Ambient Sounds";
					long hora = System.currentTimeMillis();

					Notification notif = new Notification(icono, textoEstado, hora);
					
					//Configuramos el Intent
					Context contexto = getApplicationContext();
					CharSequence titulo = "Second Plane";
					CharSequence descripcion = "Elisa es Cute";
					
					Intent notIntent = new Intent(contexto, AmbientSounds.class);
					
					PendingIntent contIntent = PendingIntent.getActivity(contexto, 0, notIntent, 0);

					notif.setLatestEventInfo(contexto, titulo, descripcion, contIntent);
					
					//AutoCancel: cuando se pulsa la notificai�n �sta desaparece
					notif.flags |= Notification.FLAG_AUTO_CANCEL;
					
					//Añadir sonido, vibracion y luces
					//notif.defaults |= Notification.DEFAULT_SOUND;
					//notif.defaults |= Notification.DEFAULT_VIBRATE;
					//notif.defaults |= Notification.DEFAULT_LIGHTS;
					
					//Enviar notificacion
					notManager.notify(NOTIF_ALERTA_ID, notif);		
					
					//IMPORTANTE VER COMO FALLA LO DE SEGUNDO PLANO
					finish();
				}
			});
        	
        	alertDialog.show();
        	
        }
        /*if((keyCode == KeyEvent.KEYCODE_MENU))
        {
        	
        	AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        	
        	alertDialog.setTitle("     Ambiental Sounds");
        	//alertDialog.setMessage("Alis Black y Nyoron Sheppard");
        	
        	//alertDialog.setIcon(icon)
        	
        	alertDialog.setButton("About", new DialogInterface.OnClickListener() 
        	{
        	      public void onClick(DialogInterface dialog, int which) 
        	      {       	 
        	    	  activityAbout();
        	      } 
        	}); 
        	alertDialog.setButton2("Instructions", new DialogInterface.OnClickListener() 
        	{
				
				@Override
				public void onClick(DialogInterface dialog, int which) 
				{
					activityInstructions();					
				}
			});
        	alertDialog.setButton3("Instructions", new DialogInterface.OnClickListener() 
        	{
				
				@Override
				public void onClick(DialogInterface dialog, int which) 
				{
					activityInstructions();					
				}
			});
        	
        	alertDialog.show();
        	
        }*/
        
        return super.onKeyDown(keyCode, event);
    }
    
    /**
     * Crea el Menu de Button Menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.menu, menu);
        return true;
    }

    /**
     * Selecciona uno de los botones
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {
        switch (item.getItemId()) 
        {
            case R.id.about:     
            					activityAbout();
                                break;
            case R.id.instructions:     
            					activityInstructions();
                                break;
        }
        return true;
    }
    
    /**
     * Metodo para cambiar a la actividad About
     */
    public void activityAbout()
    {
    	Intent IntAbout = new Intent(this, About.class);
    	startActivity(IntAbout);  
    }
    
    /**
     * Metodo para cambiar a la actividad Instructions
     */
    public void activityInstructions()
    {
    	Intent IntInstrcutions = new Intent(this, Instructions.class);
    	startActivity(IntInstrcutions);
    }
    
    /*    //cuando pasa a segundo plano la aplicacion
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
    }*/
    

}