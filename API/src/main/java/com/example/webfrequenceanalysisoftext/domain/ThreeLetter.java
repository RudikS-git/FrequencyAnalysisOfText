package com.example.webfrequenceanalysisoftext.domain;

import javax.persistence.*;

@Entity
@Table(name = "three_letters")
public class ThreeLetter {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long amount;

    @Column(name = "content", columnDefinition="varchar(3)")
    private String content;
    private Double frequency;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCount() { return amount; }
    public void setCount(Long value) {
        amount = value;
    }

    public String getContent() { return content; }
    public void setContent(String value) {
        content = value;
    }

    public Double getFrequency() { return frequency; }
    public void setFrequency(Double value) {
        frequency = value;
    }
}
