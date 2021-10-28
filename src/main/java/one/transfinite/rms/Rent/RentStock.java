package one.transfinite.rms.Rent;

import one.transfinite.rms.stock.Stock;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table
public class RentStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    private Rent rent;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    private Stock stock;
}
