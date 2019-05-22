package br.com.metro.escala;

import android.app.*;
import android.os.*;
import android.content.*;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;

public class EscalaActivity extends Activity{

    Button btn424;
    Button btn4143;
    Button btn42410;
    Button btn4264;
    Button btn6123;
    Button btn6152;
    Button btn5261;
    private TextView pDisplayDate;
    private Button pPickDate;
    private int pYear;
    private int pMonth;
    private int pDay;
    public String mensagem = "";
    public static Date data = null;

    /**
     * This integer will uniquely define the dialog to be used for displaying date picker.
     */
    static final int DATE_DIALOG_ID = 0;

    /** Callback received when the user "picks" a date in the dialog */
    private DatePickerDialog.OnDateSetListener pDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    pYear = year;
                    pMonth = monthOfYear;
                    pDay = dayOfMonth;
                    updateDisplay();
                    displayToast();
                }
            };

    /** Updates the date in the TextView */
    private void updateDisplay() {
        pDisplayDate.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(pDay).append("/")
                        .append(pMonth + 1).append("/")
                        .append(pYear));
    }

    /** Displays a notification when the date is updated */
    private void displayToast() {
        Toast.makeText(this, new StringBuilder().append("Data escolhida é ").append(pDisplayDate.getText()),  Toast.LENGTH_SHORT).show();
    }

    public void atualizaData(){

        Date data1 = new Date();
        Calendar c1 = Calendar.getInstance();

        //Pega a segunda data
        c1.set (pYear, pMonth, pDay);
        data1.setTime(c1.getTimeInMillis());
        data = c1.getTime();
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.escalas);

        btn424 = (Button) findViewById(R.id.btn424);
        btn424.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                atualizaData();
                Intent i = new Intent();
                i.setClass (EscalaActivity.this, MostraEscalaActivity.class);
                Bundle params = new Bundle();
                params.putString("mensagem","424");
                i.putExtras(params);
                startActivity(i);
            }
        });

        btn4143 = (Button) findViewById(R.id.btn4143);
        btn4143.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                atualizaData();
                Intent i = new Intent();
                i.setClass (EscalaActivity.this, MostraEscalaActivity.class);
                Bundle params = new Bundle();
                params.putString("mensagem", "4143");
                i.putExtras(params);
                startActivity(i);
            }
        });

        btn5261 = (Button) findViewById(R.id.btn5261);
        btn5261.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                atualizaData();
                Intent i = new Intent();
                i.setClass (EscalaActivity.this, MostraEscalaActivity.class);
                Bundle params = new Bundle();
                params.putString("mensagem", "5261");
                i.putExtras(params);
                startActivity(i);
            }
        });

        btn42410 = (Button) findViewById(R.id.btn42410);
        btn42410.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                atualizaData();
                Intent i = new Intent();
                i.setClass (EscalaActivity.this, MostraEscalaActivity.class);
                Bundle params = new Bundle();
                params.putString("mensagem", "42410");
                i.putExtras(params);
                startActivity(i);
            }
        });

        btn4264 = (Button) findViewById(R.id.btn4264);
        btn4264.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                atualizaData();
                Intent i = new Intent();
                i.setClass (EscalaActivity.this, MostraEscalaActivity.class);
                Bundle params = new Bundle();
                params.putString("mensagem", "4264");
                i.putExtras(params);
                startActivity(i);
            }
        });

        btn6123 = (Button) findViewById(R.id.btn6123);
        btn6123.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                atualizaData();
                Intent i = new Intent();
                i.setClass (EscalaActivity.this, MostraEscalaActivity.class);
                Bundle params = new Bundle();
                params.putString("mensagem", "6123");
                i.putExtras(params);
                startActivity(i);
            }
        });

        btn6152 = (Button) findViewById(R.id.btn6152);
        btn6152.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                atualizaData();
                Intent i = new Intent();
                i.setClass (EscalaActivity.this, MostraEscalaActivity.class);
                Bundle params = new Bundle();
                params.putString("mensagem", "6152");
                i.putExtras(params);
                startActivity(i);
            }
        });

        /** Capture our View elements */
        pDisplayDate = (TextView) findViewById(R.id.displayDate);
        pPickDate = (Button) findViewById(R.id.pickDate);

        /** Get the current date */
        final Calendar cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
        pMonth = cal.get(Calendar.MONTH);
        pDay = cal.get(Calendar.DAY_OF_MONTH);

        /** Listener for click event of the button */
        pPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        /** Display the current date in the TextView */
        updateDisplay();
    }

    /** Create a new dialog for date picker */
    @Override
    protected Dialog onCreateDialog ( int id){
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        pDateSetListener,
                        pYear, pMonth, pDay);
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.sobre) {
        	LayoutInflater layoutInflater = getLayoutInflater();
        	 
        	int layout = R.layout.toast;
        	ViewGroup viewGroup = (ViewGroup) findViewById(R.id.toast_layout_root);
        	View view = layoutInflater.inflate(layout, viewGroup);
        	 
        	TextView tv_texto = (TextView) view.findViewById(R.id.base);
        	tv_texto.setText("Telefone do Tráfego");
        	 
        	Toast toast = new Toast(this);
        	toast.setDuration(Toast.LENGTH_LONG);
        	toast.setView(view);
        	toast.show();
        }
		return false;
    }
    
}
