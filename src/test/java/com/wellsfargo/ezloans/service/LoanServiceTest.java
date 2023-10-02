package com.wellsfargo.ezloans.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.wellsfargo.ezloans.exception.ResourceNotFoundException;
import com.wellsfargo.ezloans.model.LoanCard;
import com.wellsfargo.ezloans.repository.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LoanServiceTest {

    @InjectMocks
    private LoanService loanService;

    @Mock
    private LoanRepository loanRepo;

    private LoanCard loanCard;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        loanCard = new LoanCard();
        loanCard.setLoanId("loan123");
    }

    @Test
    public void testSaveLoanCard() {
        when(loanRepo.save(loanCard)).thenReturn(loanCard);
        loanService.saveLoanCard(loanCard);
        verify(loanRepo, times(1)).save(loanCard);
    }

    @Test
    public void testListAll() {
        List<LoanCard> loanCardList = new ArrayList<>();
        loanCardList.add(loanCard);
        when(loanRepo.findAll()).thenReturn(loanCardList);
        List<LoanCard> result = loanService.listAll();
        assertEquals(loanCardList, result);
        verify(loanRepo, times(1)).findAll();
    }

    @Test
    public void testUpdateLoan() throws Exception {
        when(loanRepo.findById(loanCard.getLoanId())).thenReturn(Optional.of(loanCard));
        LoanCard updatedLoanCard = new LoanCard();
        updatedLoanCard.setLoanId("loan123");
        loanService.updateLoan(updatedLoanCard);
        verify(loanRepo, times(1)).findById(loanCard.getLoanId());
        verify(loanRepo, times(1)).save(updatedLoanCard);
    }

    @Test
    public void testUpdateLoanInvalidLoanId() {
        when(loanRepo.findById(loanCard.getLoanId())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> loanService.updateLoan(loanCard));
        verify(loanRepo, times(1)).findById(loanCard.getLoanId());
        verify(loanRepo, never()).save(loanCard);
    }

    @Test
    public void testDeleteLoan() throws Exception {
        when(loanRepo.findById(loanCard.getLoanId())).thenReturn(Optional.of(loanCard));
        loanService.deleteLoan(loanCard);
        verify(loanRepo, times(1)).findById(loanCard.getLoanId());
        verify(loanRepo, times(1)).deleteById(loanCard.getLoanId());
    }

    @Test
    public void testDeleteLoanInvalidLoanId() {
        when(loanRepo.findById(loanCard.getLoanId())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> loanService.deleteLoan(loanCard));
        verify(loanRepo, times(1)).findById(loanCard.getLoanId());
        verify(loanRepo, never()).deleteById(loanCard.getLoanId());
    }
}
