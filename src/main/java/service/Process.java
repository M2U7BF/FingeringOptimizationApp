package service;

import entity.Guitar;
import form.ConditionForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Process {
    //TODO : 分散の戻り値が1つなのでそこを精査する

//    private int stringNumber;
//    private int fretNumber;
    private int[] pressingPosition;
    private List<int[]> candidate = new ArrayList<>();
    private List<List<int[]>> candidates = new ArrayList<>();
    private List<int[]> result = new ArrayList<>();
    private List<List<int[]>> results = new ArrayList<>();
    private List<Double> varianceList = new ArrayList<>();

    private Guitar guitar;

    // TODO : 何本指でするのが最適か、も出せるようにする

    public List<int[]> findOut(ConditionForm conditionForm){
        //melodyを展開
        List<String> melodies = conditionForm.getMelodies();
        List<List<int[]>> candidates = new ArrayList<>();

        for (String melody : melodies) {
            guitar = new Guitar();

            candidate = comparingTheMelodySoundTo(Guitar.FINGERBOARD_DIAGRAM, melody);
            candidates.add(candidate);
        }

        varianceList = sortCandidatesByVariance(candidates);

        //距離の分散の小さい順にならべ、上位5~1件を提出
        for(int i=0; i<5 && i<varianceList.size(); i++){
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

    private List<Double> sortCandidatesByVariance(List<List<int[]>> candidates) {
        //小さい順に並べる =
        //各組み合わせで分散を算出。その組み合わせを保持
        if(varianceList.size() == 0){
            varianceList.add(obtainTheVariance(candidates));
        }else{
            //小さい順に並べる
            for(int i=0; i<varianceList.size(); i++){
                // TODO : 無限に増えている
                System.out.println("size : "+varianceList.size());

                double variance = varianceList.get(i);

                if(variance <= obtainTheVariance(candidates)){
                    varianceList.add(i,variance);
                } else if (variance >= obtainTheVariance(candidates)) {
                    varianceList.add(i+1,variance);
                }
            }
        }

        return varianceList;
    }

    public double obtainTheVariance(List<List<int[]>> candidates){
        //ある組み合わせを取り出す(Listに格納)(拡張for)
        List<Double> melodiesForCalc = new ArrayList<>();
        for(List<int[]> candidate : candidates){
            for(int[] position : candidate){
                melodiesForCalc.add(Math.sqrt(Math.pow(position[0],2)+Math.pow(position[1],2)));
            }
        }

        //重心の座標を求め平均の座標とする(拡張for)
        double average = 0;
        for (double melody : melodiesForCalc){
            average += melody/melodiesForCalc.size();
        }

        //分散を求める
        double variance = 0;
        for (double melody : melodiesForCalc){
            variance += Math.pow((melody - average),2);
        }

        //√(1/n*Σ(xi-平均)**2)

        return variance;
    }

}
