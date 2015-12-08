package com.rbonick.co2conscious.controller;

import com.rbonick.co2conscious.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller to manage API
 *
 * Created by rbonick on 12/7/15.
 */
@Controller
public class APIController {

    @Autowired
    private DataService dataService;

    @RequestMapping("/")
    @ResponseBody
    String getCurrentLevels() {
        return dataService.getData().replace("\n", "<br>");
    }
}
