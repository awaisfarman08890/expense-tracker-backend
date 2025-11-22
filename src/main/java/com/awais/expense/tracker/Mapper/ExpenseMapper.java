package com.awais.expense.tracker.Mapper;

import com.awais.expense.tracker.Entity.Category;
import com.awais.expense.tracker.Entity.Expense;
import com.awais.expense.tracker.dto.CategoryDto;
import com.awais.expense.tracker.dto.ExpenseDto;

public class ExpenseMapper {
  public static ExpenseDto maptoExpenseDto(Expense expense) {
      return new ExpenseDto(
              expense.getId(),
              expense.getAmount(),
              expense.getExpenseDate(),
              new CategoryDto(
                      expense.getCategory().getId(),
                      expense.getCategory().getName()
              )
      );
  }
  public static Expense maptoExpense(ExpenseDto expenseDto) {
      Category category = new Category();
      category.setId(expenseDto.categoryDto().id());
      return new Expense(
              expenseDto.id(),
              expenseDto.amount(),
              expenseDto.expenseDate(),
              category

      );
  }
}
