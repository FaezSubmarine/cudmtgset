package com.mtg.cudmtgset.Controllers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtg.cudmtgset.MongoDBConfig;
import com.mtg.cudmtgset.Models.MTGSetCardModel;
import com.mtg.cudmtgset.Repos.MongoDBCardRepo;
import com.mtg.cudmtgset.Services.ICardService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.Path;

@RestController
public class CardController {
    private final static Logger LOGGER = LoggerFactory.getLogger(CardController.class);
    private final ICardService cardService;

    public CardController(ICardService cardService){
        this.cardService = cardService;
    }
    @GetMapping("Ping")
    public String ping() {
        return "Ping";
    }
    //return all distinct values of the set field
    @GetMapping("DownloadAllSets")
    public ResponseEntity<Resource> getMethodName() throws FileNotFoundException,IOException {
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=AllSets.txt");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        String allSets = String.join(",",cardService.getAllSets());
        byte[] bytes = allSets.getBytes();
        ByteArrayResource resource = new ByteArrayResource(bytes);

        return ResponseEntity.ok()
            .headers(header)
            .contentLength(resource.contentLength())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    }
    
    @PostMapping(path = "/PostOneCard", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String postOneCard(@RequestPart("file") MultipartFile filename) throws JsonMappingException, JsonProcessingException,IOException{
        String content = new String(filename.getBytes());
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        MTGSetCardModel[] card = mapper.readValue(content,MTGSetCardModel[].class);

        cardService.saveOne(card[0]);

        return card[0].toString();
    }
    @PostMapping(path = "/PostManyCards", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String postManyCards(@RequestPart("file") MultipartFile filename) throws JsonMappingException, JsonProcessingException,IOException{
        String content = new String(filename.getBytes());
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<MTGSetCardModel> card = mapper.readValue(content,new TypeReference<List<MTGSetCardModel>>(){});
        
        cardService.saveAll(card);

        return "ok";
    }
    @DeleteMapping("/deleteAll")
    public long deleteAllCards(){
        return cardService.deleteAll();
    }
}