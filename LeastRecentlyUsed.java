//Practical 07 - Least Recently Used

import java.util.ArrayList;
import java.util.Scanner;

public class LeastRecentlyUsed
{

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> stk = new ArrayList<>();
        int nFrames, index = 0, hit = 0, fault = 0, len;
        boolean isFull = false;
        int[] lruFrames;
        int[] str;
        int[][] table;

        System.out.print("Enter the number of frames: ");
        nFrames = in.nextInt();

        System.out.print("Enter length of the string: ");
        len = in.nextInt();

        str = new int[len];
        table = new int[len][nFrames];
        lruFrames = new int[nFrames];

        for(int j = 0; j < nFrames; j++)
            lruFrames[j] = -1;

        System.out.print("Enter the reference string: ");
        for(int i = 0; i < len; i++)
            str[i] = in.nextInt();

        for(int i = 0; i < len; i++)
        {
            if(stk.contains(str[i]))
            {
                stk.remove((Integer) str[i]);
            }
            stk.add(str[i]);
            int search = -1;
            for(int j = 0; j < nFrames; j++)
            {
                if(lruFrames[j] == str[i])
                {
                    search = j;
                    hit++;
                    break;
                }
            }
            if(search == -1)
            {
                if(isFull)
                {
                    int min_loc = len;
                    for(int j = 0; j < nFrames; j++)
                    {
                        if(stk.contains(lruFrames[j]))
                        {
                            int temp = stk.indexOf(lruFrames[j]);
                            if(temp < min_loc)
                            {
                                min_loc = temp;
                                index = j;
                            }
                        }
                    }
                }
                lruFrames[index] = str[i];
                fault++;
                index++;
                if(index == nFrames)
                {
                    index = 0;
                    isFull = true;
                }
            }
            System.arraycopy(lruFrames, 0, table[i], 0, nFrames);
        }

        System.out.println("\n----------------------------------------------------------------------");
        for(int i = 0; i < nFrames; i++)
        {
            for(int j = 0; j < len; j++)
                System.out.printf("%3d ",table[j][i]);
            System.out.println();
        }

        System.out.println("----------------------------------------------------------------------");
        System.out.println("Total Hits: " + hit);
        System.out.println("Total Faults: " + fault);
        System.out.println("Hit Ratio: " + ((float)hit/len));
        System.out.println("Page Fault Ratio: " + ((float)fault/len));
        System.out.println("----------------------------------------------------------------------");
    }
}
