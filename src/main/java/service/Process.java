package service;

import entity.Guitar;
import form.ConditionForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class Process {

//    private int stringNumber;
//    private int fretNumber;
    private int[] pressingPosition;
    private List<int[]> result = new ArrayList<>();
    private List<List<int[]>> results = new ArrayList<>();

    private Guitar guitar;

    // TODO : 何本指でするのが最適か、も出せるようにする

    public List<int[]> findOut(ConditionForm conditionForm){
        //melodyを展開
        List<String> melodies = conditionForm.getMelodies();
        List<List<int[]>> candidates = new ArrayList<>();

        for (String melody : melodies) {
            System.out.println("melodies : "+melodies.size());
            guitar = new Guitar();

            //1つの音に対し、候補となる座標をリスト形式でまとめる
            List<int[]> candidate = comparingTheMelodySoundTo(Guitar.FINGERBOARD_DIAGRAM, melody);

            //リストをリストとしてまとめる
            candidates.add(candidate);
        }

        //組み合わせを考える
        //組み合わせを作成
        List<int[]> combination = new ArrayList<>();
        List<List<int[]>> combinations = new ArrayList<>();

        for(List<int[]> candidate : candidates) {
            System.out.println("candidates : "+candidates.size());
            for(int[] candidatePositon : candidate) {
                System.out.println("candidate : "+candidate.size());
                combination.add(candidatePositon);
            }
            combinations.add(combination);
            System.out.println("combination : "+combination.size());
        }

        //各組み合わせの(距離の)分散を求め、小さい順にならべる
        List<Double> varianceList = sortCandidatesByVariance(combinations);
        System.out.println("戻されたvarianceList : "+varianceList.size());

        //上位5~1件を戻り値とする
        for(int i=0;  i<varianceList.size() && i<5 ; i++){
            System.out.println("varianceList.size() : "+varianceList.size());
            System.out.println("variance : "+String.valueOf(varianceList.get(i)));
        }

        return result;
    }

    private List<int[]> comparingTheMelodySoundTo(String[][] fingerboardDiagram, String melody) {
        List<int[]> candidate = new ArrayList<>();

        for (int string = 0; string < Guitar.FINGERBOARD_DIAGRAM.length; string++) {
            for (int fret = 0; fret < Guitar.FINGERBOARD_DIAGRAM[string].length; fret++) {
                String fingerboardSound = Guitar.FINGERBOARD_DIAGRAM[string][fret];

                if (melody.equals(fingerboardSound)) {
                    int[] pressingPosition = {string, fret};
                    candidate = new ArrayList<>();

                    //melodyに一致するポジションのリストを作成
                    candidate.add(pressingPosition);
                }
            }
        }

        return candidate;
    }

    private List<Double> sortCandidatesByVariance(List<List<int[]>> combinations) {
        List<Double> varianceList = new ArrayList<>();

        for(List<int[]> combination : combinations) {
            varianceList.add(obtainTheVariance(combination));
        }

        Collections.sort(varianceList, Collections.reverseOrder());

        //小さい順に並べる =
        //各組み合わせで分散を算出。その組み合わせを保持
//        for(List<int[]> combination : combinations){
//            System.out.println("combinations : "+combinations.size());
//            if(varianceList.size() == 0){
//                varianceList.add(obtainTheVariance(combination));
//            }else{
//                //小さい順に並べる
//                for(int i=0; i<varianceList.size(); i++){
////                    System.out.println("varianceList : "+varianceList.size());
//
//                    if(varianceList.get(i) <= obtainTheVariance(combination)){
//                        varianceList.add(i,obtainTheVariance(combination));
//                        //TODO : 無限
//                    } else {
//                        varianceList.add(i+1,obtainTheVariance(combination));
//                    }
//                }
//            }
//        }

        return varianceList;
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
