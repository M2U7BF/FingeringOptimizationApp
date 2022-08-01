package service;

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
    private List<List<String>> candidates;
    private List<int[]> result;
    private List<List<int[]>> results;

    public List<int[]> findOut(ConditionForm conditionForm){
        //melodyを展開
        List<String> melodies = conditionForm.getMelodies();

        //指板と照らし合わせて候補を出す
        ////melodyを、一つづつ取り出してそれぞれに適用可能な座標をcandidate(s)として羅列してまとめる
        ////この時、座標とは何弦の何フレットである

        // 候補の中から最適化
        ////標準偏差のように、距離でばらつきを見る

        return result;
    }

}
