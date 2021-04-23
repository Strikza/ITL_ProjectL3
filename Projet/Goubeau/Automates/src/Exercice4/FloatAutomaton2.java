package Exercice4;

import java.util.Scanner;

public class FloatAutomaton2 {

    ////////////////////////////
    //  ATTRIBUTES
    ////////////////////////////

    private boolean isNegative;
    private boolean isReel;
    private float integers;
    private float decimals;
    private int i;
    private String m;
    private final Scanner scan;


    ////////////////////////////
    //  CLASS METHODS
    ////////////////////////////

    public void initValues(){
        this.isNegative = false;
        this.integers = 0;
        this.decimals = 0;
        this.i = 0;
    }

    public FloatAutomaton2(){
        this.initValues();
        this.scan = new Scanner(System.in);
    }

    public String getM(){
        return this.m;
    }

    public void setMScan(){
        System.out.print(" Veuillez entrer un réel ('stop' to finish) : ");
        this.m = this.scan.next();
    }

    public void setM(String m){
        this.m = m;
    }


    /////////////////////////////////
    //  PARTIE EX4 : Gestion du réel
    /////////////////////////////////

    public void itsNegative(char c){
        if(c == '-')
            this.isNegative = true;
    }

    public void manageIntegers(char c){
        float tmp = Float.parseFloat("" + c);
        this.integers = (this.integers * 10) + tmp;
    }

    public void manageDecimals(char c){
        float tmp = Float.parseFloat("" + c);
        this.decimals = (this.decimals * 10) + tmp;
        this.i += 1;
    }


    ////////////////////////////
    //  ÉTATS
    ////////////////////////////

    // État 3 : État terminal
    private boolean reconnaitRec_3(String m){
        if(m.isEmpty()){
            return true;
        }
        else{
            char c = m.charAt(0);

            if(c>='0' && c<='9'){
                this.manageDecimals(c);
                return this.reconnaitRec_3(m.substring(1));
            }
            else{
                return false;
            }
        }
    }

    // État 2
    private boolean reconnaitRec_2(String m){
        if(m.isEmpty()){
            return false;
        }
        else{
            char c = m.charAt(0);

            if(c>='0' && c<='9'){
                this.manageIntegers(c);
                return this.reconnaitRec_2(m.substring(1));
            }
            if(c == '.'){
                return this.reconnaitRec_3(m.substring(1));
            }
            else{
                return false;
            }
        }
    }

    //État 1
    private boolean reconnaitRec_1(String m) {
        if (m.isEmpty()) {
            return false;
        } else {
            char c = m.charAt(0);

            if (c >= '0' && c <= '9') {
                this.manageIntegers(c);
                return this.reconnaitRec_2(m.substring(1));
            }
            else
                return false;
        }
    }

    //État 0 : État initial
    private boolean reconnaitRec_0(String m) {
        if (m.isEmpty()) {
            return false;
        } else {
            char c = m.charAt(0);

            if(c == '+' || c == '-'){
                this.itsNegative(c);
                return this.reconnaitRec_1(m.substring(1));
            }
            if (c >= '0' && c <= '9') {
                this.manageIntegers(c);
                return this.reconnaitRec_2(m.substring(1));
            }
            else
                return false;
        }
    }


    ////////////////////////////
    //  FINAL METHODS
    ////////////////////////////

    public void reconnaitReelRec(String mot){
        this.initValues();
        boolean res = this.reconnaitRec_0(mot);

        if(res){
            this.isReel = true;
            System.out.println("VRAI : Le mot est bien un réel !");
        }
        else{
            this.isReel = false;
            System.out.println("FAUX : Le mot n'est pas un réel !");
        }
    }

    public void evalueRecReel(String mot){
        this.reconnaitReelRec(mot);

        if(this.isReel){
            for(int j=0; j<this.i; j++){
                this.decimals *= 0.1;
            }

            float val;
            if(isNegative){
                val = (this.integers + this.decimals) * -1;
            }
            else{
                val = this.integers + this.decimals;
            }

            System.out.println("Valeur réel du mot : " + val);
        }
    }


    ////////////////////////////
    //  MAIN
    ////////////////////////////

    public static void main(String [] args){
        String [] testReel2 = {"3.14", "002.24", "-3.14", "1000.14", "123.", "123.45", "-123.", "+123.34", "-123.34", "12A3.34", "123..33", "123.34.44", ".34"};
        FloatAutomaton2 fa2 = new FloatAutomaton2();

        for(int i=0; i<testReel2.length; i++){
            fa2.evalueRecReel(testReel2[i]);
        }

        fa2.setMScan();

        while(!fa2.getM().equals("stop")){
            fa2.evalueRecReel(fa2.m);
            fa2.setMScan();
        }

        System.out.println("Le programme se termine");
    }
}
