package kr.co.owocatalog.controller;

import kr.co.owocatalog.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalogs")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;
    private final Environment enviroment;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome Catalog-Service";
    }

    @GetMapping("/status")
    public String status(){
        return String.format("OWO-CATALOG %s"
                , enviroment.getProperty("local.server.port"));
    }
}
