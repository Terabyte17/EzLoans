package com.wellsfargo.ezloans.controller;

import com.wellsfargo.ezloans.model.Category;
import com.wellsfargo.ezloans.model.LoanCard;
import com.wellsfargo.ezloans.service.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoanControllerTest {

    @InjectMocks
    private LoanController loanController;

    @Mock
    private LoanService loanService;
    private LoanCard loanCard;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        loanCard = new LoanCard();
        loanCard.setLoanType(Category.ElectronicGadgets);
        loanCard.setDurationInYears(10);
    }

    @Test
    public void testSaveLoanCard() {
        doNothing().when(loanService).saveLoanCard(loanCard);

        ResponseEntity<String> response = loanController.saveLoanCard(loanCard);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Successfully saved loan card.", response.getBody());
    }

    @Test
    public void testSaveLoanCardException() {
        doThrow(RuntimeException.class).when(loanService).saveLoanCard(loanCard);

        ResponseEntity<String> response = loanController.saveLoanCard(loanCard);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error while saving loan card.", response.getBody());
    }

    @Test
    public void testGetAllLoans() {
        List<LoanCard> loanCards = new ArrayList<>();
        loanCards.add(loanCard);
        when(loanService.listAll()).thenReturn(loanCards);

        ResponseEntity<?> response = loanController.getAllLoans();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(loanCards, response.getBody());
    }

    @Test
    public void testGetAllLoansEmptyList() {
        List<LoanCard> loanCards = new ArrayList<>();
        when(loanService.listAll()).thenReturn(loanCards);

        ResponseEntity<?> response = loanController.getAllLoans();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetAllLoansException() {
        when(loanService.listAll()).thenThrow(RuntimeException.class);

        ResponseEntity<?> response = loanController.getAllLoans();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error while fetching Loan Cards.", response.getBody());
    }

    @Test
    public void testUpdateLoan() throws Exception {
        doNothing().when(loanService).updateLoan(loanCard);

        ResponseEntity<String> response = loanController.updateLoan(loanCard);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Loan Card updated successfully.", response.getBody());
    }

    @Test
    public void testUpdateLoanException() throws Exception {
        doThrow(Exception.class).when(loanService).updateLoan(loanCard);

        ResponseEntity<String> response = loanController.updateLoan(loanCard);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Invalid Loan Card ID. Updation failed.", response.getBody());
    }

    @Test
    public void testDeleteLoanCard() throws Exception {
        doNothing().when(loanService).deleteLoan(loanCard);

        ResponseEntity<String> response = loanController.deleteLoanCard(loanCard);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Loan Card deleted successfully.", response.getBody());
    }

    @Test
    public void testDeleteLoanCardException() throws Exception {
        doThrow(Exception.class).when(loanService).deleteLoan(loanCard);

        ResponseEntity<String> response = loanController.deleteLoanCard(loanCard);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Invalid Loan ID. Deletion failed.", response.getBody());
    }
}
