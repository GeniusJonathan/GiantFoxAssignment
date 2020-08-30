package com.giantfox.gemeenteAPI.controller;

import com.giantfox.gemeenteAPI.model.Gemeente;
import com.giantfox.gemeenteAPI.service.GemeentenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/gemeenten")
public class GemeenteController {

    @Autowired
    private GemeentenService gemeentenService;

    @GetMapping
    public List<Gemeente> getAllGemeenteWithSorting(
            @RequestParam(value = "sortby", required = false) String sortBy,
            @RequestParam(value = "order", required = false) String order)
    {

        return gemeentenService.getAllGemeeenten(sortBy, order);
    }

    @GetMapping("/{id}")
    public Gemeente getGemeenteById(@PathVariable(value = "id") Long gemeenteId){
        return gemeentenService.findGemeenteById(gemeenteId);

    }

}
