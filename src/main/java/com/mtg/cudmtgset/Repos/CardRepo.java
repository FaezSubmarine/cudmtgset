package com.mtg.cudmtgset.Repos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mtg.cudmtgset.Models.MTGSetCardModel;

@Repository
public interface CardRepo {
    Boolean save(MTGSetCardModel cardModel);
    Boolean saveAll(List<MTGSetCardModel> cardModels);
    Long deleteAll();
}