package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.validation.ZooKangrooValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {
    private Map<Integer, Kangaroo> kangaroos;


    @PostConstruct
    public void init() {
        kangaroos = new HashMap<>();
    }

    @GetMapping
    public List<Kangaroo> getAllKangaroos(){
        return this.kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo getKangarooById(@PathVariable("id") Integer id) {
        ZooKangrooValidation.isIdValid(id);
        ZooKangrooValidation.checkKangarooExistence(kangaroos,id,true);
        return null;
    }

    @PostMapping
    public Kangaroo addKangaroo(@RequestBody Kangaroo kangaroo) {
        ZooKangrooValidation.checkKangarooExistence(kangaroos,kangaroo.getId(),false);
        ZooKangrooValidation.checkKangarooWeight(kangaroo.getWeight());
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroos.get(kangaroo.getId());
    }

    @PutMapping("/{id}")
    public Kangaroo updateKangaroo(@PathVariable Integer id, @RequestBody Kangaroo updatedKangaroo) {
        ZooKangrooValidation.isIdValid(id);
        ZooKangrooValidation.checkKangarooWeight(updatedKangaroo.getWeight());
        updatedKangaroo.setId(id);
        if(kangaroos.containsKey(id)){
            kangaroos.put(id,updatedKangaroo);
            return kangaroos.get(id);
        }
        else {
            return updatedKangaroo;
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Kangaroo deleteKangaroo(@PathVariable Integer id) {
        ZooKangrooValidation.isIdValid(id);
        ZooKangrooValidation.checkKangarooExistence(kangaroos,id,true);
        return kangaroos.remove(id);
    }
}
