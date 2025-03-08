package at.ikic.tradingPlatform.controller;

import at.ikic.tradingPlatform.dto.request.TransactionRequestDto;
import at.ikic.tradingPlatform.entity.Transaction;
import at.ikic.tradingPlatform.entity.Wallet;
import at.ikic.tradingPlatform.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private WalletRepository walletRepository;

    @PostMapping("/transaction")
    public ResponseEntity<Transaction> createTransaction (@RequestBody TransactionRequestDto data){
        Wallet wallet = new Wallet();



        walletRepository.save(wallet);

        return ResponseEntity.ok(wallet);
    }

    @GetMapping("/wallet/{id}")
    public ResponseEntity<Wallet> readWallet (@PathVariable UUID id){
        Wallet wallet = walletRepository.findById(id).orElse(null);

        assert wallet != null;
        return ResponseEntity.ok(wallet);
    }
}
