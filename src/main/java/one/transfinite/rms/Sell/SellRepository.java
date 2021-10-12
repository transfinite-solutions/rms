package one.transfinite.rms.Sell;

import one.transfinite.rms.user.User;
import one.transfinite.rms.utility.PaymentStatus;
import one.transfinite.rms.utility.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellRepository extends JpaRepository<Sell, Long> {

    Optional<List<Sell>> findSellByVendor(User vendor);

    Optional<List<Sell>> findSellByCustomer(User customer);

    Optional<List<Sell>> findSellByStatus(Status status);

    Optional<List<Sell>> findSellByPaymentStatus(PaymentStatus paymentStatus);
}
