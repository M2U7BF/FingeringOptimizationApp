import com.example.FingeringOptimizationApp.form.ConditionForm;
import org.junit.jupiter.api.Test;
import com.example.FingeringOptimizationApp.service.Process;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ProcessTest {
    @Test
    void findOutTest(){
        Process process = new Process();

        //nullでないことを確かめる
        ConditionForm conditionForm = new ConditionForm(
                List.of("A","B","C","D"),
                4
        );
        isFindOutTestNull(process.findOut(conditionForm));


        conditionForm = new ConditionForm(
                List.of("A","B","C","3"),
                4
        );
        isFindOutTestNull(process.findOut(conditionForm));


        conditionForm = new ConditionForm(
                List.of("M","B","C","D"),
                4
        );
        isFindOutTestNull(process.findOut(conditionForm));


        conditionForm = new ConditionForm(
                List.of("M","B","C","D"),
                10
        );
        isFindOutTestNull(process.findOut(conditionForm));
    }

    public void isFindOutTestNull(List<List<int[]>> result){
        if (result == null){
            throw new RuntimeException("It is null");
        }
    }

    @Test
    void varianceTest(){
        Process process = new Process();
        List<int[]> test = List.of(
            new int[]{0,1},
            new int[]{0,1},
            new int[]{1,0}
        );

        //同値性 : 0に等しいか
        checkObtainTheVariance(0.000, process.obtainTheVariance(test));

        //0以上か
    }

    public void checkObtainTheVariance(double test, double variance){
        //小数点3桁切り捨て
        boolean isEqual =
                test == (Math.floor(variance*1000))/1000;

        if(isEqual){
            // do nothing.
        }else{
            throw new RuntimeException("error!");
        }
    }

//    @Test
//    void 戻り値のList要素の型が適切(){
//        Process process = new Process();
//
//        ConditionForm conditionForm = new ConditionForm();
//        List<int[]> result;
//
//        conditionForm.setMelodies(List.of("A","B","C","D"));
//        conditionForm.setFingers(4);
//
//        int i = 0;
//        result = process.findOut(conditionForm);
//
//        assertTrue(i == result.get(0)[0]);
//    }
}
