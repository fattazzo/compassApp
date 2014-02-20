package it.mobileos.fattazzo.app;

import it.mobileos.fattazzo.app.listener.CompassSensorEventListener;
import it.mobileos.fattazzo.app.listener.SpinnerImagesItemSelectionListener;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * 
 * @author fattazzo - fattazzo82 [at] gmail . com
 * 
 */
public class MainActivity extends Activity {

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// applica il layout dell'activity
		setContentView(R.layout.activity_main);

		// ottengo il controllo che visualizza la scelta delle immagini da usare
		Spinner spinnerImages = (Spinner) findViewById(R.id.spinnerImages);

		// creo l'ArrayAdapter che contiene tutte le scelte possibili che vengono caricare dall'array
		// definito nel file strings.xml
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.compass_array,
				android.R.layout.simple_spinner_item);
		// Vado a definire che l'adapter si presenterà in forma di drop down list
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Applico l'adapter al controllo
		spinnerImages.setAdapter(adapter);

		// Aggiungo il listener che si attiva sul cambio di selezione
		spinnerImages.setOnItemSelectedListener(new SpinnerImagesItemSelectionListener(this));

		// ottengo il servizio dei sensori di android
		SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		// registro la main activity stessa come listener al sensor manager
		// Sensor.TYPE_ORIENTATION risulta ora deprecato perchè anzichè i sensori di orientamento dovrebbero
		// essere usati in coppia quelli dell'accelerometro e magnetici. Su alcuni dispositivi
		// questo non sembra ancora funzionare quindi lascio la vecchia implementazione
		mSensorManager.registerListener(new CompassSensorEventListener(this),
				mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
	}
}
