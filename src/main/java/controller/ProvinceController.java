package controller;

import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.IProvinceService;

@Controller
public class ProvinceController {
    @Autowired
    IProvinceService provinceService;
    @GetMapping("/home")
    public String home(){
        return "/index";
    }
    @GetMapping("/provinces")
    public ModelAndView show(){
        ModelAndView modelAndView = new ModelAndView("/province/list");
        modelAndView.addObject("provinces",provinceService.findAll());
        return modelAndView;
    }
    @GetMapping("/provinces/create")
    public ModelAndView showForm(){
        ModelAndView modelAndView = new ModelAndView("/province/create");
        return modelAndView;
    }
    @PostMapping("/provinces/create")
    public ModelAndView save(Province province){
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("redirect:/provinces");
        return modelAndView;

    }
    @GetMapping("/provinces/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/province/edit");
        modelAndView.addObject("pro",provinceService.findById(id).get());
        return modelAndView;
    }
    @PostMapping("/provinces/edit/{id}")
    public ModelAndView update(@PathVariable Long id, Province province) {
        ModelAndView modelAndView = new ModelAndView("redirect:/provinces");
        provinceService.save(province);
        return modelAndView;
    }
}
