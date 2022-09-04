import com.example.FingeringOptimizationApp.form.ConditionForm;
import org.junit.jupiter.api.Test;
import com.example.FingeringOptimizationApp.service.Process;

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
        int[] testList = {0,0};
        List<List<int[]>> test = List.of(List.of(testList));
        List<int[]> test2 = List.of(testList);

        assertThat(0.0)
                .as("結果が0に等しいか")
                .isEqualTo(process.obtainTheVariance(test2));

        assertThat(process.obtainTheVariance(test2))
                .as("0以上か")
                .isGreaterThanOrEqualTo(0);

    };
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
