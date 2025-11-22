package com.awais.expense.tracker.Controller;

import com.awais.expense.tracker.Service.ExpenseService;
import com.awais.expense.tracker.dto.ExpenseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@AllArgsConstructor
@Data
@Tag(
        name = "CRUD REST API Expense - Resource ",
        description = "CRUD REST API Expense - Resource Create Expense, Update expense, Get expense, delete expense"
)
public class ExpenseController {
    private final ExpenseService expenseService;
    @Operation(
            summary = "Create Expense REST API",
            description = "CREATE Expense REST API save data into database"

    )
    @ApiResponse(
            responseCode = "201",
            description = "Http STATUS 201 CREATED "
    )
    @PostMapping
    public ResponseEntity<ExpenseDto> createExpense(@RequestBody ExpenseDto expenseDto) {
        ExpenseDto expense = expenseService.createExpense(expenseDto);
        return new ResponseEntity<>(expense, HttpStatus.CREATED);

    }
    @Operation(
            summary = "GET BY ID Expense REST API",
            description = "GET BY ID Expense REST API"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http STATUS 200 OK "
    )
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDto> getExpenseById(@PathVariable("id") long expenseId) {
        ExpenseDto expense = expenseService.getExpenseById(expenseId);
        return ResponseEntity.ok(expense);
    }
    @Operation(
            summary = "GET ALL Expense REST API",
            description = "GET ALL Expense REST API "

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http STATUS 200 OK "
    )

    @GetMapping
    public ResponseEntity<List<ExpenseDto>> getAllExpenses() {
        List<ExpenseDto> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }
    @Operation(
            summary = "UPDATE Expense REST API",
            description = "UPDATE Expense REST API "

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http STATUS 200 OK "
    )
    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDto> updateExpense(@PathVariable("id") Long expenseId, @RequestBody ExpenseDto expenseDto) {
        ExpenseDto expense = expenseService.updateExpense(expenseId, expenseDto);
        return ResponseEntity.ok(expense);
    }
    @Operation(
            summary = "DELETE Expense REST API",
            description = "DELETE Expense REST API"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http STATUS 200 OK "
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable("id") long expenseId) {
        expenseService.deleteExpense(expenseId);
        return ResponseEntity.ok("Deleted expense");
    }
}
