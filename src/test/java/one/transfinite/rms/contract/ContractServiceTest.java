package one.transfinite.rms.contract;

import one.transfinite.rms.execption.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContractServiceTest {

    @Mock
    private ContractRepository contractRepository;

    @InjectMocks
    private ContractService contractService;

    @Test
    void saveContractTest() {
        Contract contract = new Contract();
        contract.setName("contract1");
        contract.setTotalPrice(123.56);
        contract.setDescription("description");
        contract.setCreatedAt(new Date());
        contract.setStartDate(new Date(2021, 11, 1));
        contract.setEndDate(new Date(2021, 12, 1));

        contractService.addContract(contract);
        ArgumentCaptor<Contract> contractArgumentCaptor = ArgumentCaptor.forClass(Contract.class);
        verify(contractRepository).save(contractArgumentCaptor.capture());
        Contract captorValue = contractArgumentCaptor.getValue();

        assertThat(captorValue).isEqualTo(contract);
    }

    @Test
    void getAllContractTest() {
        List<Contract> contractList = new ArrayList<>();
        Contract contract = new Contract();
        contract.setName("contract1");
        contract.setTotalPrice(123.56);
        contract.setDescription("description");
        contract.setCreatedAt(new Date());
        contract.setStartDate(new Date(2021, 11, 1));
        contract.setEndDate(new Date(2021, 12, 1));
        contractList.add(contract);

        when(contractRepository.findAll()).thenReturn(contractList);
        List<Contract> allContract = contractService.getAllContract();

        assertThat(allContract).isEqualTo(contractList);
    }

    @Test
    void itShouldGetContractById() {
        long contractId = 1L;
        Contract contract = new Contract();
        contract.setContractId(contractId);
        contract.setName("contract1");
        contract.setTotalPrice(123.56);
        contract.setDescription("description");
        contract.setCreatedAt(new Date());
        contract.setStartDate(new Date(2021, 11, 1));
        contract.setEndDate(new Date(2021, 12, 1));

        when(contractRepository.findById(contractId)).thenReturn(Optional.of(contract));
        Contract contractById = contractService.getContractById(contractId);

        assertThat(contractById).isEqualTo(contract);
    }

    @Test
    void itShouldNotGetContractById() {
        long contractId = 1L;

        when(contractRepository.findById(contractId)).thenReturn(Optional.ofNullable(null));
        try {
            contractService.getContractById(contractId);
        } catch (ResourceNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Contract does not exists");
        }
    }
}