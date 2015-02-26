package be.howest.nmct;

import java.util.Scanner;

/**
 * Created by Anthony on 13/02/2015.
 */
public class RunBMI {
    public static void main(String [] args){
        System.out.println("BMI Calculator");
        Scanner sc = new Scanner(System.in);

        //Lengte vragen ...
        System.out.println("Lengte(m)?");
        float lengte = sc.nextFloat();

        //Gewicht vragen ...
        System.out.println("Gewicht(kg)");
        int gewicht = sc.nextInt();

        //BMI berekenen:
        BMIInfo bmi = new BMIInfo(lengte, gewicht);
        float waarde = bmi.getBmiIndex();
        System.out.println("Uw BMI = " + waarde);

        // Categorie:
        BMIInfo.Category c1 = bmi.getCategory(waarde);
        System.out.println("Categorie = " + c1);
    }

}
