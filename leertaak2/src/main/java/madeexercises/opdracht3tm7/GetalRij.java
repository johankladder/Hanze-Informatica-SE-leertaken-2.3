package madeexercises.opdracht3tm7;

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

    /**
     * Methods thats is lookinh if an int is in the array. In combination with a sorted array
     *
     * @param zoekWaarde
     * @return
     */
    public boolean zitErinC(int zoekWaarde) {
        sorteer();
        return zitErinB(zoekWaarde);
    }

    public boolean zitErinD(int zoekWaarde) {
        sorteer();
        int retval = Arrays.binarySearch(getallen, zoekWaarde);
        if(retval > -1) {
            return true;
        }
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
    public static Map<String, Long> testTime(final Integer value, boolean multipleCore) {

        final Map<String, Long> map = new HashMap<String, Long>();
        final long[] tDeltaA = {0};
        final long[] tDeltaB = {0};
        final long[] tDeltaC = {0};
        final long[] tDeltaD = {0};

        final Thread thread1 = new Thread(new Runnable() {
            public void run() {
                long tStart = System.currentTimeMillis();
                GetalRij rij = new GetalRij(10000, 1000000);
                rij.zitErinA(value);
                long tEnd = System.currentTimeMillis();
                long tDelta = tEnd - tStart;
                tDeltaA[0] = tDelta;
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                long tStart = System.currentTimeMillis();

                GetalRij rij = new GetalRij(10000, 1000000);
                rij.zitErinB(value);
                long tEnd = System.currentTimeMillis();
                long tDelta = tEnd - tStart;
                tDeltaB[0] = tDelta;

            }
        });

        Thread thread3 = new Thread(new Runnable() {
            public void run() {
                long tStart = System.currentTimeMillis();

                GetalRij rij = new GetalRij(10000, 1000000);
                rij.zitErinC(value);
                long tEnd = System.currentTimeMillis();
                long tDelta = tEnd - tStart;
                tDeltaC[0] = tDelta;
            }
        });

        Thread thread4 = new Thread(new Runnable() {
            public void run() {
                long tStart = System.currentTimeMillis();

                GetalRij rij = new GetalRij(10000, 1000000);
                rij.zitErinC(value);
                long tEnd = System.currentTimeMillis();
                long tDelta = tEnd - tStart;
                tDeltaD[0] = tDelta;
            }
        });

        Thread[] arrayForThread = {thread1, thread2, thread3, thread4};
        try {
            // Start all the threads:
            for (int i = 0; i < arrayForThread.length; i++) {
                Thread tempThread = arrayForThread[i];
                tempThread.start();
                if(!multipleCore) {
                    tempThread.join();
                }
            }

            if(multipleCore) {
                // For split all the processes on different cpu's:
                for (int i = 0; i < arrayForThread.length; i++) {
                    Thread tempThread = arrayForThread[i];
                    tempThread.join();
                }
            }

            map.put("a", tDeltaA[0]);
            map.put("b", tDeltaB[0]);
            map.put("c", tDeltaC[0]);
            map.put("d", tDeltaD[0]);
            return map;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
