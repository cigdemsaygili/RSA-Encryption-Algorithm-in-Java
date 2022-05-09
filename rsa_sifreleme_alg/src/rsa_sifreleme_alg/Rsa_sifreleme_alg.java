package rsa_sifreleme_alg;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Rsa_sifreleme_alg {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random rnd = new Random();
        ArrayList liste1 = new ArrayList();
        final BigInteger p = generating_p();
        final BigInteger q = generating_q();
        final BigInteger phi = generate_totient(p, q);
        final BigInteger e = BigInteger.probablePrime(11, rnd);
        final BigInteger n = generating_n(p, q);
        final BigInteger d = generate_d(phi, e);
    
        ArrayList liste = new ArrayList();
        ArrayList list = new ArrayList();
        ArrayList sifir = new ArrayList();

        System.out.println("e=" + generate_e(e, phi));
        int Lcipher = nbasamaksayisi(n);
        int Lclear = nbasamaksayisi(n) - 1;
        System.out.println("Lcipher=" + Lcipher);//Lcipher
        System.out.println("Lclear=" + Lclear);//LcipherF
        Scanner scan = new Scanner(System.in);
  
        System.out.println("Şifrelenecek metni giriniz:");
        String metin = scan.nextLine();

        //ascıı
        char[] chars2 = new char[metin.length()];
        chars2 = metin.toCharArray();
        for (int i = 0; i < metin.length(); i++) {

            list.add((int) chars2[i]);

        }
        //ascıı
        System.out.println("ascıı ifadeler= " + list);
        //sifir arraylist
        for (int i = 0; i < 150; i++) {
            sifir.add(0);
        }
        //sifir arraylist

        for (int j = 0; j < list.size(); j++) {
            if (list.get(j).toString().length() < Lclear) {

                for (int i = 0; i < Lclear - list.get(j).toString().length();) {

                    list.set(j, sifir.get(j).toString() + list.get(j).toString());
                }
            }
        }

        System.out.println("sıfırlı list=" + list);
        String butun = "";
        for (int x = 0; x < list.size(); x++) {

            butun += list.get(x).toString();

        }
        System.out.println("butun metin=" + butun);
        System.out.println("Parçalı=" + parca(Lclear, butun));
        String ifade = "";
        String ifadee = "";
        int g = 0;
        for (int y = 0; y < list.size(); y++) {
            ifadee = list.get(y).toString();
            ifade = ifadee.toString();
            g = Integer.valueOf(ifade);
            liste.add(g);

        }
        System.out.println("Liste=" + liste);

        ArrayList a = new ArrayList();
        BigInteger veri;
        BigInteger veri1;
        for (int i = 0; i < liste.size(); i++) {
            veri = BigInteger.valueOf((int) liste.get(i));
            veri1 = veri.pow(e.intValue()).mod(n);
            a.add(veri1);
        }

        System.out.println("Rsa Şifreli = " + a);

        for (int j = 0; j < a.size(); j++) {
            if (a.get(j).toString().length() < Lclear) {

                for (int i = 0; i < Lclear - a.get(j).toString().length();) {

                    a.set(j, a.get(j).toString() + sifir.get(j).toString());
                }
            }
        }

        System.out.println("sağ sıfırlı list=" + a);
        String butun1 = "";
        for (int x = 0; x < a.size(); x++) {

            butun1 += a.get(x).toString();

        }
        System.out.println("butun1 metin=" + butun1);
        System.out.println("parçalı3 = " + parcaa(butun1));

        ArrayList sifre = parcaa(butun1);
        char[] dizi = new char[sifre.size()];
        for (int s = 0; s < sifre.size(); s++) {
            dizi[s] = (char) (Integer.parseInt(sifre.get(s).toString()));
        }
        System.out.println("sifre=");
        System.out.println(dizi);
        String deger4 = "";
        String deger3 = "";
        int g1 = 0;
        ArrayList v = new ArrayList();
        for (int y = 0; y < a.size(); y++) {
            deger3 = a.get(y).toString();
            deger4 = deger3.toString();
            g1 = Integer.valueOf(deger4);
            v.add(g1);

        }
        System.out.println("v="+v);
        ArrayList b = new ArrayList();
        BigInteger verii;
        BigInteger verii1;
        for (int i = 0; i < v.size(); i++) {
            verii = BigInteger.valueOf((int) v.get(i));
            verii1 = verii.pow(d.intValue()).mod(n);
            b.add(verii1);
        }

        System.out.println("Çözüm=" + b);

        char[] girilenHal = new char[b.size()];
        for (int m = 0; m < b.size(); m++) {
            girilenHal[m] = (char) (Integer.parseInt(b.get(m).toString()));
        }
        System.out.println("Girilen Hal=");
        System.out.println(girilenHal);

    }


    private static BigInteger generating_p() {
        BigInteger p;
        Random rnd = new Random();

        p = BigInteger.probablePrime(11, rnd);
        System.out.println("p=" + p);

        return p;
    }

    private static BigInteger generating_q() {

        BigInteger q;
        Random rnd = new Random();

        q = BigInteger.probablePrime(11, rnd);
        System.out.println("q=" + q);
        return q;
    }

    private static BigInteger generating_n(BigInteger p, BigInteger q) {
        BigInteger n;

        n = p.multiply(q);

        System.out.println("n=" + n);
        return n;
    }

    private static BigInteger generate_totient(BigInteger p, BigInteger q) {
        BigInteger sayi = p.subtract(BigInteger.ONE);
        BigInteger sayiiki = q.subtract(BigInteger.ONE);
        BigInteger phi = sayi.multiply(sayiiki);
        System.out.println("phi=" + phi);

        return phi;
    }

    private static BigInteger generate_e(BigInteger e, BigInteger phi) {
        Random rnd = new Random();
        BigInteger bir = BigInteger.ONE;

        if (e.compareTo(bir) > 0 && e.compareTo(phi) < 0) {
            e.add(BigInteger.probablePrime(11, rnd));
        }

        return e;
    }
 //ed=1 mod(phi)
    public static BigInteger generate_d(BigInteger phi, BigInteger e) {
        return e.modInverse(phi);
    }

    private static ArrayList parca(int Lclear, String butun) {
        ArrayList sub = new ArrayList();
        int x = 0;
        int y = Lclear;
        for (int k = 0; k < butun.length() / Lclear + 1; k++) {
            if (x <= butun.length() && y <= butun.length()) {
                sub.add(butun.substring(x, y));
                x = x + Lclear;
                y = y + Lclear;

                if (butun.length() - x <= Lclear) {

                    sub.add(butun.substring(x));
                    break;
                }
            }
        }
        return sub;

    }

    private static ArrayList parcaa(String butun) {
        ArrayList sub = new ArrayList();
        int x = 0;
        int y = 3;
        for (int k = 0; k < butun.length() / 3 + 1; k++) {
            if (x <= butun.length() && y <= butun.length()) {
                sub.add(butun.substring(x, y));
                x = x + 3;
                y = y + 3;

                if (butun.length() - x <= 3) {

                    sub.add(butun.substring(x));
                    break;
                }
            }
        }
        return sub;

    }

    private static int nbasamaksayisi(BigInteger n) {
        int nb = 0;
        int x = n.intValue();
        while (x > 0) {
            x = x / 10;
            nb++;
        }

        return nb;
    }
}
