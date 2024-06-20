package org.javacoders.main;

import com.fasterxml.jackson.databind.JsonNode;
import org.javacoders.query_translator.QueryTranslatorService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/translate")
public class QueryTranslatorController {

    @PostMapping
    public JsonNode translateQuery(@RequestBody Map<String, Object> request) throws IOException {
        String rootField = (String) request.get("rootField");
        Map<String, String> params = (Map<String, String>) request.get("parameters");

        QueryTranslatorService translatorService = new QueryTranslatorService();
        return translatorService.translateAndSendRequest(rootField, params);
    }
}
