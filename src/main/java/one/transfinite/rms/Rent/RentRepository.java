package one.transfinite.rms.Rent;

import one.transfinite.rms.user.User;
import one.transfinite.rms.utility.PaymentStatus;
import one.transfinite.rms.utility.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {

    Optional<List<Rent>> findRentByVendor(User vendor);

    Optional<List<Rent>> findRentByCustomer(User customer);

    Optional<List<Rent>> findRentByStatus(Status status);

    Optional<List<Rent>> findRentByPaymentStatus(PaymentStatus paymentStatus);
}
