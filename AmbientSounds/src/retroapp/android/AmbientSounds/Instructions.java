package retroapp.android.AmbientSounds;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

/**
 * Clase Instructions
 * @author AlisBlack
 * @date 16/09/2012
 * @version 0.1.0
 */
public class Instructions extends Activity
{
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions);
    }	
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();

        }
		return super.onKeyDown(keyCode, event);
    }
}
