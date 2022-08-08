import form.ConditionForm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import service.Process;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class FindOutTest {
    @Test
    void 戻り値がnullでない(){
        Process process = new Process();

        ConditionForm conditionForm = new ConditionForm();
        List<int[]> result;

        conditionForm.setMelodies(List.of("A","B","C","D"));
        conditionForm.setFingers(4);

        assertNotNull(process.findOut(conditionForm));
    }

    @Test
    void varianceTest(){
        Process process = new Process();
        int[] testList = {0,0};
        List<List<int[]>> test = List.of(List.of(testList));

        assertEquals(0,process.obtainTheVariance(test));
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
