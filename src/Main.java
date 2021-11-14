import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {

    public static ArrayList<BigInteger> primeList = new ArrayList<BigInteger>();


    public static void main(String[] args) throws IOException {

        //String n = "85";
        //String n          = "243525234415290226117756567";   //88 Bits          // 12 Dezimalstellen noch berechenbar, danach wirds schwer...
        String n          = "2824768343";
        String challengeN = "275398901700898900724918474136345950999";


        //loadPrimeList();
        long startTime = System.currentTimeMillis();
        factorize3(n);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Berechnungsdauer in Minuten: " + elapsedTime/60000.0);

    }


    /*
     * method for loading a list of known prime numbers into memory
     */
    /*public static void loadPrimeList() {
        System.out.println("loading list of prime numbers ...");
        Path path = Paths.get("resources/Prime Numbers/P1/P1.TXT");
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line = null;
            while ((line = br.readLine()) != null) {
                BigInteger prime = new BigInteger(line);
                primeList.add(prime);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("done loading list\n");
    } */

    // aktuellste methode
    /*public static void loadPrimeList() {
        System.out.println("loading list of prime numbers ...");
        Path path = Paths.get("resources/Prime Numbers/Ate_100G_part2/Ate_100G_part2.txt");
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                for (int i = 0; i < 10; i++) {
                    BigInteger prime = new BigInteger(values[i]);
                    primeList.add(prime);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("done loading list\n");
    }*/


    /*
     * method for factorizing a given n using a list of known prime numbers
     */
    /*public static void factorize(String n_str) {
        System.out.println("factorizing n = " + n_str + " ...");
        BigInteger n = new BigInteger(n_str);
        BigInteger zero = BigInteger.ZERO;
        for (BigInteger p : primeList) {
            if (n.mod(p).equals(zero)) {
                BigInteger q = n.divide(p);
                System.out.println("factorization completed!");
                System.out.println("p = " + p);
                System.out.println("q = " + q);
                return;
            }
        }
        System.out.println("no factorization has been found");
    }*/


    /*public static void factorize(String n_str) {
        System.out.println("factorizing n = " + n_str + " ...");
        BigInteger n = new BigInteger(n_str);
        BigInteger zero = BigInteger.ZERO;
        BigInteger p = new BigInteger("2");
        while (true) {
            if (n.mod(p).equals(zero)) {
                BigInteger q = n.divide(p);
                System.out.println("factorization completed!");
                System.out.println("p = " + p);
                System.out.println("q = " + q);
                return;
            }
            p = p.nextProbablePrime();
            System.out.println(p);
        }
        //System.out.println("no factorization has been found");
    }*/


    /*
     * method for factorizing a given n
     */
    public static void factorize(String n_str) throws IOException {
        System.out.println("factorizing n = " + n_str + " ...");
        BigInteger n = new BigInteger(n_str);
        BigInteger zero = BigInteger.ZERO;
        BigInteger two = new BigInteger("2");
        String startValue = "100000000027";
        //String startValue = "3";
        BigInteger p = new BigInteger(startValue);
        while (true) {
            if (n.mod(p).equals(zero)) {
                BigInteger q = n.divide(p);
                System.out.println("factorization completed!");
                System.out.println("p = " + p);
                System.out.println("q = " + q);
                return;
            }
            p = p.add(two);
        }
        //System.out.println("no factorization has been found");
    }



    // überspringt 3er und 5er Vielfache               ------------------------------
    /*
    * method for factorizing a given n
    */
    public static void factorize2(String n_str) throws IOException {
        System.out.println("factorizing n = " + n_str + " ...");
        BigInteger n = new BigInteger(n_str);
        BigInteger zero = BigInteger.ZERO;
        BigInteger two = new BigInteger("2");
        BigInteger five = new BigInteger("5");
        BigInteger mask = new BigInteger("7");
        //String startValue = "100000000011";         // Vielfaches von 3 und ungerade
        String startValue = "10231750000027";
        //String startValue = "9";
        BigInteger p = new BigInteger(startValue);
        int stepCounter = 0;
        while (true) {
            /*if (stepCounter == 3) {
                stepCounter = 1;

            }*/
            if (p.mod(five).equals(zero)) {
                stepCounter += 1;
            }
            else if (n.mod(p).equals(zero)) {
                BigInteger q = n.divide(p);
                System.out.println("factorization completed!");
                System.out.println("p = " + p);
                System.out.println("q = " + q);
                return;
            }
            else {
                stepCounter += 1;
            }
            p = p.add(two);
        }
        //System.out.println("no factorization has been found");
    }


    // überspringt 3er und 5er Vielfache mithilfe von Patterns              ------------------------------
    /*
    * method for factorizing a given n
    */
    public static void factorize3(String n_str) throws IOException {
        System.out.println("factorizing n = " + n_str + " ...");
        BigInteger n = new BigInteger(n_str);
        BigInteger zero = BigInteger.ZERO;
        BigInteger two = new BigInteger("2");
        //String startValue = "100000000027";         // Vielfaches von 30 +7 und ungerade
        //String startValue = "10007500000027";
        String startValue = "7";
        BigInteger p = new BigInteger(startValue);

        System.out.println("Startwert p*p = " + p.multiply(p) + "\n");

        int stepCounter = 0;
        ArrayList<BigInteger> stepIncrements = new ArrayList<BigInteger>();
        BigInteger four = new BigInteger("4");
        BigInteger six = new BigInteger("6");
        BigInteger seven = new BigInteger("7");
        stepIncrements.add(four);
        stepIncrements.add(two);
        stepIncrements.add(four);
        stepIncrements.add(two);
        stepIncrements.add(four);
        stepIncrements.add(six);
        stepIncrements.add(two);
        stepIncrements.add(six);
        while (true) {
            long startTime = System.currentTimeMillis();

            System.out.println("testing  " + p + "\n" + "             ...");
            for (int i = 0; i < 100000000; i++) {           // print p every 100000000 tests

                if (n.remainder(p).equals(zero)) {          // if n mod p == 0
                    BigInteger q = n.divide(p);
                    System.out.println("\nfactorization completed!");
                    System.out.println("p = " + p);
                    System.out.println("q = " + q);
                    return;
                }

                p = p.add(stepIncrements.get(stepCounter));

                if (stepCounter != 7) {
                    stepCounter += 1;
                }
                else {
                    stepCounter = 0;
                }
            }
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println("Berechnungsdauer in Sekunden: " + elapsedTime/1000.0);

        }
        //System.out.println("no factorization has been found");
    }


}
