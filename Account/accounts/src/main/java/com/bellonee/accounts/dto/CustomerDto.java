package com.bellonee.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    @Schema(
            description = "Name of the customer",
            example = "Wajiu Bello Olarewaju"
    )
    @NotEmpty(message="Name field can not be null or empty")
    @Size(min=4, max = 30, message = "please customer name must be minimum of 4 character and maximum of 30 character")
    private String name;

    @Schema(
            description = "Email of the customer",
            example = "bellowajiuo@gmail.com"
    )
    @NotEmpty(message="Email field can not be null or empty")
    @Email(message = "Email format must be valid")
    private String email;

    @Schema(
            description = "Phone number of the customer",
            example = "09039156972"
    )
    @NotEmpty(message="Phone number can not be  null or empty")
    @Pattern(regexp = "(^$|[0-9]{11})", message = "Phone number must be atLeast 10 digits")
    private String phoneNumber;

    @Schema(
            description = "Account details of the customer"
    )
    private AccountDto accountDto;
}
