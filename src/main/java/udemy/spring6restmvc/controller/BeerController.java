package udemy.spring6restmvc.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import udemy.spring6restmvc.model.Beer;
import org.springframework.stereotype.Controller;
import udemy.spring6restmvc.service.BeerService;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Controller
public class BeerController {
    private final BeerService beerService;

    public Beer getBeerById(UUID id){

        log.debug("Get Beer ID in controller");

        return beerService.getBeerById(id);
    }

}
