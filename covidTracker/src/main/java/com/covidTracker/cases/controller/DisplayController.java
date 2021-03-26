package com.covidTracker.cases.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.covidTracker.cases.model.LocationStats;
import com.covidTracker.cases.services.CoronaVirusCasesService;


@Controller
public class DisplayController {

	@Autowired
	CoronaVirusCasesService coronaVirusCasesService;
	
	@GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronaVirusCasesService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
	}

}
