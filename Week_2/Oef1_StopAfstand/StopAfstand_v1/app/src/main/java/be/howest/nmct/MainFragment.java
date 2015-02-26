package be.howest.nmct;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by Anthony on 20/02/2015.
 */
public class MainFragment extends Fragment {
    // attributen: de verschillende views:
    private TextView textViewSnelheid;
    private EditText editTextSnelheid;
    private TextView textViewReactietijd;
    private EditText editTextReactietijd;
    private TextView textViewWegtype;
    private RadioButton radioButtonDroogWegdek;
    private RadioButton radioButtonNatWegdek;
    private Button buttonStopafstand;
    private TextView textViewStopafstand;
    private TextView textViewStopafstandWaarde;
    StopAfstandBerekenen stopafstand;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        textViewSnelheid = (TextView)v.findViewById(R.id.textViewWegtype);
        editTextSnelheid = (EditText)v.findViewById(R.id.editTextSnelheid);
        textViewReactietijd = (TextView)v.findViewById(R.id.textViewWegtype);
        editTextReactietijd = (EditText)v.findViewById(R.id.editTextReactieTijd);
        textViewWegtype = (TextView)v.findViewById(R.id.textViewWegtype);
        radioButtonDroogWegdek = (RadioButton)v.findViewById(R.id.radioButtonDroogWegdek);
        radioButtonNatWegdek = (RadioButton)v.findViewById(R.id.radioButtonNatWegdek);
        buttonStopafstand = (Button)v.findViewById(R.id.buttonStopafstand);
        textViewStopafstand = (TextView)v.findViewById(R.id.textViewStopafstand);
        textViewStopafstandWaarde = (TextView)v.findViewById(R.id.textViewStopafstandWaarde);

        buttonStopafstand.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                stopAfstandBerekenen();
            }
        });
        return v;
    }

    private void stopAfstandBerekenen(){
        boolean validation = true;
        int snelheid = 0;
        float reactietijd = Float.parseFloat("0.0");
        int wegdek = 0;
        StopAfstandBerekenen.Wegtype w = null;

        // Snelheid:
        if(tryParseInt(editTextSnelheid.getText().toString())){
            snelheid = Integer.parseInt(editTextSnelheid.getText().toString());
        }
        else{
            validation = false;
            Toast.makeText(getActivity(), "Gelieve een geldige snelheid in te voeren.", Toast.LENGTH_LONG).show();
        }

        // Reactietijd:
        if(tryParseFloat(editTextReactietijd.getText().toString())){
            reactietijd = Float.parseFloat(editTextReactietijd.getText().toString());
        }
        else{
            validation = false;
            Toast.makeText(getActivity(), "Gelieve een geldige reactietijd in te voeren.", Toast.LENGTH_LONG).show();
        }

        // Wegdek:
        if(radioButtonDroogWegdek.isChecked()){
            wegdek = 1;
        }
        else if(radioButtonNatWegdek.isChecked()){
            wegdek = 2;
        }
        else{
            validation = false;
            Toast.makeText(getActivity(), "Gelieve een wegdek te selecteren.", Toast.LENGTH_LONG).show();
        }

        // stopafstandinfo opvragen:
       if(validation == true){
           if(wegdek == 1){
               w = StopAfstandBerekenen.Wegtype.WEGDEK_DROOG;
           }
           else if(wegdek == 2){
               w = StopAfstandBerekenen.Wegtype.WEGDEK_NAT;
           }
           stopafstand = new StopAfstandBerekenen(snelheid,reactietijd,w);
           float stopAfstand1 = stopafstand.getStopafstand();
           textViewStopafstandWaarde.setText(""+stopAfstand1);

       }
       else{

           Toast.makeText(getActivity(), "De berekening kon niet worden gemaakt: gelieve alle velden correct in te voeren....", Toast.LENGTH_LONG).show();
       }

    }

    boolean tryParseInt(String value)
    {
        try
        {
            Integer.parseInt(value);
            return true;
        } catch(NumberFormatException nfe)
        {
            return false;
        }
    }
    boolean tryParseFloat(String value)
    {
        try
        {
            Float.parseFloat(value);
            return true;
        } catch(NumberFormatException nfe)
        {
            return false;
        }
    }

    // kort pop-upbericht:
    // Toast.makeText(getActivity(), "dit is een test",Toast.LENGTH_LONG).show();

}
