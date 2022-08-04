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

                        candidates.get(i).add(pressingPosition);
                    }
                }
            }

        }

        // 候補の中から最適化
        ////標準偏差のように、距離でばらつきを見る

        return result;
    }

}
