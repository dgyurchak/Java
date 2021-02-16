package hu.unideb.inf.duogame;

import java.util.Arrays;
import java.util.TreeMap;

public class MyDuoGame implements DuoGame {
    @Override
    public TreeMap<Integer, Integer> freeCells(Status[] status) {
        TreeMap<Integer, Integer> result = new TreeMap<>();
        int idx = -1;
        int length = -1;
        boolean inside = false;
        for (int i = 1; i < status.length; i++) {
            if (!inside && status[i] == Status.FREE) {
                idx = i;
                length = 1;
                result.put(idx, length);
                inside = true;
            }
            else if(inside && status[i] == Status.FREE) {
                length++;
                result.put(idx, length);
            }
            else if (status[i] != Status.FREE) {
                inside = false;
            }
        }
        return result;
    }

    @Override
    public boolean finishedInNextMove(Status[] status) {
        var freeCells = freeCells(status);
        if(freeCells.size() == 1 && (freeCells.firstEntry().getValue() == 1 || freeCells.firstEntry().getValue() == 2)){
            return true;
        }
        return false;
    }


    @Override
    public Status[] applyMove(Status[] status, Status player, int start, int length) {
        Status[] result = Arrays.copyOf(status, status.length);
        result[start] = player;
        if (length == 2) {
            result[start+1] = player;
        }
        return result;

    }
}
