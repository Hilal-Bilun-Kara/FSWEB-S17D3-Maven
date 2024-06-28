package com.workintech.zoo.validation;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class ZooKangrooValidation {
    public static void isIdValid(Integer id) {
        if(id == null || id<0){
            throw new ZooException("Id is not valid: " +id, HttpStatus.BAD_REQUEST);
        }

    }

    public static void checkKangarooExistence(Map<Integer, Kangaroo> kangaroos, Integer id, boolean existence) {
    if(existence){
        if(!kangaroos.containsKey(id)){
            throw new ZooException("Record is not exist: ", HttpStatus.NOT_FOUND);
        }
        else{
            if(kangaroos.containsKey(id)){
                throw new ZooException("Record is already exist: ", HttpStatus.BAD_REQUEST);
            }
        }
    }
    }

    public static void checkKangarooWeight(Double weight) {
        if(weight == null || weight <= 0){
            throw new ZooException("Wight shouldn't be null or less then zero: " + weight, HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkKoalaExistence(Map<Integer, Koala> koalas, Integer id, boolean existence) {
        if(existence){
            if(!koalas.containsKey(id)){
                throw new ZooException("Record is not exist: ", HttpStatus.NOT_FOUND);
            }
            else{
                if(koalas.containsKey(id)){
                    throw new ZooException("Record is already exist: ", HttpStatus.BAD_REQUEST);
                }
            }
        }
    }
}
