package com.awais.expense.tracker.Service.imp;

import com.awais.expense.tracker.Entity.Category;
import com.awais.expense.tracker.Entity.Expense;
import com.awais.expense.tracker.Mapper.ExpenseMapper;
import com.awais.expense.tracker.Service.ExpenseService;
import com.awais.expense.tracker.dto.ExpenseDto;
import com.awais.expense.tracker.exceptions.ResourceNotFoundException;
import com.awais.expense.tracker.repository.CategoryRepository;
import com.awais.expense.tracker.repository.ExpenseRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto) {
        Expense expense = ExpenseMapper.maptoExpense(expenseDto);
        Expense savedExpense = expenseRepository.save(expense);
        return ExpenseMapper.maptoExpenseDto(savedExpense);
    }

    @Override
    public ExpenseDto getExpenseById(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(()-> new ResourceNotFoundException("Expense not found"));
        return ExpenseMapper.maptoExpenseDto(expense);
    }

    @Override
    public List<ExpenseDto> getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        return expenses.stream().map(ExpenseMapper::maptoExpenseDto).collect(Collectors.toList());
    }

    @Override
    public ExpenseDto updateExpense(Long expenseId, ExpenseDto expenseDto) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(()-> new ResourceNotFoundException("Expense not found"));
        expense.setAmount(expenseDto.amount());
        expense.setExpenseDate(expenseDto.expenseDate());
        if(expenseDto.categoryDto() != null) {
            Category category = categoryRepository.findById(expenseDto.categoryDto().id())
                    .orElseThrow(()-> new ResourceNotFoundException("Category not found"));
            expense.setCategory(category);

        }
        Expense updatedExpense = expenseRepository.save(expense);
        return ExpenseMapper.maptoExpenseDto(updatedExpense);
    }

    @Override
    public void deleteExpense(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(()-> new ResourceNotFoundException("Expense not found"));
        expenseRepository.deleteById(expenseId);
    }
}
