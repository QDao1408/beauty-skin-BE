package online.beautyskin.beauty.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.beautyskin.beauty.entity.Report;
import online.beautyskin.beauty.entity.request.ReportRequest;
import online.beautyskin.beauty.service.ReportService;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/report")
@SecurityRequirement(name = "api")
public class ReportAPI {
    
    @Autowired
    private ReportService reportService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity createReport(@RequestBody ReportRequest reportRequest) {  
        Report report = reportService.create(reportRequest);
        return ResponseEntity.ok(report);
    }
    
    @PatchMapping("/approve/{reportId}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity approveReport(@PathVariable long reportId) {
        Report report = reportService.approveReport(reportId);
        return ResponseEntity.ok(report);
    }


}
