package com.example.FingeringOptimizationApp.entity;

import lombok.Data;

@Data
public class Guitar {
    //開放弦も含まれる
    //0f~11fまで
    public static final String[][] FINGERBOARD_DIAGRAM =
            {{"E","F","G♭","G","Am","A","Bm","B","C","Dm","D","Em"},
            {"B","C","Dm","D","Em","E","F","G♭","G","Am","A","Bm"},
            {"G","Am","A","Bm","B","C","Dm","D","Em","E","F","G♭"},
            {"D","Em","E","F","G♭","G","Am","A","Bm","B","C","Dm"},
            {"A","Bm","B","C","Dm","D","Em","E","F","G♭","G","Am"},
            {"E","F","G♭","G","Am","A","Bm","B","C","Dm","D","Em"},};

    public static final int[][] FINGERBOARD_OCTAVE =
            {{2,2,2,2,2,2,2,2,2,2,2,2},
            {1,1,1,1,1,2,2,2,2,2,2,2},
            {1,1,1,1,1,1,1,1,1,2,2,2},
            {0,0,1,1,1,1,1,1,1,1,1,1},
            {0,0,0,0,0,0,0,1,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0}};
}
