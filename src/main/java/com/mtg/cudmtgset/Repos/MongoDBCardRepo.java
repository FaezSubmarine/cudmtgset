package com.mtg.cudmtgset.Repos;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
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
    public MTGSetCardModel save(MTGSetCardModel cardModel) {
        cardCollection.insertOne(cardModel);
        return cardModel;
    }

    @Override
    public List<MTGSetCardModel> saveAll(List<MTGSetCardModel> cardModels) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveAll'");
    }

}
