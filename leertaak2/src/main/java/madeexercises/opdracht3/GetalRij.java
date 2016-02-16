package madeexercises.opdracht3;

import java.util.*;


public class GetalRij {
    private int[] getallen;

    public GetalRij(int aantal, int max) {
        // Belangrijke aanname: aantal < max, anders kunnen de getallen niet uniek zijn.
        getallen = new int[aantal];
        vulArrayMetUniekeWaarden(aantal, max);
    }

    private void vulArrayMetUniekeWaarden(int aantal, int max) {
        // Vul een hulplijst met getallen 0, ..., max
        ArrayList hulpLijst = new ArrayList(max);
        for (int i = 0; i < max; i++) {
            hulpLijst.add(i);
        }

        // Stop 'aantal' random waarden in getallen
        Random r = new Random();
        for (int i = 0; i < aantal; i++) {
            // Het omzetten van Integer naar int gaat sinds Java 1.5 automatisch (unboxing).
            int getal = (Integer) (hulpLijst.remove(r.nextInt(hulpLijst.size())));
            getallen[i] = getal;
        }
    }

    public boolean zitErinA(int zoekWaarde) {
        int counter = 0;
        boolean hit = false;
        while (counter < getallen.length) {
            if (getallen[counter] == zoekWaarde) {
                hit = true;
            }
            counter++;
        }
        return hit;
    }

    public boolean zitErinB(int zoekWaarde) {
        int counter = 0;
        boolean hit = false;
        while (counter < getallen.length && !hit) {
            if (getallen[counter] == zoekWaarde) {
                hit = true;
            }
            counter++;
        }
        return hit;
    }

    public boolean zitErinC(int zoekWaarde) {
        return false;
    }

    public boolean zitErinD(int zoekWaarde) {
        return false;
    }

    public void sorteer() {
        Arrays.sort(getallen);
    }

    public void print() {
        for (int i = 0; i < getallen.length; i++)
            System.out.println(getallen[i]);
    }


    /*
    Test method. This method is used in the test, to indicate which method is the fastest.
     */
    public static Map<String, Long> testTime(final Integer value) {

        Map<String, Long> map = new HashMap<String, Long>();
        final long[] tDeltaA = {0};
        final long[] tDeltaB = {0};

        final Thread thread1 = new Thread(new Runnable() {
            public void run() {
                long tStart = System.currentTimeMillis();
                GetalRij rij = new GetalRij(10000, 1000000);
                boolean status = rij.zitErinA(value);

                long tEnd = System.currentTimeMillis();
                long tDelta = tEnd - tStart;
                tDeltaA[0] = tDelta;
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                long tStart = System.currentTimeMillis();

                GetalRij rij = new GetalRij(10000, 1000000);
                boolean status = rij.zitErinB(value);

                long tEnd = System.currentTimeMillis();
                long tDelta = tEnd - tStart;
                tDeltaB[0] = tDelta;
            }
        });

        try {
            thread1.start();
            thread1.join();
            thread2.start();
            thread2.join();
            map.put("a", tDeltaA[0]);
            map.put("b", tDeltaB[0]);
            return map;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
