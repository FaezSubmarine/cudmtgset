package com.mtg.cudmtgset.Services;

import java.util.List;

import com.mtg.cudmtgset.Models.MTGSetCardModel;
public interface ICardService {
    MTGSetCardModel saveOne(MTGSetCardModel cardModel);
    Boolean saveAll(List<MTGSetCardModel> cardModels);
    long deleteAll();
}
