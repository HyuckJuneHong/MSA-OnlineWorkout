package kr.co.owopayment.repository;

import kr.co.owopayment.domain.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    Optional<PaymentEntity> findByPaymentCode(String paymentCode);
    List<PaymentEntity> findPaymentsByMemberIdentity(String memberIdentity);
}
