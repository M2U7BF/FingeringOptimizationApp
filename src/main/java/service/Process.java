package service;

import entity.Guitar;
import form.ConditionForm;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

@Service
public class Process {
//    private int stringNumber;
//    private int fretNumber;
    private int[] pressingPosition;
    private List<int[]> candidate;
    private List<List<int[]>> candidates;
    private List<int[]> result;
    private List<List<int[]>> results;

    private Guitar guitar;

    // TODO : 何本指でするのが最適か、も出せるようにする

    public List<int[]> findOut(ConditionForm conditionForm){
        //melodyを展開
        List<String> melodies = conditionForm.getMelodies();
        //指板と照らし合わせて候補を出す
        ////melodyを、一つづつ取り出してそれぞれに適用可能な座標(何弦の何フレット)をcandidate(s)として羅列してまとめる
        for(int i=0; i < melodies.size(); i++){
            String melody = melodies.get(i);
            guitar = new Guitar();

            for(int string=0 ; string < guitar.FINGERBOARD_DIAGRAM.length; string++){
                for (int fret=0 ; fret < guitar.FINGERBOARD_DIAGRAM[string].length; fret++){
                    String fingerboardSound = guitar.FINGERBOARD_DIAGRAM[string][fret];

                    if(melody.equals(fingerboardSound)){
                        int[] pressingPosition = {string,fret};
                        candidate = new ArrayList<>();

                        //melodyに一致するポジションのリストを作成
                        candidate.add(pressingPosition);
                    }

                    candidates.add(candidate);
                }
            }
        }

        // 候補の中から最適化
        ////距離の標準偏差の小さい順にならべ、上位5件を提出

        obtainTheVariance(candidates);

        return result;
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
