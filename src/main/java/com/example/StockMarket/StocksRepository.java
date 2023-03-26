package com.example.StockMarket;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StocksRepository extends MongoRepository<Stocks,Integer> {
}
