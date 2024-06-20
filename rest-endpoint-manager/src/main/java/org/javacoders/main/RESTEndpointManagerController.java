package org.javacoders.main;

import org.javacoders.rest_endpoint_manager.CachingManager;
import org.javacoders.rest_endpoint_manager.PerformanceOptimizer;
import org.javacoders.rest_endpoint_manager.ScalingController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/manage")
public class RESTEndpointManagerController {

    private final CachingManager cachingManager;
    private final PerformanceOptimizer performanceOptimizer;
    private final ScalingController scalingController;

    @Autowired
    public RESTEndpointManagerController(CachingManager cachingManager, PerformanceOptimizer performanceOptimizer, ScalingController scalingController) {
        this.cachingManager = cachingManager;
        this.performanceOptimizer = performanceOptimizer;
        this.scalingController = scalingController;
    }

    @PostMapping("/cache")
    public Object cacheEndpointResponse(@RequestParam String endpoint, @RequestBody Map<String, ?> params, @RequestBody Object response) {
        return cachingManager.cacheResponse(endpoint, params, response);
    }

    @PostMapping("/optimize")
    public Object optimizeEndpointResponse(@RequestBody Object response) {
        return performanceOptimizer.optimizeResponse(response);
    }

    @PostMapping("/scale")
    public void scaleEndpoints() {
        scalingController.scaleEndpoints();
    }
}
