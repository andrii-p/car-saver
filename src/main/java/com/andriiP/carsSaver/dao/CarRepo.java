package com.andriiP.carsSaver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kurt on 6/18/17.
 */
@Repository
public interface CarRepo extends PagingAndSortingRepository<Car, Long> {

    public Car findByTitleAndYearMakeModel(String title, String yearMakeModel);
}
