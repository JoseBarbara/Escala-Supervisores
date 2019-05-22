package br.com.metro.escala;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.content.*;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class SplashActivity extends Activity implements Runnable {
			
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.splash);		
		
		
		PackageInfo pinfo = null;
		try {
			pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int versionNumber = pinfo.versionCode; //versionCode expecificado no Manifest.
		String versionName = pinfo.versionName; //versionName expecificado no Manifest.
		TextView textView = (TextView)findViewById(R.id.versao);
		textView.setText("Versão - " + versionNumber + "." + versionName);
		
		// Tempo de espera para abrir a tela principal do app.
		Handler handler = new Handler();
		handler.postDelayed(this, 2000);
		
	}

	// Abre a tela inicial do app e fecha a tela de abertura.
	public void run() {
		startActivity(new Intent(this, EscalaActivity.class));
		finish();
	}

}
