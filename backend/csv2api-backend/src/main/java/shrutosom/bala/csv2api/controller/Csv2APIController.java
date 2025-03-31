package shrutosom.bala.csv2api.controller;

import org.springframework.web.bind.annotation.RestController;

import shrutosom.bala.csv2api.service.Csv2APIService;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class Csv2APIController {

    private final Csv2APIService service;

	public Csv2APIController(Csv2APIService service) {
		this.service = service;
	}


    //TODO: This should come from Spring Actuator
    @GetMapping("/health")
    public String health() {
        service.fetchCSVData();
        return "Up";
    }
    

}
