package Exercice3;

import java.util.Scanner;

public class NoDeterministicAutomaton {

    ////////////////////////////
    //  ATTRIBUTES
    ////////////////////////////

    private String m;
    private final Scanner scan;


    ////////////////////////////
    //  CLASS METHODS
    ////////////////////////////

    public NoDeterministicAutomaton(){
        this.scan = new Scanner(System.in);
    }

    public String getM(){
        return this.m;
    }

    public void setM(){
        System.out.print(" Veuillez entrer un réel ('stop' to finish) : ");
        this.m = this.scan.next();
    }


    ////////////////////////////
    //  ÉTATS
    ////////////////////////////

    //État 4 : Puit
    private boolean reconnaitRec_4(String m){
        return false; // Quelque soit le mot m, on renvoit false car on est dans unn puit
    }

    //État 3 : État terminal
    private boolean reconnaitRec_3(String m){
        if(m.isEmpty()){
            return true;
        }
        else{
            return this.reconnaitRec_4(m.substring(1));
        }
    }

    //État 2
    private boolean reconnaitRec_2(String m){
        if(m.isEmpty()){
            return false;
        }
        else{
            char c = m.charAt(0);

            if(c == 'a'){
                return this.reconnaitRec_3(m.substring(1));
            }
            if(c == 'c'){
                return this.reconnaitRec_2(m.substring(1));
            }
            else{
                return this.reconnaitRec_4(m.substring(1));
            }
        }
    }

    //État 1
    private boolean reconnaitRec_1(String m){
        if(m.isEmpty()){
            return false;
        }
        else{
            char c = m.charAt(0);

            if(c == 'a'){
                return (this.reconnaitRec_3(m.substring(1)) || this.reconnaitRec_2(m));
            }
            if(c == 'b'){
                return (this.reconnaitRec_1(m.substring(1)) || this.reconnaitRec_2(m));
            }
            if(c == 'c'){
                return this.reconnaitRec_2(m);
            }
            else{
                return false;
            }
        }
    }

    //État 0 : État initial
    private boolean reconnaitRec_0(String m){
        if(m.isEmpty()){
            return false;
        }
        else {
            char c = m.charAt(0);

            if(c == 'a'){
                return (this.reconnaitRec_1(m.substring(1)) || this.reconnaitRec_2(m.substring(1)));
            }
            else{
                return this.reconnaitRec_4(m.substring(1));
            }
        }
    }


    ////////////////////////////
    //  FINAL METHOD
    ////////////////////////////

    public void reconnaitRecL4(String mot){
        boolean res = this.reconnaitRec_0(mot);

        if(res){
            System.out.println("VRAI : Le mot appartient au langage L4 !");
        }
        else{
            System.out.println("FAUX : Le mot n'appartient pas au langage L4 !");
        }
    }


    ////////////////////////////
    //  MAIN
    ////////////////////////////

    public static void main(String [] args){
        String [] testL4 = {"abbcca", "accca" ,"abbccccba"};
        NoDeterministicAutomaton nda = new NoDeterministicAutomaton();

        for(int i=0; i<testL4.length; i++){
            nda.reconnaitRecL4(testL4[i]);
        }

        System.out.println("RAPPEL => L4 : ab*c*a + ac*a");
        nda.setM();

        while(!nda.getM().equals("stop")){
            nda.reconnaitRecL4(nda.m);
            nda.setM();
        }

        System.out.println("Le programme se termine");
    }
}
