package com.mtg.cudmtgset.Repos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mtg.cudmtgset.Models.MTGSetCardModel;

@Repository
public interface CardRepo {
    MTGSetCardModel save(MTGSetCardModel cardModel);
    List<MTGSetCardModel> saveAll(List<MTGSetCardModel> cardModels);
    
}