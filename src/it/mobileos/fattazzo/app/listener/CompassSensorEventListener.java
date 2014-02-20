package it.mobileos.fattazzo.app.listener;

import it.mobileos.fattazzo.app.MainActivity;
import it.mobileos.fattazzo.app.R;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * 
 * @author fattazzo - fattazzo82 [at] gmail . com
 * 
 */
public class CompassSensorEventListener implements SensorEventListener {

	// rappresenta i gradi attuali
	private float gradi = 0f;

	// image view che visualizza l'immagine della bussola
	private ImageView imageCompass;

	public CompassSensorEventListener(MainActivity mainActivity) {
		super();

		// ottengo il controllo che visualizza l'immagine
		imageCompass = (ImageView) mainActivity.findViewById(R.id.imageViewCompass);
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {

		// angolo di rotazione attuale
		float degree = Math.round(event.values[0]);

		// creo la rotazione che dovrà fare immagine per portarsi dai gradi precedenti a quelli attuali
		RotateAnimation ra = new RotateAnimation(gradi, -degree, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);

		// durata dell'animazione
		ra.setDuration(200);

		// una volta terminata l'animazione, questa viene applicata all'immagine
		ra.setFillAfter(true);

		// faccio partire l'animazione
		imageCompass.startAnimation(ra);

		// memorizzo i gradi attuali
		gradi = -degree;
	}

}
