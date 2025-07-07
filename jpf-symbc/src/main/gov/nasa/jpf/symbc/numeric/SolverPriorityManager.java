package gov.nasa.jpf.symbc.numeric;

import gov.nasa.jpf.symbc.SymbolicInstructionFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class SolverPriorityManager {
    private static Map<String, Integer> scoreMap;
    private static Map<String, Integer> initialOrder;
    private static TreeSet<String> sortedDP;

    public void initialize() {
        scoreMap = new HashMap<>();
        initialOrder = new HashMap<>();
        sortedDP = new TreeSet<>((s1, s2) -> {
            int cmp = scoreMap.get(s2).compareTo(scoreMap.get(s1));
            if(cmp != 0) return cmp;
            return Integer.compare(initialOrder.get(s1), initialOrder.get(s2));
        });
        String[] dp = SymbolicInstructionFactory.dp;
        for(int i=0;i<dp.length;i++) {
            scoreMap.put(dp[i], 0);
            initialOrder.put(dp[i], i);
            sortedDP.add(dp[i]);
        }
    }

    public static void incrementScore(String solver) {
        sortedDP.remove(solver);
        scoreMap.put(solver, scoreMap.get(solver) + 1);
        sortedDP.add(solver);
    }

    public static String[] getSolversByPriority() {
        return sortedDP.toArray(new String[0]);
    }
}
