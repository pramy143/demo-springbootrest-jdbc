package com.example.dao.impl;

import com.example.dao.PaymentHubProcessingDao;
import com.example.dao.mapper.PaymentRowMapper;
import com.example.model.Payment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PaymentHubProcessingDaoImpl extends NamedParameterJdbcDaoSupport implements PaymentHubProcessingDao {

    @Value("${payment.select.query}")
    private String selectQuery;

    @Value("${payment.select.byid.query}")
    private String selectByIdQuery;

    @Value("${payment.insert.query}")
    private String insertQuery;

    @Value("${payment.update.query}")
    private String updateQuery;

    @Value("${payment.softdelete.query}")
    private String deleteQuery;


    private NamedParameterJdbcTemplate jdbcTemplate;

    public PaymentHubProcessingDaoImpl() {
    }

    public PaymentHubProcessingDaoImpl(DataSource dataSource) {
        setDataSource(dataSource);
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Payment> fetchAllPayments() {
        List<Payment> list = jdbcTemplate.query(selectQuery, new PaymentRowMapper());

        return list;
    }

    public Payment fetchPaymentById(final Long paymentId) {
        Map<String, Object> params = new HashMap<>();
        params.put("paymentId", paymentId);

        return jdbcTemplate.queryForObject(selectByIdQuery, params, new PaymentRowMapper());
    }

    public Payment createPayment(final Payment payment) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(insertQuery, createSqlParameterSourceForInsert(payment), keyHolder);

        return fetchPaymentById(keyHolder.getKey().longValue());
    }

    public Payment updatePaymentById(final Payment payment) {
        jdbcTemplate.update(updateQuery, createSqlParameterSourceForUpdate(payment));

        return fetchPaymentById(payment.getPaymentId());
    }

    public Payment deletePaymentById(final Payment payment) {
        jdbcTemplate.update(deleteQuery, createSqlParameterSourceForDelete(payment));

        return fetchPaymentById(payment.getPaymentId());
    }

    private SqlParameterSource createSqlParameterSourceForInsert(final Payment payment) {
        return new MapSqlParameterSource("paymentStatus", payment.getPaymentStatus().name())
            .addValue("paymentType", payment.getPaymentType().name())
            .addValue("description", payment.getDescription())
            .addValue("createdDate", new Date());
    }

    private SqlParameterSource createSqlParameterSourceForUpdate(final Payment payment) {
        return new MapSqlParameterSource("paymentStatus", payment.getPaymentStatus().name())
            .addValue("paymentType", payment.getPaymentType().name())
            .addValue("description", payment.getDescription())
            .addValue("createdDate", new Date())
            .addValue("paymentId", payment.getPaymentId());
    }

    private SqlParameterSource createSqlParameterSourceForDelete(final Payment payment) {
        return new MapSqlParameterSource("paymentStatus", payment.getPaymentStatus().name())
            .addValue("paymentId", payment.getPaymentId());
    }

}
