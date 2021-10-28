package one.transfinite.rms.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @GetMapping
    public List<Contract> getAllContract() {
        return contractService.getAllContract();
    }

    @GetMapping("/{contractId}")
    public Contract getContractById(@PathVariable Long contractId) {
        return contractService.getContractById(contractId);
    }

    @PostMapping
    public ResponseEntity<Contract> addContract(@RequestBody Contract contract) {
        Contract savedContract = contractService.addContract(contract);
        return new ResponseEntity<>(savedContract, HttpStatus.OK);
    }

    @PutMapping
    public void updateContract(@RequestBody Contract contract) {
        contractService.updateContract(contract);
    }

    @DeleteMapping("/{contractId}")
    public void deleteMapping(@PathVariable Long contractId) {
        contractService.deleteContract(contractId);
    }
}
