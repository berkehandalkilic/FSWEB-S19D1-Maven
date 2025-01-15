package com.workintech.s18d2.services;

import com.workintech.s18d2.repository.VegetableRepository;
import com.workintech.s18d2.entity.Vegetable;
import com.workintech.s18d2.exceptions.PlantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VegetableServiceImpl implements VegetableService {
    private VegetableRepository vegetableRepository;

    @Autowired
    public VegetableServiceImpl(VegetableRepository vegetableRepository){
        this.vegetableRepository = vegetableRepository;
    }
    @Override
    public List<Vegetable> getByPriceAsc() {
        return vegetableRepository.getByPriceAsc();
    }

    @Override
    public List<Vegetable> getByPriceDesc() {
        return vegetableRepository.getByPriceDesc();
    }

    @Override
    public List<Vegetable> searchByName(String name) {
        return vegetableRepository.searchByName(name);
    }

    @Override
    public Vegetable save(Vegetable vegetable) {
        if (vegetable == null) {
            throw new PlantException("Vegetable data is required.", HttpStatus.BAD_REQUEST);
        }

        return vegetableRepository.save(vegetable);
    }

    @Override
    public Vegetable getById(long id) {
        if(id < 0){
            throw new PlantException("Invalid ID: " + id + ". ID must be greater than or equal to 0.", HttpStatus.BAD_REQUEST);
        }

        Optional<Vegetable> vegetableOptional = vegetableRepository.findById(id);
        if(vegetableOptional.isPresent()){
            return vegetableOptional.get();
        }

        throw new PlantException("Fruit not found with ID: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public Vegetable delete(long id) {
        if(id < 0){
            throw new PlantException("Invalid ID: " + id + ". ID must be greater than or equal to 0.", HttpStatus.BAD_REQUEST);
        }

        Vegetable vegetableToBeSaved = getById(id);
        vegetableRepository.save(vegetableToBeSaved);
        return vegetableToBeSaved;
    }
}
