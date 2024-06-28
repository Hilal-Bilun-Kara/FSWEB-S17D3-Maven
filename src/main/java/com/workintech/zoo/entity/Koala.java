package com.workintech.zoo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Koala extends Animal{
    private int sleepHour;

    public Koala(int id, String name, double weight, Gender gender) {
        super(id, name, weight, gender);
    }
}
