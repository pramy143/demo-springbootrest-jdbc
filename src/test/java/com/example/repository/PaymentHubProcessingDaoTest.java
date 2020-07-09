package com.example.repository;

import com.example.dao.PaymentHubProcessingDao;
import com.example.dao.impl.PaymentHubProcessingDaoImpl;
import com.example.model.Payment;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DatabaseSetup("classpath:/data/payments_dataset.xml")
public class PaymentHubProcessingDaoTest extends AbstractDependencyInjectionSpringContextTests {

    @MockBean
    private PaymentHubProcessingDao paymentHubProcessingDao;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSourceDBDirect;

    @BeforeAll
    private void setUP() {

        paymentHubProcessingDao = new PaymentHubProcessingDaoImpl(dataSourceDBDirect());
    }

    @Test
    @DisplayName("Repository: Fetch all payment success")
    public void testFetchAllPayments() {

        List<Payment> results = paymentHubProcessingDao.fetchAllPayments();

        assertNotNull(results);
        assertEquals(Long.valueOf("8"), results.get(0).getPaymentId());
    }

    @Test
    @DisplayName("Repository: Create payment success")
    public void testCreatePayment() {

        Payment payment = new Payment.Builder()
            .paymentId(8L)
            .paymentType("SEPA")
            .paymentStatus("COMPLETED")
            .description("Testing")
            .build();

        Payment result = paymentHubProcessingDao.createPayment(payment);

        assertNotNull(result);
        assertEquals(result.getPaymentType(), payment.getPaymentType());
        assertEquals(result.getPaymentStatus(), payment.getPaymentStatus());
    }

    @Test
    @DisplayName("Repository: Update payment success")
    public void testUpdatePayment() {

        Payment payment = new Payment.Builder()
            .paymentId(8L)
            .paymentType("SEPA")
            .paymentStatus("STOPPED")
            .description("Testing")
            .build();

        Payment result = paymentHubProcessingDao.updatePaymentById(payment);
        assertNotNull(result);
        assertEquals(result.getPaymentType(), payment.getPaymentType());
        assertEquals(result.getPaymentStatus(), payment.getPaymentStatus());
    }

    @Test
    @DisplayName("Repository: Delete payment success")
    public void testDeletePaymentById() {

        Payment payment = new Payment.Builder()
            .paymentId(8L)
            .paymentType("SEPA")
            .paymentStatus("REJECTED")
            .description("Testing")
            .build();

        Payment result = paymentHubProcessingDao.deletePaymentById(payment);
        assertNotNull(result);
        assertEquals(result.getPaymentType(), payment.getPaymentType());
        assertEquals(result.getPaymentStatus(), payment.getPaymentStatus());
    }

    @Test
    @DisplayName("Repository: Fetch payment by Id success")
    public void testFetchPaymentById() {

        Payment payment = new Payment.Builder()
            .paymentId(8L)
            .paymentType("SEPA")
            .paymentStatus("STOPPED")
            .description("Testing")
            .build();

        Payment result = paymentHubProcessingDao.deletePaymentById(payment);
        assertNotNull(result);
        assertEquals(result.getPaymentType(), payment.getPaymentType());
        assertEquals(result.getPaymentStatus(), payment.getPaymentStatus());
    }

}
