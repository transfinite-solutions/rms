package one.transfinite.rms.contract;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ContractRepositoryTest {

    @Autowired
    private ContractRepository contractRepository;

    @AfterEach
    void tearDown() {
        contractRepository.deleteAll();
    }

    @Test
    void saveContractTest() {
        Contract contract = new Contract();
        contract.setCreatedAt(new Date());
        contract.setDescription("description");
        contract.setName("contract1");
        contract.setTotalPrice(768d);

        Contract savedContract = contractRepository.save(contract);
        assertThat(savedContract.getContractId()).isGreaterThan(0);
    }

    @Test
    void deleteAllContractTest() {
        Contract contract = new Contract();
        contract.setCreatedAt(new Date());
        contract.setDescription("description");
        contract.setName("contract1");
        contract.setTotalPrice(768d);

        contractRepository.save(contract);
        contractRepository.deleteAll();

        assertThat(contractRepository.count()).isEqualTo(0);
    }
}