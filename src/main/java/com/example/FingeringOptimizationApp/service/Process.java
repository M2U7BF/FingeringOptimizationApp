package com.example.FingeringOptimizationApp.service;

import com.example.FingeringOptimizationApp.pojo.Guitar;
import com.example.FingeringOptimizationApp.form.ConditionForm;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class Process {
    private int[] pressingPosition;

    // TODO : 何本指でするのが最適か、も出せるようにする

    public List<List<int[]>> findOut(ConditionForm conditionForm){
        //melodyを展開
        List<String> melodies = conditionForm.getMelodies();
        List<List<int[]>> candidates = new ArrayList<>();

        for (String melody : melodies) {
            Guitar guitar = new Guitar();

            //1つの音に対し、候補となる座標をリスト形式でまとめる
            List<int[]> candidate = comparingTheMelodySoundTo(Guitar.FINGERBOARD_DIAGRAM, melody);

            //リストをリストとしてまとめる
            candidates.add(candidate);
        }

        //組み合わせを作成
        List<List<int[]>> combinations = GenerateCombinations(candidates);

        List<List<int[]>> results = sortCombinationsByVariance(combinations);

        return results;
    }

    private List<List<int[]>> GenerateCombinations(List<List<int[]>> candidates) {
        //組み合わせをnCrで考える
        List<List<int[]>> combinations = new ArrayList<>();

        for(int i=0; i<candidates.get(0).size(); i++){
            for(int j=0; j<candidates.get(1).size(); j++){
                for(int k=0; k<candidates.get(2).size(); k++){
                    for(int l=0; l<candidates.get(3).size(); l++){
                        List<int[]> combination = new ArrayList<>();

                        combination.add(candidates.get(0).get(i));
                        combination.add(candidates.get(1).get(j));
                        combination.add(candidates.get(2).get(k));
                        combination.add(candidates.get(3).get(l));

                        combinations.add(combination);
                    }
                }
            }
        }

        return combinations;
    }

    private List<int[]> comparingTheMelodySoundTo(String[][] fingerboardDiagram, String melody) {
        List<int[]> candidate = new ArrayList<>();

        for (int string = 0; string < Guitar.FINGERBOARD_DIAGRAM.length; string++) {
            for (int fret = 0; fret < Guitar.FINGERBOARD_DIAGRAM[string].length; fret++) {
                String fingerboardSound = Guitar.FINGERBOARD_DIAGRAM[string][fret];

                if (melody.equals(fingerboardSound)) {
                    int[] pressingPosition = {string, fret};

                    //melodyに一致するポジションのリストを作成
                    candidate.add(pressingPosition);
                }
            }
        }

        return candidate;
    }

    private List<List<int[]>> sortCombinationsByVariance(List<List<int[]>> combinations) {
        //各組み合わせの(距離の)分散を求め、リストへ格納
        List<Double> varianceList = new ArrayList<>();

        quicksort(combinations,0,combinations.size()-1);

        List<List<int[]>> results = new ArrayList<>();
        List<List<int[]>> sub_results = new ArrayList<>();

        //重複を排除(有効かは不明)
        sub_results = combinations
                .stream()
                .distinct()
                .collect(Collectors.toList());

        for(int i=0; i<6 && i<combinations.size(); i++){
            results.add(sub_results.get(i));
        }

        //5件返す
        return results;
    }

    public void swap (List<List<int[]>> arr, int i, int j) {
        List<int[]> temp = arr.get(i);
        arr.set(i,arr.get(j));
        arr.set(j,temp);
    }

    public int partition(List<List<int[]>> a, int start, int end) {
        double pivot = obtainTheVariance(a.get(end));

        int pIndex = start;

        for (int i = start; i < end; i++) {
            if (obtainTheVariance(a.get(i)) <= pivot) {
                swap(a, i, pIndex);
                pIndex++;
            }
        }

        swap(a, end, pIndex);

        return pIndex;
    }

    //クイックソートルーチン
    public void quicksort(List<List<int[]>> a, int start, int end)
    {
        //基本条件
        if (start >= end) {
            return;
        }

        int pivot = partition(a, start, end);

        quicksort(a, start, pivot - 1);

        quicksort(a, pivot + 1, end);
    }

    public double obtainTheVariance(List<int[]> combination){
        //座標の組み合わせを、全て距離に変換
        List<Double> melodiesForCalc = new ArrayList<>();

        for(int[] position : combination){
            melodiesForCalc.add(Math.sqrt(Math.pow(position[0],2)+Math.pow(position[1],2)));
        }

        //重心の座標を求め平均の座標とする
        double average = 0;
        for (double melody : melodiesForCalc){
            average += melody/melodiesForCalc.size();
        }

        //分散を求める
        double variance = 0;
        for (double melody : melodiesForCalc){
            variance += Math.pow((melody - average),2);
        }

        return variance;
    }

}
