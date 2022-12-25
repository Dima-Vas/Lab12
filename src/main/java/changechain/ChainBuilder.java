package changechain;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
public class ChainBuilder {
    private Integer toBuild;
    private List<Integer> possibleCoins;

    private Integer countOccurences(List<Integer> toCount, Integer el) {
        Integer output = 0;
        for (Integer i : toCount) {
            if (i == el) {
                output += 1;
            }
        }
        return output;
    }

    public List<Integer> buildRecursionChain(List<Integer> output) {
        Integer sum = 0;
        for (Integer i : output){
            sum += i;
        }
        if (sum == toBuild) {
            return output;
        }
        else if (sum > toBuild) {
            return null;
        }

        for (Integer i : possibleCoins) {
            List<Integer> curr = new ArrayList<>(output);
            curr.add(i);
            List<Integer> res = buildRecursionChain(curr);
            if (res != null) {
                for (Integer j : res) {
                    if (!Objects.equals(countOccurences(res, j), countOccurences(output, j))) {
                        output.add(j);
                    }
                }
                return output;
            }
        }
        return null;
    }
}
