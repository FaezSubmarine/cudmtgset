package com.mtg.cudmtgset.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mtg.cudmtgset.Models.MTGSetCardModel;
import com.mtg.cudmtgset.Repos.MongoDBCardRepo;

@Service
public class CardService implements ICardService{
    private final MongoDBCardRepo cardRepo;
    
    public CardService(MongoDBCardRepo cardRepo){
        this.cardRepo = cardRepo;
    }
    @Override
    public MTGSetCardModel saveOne(MTGSetCardModel cardModel) {
       cardRepo.save(cardModel);
       return cardModel;
    }

    @Override
    public List<MTGSetCardModel> saveAll(List<MTGSetCardModel> cardModels) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveAll'");
    }

    @Override
    public long deleteAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

}
