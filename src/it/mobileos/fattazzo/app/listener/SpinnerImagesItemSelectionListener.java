package it.mobileos.fattazzo.app.listener;

import it.mobileos.fattazzo.app.MainActivity;
import it.mobileos.fattazzo.app.R;

import java.lang.reflect.Field;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;

/**
 * 
 * @author fattazzo - fattazzo82 [at] gmail . com
 * 
 */
public class SpinnerImagesItemSelectionListener implements OnItemSelectedListener {

	private ImageView imageCompass;

	public SpinnerImagesItemSelectionListener(MainActivity mainActivity) {
		imageCompass = (ImageView) mainActivity.findViewById(R.id.imageViewCompass);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos, long arg3) {

		// recupero il nome dell'immagine selezionata
		String imageName = (String) parent.getItemAtPosition(pos);

		R.drawable ourRID = new R.drawable();
		Field photoNameField;
		try {
			// attraverso la reflection ottengo il field dell'immagine
			photoNameField = ourRID.getClass().getField(imageName);

			// applico l'immagine ottenendo l'id del filed
			imageCompass.setImageResource(photoNameField.getInt(ourRID));
		} catch (Exception e) {
			// Eccezione sollevata in caso non venga trovata l'immagine o
			// durante l'ottenimento del suo id
			e.printStackTrace();
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}

}
