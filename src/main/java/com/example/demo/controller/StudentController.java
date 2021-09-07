package com.example.demo.controller;

import com.example.demo.model.ClassRoom;
import com.example.demo.model.Student;
import com.example.demo.service.IClassService;
import com.example.demo.service.IStudentService;
import com.example.demo.validate.StudentValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class StudentController {

    @Autowired
    private IStudentService iStudentService;

    @Autowired
    private IClassService iClassService;

    @Autowired
    private StudentValidate studentValidate;

    @ModelAttribute("class")
    private Iterable<ClassRoom> classRooms() {
        return iClassService.findAll();
    }

    @GetMapping("/home")
    public ModelAndView home(@PageableDefault(value = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("home");
        Page<Student> students = iStudentService.fillAll(pageable);
        modelAndView.addObject("list", students);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute Student student, BindingResult bindingResult) {
        studentValidate.validate(student, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("create");
            return modelAndView;
        }
        iStudentService.save(student);
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @GetMapping("/findByName")
    public ModelAndView findByName(@RequestParam String findName) {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("list", iStudentService.findAllByName(findName));
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable int id) {
        Student student = iStudentService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(@Valid @ModelAttribute Student student, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("edit");
            modelAndView.addObject("student", student);
            return modelAndView;
        }
        iStudentService.save(student);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDelete(@PathVariable int id) {
        Student student = iStudentService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("delete");
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView delete(@ModelAttribute Student student) {
        iStudentService.delete(student.getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable int id) {
        Student student = iStudentService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("details");
        modelAndView.addObject("student", student);
        return modelAndView;
    }
}
