package com.example.crm_system.controller;

import com.example.crm_system.entity.ApplicationRequest;
import com.example.crm_system.entity.Courses;
import com.example.crm_system.entity.Operator;
import com.example.crm_system.service.ApplicationRequestService;
import com.example.crm_system.service.CoursesService;
import com.example.crm_system.service.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ApplicationRequestService applicationRequestService;
    private final CoursesService coursesService;
    private final OperatorService operatorService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("requests", applicationRequestService.getAllRequests());
        return "home";
    }

    @GetMapping("/new-requests")
    public String newRequests(Model model) {
        model.addAttribute("requests", applicationRequestService.getNewRequests());
        return "new-requests";
    }

    @GetMapping("/handled-requests")
    public String handledRequests(Model model) {
        model.addAttribute("requests", applicationRequestService.getHandledRequests());
        return "handled-requests";
    }

    // ⭐⭐⭐ ФУНКЦИЯ ДОБАВЛЕНИЯ НОВОЙ ЗАЯВКИ ⭐⭐⭐
    @GetMapping("/add-request")
    public String addRequestPage(Model model) {
        model.addAttribute("request", new ApplicationRequest());
        model.addAttribute("courses", coursesService.getAllCourses());
        return "add-request";
    }

    @PostMapping("/add-request")
    public String addRequest(@ModelAttribute ApplicationRequest request,
                             @RequestParam String courseId) {
        Courses course = coursesService.getCourseById(courseId);
        request.setCourse(course);
        applicationRequestService.addRequest(request);
        return "redirect:/";
    }

    @GetMapping("/handle-request/{id}")
    public String handleRequestPage(@PathVariable String id, Model model) {
        ApplicationRequest request = applicationRequestService.getRequestById(id);
        if (request != null && !request.isHandled()) {
            model.addAttribute("request", request);
            model.addAttribute("operators", operatorService.getAllOperators());
            return "handle-request";
        }
        return "redirect:/";
    }

    @PostMapping("/assign-operators/{requestId}")
    public String assignOperators(@PathVariable String requestId,
                                  @RequestParam List<String> operatorIds) {
        List<Operator> operators = operatorService.getOperatorsByIds(operatorIds);
        applicationRequestService.handleRequest(requestId, operators);
        return "redirect:/request-details/" + requestId;
    }

    @GetMapping("/request-details/{id}")
    public String requestDetails(@PathVariable String id, Model model) {
        ApplicationRequest request = applicationRequestService.getRequestById(id);
        if (request != null) {
            model.addAttribute("request", request);
            return "request-details";
        }
        return "redirect:/";
    }

    @PostMapping("/delete-request/{id}")
    public String deleteRequest(@PathVariable String id) {
        applicationRequestService.deleteRequest(id);
        return "redirect:/";
    }

    @PostMapping("/remove-operator/{requestId}/{operatorId}")
    public String removeOperator(@PathVariable String requestId,
                                 @PathVariable String operatorId) {
        applicationRequestService.removeOperatorFromRequest(requestId, operatorId);
        return "redirect:/request-details/" + requestId;
    }
}