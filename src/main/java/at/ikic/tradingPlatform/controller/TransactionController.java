package at.ikic.tradingPlatform.controller;

import at.ikic.tradingPlatform.Service.WalletService;
import at.ikic.tradingPlatform.dto.request.TransactionRequestDto;
import at.ikic.tradingPlatform.entity.Coin;
import at.ikic.tradingPlatform.entity.Transaction;
import at.ikic.tradingPlatform.entity.Wallet;
import at.ikic.tradingPlatform.repository.CoinRepository;
import at.ikic.tradingPlatform.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private WalletService walletService;

    @Autowired
    private CoinRepository coinRepository;

    @PostMapping("/transaction")
    public ResponseEntity<Wallet> createTransaction (@RequestBody TransactionRequestDto data){
        Transaction transaction = new Transaction();
        Wallet wallet = walletRepository.findById(data.getWalletId()).orElse(null);
        Coin coin = coinRepository.findById(String.valueOf(data.getCoinId())).orElse(null);
        assert coin != null;
        double price = coin.getCurrentPrice() * data.getQuantity();

        wallet = walletService.addOrRemoveMoney(wallet, price , data.getType());


        //add coin to assets -> portfolio should update data

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
