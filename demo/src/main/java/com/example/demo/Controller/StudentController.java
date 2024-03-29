package com.example.demo.Controller;

import com.example.demo.Model.Student;
import com.example.demo.Service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
    private StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/students")
    public String getAllStudents(Model model){
        model.addAttribute("students",studentService.getAll());
        return "students";
    }

    @GetMapping("/students/add")
    public String addStudent(Model model){
        Student student = new Student();
        model.addAttribute("student",student);
        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student){
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/student/edit/{id}")
    public String editStudentForm(@PathVariable Long id , Model model){
        model.addAttribute("student",studentService.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,@ModelAttribute("student") Student student,Model model){
        Student exitdata = studentService.getStudentById(id);
        exitdata.setId(id);
        exitdata.setFirstName(student.getFirstName());
        exitdata.setLastName(student.getLastName());
        exitdata.setEmail(student.getEmail());

        studentService.updateStudent(exitdata);
        return "redirect:/students";
    }

   @GetMapping("/students/{id}")
   public String deleteStudent(@PathVariable Long id){
         studentService.deleteStudent(id);
         return "redirect:/students";
    }
}
