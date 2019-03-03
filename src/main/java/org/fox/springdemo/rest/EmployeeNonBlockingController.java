package org.fox.springdemo.rest;

import org.fox.springdemo.entity.Employee;
import org.fox.springdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

@RestController
@RequestMapping("/api")
public class EmployeeNonBlockingController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees-nonblocking")
    public DeferredResult<List<Employee>> getEmployeesAsync() {

        final DeferredResult<List<Employee>> deferredResult = new DeferredResult<>();

        //timeout and error
        deferredResult.onTimeout(() -> deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request Timeout")));
        deferredResult.onError(throwable -> deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error")));

        //put work to do on a new thread that will run in the background.
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(() -> {

            List<Employee> employeeList = employeeService.getEmployees();

            if (employeeList.isEmpty()) {
                throw new EmployeeNotFoundException("No employees could be found");
            }

            deferredResult.setResult(employeeList);
        });

        return deferredResult;
    }


}