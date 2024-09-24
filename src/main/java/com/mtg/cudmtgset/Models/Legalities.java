package com.mtg.cudmtgset.Models;

import java.util.Map;

public class Legalities {
    public Legalities(){
         standard = false;
         future = false;
         historic = false;
         timeless = false;
         gladiator = false;
         pioneer = false;
         explorer = false;
         modern = false;
         legacy = false;
         pauper = false;
         vintage = false;
         penny = false;
         commander = false;
         oathbreaker = false;
         standardbrawl = false;
         brawl = false;
         alchemy = false;
         paupercommander = false;
         duel = false;
         oldschool = false;
         premodern = false;
         predh = false;
    }
    public Legalities(Map<String,String> legStr){
        standard = legStr.get("standard") == "legal";
        future = legStr.get("future") == "legal";
        historic = legStr.get("historic") == "legal";
        timeless = legStr.get("timeless") == "legal";
        gladiator = legStr.get("gladiator") == "legal";
        pioneer = legStr.get("pioneer") == "legal";
        explorer = legStr.get("explorer") == "legal";
        modern = legStr.get("modern") == "legal";
        legacy = legStr.get("legacy") == "legal";
        pauper = legStr.get("pauper") == "legal";
        vintage = legStr.get("vintage") == "legal";
        penny = legStr.get("penny") == "legal";
        commander = legStr.get("commander") == "legal";
        oathbreaker = legStr.get("oathbreaker") == "legal";
        standardbrawl = legStr.get("standardbrawl") == "legal";
        brawl = legStr.get("brawl") == "legal";
        alchemy = legStr.get("alchemy") == "legal";
        paupercommander = legStr.get("paupercommander") == "legal";
        duel = legStr.get("duel") == "legal";
        oldschool = legStr.get("oldschool") == "legal";
        premodern = legStr.get("premodern") == "legal";
        predh = legStr.get("predh") == "legal";
    }
    Boolean standard;
    Boolean future;
    Boolean historic;
    Boolean timeless;
    Boolean gladiator;
    Boolean pioneer;
    Boolean explorer;
    Boolean modern;
    Boolean legacy;
    Boolean pauper;
    Boolean vintage;
    Boolean penny;
    Boolean commander;
    Boolean oathbreaker;
    Boolean standardbrawl;
    Boolean brawl;
    Boolean alchemy;
    Boolean paupercommander;
    Boolean duel;
    Boolean oldschool;
    Boolean premodern;
    Boolean predh;

    @Override
    public String toString(){
        return String.format("standard:%s,\nfuture:%s,\nhistoric:%s,\ntimeless:%s,\ngladiator:%s,\npioneer:%s,\nexplorer:%s,\nmodern:%s,\nlegacy:%s,\npauper:%s,\nvintage:%s,\npenny:%s,\ncommander:%s,\noathbreaker:%s,\nstandardbrawl:%s,\nbrawl:%s,\nalchemy:%s,\npaupercommander:%s,\nduel:%s,\noldschool:%s,\npremodern:%s,\npredh:%s"
        ,standard.toString(),future.toString(),historic.toString(),timeless.toString(),gladiator.toString(),pioneer.toString(),explorer.toString(),modern.toString(),legacy.toString(),pauper.toString(),vintage.toString(),penny.toString(),commander.toString(),oathbreaker.toString(),standardbrawl.toString(),brawl.toString(),alchemy.toString(),paupercommander.toString(),duel.toString(),oldschool.toString(),premodern.toString(),predh.toString());
    }
}