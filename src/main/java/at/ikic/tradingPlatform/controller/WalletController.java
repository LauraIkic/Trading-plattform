package at.ikic.tradingPlatform.controller;

import at.ikic.tradingPlatform.Enum.TransactionType;
import at.ikic.tradingPlatform.Service.WalletService;
import at.ikic.tradingPlatform.dto.request.WalletRequestDto;
import at.ikic.tradingPlatform.entity.Wallet;
import at.ikic.tradingPlatform.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class WalletController {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private WalletService walletService;

    @PatchMapping("/wallet/{id}")
    public ResponseEntity<Wallet> patchWallet (@PathVariable UUID id, @RequestBody WalletRequestDto data){
        Wallet wallet = walletRepository.findById(id).orElse(null);

        wallet = walletService.addOrRemoveMoney(wallet, data.getAmount(), data.getType());

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
