package entity;

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
}
