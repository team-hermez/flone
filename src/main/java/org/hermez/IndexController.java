package org.hermez;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/flone")
public class IndexController {

    @GetMapping(value = "/index.hm")
    public String home(Model model) {
        return "flone/index";
    }
}
