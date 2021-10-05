package one.transfinite.rms.contract;

import one.transfinite.rms.execption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    public List<Contract> getAllContract() {
        return contractRepository.findAll();
    }

    public Contract getContractById(Long contractId) {
        return contractRepository.findById(contractId).orElseThrow(() -> new ResourceNotFoundException("Contract does not exists"));
    }

    public void addContract(Contract contract) {
        contractRepository.save(contract);
    }

    public void updateContract(Contract contract){
        contractRepository.findById(contract.getContractId()).orElseThrow(() -> new ResourceNotFoundException("Contract does not exists"));

        contractRepository.save(contract);
    }

    public void deleteContract(Long contractId) {
        contractRepository.findById(contractId).orElseThrow(() -> new ResourceNotFoundException("Contract does not exists"));
        contractRepository.deleteById(contractId);
    }
}
