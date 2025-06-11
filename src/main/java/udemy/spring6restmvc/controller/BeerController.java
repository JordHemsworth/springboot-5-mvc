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
@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerService beerService;


    @PutMapping("{beerId}")
    public ResponseEntity<Beer> updateById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer ) {

        beerService.updateBeerById(beerId, beer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Beer> listBeers(){
        return beerService.listBeers();
    }

    @RequestMapping(value = "{beerId}", method = RequestMethod.GET)
    public Beer getBeerById(@PathVariable("beerId") UUID beerId){

        log.debug("Get one million beers by Id - Controller");

        return beerService.getBeerById(beerId);
    }

    @PatchMapping("{beerId}")
    public ResponseEntity<Beer> updateBeerPatchById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer){

        beerService.patchBeerById(beerId, beer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{beerId}")
    public ResponseEntity<Beer> deleteBeerById(@PathVariable("beerId") UUID beerId){
        beerService.deleteBeerById(beerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Beer> handlePost(@RequestBody Beer beer){
        Beer savedBeer = beerService.saveNewBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

}
