package com.durgasoft.Controller;

import com.durgasoft.Service.SearchService;
import com.durgasoft.payload.SearchDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/vi/api/search/")
public class SearchController {


private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService =searchService;
    }


    //search by country

   @GetMapping("Bycountry")

    public List<SearchDto> searchByCountry(@RequestParam String country){
        return searchService.searchByCountry(country);
   }

}
