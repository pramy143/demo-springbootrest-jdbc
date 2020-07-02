package com.example.repository;

import com.example.dao.impl.PaymentHubProcessingDaoImpl;
import com.example.model.Payment;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
@TestExecutionListeners({
                            DependencyInjectionTestExecutionListener.class,
                            DirtiesContextTestExecutionListener.class,
                            TransactionDbUnitTestExecutionListener.class })
public class PaymentHubProcessingDaoTest extends NamedParameterJdbcDaoSupport {

    @MockBean
    private PaymentHubProcessingDaoImpl paymentHubProcessingDao;


    @Test
    @DisplayName("Repository: Fetch all payment success")
    @DatabaseSetup(value="insert-custs.xml", type = DatabaseOperation.CLEAN_INSERT)
    public void testFetchAllPayments() {

        List<Payment> results = paymentHubProcessingDao.fetchAllPayments();

        assertNotNull(results);
    }

    /*@Test
    @DisplayName("Repository: Create payment success")
    public void testCreatePayment() {

        Payments result = paymentHubProcessingRepository.save(modelToEntityMap(payment));

        Payment output = entityToModelMap(result);
        assertNotNull(output);
        assertEquals(output.getPaymentType(), payment.getPaymentType());
        assertEquals(output.getPaymentStatus(), payment.getPaymentStatus());
    }

    @Test
    @DisplayName("Repository: Update payment success")
    public void testUpdatePayment() {

        Payments result = paymentHubProcessingRepository. saveAndFlush(modelToEntityMap(payment));

        Payment output = entityToModelMap(result);
        assertNotNull(output);
        assertEquals(output.getPaymentType(), payment.getPaymentType());
        assertEquals(output.getPaymentStatus(), payment.getPaymentStatus());
    }

    @Test
    @DisplayName("Repository: Fetch all payment success")
    public void testFetchAllPayments() {

        paymentHubProcessingRepository.save(modelToEntityMap(payment));

        List<Payment> results = paymentHubProcessingRepository.findAll().parallelStream()
                                                               .map(paymentEntity -> entityToModelMap(paymentEntity))
                                                               .collect(Collectors.toList());

        assertNotNull(results);
        assertEquals(results.get(0).getPaymentType(), payment.getPaymentType());
        assertEquals(results.get(0).getPaymentStatus(), payment.getPaymentStatus());
    }

    @Test
    @DisplayName("Repository: Delete payment success")
    public void testDeletePaymentById() {

        paymentHubProcessingRepository.delete(modelToEntityMap(payment));
    }

    @Test
    @DisplayName("Repository: Fetch payment by Id success")
    public void testFetchPaymentById() {

        paymentHubProcessingRepository.save(modelToEntityMap(payment));

        Payment result = paymentHubProcessingRepository.findAll()
                                                        .parallelStream()
                                                        //.filter(paymentEntity -> paymentEntity.getPaymentId() == 1L)
                                                        .map(paymentEntity -> entityToModelMap(paymentEntity))
                                      .findFirst()
                                      .orElse(null);

        assertNotNull(result);
        assertEquals(result.getPaymentType(), payment.getPaymentType());
        assertEquals(result.getPaymentStatus(), payment.getPaymentStatus());
    }

    private Payment entityToModelMap(Payments paymentEntity) {

        ModelMapper entityToModel = new ModelMapper();

        return entityToModel.map(paymentEntity, Payment.class);
    }

    private Payments modelToEntityMap(Payment payment) {
        ModelMapper entityToModel = new ModelMapper();

        return entityToModel.map(payment, Payments.class);
    }*/

}
