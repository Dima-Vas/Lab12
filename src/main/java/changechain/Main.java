package changechain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer toMake = sc.nextInt();
        Integer denominationSize = sc.nextInt();
        List<Integer> denominations = new ArrayList<>();
        while (denominationSize > 0) {
            denominations.add(sc.nextInt());
            denominationSize--;
        }
        ChainBuilder chainBuilder = new ChainBuilder(toMake, denominations);
        System.out.println(chainBuilder.buildRecursionChain(new ArrayList<>()));
    }

}