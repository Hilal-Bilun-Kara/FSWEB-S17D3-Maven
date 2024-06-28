package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.validation.ZooKangrooValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {
    private Map<Integer, Koala> koalas;

    @PostConstruct
    public void init() {
        this.koalas = new HashMap<>();
    }
    @GetMapping
    public List<Koala> getAllKoalas(){
        return this.koalas.values().stream().toList();
    }


    @GetMapping("/{id}")
    public Kangaroo getKoalaById(@PathVariable("id") Integer id) {
        ZooKangrooValidation.isIdValid(id);
        ZooKangrooValidation.checkKoalaExistence(koalas,id,true);
        return null;
    }

    @PostMapping
    public Koala addKoala(@RequestBody Koala koala) {
        ZooKangrooValidation.checkKoalaExistence(koalas, koala.getId(),false);
        koalas.put(koala.getId(), koala);
        return koalas.get(koala.getId());
    }

    @PutMapping("/{id}")
    public Koala updateKoala(@PathVariable Integer id, @RequestBody Koala updatedKoala) {
        ZooKangrooValidation.isIdValid(id);
        updatedKoala.setId(id);
        if(koalas.containsKey(id)){
            koalas.put(id,updatedKoala);
            return koalas.get(id);
        }
        else {
            return updatedKoala;
        }
    }

    @DeleteMapping("/{id}")
    public Koala deleteKoala(@PathVariable Integer id) {
        ZooKangrooValidation.isIdValid(id);
        ZooKangrooValidation.checkKoalaExistence(koalas,id,true);
        return koalas.remove(id);
    }
}
