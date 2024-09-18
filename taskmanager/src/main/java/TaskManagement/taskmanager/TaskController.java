package TaskManagement.taskmanager;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping({ "", "/" })
    public String showTaskList(Model model) {
        List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        TaskDTO taskDto = new TaskDTO();
        model.addAttribute("taskDto", taskDto);
        return "tasks/CreateTask";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute TaskDTO taskDto) {
        Task task = new Task();
        task.setTask(taskDto.getTask());
        task.setDescription(taskDto.getDescription());
        task.setPriority(taskDto.getPriority());
        task.setStatus(taskDto.getStatus());
        taskService.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String showUpdatePage(@PathVariable Integer id, Model model) {
        Task task = taskService.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        TaskDTO taskDto = new TaskDTO();
        taskDto.setId(task.getId());
        taskDto.setTask(task.getTask());
        taskDto.setDescription(task.getDescription());
        taskDto.setPriority(task.getPriority());
        taskDto.setStatus(task.getStatus());
        model.addAttribute("taskDto", taskDto);
        return "tasks/UpdateTask";
    }

    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable Integer id, @ModelAttribute TaskDTO taskDto) {
        Task task = taskService.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTask(taskDto.getTask());
        task.setDescription(taskDto.getDescription());
        task.setPriority(taskDto.getPriority());
        task.setStatus(taskDto.getStatus());
        taskService.update(task);
        return "redirect:/tasks";
    }

    @PostMapping("/{id}")
    public String deleteTask(@PathVariable Integer id) {
        Task task = taskService.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        taskService.delete(task);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String viewTask(@PathVariable Integer id, Model model) {
        Task task = taskService.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        model.addAttribute("task", task);
        return "tasks/TaskDetails";
    }
}
