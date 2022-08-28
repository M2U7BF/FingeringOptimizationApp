package com.example.FingeringOptimizationApp.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SubProcess {
    public List<boolean[][]> convertBitList(List<List<int[]>> results) {
        List<boolean[][]> bitLists = new ArrayList<>();

        for (List<int[]> result:results
             ) {
            boolean[][] bitList = new boolean[6][12];

            for (int[] position:result){
                bitList[position[0]][position[1]] = true;
            }

            bitLists.add(bitList);
        }

        return bitLists;
    }
}
