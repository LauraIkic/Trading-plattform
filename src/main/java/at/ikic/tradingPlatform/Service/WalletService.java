package at.ikic.tradingPlatform.Service;

import at.ikic.tradingPlatform.Enum.TransactionType;
import at.ikic.tradingPlatform.entity.Coin;
import at.ikic.tradingPlatform.entity.Wallet;
import at.ikic.tradingPlatform.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    public Wallet addOrRemoveMoney(Wallet wallet, double money, TransactionType type) {

      BigDecimal newBalance;
      if ((type == TransactionType.BUY)) {
          newBalance = wallet.getBalance().add(BigDecimal.valueOf(money));
      } else {
          newBalance = wallet.getBalance().subtract(BigDecimal.valueOf(money));
      }

      wallet.setBalance(newBalance);
      return wallet;
  }
}
