package ch.fhnw.mada.wo1;

import java.util.ArrayList;

public class U4_Sieb {

    private ArrayList<Integer> zahlen;

    public U4_Sieb(int start, int end)
    {
        zahlen = new ArrayList<Integer>();
        for (int i = start; i <= end; i++)
        {
            this.zahlen.add(i);
        }
    }

    public void makePrim()
    {
        for (int i = 0; i < zahlen.size(); i++)
        {
            if (this.zahlen.get(i) == 0) this.zahlen.remove(i);
            if (this.zahlen.get(i) == 1) this.zahlen.remove(i);
        }

        for (int i = 2; i < zahlen.size(); i++)
        {
            if ((this.zahlen.get(i) % 2) == 0) this.zahlen.remove(i);
        }

        for (int i = 3; i < zahlen.size(); i++)
        {
            if ((this.zahlen.get(i) % 3) == 0) this.zahlen.remove(i);
        }

        for (int i = 5; i < zahlen.size(); i++)
        {
            if ((this.zahlen.get(i) % 5) == 0) this.zahlen.remove(i);
        }

        for (int i = 7; i < zahlen.size(); i++)
        {
            if ((this.zahlen.get(i) % 7) == 0) this.zahlen.remove(i);
        }
    }

    public void printList()
    {
        for (int element : this.zahlen)
        {
            System.out.println(element);
        }

        System.out.println();
        System.out.print("Anzahl Primzahlen: ");
        System.out.println(this.zahlen.size());
    }

}

