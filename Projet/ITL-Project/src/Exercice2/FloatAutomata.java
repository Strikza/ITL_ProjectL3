package Exercice2;

import java.util.Scanner;

public class FloatAutomata {

    private String m;
    private Scanner scan;

    public FloatAutomata(){
        this.scan = new Scanner(System.in);
    }

    public String getM(){
        return this.m;
    }

    public void setM(){
        System.out.print(" Veuillez entrer un réel ('stop' to finish) : ");
        this.m = this.scan.next();
    }

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
            else if(c == '.'){
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
            } else
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
            else if (c >= '0' && c <= '9') {
                return this.reconnaitRec_2(m.substring(1));
            } else
                return false;
        }
    }

    private void reconnaitReelRec(String mot){
        boolean res = this.reconnaitRec_0(mot);

        if(res){
            System.out.println("VRAI : Le mot est bien un réel !");
        }
        else{
            System.out.println("FAUX : Le mot n'est pas un réel !");
        }
    }

    public static void main(String [] args){
        FloatAutomata fa = new FloatAutomata();
        fa.setM();
        while(!fa.getM().equals("stop")){
            fa.reconnaitReelRec(fa.m);
            fa.setM();
        }

        System.out.println("Le programme se termine");
    }
}
