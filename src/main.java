import java.util.*;
import java.io.*;

public class main {
    
    static int point;
    static ArrayList<Pointer>[] adj;
    static int[] valuePoint;
    static int sum;
    static int[] ColourSum;
 
    
   public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
        point = scan.nextInt();
      adj = new ArrayList[point];
      for (int i=0; i<point; i++)
         adj[i] = new ArrayList<>();

      for (int u=1; u<point; u++)
      {
         int i = scan.nextInt()-1;
         int j = scan.nextInt()-1;
         int c = scan.nextInt();
         adj[i].add(new Pointer(j, c));
         adj[j].add(new Pointer(i, c));
      }

      sum = 0;
      valuePoint = new int[point];
      ColourSum = new int[point];
      compare(0, 0);
   
      int res = 0;
      for (int i=0; i<point; i++)
         if (sum+valuePoint[i] == 0)
            res++;
      System.out.println(res);
      for (int i=0; i<point; i++)
         if (sum+valuePoint[i] == 0)
            System.out.println(i+1); 
       
      
      scan.close();
   }
 public static void compare(int i, int k) {
      for (Pointer pointer : adj[i])
         ColourSum[pointer.colour]++; 
   
      for (Pointer pointer : adj[i])
      {
         if (pointer.j == k)
         {
            if (ColourSum[pointer.colour] > 1)
            {
               sum++;
               valuePoint[i]--;
            }
         }
         else
         {
            if (ColourSum[pointer.colour] > 1)
            {
               valuePoint[pointer.j]++;
            }
         }
      }
      
      for (Pointer pointer : adj[i])
         ColourSum[pointer.colour]--; 
   
      for (Pointer pointer : adj[i]) if (pointer.j != k)
      {
         valuePoint[pointer.j] += valuePoint[i];
         compare(pointer.j, i);
      }
   }
 public static class Pointer {
      int j, colour;

      Pointer(int i, int k) {
         j=i;
         colour = k;
      }
   }
}
 
