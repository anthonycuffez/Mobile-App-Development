package be.howest.nmct;

import java.util.Scanner;

/**
 * Created by Anthony on 13/02/2015.
 */
public class TestStopAfstandInfo {
    public static void main (String [] args){
        System.out.println("StopAfstandInfo: ");
        Scanner sc = new Scanner(System.in);

        //Snelheid vragen ...
        System.out.println("Gereden snelheid?");
        int snelheid = sc.nextInt();

        //Reactietijd vragen ...
        System.out.println("Wat is de reactietijd?");
        float reactietijd = sc.nextFloat();

        //Wegdek vragen ...
        System.out.println("Welke toestand had het wegdek (DROOG = 1; NAT = 2)?");
        int wegdek = sc.nextInt();
        StopAfstandInfo.Wegtype wegtype;
        if(wegdek == 1){
            wegtype = StopAfstandInfo.Wegtype.WEGDEK_DROOG;
        }
        else if(wegdek == 2){
            wegtype = StopAfstandInfo.Wegtype.WEGDEK_NAT;
        }
        else{
            System.out.println("Er is een fout opgetreden: 'Gelieve een juiste waarde in te voeren!'.");
            return;
        }

        //StopAfstandInfo opvragen:
        StopAfstandInfo afstandinfo1 = new StopAfstandInfo(snelheid,reactietijd, wegtype);
        float stopAfstand1 = afstandinfo1.getStopafstand();
        System.out.println("De stopafstand = "+stopAfstand1 +" m.");
    }
}
