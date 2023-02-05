package com.bookit.offers;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OfferRepository extends MongoRepository<Offer, String> {

    List<Offer> findByIdIsNotIn(List<String> ids);
}
