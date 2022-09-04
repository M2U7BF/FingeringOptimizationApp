import com.example.FingeringOptimizationApp.service.SubProcess;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubProcessTest {
    @Test
    void convertBitListTest(){
        SubProcess subProcess = new SubProcess();
        
        boolean f = false;

        List<boolean[][]> test = List.of(
                new boolean[][]{
                        {true,f,f,f,f,f,f,f,f,f,f,f},
                        {f,f,f,f,f,f,f,f,f,f,f,f},
                        {f,f,f,f,f,f,f,f,f,f,f,f},
                        {f,f,f,f,f,f,f,f,f,f,f,f},
                        {f,f,f,f,f,f,f,f,f,f,f,f},
                        {f,f,f,f,f,f,f,f,f,f,f,f},
                },
                new boolean[][]{
                        {true,f,f,f,f,f,f,f,f,f,f,f},
                        {f,f,f,f,f,f,f,f,f,f,f,f},
                        {f,f,f,f,f,f,f,f,f,f,f,f},
                        {f,f,f,f,f,f,f,f,f,f,f,f},
                        {f,f,f,f,f,f,f,f,f,f,f,f},
                        {f,f,f,f,f,f,f,f,f,f,f,f},
                }
        );

        List<List<int[]>> resultTest = List.of(
                List.of(
                        new int[]{0,0},
                        new int[]{0,0}
                ),
                List.of(
                        new int[]{0,0},
                        new int[]{0,0}
                )
        );

        checkConvertBitList(test, subProcess.convertBitList(resultTest));



        test = List.of(
                new boolean[][]{
                        {f,true,f,f,f,f,f,f,f,f,f,f},
                        {true,f,f,f,f,f,f,f,f,f,f,f},
                        {f,f,f,f,f,f,f,f,f,f,f,f},
                        {f,f,f,f,f,f,f,f,f,f,f,f},
                        {f,f,f,f,f,f,f,f,f,f,f,f},
                        {f,f,f,f,f,f,f,f,f,f,f,f},
                },
                new boolean[][]{
                        {f,f,f,f,f,f,f,f,f,f,f,f},
                        {f,f,f,f,f,f,f,f,f,f,f,f},
                        {f,true,f,f,f,f,f,f,f,f,f,f},
                        {f,f,f,f,f,f,f,f,f,f,f,f},
                        {f,f,f,f,f,true,f,f,f,f,f,f},
                        {f,f,f,f,f,f,f,f,f,f,f,f},
                }
        );

        resultTest = List.of(
                List.of(
                        new int[]{0,1},
                        new int[]{1,0}
                ),
                List.of(
                        new int[]{2,1},
                        new int[]{4,5}
                )
        );

        checkConvertBitList(test, subProcess.convertBitList(resultTest));




        test = List.of(
                new boolean[][]{
                        {f,true,f,f,f,f,f,f,f,f,f,f},
                        {f,true,f,f,f,f,f,f,f,f,f,f},
                        {f,f,f,f,f,f,f,f,f,f,true,f},
                        {f,f,f,f,true,f,f,f,f,f,f,f},
                        {f,f,f,f,f,f,f,f,true,f,f,f},
                        {f,f,true,f,f,f,f,f,f,f,f,f},
                },
                new boolean[][]{
                        {f,f,f,f,f,f,true,f,f,f,f,f},
                        {true,f,f,f,f,f,f,f,f,f,f,f},
                        {f,f,f,f,f,f,f,f,f,f,f,true},
                        {f,f,f,f,f,true,f,f,f,f,f,f},
                        {f,f,f,f,f,f,f,f,f,f,f,f},
                        {f,true,f,f,f,f,f,f,f,f,f,f},
                }
        );

        resultTest = List.of(
                List.of(
                        new int[]{0,1},
                        new int[]{3,4},
                        new int[]{2,10},
                        new int[]{4,8},
                        new int[]{5,2},
                        new int[]{1,1}
                ),
                List.of(
                        new int[]{2,11},
                        new int[]{1,0},
                        new int[]{5,1},
                        new int[]{3,5},
                        new int[]{2,11},
                        new int[]{0,6}
                )
        );

        checkConvertBitList(test, subProcess.convertBitList(resultTest));
    }

    public void checkConvertBitList(List<boolean[][]> test , List<boolean[][]> bitLists){
        boolean isEqual = true;

        for (int i = 0; i< test.size(); i++) {
            for (int j = 0; j < test.get(i).length; j++) {
                boolean bool = Arrays.deepEquals(test.get(i),bitLists.get(i));
                if (bool==false){
                    isEqual = false;
                }
            }
        }

        if(isEqual==false){
            System.out.println("test :");
            for (boolean[][] elem: test
                 ) {
                System.out.println(Arrays.deepToString(elem));
            }

            System.out.println("result :");
            for (boolean[][] elem: bitLists
            ) {
                System.out.println(Arrays.deepToString(elem));
            }

            throw new RuntimeException("error!");
        }
    }
}