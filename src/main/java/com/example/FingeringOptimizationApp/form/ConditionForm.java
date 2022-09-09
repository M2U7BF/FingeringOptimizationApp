package com.example.FingeringOptimizationApp.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConditionForm {
    //    private int barQuantity;
    private List<String> melodies;
    private List<Integer> octaves;
    private int fingers;
}