package com.example.webfrequenceanalysisoftext.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AnalysisData {
    @NotNull
    @NotBlank
    private String inputText;

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }
}
