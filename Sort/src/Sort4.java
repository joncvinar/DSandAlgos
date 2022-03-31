package sorting;

import java.util.*;

public class Graph {

    public static int left (int i) {
        return 2*i + 1;

    }

    public static int right (int i) {
        return 2*i + 2;

    }

    public static int parent (int i) {
        return i/2;

    }

    public static int[] max_heapify (int[] array, int heap_size, int i) {

        int highest = i;
        int leftelement = left(i);
        int rightelement = right(i);


        if (leftelement < heap_size && array[leftelement] > array[highest])
            highest = leftelement;


        if (rightelement < heap_size && array[rightelement] > array[highest])
            highest = rightelement;


        if (highest != i)

        {


            int temp = array[i];

            array[i] = array[highest];
            array[highest] = temp;

            max_heapify(array, heap_size, highest);

        }



        return array;

    }


    public static int[] build_heap (int[] array) {
        int n = array.length;
        for (int i = n / 2 - 1; i >= 0; i--)
        {
            max_heapify(array, n, i);
        }
        return array;
    }
    public static int[] heap_sort (int[] array) {
        array = build_heap(array);

        int n = array.length;
        for (int i=n-1; i>=0; i--)
        {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            max_heapify(array, i, 0);
        }
        return array;
    }

    public static int[] quick_sort (int[] array, int p, int r) {
        if (p < r)

        {
            int partitioncount = partition(array, p, r);
            quick_sort(array, p, partitioncount-1);
            quick_sort(array, partitioncount+1, r);
        }
        return array;
    }
    public static int partition (int[] array, int p, int r) {
        int pivot = array[r];

        int i = (p-1);
        for (int j=p; j<r; j++)
        {
            if (array[j] <= pivot)
            {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i+1];
        array[i+1] = array[r];
        array[r] = temp;
        return i+1;
    }

    public static int[] counting_sort (int[] array, int k) {
        int n = array.length;
        int result[] = new int[n];
        int count[] = new int[k+1];
        for (int i=0; i<k+1; ++i)

        {
            count[i] = 0;
        }
        for (int i=0; i<n; ++i) {
            ++count[array[i]];
        }

        for (int i=1; i<=k; ++i) {
            count[i] += count[i-1];
        }
        for (int i = 0; i<n; ++i) {
            result[count[array[i]]-1] = array[i];
            --count[array[i]];
        }
        for (int i = 0; i<n; ++i) {
            array[i] = result[i];
        }
        return array;
    }


    public static int[] generate_random_array (int n, int k) {
        List<Integer> list;
        int[] array;
        Random rnd;

        rnd = new Random(System.currentTimeMillis());

        list = new ArrayList<Integer> ();
        for (int i = 1; i <= n; i++)
            list.add(new Integer(rnd.nextInt(k+1)));

        Collections.shuffle(list, rnd);

        array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = list.get(i).intValue();

        return array;
    }

    public static int[] generate_random_array (int n) {
        List<Integer> list;
        int[] array;

        list = new ArrayList<Integer> ();
        for (int i = 1; i <= n; i++)
            list.add(new Integer(i));

        Collections.shuffle(list, new Random(System.currentTimeMillis()));

        array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = list.get(i).intValue();

        return array;
    }

    public static boolean check_sorted (int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i-1] > array[i])
                return false;
        }
        return true;
    }

    public static void print_array (int[] array) {
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + ", ");
        System.out.println();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int k = 10000;

        System.out.println("Heap sort starts ------------------");
        for (int n = 100000; n <= 1000000; n=n+100000) {
            int[] array = Sort2.generate_random_array(n);
            long t1 = System.currentTimeMillis();
            array = Sort4.heap_sort(array);
            long t2 = System.currentTimeMillis();
            long t = t2 - t1;
            boolean flag = Sort2.check_sorted(array);
            System.out.println(n + "," + t + "," + flag);
        }
        System.out.println("Heap sort ends ------------------");

        System.out.println("Quick sort starts ------------------");
        for (int n = 100000; n <= 1000000; n=n+100000) {
            int[] array = Sort2.generate_random_array(n);
            long t1 = System.currentTimeMillis();
            array = Sort4.quick_sort(array, 0, n-1);
            long t2 = System.currentTimeMillis();
            long t = t2 - t1;
            boolean flag = Sort2.check_sorted(array);
            System.out.println(n + "," + t + "," + flag);
        }
        System.out.println("Quick sort ends ------------------");

        System.out.println("Counting sort starts ------------------");
        for (int n = 100000; n <= 1000000; n=n+100000) {
            int[] array = Sort2.generate_random_array(n, k);
            long t1 = System.currentTimeMillis();
            array = Sort4.counting_sort(array, k);
            long t2 = System.currentTimeMillis();
            long t = t2 - t1;
            boolean flag = Sort2.check_sorted(array);
            System.out.println(n + "," + t + "," + flag);
        }
        System.out.println("Counting sort ends ------------------");
    }
}



