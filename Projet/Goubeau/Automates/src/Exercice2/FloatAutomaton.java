package Exercice2;

import java.util.Scanner;

public class FloatAutomaton {

    ////////////////////////////
    //  ATTRIBUTES
    ////////////////////////////

    private String m;
    private final Scanner scan;


    ////////////////////////////
    //  CLASS METHODS
    ////////////////////////////

    public FloatAutomaton(){
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
                return this.reconnaitRec_1(m.substring(1));
            }
            if (c >= '0' && c <= '9') {
                return this.reconnaitRec_2(m.substring(1));
            }
            else
                return false;
        }
    }


    ////////////////////////////
    //  FINAL METHOD
    ////////////////////////////

    public void reconnaitReelRec(String mot){
        boolean res = this.reconnaitRec_0(mot);

        if(res){
            System.out.println("VRAI : Le mot est bien un réel !");
        }
        else{
            System.out.println("FAUX : Le mot n'est pas un réel !");
        }
    }


    ////////////////////////////
    //  MAIN
    ////////////////////////////

    public static void main(String [] args){
        String [] testReel = {"123.", "123.45", "-123", "+123.34", "-123.34", "12A3.34", "123..33", "123.34.44", ".34"};
        FloatAutomaton fa = new FloatAutomaton();

        for(int i=0; i<testReel.length; i++){
            fa.reconnaitReelRec(testReel[i]);
        }

        fa.setMScan();

        while(!fa.getM().equals("stop")){
            fa.reconnaitReelRec(fa.m);
            fa.setMScan();
        }

        System.out.println("Le programme se termine");
    }
}
