package com.workintech.s18d2.services;

import com.workintech.s18d2.repository.FruitRepository;
import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.exceptions.PlantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FruitServiceImpl implements FruitService {
    private FruitRepository fruitRepository;

    @Autowired
    public FruitServiceImpl(FruitRepository fruitRepository){
        this.fruitRepository = fruitRepository;
    }

    @Override
    public List<Fruit> getByPriceAsc() {
        return fruitRepository.getByPriceAsc();
    }

    @Override
    public List<Fruit> getByPriceDesc() {
        return fruitRepository.getByPriceDesc();
    }

    @Override
    public List<Fruit> searchByName(String name) {
        return fruitRepository.searchByName(name);
    }

    @Override
    public Fruit save(Fruit fruit) {

        if (fruit == null) {
            throw new PlantException("Fruit data is required.", HttpStatus.BAD_REQUEST);
        }

        return fruitRepository.save(fruit);
    }


    @Override
    public Fruit getById(long id) {
        if(id < 0){
            throw new PlantException("Invalid ID: " + id + ". ID must be greater than or equal to 0.", HttpStatus.BAD_REQUEST);
        }

        Optional<Fruit> fruitOptional = fruitRepository.findById(id);
        if(fruitOptional.isPresent()){
            return fruitOptional.get();
        }

        throw new PlantException("Fruit not found with ID: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public Fruit delete(long id) {
        if(id < 0){
            throw new PlantException("Invalid ID: " + id + ". ID must be greater than or equal to 0.", HttpStatus.BAD_REQUEST);
        }

        Fruit fruitToBeDeleted = getById(id);
        fruitRepository.delete(fruitToBeDeleted);
        return fruitToBeDeleted;
    }
}
