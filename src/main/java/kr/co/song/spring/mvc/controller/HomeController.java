package kr.co.song.spring.mvc.controller;

import kr.co.song.spring.mvc.parameter.ExampleParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/example/parameter")
public class HomeController {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/example1")
    public void example1(@RequestParam String id,
                         @RequestParam String code, Model model){
        model.addAttribute("id", id);
        model.addAttribute("code", code);
    }

    @GetMapping("/example2")
    public void example2(@RequestParam Map<String,Object> paramMap,
                            Model model){
        model.addAttribute("paramMap", paramMap);
    }

    /**
     * 클래스로 받을경우 @RequestParam 을 생략하여 준다.
     * @param parameter
     * @param model
     */
    @GetMapping("/example3")
    public void example3(ExampleParameter parameter,
                         Model model){
        model.addAttribute("parameter", parameter);
    }

    @GetMapping("/example4/{id}")
    public String example4(@PathVariable String id,
                         Model model){
        model.addAttribute("id", id);
        return "/example/parameter/example4";
    }

    @GetMapping("/example5")
    public void example5(@RequestParam String[] ids,
                           Model model){
        model.addAttribute("ids", ids);
    }


    @GetMapping("/example6/form")
    public void form(){
    }

    @PostMapping("/example6/saveData")
    @ResponseBody
    public Map<String, Object> example6(@RequestBody Map<String, Object> requestBody ){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", true);
        logger.info("requestBody : {}" ,requestBody);
        return resultMap;

    }


 /*   @PostMapping("/example6/saveData")
    @ResponseBody
    public Map<String, Object> example6(@RequestBody ExampleRequestBodyUser requestBody ){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", requestBody);
        logger.info("requestBody : {}" ,requestBody);
        return resultMap;

    }*/



}
