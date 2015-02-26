package be.howest.nmct;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by Anthony on 20/02/2015.
 */
public class MainFragment extends Fragment{
    private TextView textViewInput;
    private EditText editTextHeight;
    private EditText editTextMass;
    private Button buttonUpdate;
    private TextView textViewBmiInfo;
    private ImageView imageViewCategory;
    private TextView textViewIndex;
    private TextView textViewIndexWaarde;
    private TextView textViewCategory;
    private TextView textViewCategoryWaarde;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        textViewInput = (TextView)v.findViewById(R.id.textViewInput);
        editTextHeight = (EditText)v.findViewById(R.id.editTextHeight);
        editTextMass = (EditText)v.findViewById(R.id.editTextMass);
        buttonUpdate = (Button)v.findViewById(R.id.button);
        textViewBmiInfo = (TextView)v.findViewById(R.id.textViewBmiInfo);
        imageViewCategory = (ImageView)v.findViewById(R.id.imageViewCategory);
        textViewIndex = (TextView)v.findViewById(R.id.textViewIndex);
        textViewIndexWaarde = (TextView)v.findViewById(R.id.textViewIndexWaarde);
        textViewCategory = (TextView)v.findViewById(R.id.textViewCategory);
        textViewCategoryWaarde = (TextView)v.findViewById(R.id.textViewCategoryWaarde);

        buttonUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //....
               berekenBmi();
            }

        });
        return v;
    }

    private void berekenBmi(){
        float lengte = 0.0F;
        int gewicht = 0;
        boolean validation = true;

        // Lengte:
        if(tryParseFloat(editTextHeight.getText().toString())){
            lengte = Float.parseFloat(editTextHeight.getText().toString());
        }
        else{
            validation = false;
        }

        // Gewicht
        if(tryParseInt(editTextMass.getText().toString())){
            gewicht = Integer.parseInt(editTextMass.getText().toString());
        }
        else{
            validation = false;
        }

        if(validation==true){
            // BMI berekenen:
            BMIInfo bmi = new BMIInfo(lengte, gewicht);
            float waarde = bmi.getBmiIndex();
            textViewIndexWaarde.setText(""+waarde);

            BMIInfo.Category c1 = bmi.getCategory(waarde);
            textViewCategoryWaarde.setText(""+c1);

            switch(c1){
                case LARGE_UNDERWEIGHT:
                    imageViewCategory.setImageDrawable(getResources().getDrawable(R.drawable.silhouette_1));
                    break;
                case MEDIUM_UNDERWEIGHT:
                    imageViewCategory.setImageDrawable(getResources().getDrawable(R.drawable.silhouette_2));
                    break;
                case UNDERWEIGHT:
                    imageViewCategory.setImageDrawable(getResources().getDrawable(R.drawable.silhouette_3));
                    break;
                case NORMAL:
                    imageViewCategory.setImageDrawable(getResources().getDrawable(R.drawable.silhouette_4));
                    break;
                case OVERWEIGHT:
                    imageViewCategory.setImageDrawable(getResources().getDrawable(R.drawable.silhouette_5));
                    break;
                case MODERATE_OVERWEIGHT:
                    imageViewCategory.setImageDrawable(getResources().getDrawable(R.drawable.silhouette_6));
                    break;
                case MEDIUM_OVERWEIGHT:
                    imageViewCategory.setImageDrawable(getResources().getDrawable(R.drawable.silhouette_7));
                    break;
                case LARGE_OVERWEIGHT:
                    imageViewCategory.setImageDrawable(getResources().getDrawable(R.drawable.silhouette_8));
                    break;
            }
        }
        else{
            Toast.makeText(getActivity(), "Gelieve alle velden correct in te voeren!", Toast.LENGTH_LONG).show();
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
}
