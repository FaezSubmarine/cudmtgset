package com.mtg.cudmtgset.Repos;

import java.util.ArrayList;
import java.util.List;

import org.bson.BsonDocument;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.ClientSession;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mtg.cudmtgset.Models.MTGSetCardModel;

@Repository
public class MongoDBCardRepo implements CardRepo{

    private static final TransactionOptions transOption = TransactionOptions.builder()
                                                                            .readPreference(ReadPreference.primary())
                                                                            .readConcern(ReadConcern.MAJORITY)
                                                                            .writeConcern(WriteConcern.MAJORITY)
                                                                            .build();

    private final MongoClient client;
    private MongoCollection<MTGSetCardModel> cardCollection;
    public MongoDBCardRepo(MongoClient client){
        this.client = client;
    }

    @PostConstruct
    void  init(){
        cardCollection = client.getDatabase("MTG").getCollection("CardCollection", MTGSetCardModel.class);
    }
    @Override
    public Boolean save(MTGSetCardModel cardModel) {
        cardCollection.insertOne(cardModel);
        return true;
    }

    @Override
    public Boolean saveAll(List<MTGSetCardModel> cardModels) {
        try (ClientSession clientSession = client.startSession()) {
            return clientSession.withTransaction(() -> {
                cardCollection.insertMany(clientSession, cardModels);
                return true;
            }, transOption);
        }
    }

    @Override
    public Long deleteAll() {
        try (ClientSession clientSession = client.startSession()) {
            return clientSession.withTransaction(
                    () -> cardCollection.deleteMany(clientSession, new BsonDocument()).getDeletedCount(), transOption);
        }

    }

    @Override
    public List<String> getAllSets() {
        // TODO Auto-generated method stub
        DistinctIterable<String> res = cardCollection.distinct("set_name",String.class);
        List<String> target = new ArrayList<>();
        res.forEach(target::add);
        return target;
    }

}
