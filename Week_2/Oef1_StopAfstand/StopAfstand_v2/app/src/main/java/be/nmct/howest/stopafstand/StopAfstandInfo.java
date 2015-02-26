package be.nmct.howest.stopafstand;

public class StopAfstandInfo {

    private int snelheid;
    private float reactietijd;
    private float stopafstand;
    private WegType wegtype = WegType.WEGDEK_DROOG;

    public static enum WegType {  WEGDEK_DROOG, WEGDEK_NAT }

    public int getSnelheid() {
        return snelheid;
    }

    public void setSnelheid(int snelheid) {
        this.snelheid = snelheid;
    }

    public float getReactietijd() {
        return reactietijd;
    }

    public void setReactietijd(float reactietijd) {
        this.reactietijd = reactietijd;
    }

    public float getStopafstand() {
        return stopafstand;
    }

    public void setStopafstand(float stopafstand) {
        this.stopafstand = stopafstand;
    }

    public StopAfstandInfo(int snelheid, float reactietijd, WegType wegtype) {
        this.snelheid = snelheid;
        this.reactietijd = reactietijd;
        this.wegtype = wegtype;
    }

    public StopAfstandInfo(){}

    public float getStopAfstand(){

        int remvertraging = 0;

        if(this.wegtype.equals(WegType.WEGDEK_DROOG)){
            remvertraging = 8;
        }else{
            remvertraging = 5;
        }

        float reactieweg = (float) (snelheid / 3.6) * reactietijd;
        float remweg = (float) Math.pow(snelheid / 3.6, 2) / (2 * remvertraging);

        return reactieweg + remweg;
    }

    @Override
    public String toString() {
        return "StopAfstandInfo{" +
                "snelheid=" + snelheid +
                ", reactietijd=" + reactietijd +
                ", stopafstand=" + stopafstand +
                '}';
    }
}