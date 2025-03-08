package at.ikic.tradingPlatform.dto.request;

import at.ikic.tradingPlatform.Enum.TransactionType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class TransactionRequestDto {

    @NotNull(message = "Wallet id cannot be null")
    private UUID walletId;

    private UUID assetId;

    private int quantity;

    private UUID portfolioID;

    @NotNull(message = "Type cannot be null")
    private TransactionType type;
}
