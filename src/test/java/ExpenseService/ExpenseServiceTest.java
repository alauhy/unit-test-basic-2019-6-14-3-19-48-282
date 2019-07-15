package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {

        Project project = new Project(ProjectType.INTERNAL, "project1");


        assertSame(ExpenseType.INTERNAL_PROJECT_EXPENSE, ExpenseService.getExpenseCodeByProjectTypeAndName(project));
    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {

        Project project = new Project(ProjectType.EXTERNAL, "Project A");

        assertSame(ExpenseType.EXPENSE_TYPE_A, ExpenseService.getExpenseCodeByProjectTypeAndName(project));
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
        Project project = new Project(ProjectType.EXTERNAL, "Project B");

        assertSame(ExpenseType.EXPENSE_TYPE_B, ExpenseService.getExpenseCodeByProjectTypeAndName(project));
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        Project project = new Project(ProjectType.EXTERNAL, "Project C");

        assertSame(ExpenseType.OTHER_EXPENSE,ExpenseService.getExpenseCodeByProjectTypeAndName(project));
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() {
        Project project = new Project(ProjectType.UNEXPECTED_PROJECT_TYPE, "Project C");

        assertThrows(UnexpectedProjectTypeException.class,()->ExpenseService.getExpenseCodeByProjectTypeAndName(project));

    }
}