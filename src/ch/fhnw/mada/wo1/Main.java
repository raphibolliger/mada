package ch.fhnw.mada.wo1;

public class Main {

    public static void main(String[] args){

        U4_Sieb primzahlen = new U4_Sieb(0,1000000);

        primzahlen.makePrim();
        primzahlen.printList();

        System.out.print("Integer.MAXVALUE: ");
        System.out.println(Integer.MAX_VALUE);

    }


}