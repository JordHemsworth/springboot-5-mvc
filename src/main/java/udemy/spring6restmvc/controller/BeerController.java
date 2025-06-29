package udemy.spring6restmvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udemy.spring6restmvc.model.Beer;
import udemy.spring6restmvc.service.BeerService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping
public class BeerController {

    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

    private final BeerService beerService;


    @PutMapping(BEER_PATH_ID)
    public ResponseEntity<Beer> updateById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer ) {

        beerService.updateBeerById(beerId, beer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = BEER_PATH)
    public List<Beer> listBeers(){
        return beerService.listBeers();
    }

    @GetMapping(value = BEER_PATH_ID)
    public Beer getBeerById(@PathVariable("beerId") UUID beerId){

        log.debug("Get one million beers by Id - Controller");

        return beerService.getBeerById(beerId).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity<Beer> updateBeerPatchById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer){

        beerService.patchBeerById(beerId, beer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity<Beer> deleteBeerById(@PathVariable("beerId") UUID beerId){
        beerService.deleteBeerById(beerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(BEER_PATH)
    public ResponseEntity<Beer> handlePost(@RequestBody Beer beer){
        Beer savedBeer = beerService.saveNewBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

}
