package br.com.metro.escala;

import android.app.*;
import android.os.*;
import android.content.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Days;

public class MostraEscalaActivity extends Activity {

	String[] escala;
	String[] semanal;
	Button btnmanha;
	Button btntarde;
	Button btnnoite;
	Button btnfolga1;
	Button btnfolga2;
	String texto = "";
	String textos = "";
	public String tamanho;
	TextView tv_toast_titulo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent idr = getIntent();
		Bundle pr = idr.getExtras();
		String dr = pr.getString("mensagem");

		if (dr.equals("424")) {
			setContentView(R.layout.escala4x2x4);
			calcula424(EscalaActivity.data);
		} else if (dr.equals("4143")) {
			setContentView(R.layout.escala4x1x4x3);
			calcula4143(EscalaActivity.data);
		} else if (dr.equals("42410")) {
			setContentView(R.layout.escala4x2x4x10);
			calcula42410(EscalaActivity.data);
		} else if (dr.equals("4264")) {
			setContentView(R.layout.escala4x2x6x4);
			calcula4264(EscalaActivity.data);
		} else if (dr.equals("6123")) {
			setContentView(R.layout.escala6x1x2x3);
			calcula6123(EscalaActivity.data);
		} else if (dr.equals("5261")) {
			setContentView(R.layout.escala5x2e6x1);
			calcula5261(EscalaActivity.data);
		} else if (dr.equals("6152")) {
			setContentView(R.layout.escala6x1x5x2);
			calcula6152(EscalaActivity.data);
		}
	}

	public void clickNoBotao(final View view) {

		// view.setOnClickListener(new View.OnClickListener(){
		// public void onClick(View v){

		if (view.getId() == R.id.btnmanha) {
			btnmanha = (Button) findViewById(R.id.btnmanha);
		} else if (view.getId() == R.id.btntarde) {
			btntarde = (Button) findViewById(R.id.btntarde);
		} else if (view.getId() == R.id.btnnoite) {
			btnnoite = (Button) findViewById(R.id.btnnoite);
		} else if (view.getId() == R.id.btnfolga1) {
			btnfolga1 = (Button) findViewById(R.id.btnfolga1);
		} else if (view.getId() == R.id.btnfolga2) {
			btnfolga2 = (Button) findViewById(R.id.btnfolga2);
		}

		LayoutInflater layoutInflater = getLayoutInflater();
		int layout = R.layout.toast;
		ViewGroup viewGroup = (ViewGroup) findViewById(R.id.toast_layout_root);
		View v = layoutInflater.inflate(layout, viewGroup);

		if (view.getId() == R.id.btnmanha) {
			tamanho = btnmanha.getText().toString();
		} else if (view.getId() == R.id.btntarde) {
			tamanho = btntarde.getText().toString();
		} else if (view.getId() == R.id.btnnoite) {
			tamanho = btnnoite.getText().toString();
		} else if (view.getId() == R.id.btnfolga1) {
			tamanho = btnfolga1.getText().toString();
		} else if (view.getId() == R.id.btnfolga2) {
			tamanho = btnfolga2.getText().toString();
		}

		String letra = tamanho.substring(tamanho.length() - 1);
		TextView tv_toast_titulo = (TextView) v.findViewById(R.id.toast_titulo);
		TextView tv_base = (TextView) v.findViewById(R.id.base);
		TextView tv_semanal = (TextView) v.findViewById(R.id.semanal);
		String[] semanal = getResources().getStringArray(R.array.semanal);

		char letra1 = letra.charAt(0);
		if (letra1 == 'A') {
			String[] escala = getResources().getStringArray(R.array.escala_a);
			montaEscala(letra, tv_base, tv_semanal, escala, semanal,
					tv_toast_titulo);
		} else if (letra1 == 'B') {
			String[] escala = getResources().getStringArray(R.array.escala_b);
			montaEscala(letra, tv_base, tv_semanal, escala, semanal,
					tv_toast_titulo);
		} else if (letra1 == 'C') {
			String[] escala = getResources().getStringArray(R.array.escala_c);
			montaEscala(letra, tv_base, tv_semanal, escala, semanal,
					tv_toast_titulo);
		} else if (letra1 == 'D') {
			String[] escala = getResources().getStringArray(R.array.escala_d);
			montaEscala(letra, tv_base, tv_semanal, escala, semanal,
					tv_toast_titulo);
		} else if (letra1 == 'E') {
			String[] escala = getResources().getStringArray(R.array.escala_e);
			montaEscala(letra, tv_base, tv_semanal, escala, semanal,
					tv_toast_titulo);
		}

		Toast toast = new Toast(getApplicationContext());
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(v);
		toast.show();
		texto = "";
		textos = "";

		// }

	}

	// });

	private void montaEscala(String letra, TextView tv_base,
			TextView tv_semanal, String[] escala, String[] semanal,
			TextView tv_toast_titulo) {
		tv_toast_titulo.setText("Escala " + letra + " - Supervisores");
		for (int i = 0; i < escala.length; i++) {
			texto = texto + (String) escala[i] + "\n";
			tv_base.setText(texto);
		}
		for (int i = 0; i < semanal.length; i++) {
			textos = textos + (String) semanal[i] + "\n";
			tv_semanal.setText(textos);
		}
	}

	public int calcula424(Date dt1) {

		DateTime datacalculo = new DateTime(dt1);
		DateTime datareferencia = new DateTime(1900, 01, 01, 00, 00, 00);
		Days dias = Days.daysBetween(datareferencia, datacalculo);
		int calculo = (dias.getDays()) % 10;

		String[][] pos0 = { { "A4", "C2", "E6", "B10", "D8" },
				{ "B1", "C3", "A5", "E7", "D9" },
				{ "B2", "C4", "A6", "E8", "D10" },
				{ "B3", "D1", "C5", "A7", "E9" },
				{ "B4", "D2", "C6", "A8", "E10" },
				{ "E1", "D3", "B5", "A9", "C7" },
				{ "E2", "D4", "B6", "A10", "C8" },
				{ "A1", "E3", "D5", "B7", "C9" },
				{ "A2", "E4", "D6", "B8", "C10" },
				{ "A3", "C1", "E5", "B9", "D7" } };

		String posicao;

		Button btn1 = (Button) findViewById(R.id.btnmanha);
		posicao = pos0[calculo][0];
		btn1.setText(calculaposicao424(posicao));

		Button btn2 = (Button) findViewById(R.id.btntarde);
		posicao = pos0[calculo][1];
		btn2.setText(calculaposicao424(posicao));

		Button btn3 = (Button) findViewById(R.id.btnnoite);
		posicao = pos0[calculo][2];
		btn3.setText(calculaposicao424(posicao));

		Button btn4 = (Button) findViewById(R.id.btnfolga1);
		posicao = pos0[calculo][3];
		btn4.setText(calculaposicao424(posicao));

		Button btn5 = (Button) findViewById(R.id.btnfolga2);
		posicao = pos0[calculo][4];
		btn5.setText(calculaposicao424(posicao));

		return calculo;
	}

	public String calculaposicao424(String posicao) {
		String letra = posicao.substring(0, 1);
		String numero = posicao.substring(1, posicao.length());
		String posicao1 = "";
		switch (numero) {
		case "1":
		case "5":
		case "7":
			posicao1 = ("1ª " + letra);
			break;
		case "2":
		case "6":
		case "8":
			posicao1 = ("2ª " + letra);
			break;
		case "3":
			if (letra.equals("E")) {
				posicao1 = ("1ª " + letra);
			} else {
				posicao1 = ("3ª " + letra);
			}
			break;
		case "9":
			posicao1 = ("3ª " + letra);
			break;
		case "4":
			if (letra.equals("E")) {
				posicao1 = ("2ª " + letra);
			} else {
				posicao1 = ("4ª " + letra);
			}
			break;
		case "10":
			posicao1 = ("4ª " + letra);
			break;
		default:
		}
		return posicao1;
	}

	public int calcula42410(Date dt1) {

		DateTime datacalculo = new DateTime(dt1);
		DateTime datareferencia = new DateTime(1900, 01, 01, 00, 00, 00);
		Days dias = Days.daysBetween(datareferencia, datacalculo);
		int calculo = (dias.getDays()) % 10;

		String[][] pos0 = { { "M-R 4", "N-S 2", "Q-V 6", "0-T 10", "P-U 8" },
				{ "O-T 1", "N-S 3", "M-R 5", "Q-V 7", "P-U 9" },
				{ "O-T 2", "N-S 4", "M-R 6", "Q-V 8", "P-U 10" },
				{ "O-T 3", "P-U 1", "N-S 5", "M-R 7", "Q-V 9" },
				{ "O-T 4", "P-U 2", "N-S 6", "M-R 8", "Q-V 10" },
				{ "Q-V 1", "P-U 3", "O-T 5", "M-R 9", "N-S 7" },
				{ "Q-V 2", "P-U 4", "O-T 6", "M-R 10", "N-S 8" },
				{ "M-R 1", "Q-V 3", "P-U 5", "O-T 7", "N-S 9" },
				{ "M-R 2", "Q-V 4", "P-U 6", "O-T 8", "N-S 10" },
				{ "M-R 3", "N-S 1", "Q-V 5", "O-T 9", "P-U 7" } };

		TextView tv1 = (TextView) findViewById(R.id.tvmanha);
		tv1.setText(pos0[calculo][0]);

		TextView tv2 = (TextView) findViewById(R.id.tvtarde);
		tv2.setText(pos0[calculo][1]);

		TextView tv3 = (TextView) findViewById(R.id.tvnoite1);
		tv3.setText(pos0[calculo][2]);

		TextView tv4 = (TextView) findViewById(R.id.tvfolga1);
		tv4.setText(pos0[calculo][3]);

		TextView tv5 = (TextView) findViewById(R.id.tvfolga2);
		tv5.setText(pos0[calculo][4]);

		return calculo;
	}

	public int calcula6123(Date dt1) {

		DateTime datacalculo = new DateTime(dt1);
		DateTime datareferencia = new DateTime(1900, 01, 01, 00, 00, 00);
		Days dias = Days.daysBetween(datareferencia, datacalculo);
		int calculo = (dias.getDays()) % 12;

		String[][] pos0 = { { "A2", "D6", "E4", "B8", "F10", "C12" },
				{ "A3", "C1", "E5", "B9", "D7", "F11" },
				{ "A4", "C2", "E6", "D8", "B10", "F12" },
				{ "A5", "C3", "F1", "D9", "B11", "E7" },
				{ "A6", "C4", "F2", "E8", "B12", "D10" },
				{ "B1", "C5", "F3", "E9", "A7", "D11" },
				{ "B2", "C6", "F4", "A8", "E10", "D12" },
				{ "B3", "D1", "F5", "A9", "E11", "C7" },
				{ "B4", "D2", "F6", "C8", "A10", "E12" },
				{ "B5", "D3", "E1", "C9", "A11", "F7" },
				{ "B6", "D4", "E2", "F8", "A12", "C10" },
				{ "A1", "D5", "E3", "F9", "B7", "C11" } };

		String posicao;

		TextView tv1 = (TextView) findViewById(R.id.tvmanha);
		// tv1.setText(pos0[calculo] [0]);
		posicao = pos0[calculo][0];
		tv1.setText(calculaposicao6123(posicao));

		TextView tv2 = (TextView) findViewById(R.id.tvtarde);
		// tv2.setText(pos0[calculo] [1]);
		posicao = pos0[calculo][1];
		tv2.setText(calculaposicao6123(posicao));

		TextView tv3 = (TextView) findViewById(R.id.tvvespertino);
		// tv3.setText(pos0[calculo] [2]);
		posicao = pos0[calculo][2];
		tv3.setText(calculaposicao6123(posicao));

		TextView tv4 = (TextView) findViewById(R.id.tvnoite1);
		// tv4.setText(pos0[calculo] [3]);
		posicao = pos0[calculo][3];
		tv4.setText(calculaposicao6123(posicao));

		TextView tv5 = (TextView) findViewById(R.id.tvfolga1);
		// tv5.setText(pos0[calculo] [4]);
		posicao = pos0[calculo][4];
		tv5.setText(calculaposicao6123(posicao));

		TextView tv6 = (TextView) findViewById(R.id.tvfolga2);
		// tv6.setText(pos0[calculo] [5]);
		posicao = pos0[calculo][5];
		tv6.setText(calculaposicao6123(posicao));

		return calculo;
	}

	public String calculaposicao6123(String posicao) {
		String letra = posicao.substring(0, 1);
		String numero = posicao.substring(1, posicao.length());
		String posicao1 = "";
		switch (numero) {
		case "1":
		case "8":
			posicao1 = ("1ª " + letra);
			break;
		case "2":
		case "9":
			posicao1 = ("2ª " + letra);
			break;
		case "3":
			posicao1 = ("3ª " + letra);
			break;
		case "4":
			posicao1 = ("4ª " + letra);
			break;
		case "5":
			posicao1 = ("5ª " + letra);
			break;
		case "6":
			posicao1 = ("6ª " + letra);
			break;
		case "7":
			posicao1 = ("1x1 " + letra);
			break;
		case "10":
			posicao1 = ("1ªx3 " + letra);
			break;
		case "11":
			posicao1 = ("2ªx3 " + letra);
			break;
		case "12":
			posicao1 = ("3ªx3 " + letra);
			break;
		default:
		}
		return posicao1;
	}

	public int calcula4143(Date dt1) {

		DateTime datacalculo = new DateTime(dt1);
		DateTime datareferencia = new DateTime(1900, 01, 01, 00, 00, 00);
		Days dias = Days.daysBetween(datareferencia, datacalculo);
		int calculo = (dias.getDays()) % 12;

		/**
		 * String[][] pos0 = { {"F6", "J6", "H10", "G2", "K2", "L10"}, {"F7",
		 * "J7", "H11", "G3", "K3", "L11"}, {"F8", "J8", "H12", "G4", "K4",
		 * "L12"}, {"F9", "J9", "G5", "H1", "L1", "K5"}, {"G6", "K6", "F10",
		 * "H2", "L2", "J10"}, {"G7", "K7", "F11", "H3", "L3", "J11"}, {"G8",
		 * "K8", "F12", "H4", "L4", "J12"}, {"F1", "J1", "H5", "G9", "K9",
		 * "L5"}, {"F2", "J2", "G10", "H6", "L6", "K10"}, {"F3", "J3", "G11",
		 * "H7", "L7", "K11"}, {"F4", "J4", "G12", "H8", "L8", "K12"}, {"G1",
		 * "K1", "F5", "H9", "L9", "J5"}, };
		 */

		// Versão com Noite
		String[][] pos0 = {
				{ "F6", "J6", "H10", "G2", "K2", "L10", "R6", "S2", "T10" },
				{ "F7", "J7", "H11", "G3", "K3", "L11", "R7", "S3", "T11" },
				{ "F8", "J8", "H12", "G4", "K4", "L12", "R8", "S4", "T12" },
				{ "F9", "J9", "G5", "H1", "L1", "K5", "R9", "T1", "S5" },
				{ "G6", "K6", "F10", "H2", "L2", "J10", "S6", "T2", "R10" },
				{ "G7", "K7", "F11", "H3", "L3", "J11", "S7", "T3", "R11" },
				{ "G8", "K8", "F12", "H4", "L4", "J12", "S8", "T4", "R12" },
				{ "F1", "J1", "H5", "G9", "K9", "L5", "R1", "S9", "T5" },
				{ "F2", "J2", "G10", "H6", "L6", "K10", "R2", "T6", "S10" },
				{ "F3", "J3", "G11", "H7", "L7", "K11", "R3", "T7", "S11" },
				{ "F4", "J4", "G12", "H8", "L8", "K12", "R4", "T8", "S12" },
				{ "G1", "K1", "F5", "H9", "L9", "J5", "S1", "T9", "R5" }, };

		// Versão completa Tráfego e Estação
		/**
		 * String[][] pos0 = { {"F6", "J6 R6", "H10", "G2 Q2", "K2", "L10 P10"},
		 * {"F7", "J7 R7", "H11", "G3 Q3", "K3", "L11 P11"}, {"F8", "J8 R8",
		 * "H12", "G4 Q4", "K4", "L12 P12"}, {"F9", "J9 R9", "G5", "H1 P1",
		 * "L1", "K5 Q5"}, {"G6 Q6", "K6", "F10 R10", "H2", "L2 P2", "J10"},
		 * {"G7 Q7", "K7", "F11 R11", "H3", "L3 P3", "J11"}, {"G8 Q8", "K8",
		 * "F12 R12", "H4", "L4 P4", "J12"}, {"F1", "J1 R1", "H5", "G9 Q9",
		 * "K9", "L5 P5"}, {"F2", "J2 R2", "G10", "H6 P6", "L6", "K10 Q10"},
		 * {"F3", "J3 R3", "G11", "H7 P7", "L7", "K11 Q11"}, {"F4", "J4 R4",
		 * "G12", "H8 P8", "L8", "K12 Q12"}, {"G1 Q1", "K1", "F5 R5", "H9",
		 * "L9 P9", "J5"}, };
		 */

		String posicao;

		TextView tv1 = (TextView) findViewById(R.id.tvmanha);
		posicao = pos0[calculo][0];
		tv1.setText(calculaposicao4143(posicao));

		TextView tv2 = (TextView) findViewById(R.id.tvtarde);
		posicao = pos0[calculo][1];
		tv2.setText(calculaposicao4143(posicao));

		TextView tv3 = (TextView) findViewById(R.id.tvfolga);
		posicao = pos0[calculo][2];
		tv3.setText(calculaposicao4143(posicao));

		TextView tv4 = (TextView) findViewById(R.id.tvmanha1);
		posicao = pos0[calculo][3];
		tv4.setText(calculaposicao4143(posicao));

		TextView tv5 = (TextView) findViewById(R.id.tvtarde1);
		posicao = pos0[calculo][4];
		tv5.setText(calculaposicao4143(posicao));

		TextView tv6 = (TextView) findViewById(R.id.tvfolga1);
		posicao = pos0[calculo][5];
		tv6.setText(calculaposicao4143(posicao));

		TextView tv7 = (TextView) findViewById(R.id.tvnoite);
		posicao = pos0[calculo][6];
		tv7.setText(calculaposicao4143(posicao));

		TextView tv8 = (TextView) findViewById(R.id.tvnoite1);
		posicao = pos0[calculo][7];
		tv8.setText(calculaposicao4143(posicao));

		TextView tv9 = (TextView) findViewById(R.id.tvfolga2);
		posicao = pos0[calculo][8];
		tv9.setText(calculaposicao4143(posicao));

		return calculo;
	}

	public String calculaposicao4143(String posicao) {
		String letra = posicao.substring(0, 1);
		String numero = posicao.substring(1, posicao.length());
		String posicao1 = "";
		switch (numero) {
		case "1":
			posicao1 = (letra + " 1ªx1");
			break;
		case "6":
		case "10":
			posicao1 = (letra + " 1ªx3");
			break;
		case "2":
			posicao1 = (letra + " 2ªx1");
			break;
		case "7":
		case "11":
			posicao1 = (letra + " 2ªx3");
			break;
		case "3":
			posicao1 = (letra + " 3ªx1");
			break;
		case "8":
		case "12":
			posicao1 = (letra + " 3ªx3");
			break;
		case "4":
			posicao1 = (letra + " 4ªx1");
			break;
		case "9":
			posicao1 = (letra + " 4ªx3");
			break;
		case "5":
			posicao1 = (letra + " 1x1");
			break;
		default:
		}
		return posicao1;
	}

	public int calcula4264(Date dt1) {

		DateTime datacalculo = new DateTime(dt1);
		DateTime datareferencia = new DateTime(1900, 01, 01, 00, 00, 00);
		Days dias = Days.daysBetween(datareferencia, datacalculo);
		int calculo = (dias.getDays()) % 16;

		String[][] pos0 = {
				{ "A1 12 A2 10", "C1 12 C2 10", "A4 6", "C4 6", "A3 8 B1 4",
						"C3 8 D1 4", "B3 16", "D3 16", "B2 2", "D2 2", "B4 14",
						"D4 14" },
				{ "A2 11 A3 9", "C2 11 C3 9", "A1 13", "C1 13", "A4 7 B2 3",
						"C4 7 D2 3", "B1 5", "D1 5", "B3", "D3", "B4", "D4" },
				{ "A2 12 A3 10", "C2 12 C2 13", "A1", "C1", "A4 8 B2 4",
						"C4 8 D2 4", "B1 6", "D1 6", "B3 2", "D3 2", "B4 16",
						"D4 16" },
				{ "A3 11 A4 9", "C3 11 C4 9", "A1 15", "C1 15", "B1 7 B3 3",
						"D1 7 D3 3", "A2 13", "C2 13", "B4 1", "D4 1", "B2 5",
						"D2 5" },
				{ "A3 12 A4 10", "C3 12 C4 10", "A1 16", "C1 16", "B1 8 B3 4",
						"D1 8 D3 4", "A2 14", "C2 14", "B4 4", "D4 2", "B2 6",
						"D2 6" },
				{ "A1 1 A4 11", "C1 1 C4 11", "A2 15", "C2 15", "B1 9 B2 7",
						"D1 9 D2 7", "A3 13", "C3 13", "B4 3", "D4 3", "B3 5",
						"D3 5" },
				{ "A1 2 A4 12", "C1 2 C4 12", "A2 16", "C2 16", "B1 10 B2 8",
						"D1 10 D2 8", "A3 14", "C3 14", "B4 8", "D4 4", "B3 6",
						"D3 6" },
				{ "A1 3 A2 1", "C1 3 C2 1", "A4 13", "C4 13", "B1 11 B2 9",
						"D1 11 D2 9", "A3 15", "C3 15", "B3 7", "D3 7", "B4 5",
						"D4 5" },
				{ "A1 4 A2 2", "C1 4 C2 2", "A3 16", "C3 16", "B1 12 B2 10",
						"D1 12 D2 10", "A4 14", "C4 14", "B3 8", "D3 8",
						"B4 6", "D4 6" },
				{ "A2 3 A3 1", "C2 3 C3 1", "A1 5", "C1 5", "B2 11 B3 9",
						"D2 11 D3 9", "A4 15", "C4 15", "B4 7", "D4 7",
						"B1 13", "D1 13" },
				{ "A2 4 A3 2", "C2 4 C3 2", "A1 6", "C1 6", "B2 12 B3 10",
						"D2 12 D3 10", "A4 16", "C4 16", "B4 8", "D4 8",
						"B1 14", "D1 14" },
				{ "A1 7 A3 3", "C1 7 C3 3", "A2 5", "C2 5", "A4 1 B3 11",
						"C4 1 D3 11", "B1 15", "D1 15", "B4 9", "D4 9",
						"B2 13", "D2 13" },
				{ "A1 8 A3 4", "C1 8 C3 4", "A2 6", "C2 6", "A4 2 B3 12",
						"C4 2 D3 12", "B1 16", "D1 16", "B4 10", "D4 10",
						"B2 14", "D2 14" },
				{ "A1 9 A2 7", "C1 9 C2 7", "A3 5", "C3 5", "A4 3 B1 1",
						"C4 3 D1 1", "B2 15", "D2 15", "B4 11", "D4 11",
						"B3 13", "D3 13" },
				{ "A1 10 A2 8", "C1 10 C2 8", "A3 6", "C3 6", "A4 4 B1 2",
						"C4 4 D1 2", "B2 16", "D2 16", "B4 12", "D4 12",
						"B3 14", "D3 14" },
				{ "A1 11 A2 9", "C1 11 C2 9", "A4 5", "C4 5", "A3 7 B1 3",
						"C3 7 D1 3", "B3 15", "D3 15", "B2 1", "D2 1", "B4 13",
						"D4 13" }, };

		TextView tv1 = (TextView) findViewById(R.id.tvmanha);
		tv1.setText(pos0[calculo][0]);

		TextView tv2 = (TextView) findViewById(R.id.tvtarde);
		tv2.setText(pos0[calculo][1]);

		TextView tv3 = (TextView) findViewById(R.id.tvfolga);
		tv3.setText(pos0[calculo][2]);

		TextView tv4 = (TextView) findViewById(R.id.tvfolga10);
		tv4.setText(pos0[calculo][3]);

		TextView tv5 = (TextView) findViewById(R.id.tvmanha1);
		tv5.setText(pos0[calculo][4]);

		TextView tv6 = (TextView) findViewById(R.id.tvtarde1);
		tv6.setText(pos0[calculo][5]);

		TextView tv7 = (TextView) findViewById(R.id.tvfolga1);
		tv7.setText(pos0[calculo][6]);

		TextView tv8 = (TextView) findViewById(R.id.tvfolga11);
		tv8.setText(pos0[calculo][7]);

		TextView tv9 = (TextView) findViewById(R.id.tvmanha2);
		tv9.setText(pos0[calculo][8]);

		TextView tv10 = (TextView) findViewById(R.id.tvtarde2);
		tv10.setText(pos0[calculo][9]);

		TextView tv11 = (TextView) findViewById(R.id.tvfolga2);
		tv11.setText(pos0[calculo][10]);

		TextView tv12 = (TextView) findViewById(R.id.tvfolga22);
		tv12.setText(pos0[calculo][11]);

		return calculo;
	}

	public int calcula5261(Date dt1) {

		DateTime datacalculo = new DateTime(dt1);
		DateTime datareferencia = new DateTime(1900, 01, 01, 00, 00, 00);
		Days dias = Days.daysBetween(datareferencia, datacalculo);
		int calculo = (dias.getDays()) % 7;

		String[][] pos0 = { { "Segunda-Feira", "1° Dia da Escala" },
				{ "Terça-Feira", "2° Dia da Escala" },
				{ "Quarta-Feira", "3° Dia da Escala" },
				{ "Quinta-Feira", "4° Dia da Escala" },
				{ "Sexta-Feira", "5° Dia da Escala" },
				{ "Sábado", "6° Dia da Escala" },
				{ "Domingo", "7° Dia da Escala" }, };

		TextView tv1 = (TextView) findViewById(R.id.tvdiadasemana);
		tv1.setText(pos0[calculo][0]);

		TextView tv2 = (TextView) findViewById(R.id.tvposicao);
		tv2.setText(pos0[calculo][1]);

		return calculo;
	}

	public int calcula6152(Date dt1) {

		DateTime datacalculo = new DateTime(dt1);
		DateTime datareferencia = new DateTime(1900, 01, 01, 00, 00, 00);
		Days dias = Days.daysBetween(datareferencia, datacalculo);
		int calculo = (dias.getDays()) % 28;

		String[][] pos0 = { { "15", "8", "1 TS", "22" },
				{ "16", "9", "2 TS", "23" }, { "17", "10", "3 TS", "24" },
				{ "18", "11", "4 TS", "25" }, { "19", "12", "5 TS", "26" },
				{ "20", "13", "6 TS", "27" }, { "21", "14", "7", "28" },
				{ "22", "15", "8", "1 TS" }, { "23", "16", "9", "2 TS" },
				{ "24", "17", "10", "3 TS" }, { "25", "18", "11", "4 TS" },
				{ "26", "19", "12", "5 TS" }, { "27", "20", "13", "6 TS" },
				{ "28", "21", "14", "7" }, { "1 TS", "22", "15", "8" },
				{ "2 TS", "23", "16", "9" }, { "3 TS", "24", "17", "10" },
				{ "4 TS", "25", "18", "11" }, { "5 TS", "26", "19", "12" },
				{ "6 TS", "27", "20", "13" }, { "7", "28", "21", "14" },
				{ "8", "1 TS", "22", "15" }, { "9", "2 TS", "23", "16" },
				{ "10", "3 TS", "24", "17" }, { "11", "4 TS", "25", "18" },
				{ "12", "5 TS", "26", "19" }, { "13", "6 TS", "27", "20" },
				{ "14", "7", "28", "21" } };

		TextView tv1 = (TextView) findViewById(R.id.tvsemana1);
		tv1.setText(pos0[calculo][0]);

		TextView tv2 = (TextView) findViewById(R.id.tvsemana2);
		tv2.setText(pos0[calculo][1]);

		TextView tv3 = (TextView) findViewById(R.id.tvsemana3);
		tv3.setText(pos0[calculo][2]);

		TextView tv4 = (TextView) findViewById(R.id.tvsemana4);
		tv4.setText(pos0[calculo][3]);

		return calculo;
	}

}