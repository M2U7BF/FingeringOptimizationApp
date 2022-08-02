package service;

import entity.Guitar;
import form.ConditionForm;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Service
public class Process {
    private int stringNumber;
    private int fretNumber;
    private int[] pressingPosition;
    private List<String> candidate;
    private List<List<int[]>> candidates;
    private List<int[]> result;
    private List<List<int[]>> results;

    private Guitar guitar;

    public List<int[]> findOut(ConditionForm conditionForm){
        //melodyを展開
        List<String> melodies = conditionForm.getMelodies();

        //指板と照らし合わせて候補を出す
        ////melodyを、一つづつ取り出してそれぞれに適用可能な座標をcandidate(s)として羅列してまとめる
        for(int i=0; i < melodies.size(); i++){
            String melody = melodies.get(i);
            guitar = new Guitar();

            for(int string=0 ; string < guitar.FINGERBOARD_DIAGRAM.length; string++){
                for (int fret=0 ; fret < guitar.FINGERBOARD_DIAGRAM[string].length; fret++){
                    String fingerboardSound = guitar.FINGERBOARD_DIAGRAM[string][fret];

                    if(melody.equals(fingerboardSound)){
                        int[] pressingPosition = new int[2];
                        pressingPosition[0] = string;
                        pressingPosition[1] = fret;

                        candidates.get(i).add(pressingPosition);
                    }
                }
            }

        }
        ////この時、座標とは何弦の何フレットである

        // 候補の中から最適化
        ////標準偏差のように、距離でばらつきを見る

        return result;
    }

}
