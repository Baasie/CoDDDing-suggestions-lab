package com.baasie.SeatsSuggestionsApi.controllers;

import com.baasie.ExternalDependencies.IProvideAuditoriumLayouts;
import com.baasie.ExternalDependencies.IProvideCurrentReservations;
import com.baasie.ExternalDependencies.auditoriumlayoutrepository.AuditoriumLayoutRepository;
import com.baasie.ExternalDependencies.reservationsprovider.ReservationsProvider;
import com.baasie.SeatsSuggestions.AuditoriumSeatingAdapter;
import com.baasie.SeatsSuggestions.SeatsAllocator;
import com.baasie.SeatsSuggestions.SuggestionsMade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/SeatsSuggestions")
public class SeatSuggestionsController {

    private IProvideAuditoriumLayouts iProvideAuditoriumLayouts;
    private IProvideCurrentReservations iProvideCurrentReservations;


    public SeatSuggestionsController(IProvideAuditoriumLayouts iProvideAuditoriumLayouts, IProvideCurrentReservations iProvideCurrentReservations) {
        this.iProvideAuditoriumLayouts = iProvideAuditoriumLayouts;
        this.iProvideCurrentReservations = iProvideCurrentReservations;
    }

    // GET api/SeatsSuggestions?showId=3&party=3
    @GetMapping(produces = "application/json")
    public ResponseEntity<SuggestionsMade> makeSuggestions(@RequestParam String showId, @RequestParam int party) {

        SeatsAllocator seatsAllocator = new SeatsAllocator(new AuditoriumSeatingAdapter(iProvideAuditoriumLayouts, iProvideCurrentReservations));
        SuggestionsMade suggestionsMade = seatsAllocator.makeSuggestions(showId, party);
        return ResponseEntity.ok(suggestionsMade);
    }
}
