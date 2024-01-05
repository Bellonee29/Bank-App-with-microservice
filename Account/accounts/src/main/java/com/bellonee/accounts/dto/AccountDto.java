package com.bellonee.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountDto {

    @NotEmpty(message="Account number can not be  null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Phone number must be 10 digits")
    @Schema(
            description = "Account number of the customer",
            example = "2394857564"
    )
    private Long accountNumber;

    @NotEmpty(message="AccountType can not be null or empty")
    @Schema(
            description = "Account Type of the WalletWise",
            example = "Saving"
    )
    private String accountType;


    @NotEmpty(message="BranchAddress  can not be null or empty")
    @Schema(
            description = "Branch Address of the WalletWise",
            example = "232, Island Lagos"
    )
    private String branchAddress;
}
