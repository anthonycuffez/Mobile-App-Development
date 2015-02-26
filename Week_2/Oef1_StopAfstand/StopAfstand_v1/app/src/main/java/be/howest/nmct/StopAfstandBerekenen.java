package be.howest.nmct;
/**
 * Created by Anthony on 13/02/2015.
 */
public class StopAfstandBerekenen {
    //CONSTRUCTOR:
    private String name;
    public StopAfstandBerekenen(String name){
        this.name = name;
        System.out.println(name+" created");
    }
    public StopAfstandBerekenen(int snelheid, float reactietijd, Wegtype wegtype){
        this.snelheid = snelheid;
        this.reactietijd = reactietijd;
        this.wegtype = wegtype;
    }

    //PROPERTIES:
    //SNELHEID
    private int snelheid;
    public int getSnelheid(){
        return snelheid;
    }
    public void setSnelheid(int snelheid){
        this.snelheid = snelheid;
    }

    //REACTIETIJD
    private float reactietijd;
    public float getReactietijd(){
        return reactietijd;
    }
    public void setReactietijd(float reactietijd){
        this.reactietijd = reactietijd;
    }

    //STOPAFSTAND
    private float stopafstand;
    public float getStopafstand(){
        // berekent de stopafstand volgens een formule en geeft deze ook terug
        // formule = STOPAFSTAND = REACTIEWEG + REMWEG
        //           STOPAFSTAND = SNELHEID * REACTIETIJD + (SNELHEID* 2)/ (2* remvertraging)
        double s = snelheid / 3.6;
        double reactieweg = snelheid * reactietijd;
        double remvertraging = 0;
        switch(wegtype){
            case WEGDEK_DROOG:
                remvertraging = 8;
                break;
            case WEGDEK_NAT:
                remvertraging = 5;
                break;
        }
        double remweg = (snelheid * snelheid) / (2* remvertraging);
        stopafstand = (float)remweg;
        return stopafstand;
    }
    public void setStopafstand(float stopafstand){
        this.stopafstand = stopafstand;
    }

    //WEGTYPE
    private Wegtype wegtype;
    public Wegtype getWegtype(){
        return wegtype;
    }
    public void setWegtype(Wegtype wegtype){
        this.wegtype = wegtype;
    }
    public enum Wegtype {
        WEGDEK_DROOG,WEGDEK_NAT;
    }



}
