package be.nmct.howest.stopafstand;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class StopAfstandFragment extends Fragment {

    private SeekBar seekBarSnelheid;
    private SeekBar seekBarReactietijd;
    private TextView textViewSnelheid;
    private TextView textViewReactietijd;
    private RadioButton radioButtonDroog;
    private RadioButton radioButtonNat;
    private Button buttonBereken;
    private TextView textViewResultaat;
    private StopAfstandInfo.WegType wegType = StopAfstandInfo.WegType.WEGDEK_DROOG;
    private StopAfstandInfo sa = null;
    private int snelheid = 200;
    private float reactieTijd = 5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_stopafstand, container, false);

        this.seekBarSnelheid = (SeekBar) v.findViewById(R.id.seekBarSnelheid);
        this.seekBarReactietijd = (SeekBar) v.findViewById(R.id.seekBarReactietijd);
        this.textViewSnelheid = (TextView) v.findViewById(R.id.textViewSnelheid);
        this.textViewReactietijd = (TextView) v.findViewById(R.id.textViewReactietijd);
        this.radioButtonDroog = (RadioButton) v.findViewById(R.id.radioButtonDroog);
        this.radioButtonNat = (RadioButton) v.findViewById(R.id.radioButtonNat);
        this.textViewResultaat = (TextView) v.findViewById((R.id.textViewResult));
        this.buttonBereken = (Button) v.findViewById(R.id.buttonBereken);

        this.buttonBereken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToonStopAfstand(v);
            }
        });

        this.seekBarSnelheid.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String prefix = "";
                snelheid = progress;
                if(snelheid < 10){
                    prefix = "00";
                }else if (snelheid < 100){
                    prefix = "0";
                }
                textViewSnelheid.setText(prefix + snelheid + " km/u");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        this.seekBarReactietijd.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float newValue = getConvertedValue(progress);
                reactieTijd = newValue;
                textViewReactietijd.setText("" + newValue + " s");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

            public float getConvertedValue(int intVal){
                float floatVal = 0.0f;
                floatVal = 0.5f * intVal;
                return floatVal;
            }
        });

        return v;
    }

    private void ToonStopAfstand(View v){

        if(this.radioButtonDroog.isChecked()){
            wegType =  StopAfstandInfo.WegType.WEGDEK_DROOG;
        }else{
            wegType =  StopAfstandInfo.WegType.WEGDEK_NAT;
        }

        sa = new StopAfstandInfo(snelheid, reactieTijd, wegType);

        this.textViewResultaat.setText("" + sa.getStopAfstand());
    }
}
