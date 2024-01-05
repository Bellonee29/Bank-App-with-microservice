package com.bellonee.accounts.controller;


import com.bellonee.accounts.dto.CustomerDto;
import com.bellonee.accounts.dto.ErrorResponseDto;
import com.bellonee.accounts.dto.ResponseDto;
import com.bellonee.accounts.service.IAccountService;
import com.bellonee.accounts.utils.ConstantAccount;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(
        name = "CRUD REST API for accounts in WalletWise",
        description = "CRUD REST API in WalletWise to CREATE, UPDATE, FETCH AND DELETE Accounts details "
)
public class AccountController {

    private IAccountService iAccountService;
    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new customer accounts inside WalletWise"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount (@Valid @RequestBody CustomerDto customerDto){
        iAccountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(ConstantAccount.STATUS_CODE_201, ConstantAccount.MESSAGE_201));
    }
    @Operation(
            summary = "Fetch Account REST API",
            description = "REST API to fetch  customer details inside WalletWise"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK"
    )

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})", message = "Phone number must be 10 digits")
                                                               String phoneNumber){
        CustomerDto customerDto = iAccountService.fetchAccount(phoneNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @Operation(
            summary = "Update Account REST API",
            description = "REST API to update  customer accounts inside WalletWise"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status INTERNAL_SERVER_ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){
        boolean isUpdate = iAccountService.updateAccount(customerDto);
        if (isUpdate){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(ConstantAccount.STATUS_CODE_200, ConstantAccount.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(ConstantAccount.STATUS_417, ConstantAccount.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Account REST API",
            description = "REST API to delete  customer accounts inside WalletWise"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status INTERNAL_SERVER_ERROR"
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                                @Pattern(regexp = "(^$|[0-9]{10})", message = "Phone number must be 10 digits")
                                                                String phoneNumber){
        boolean isDelete = iAccountService.deleteAccount(phoneNumber);
        if (isDelete){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(ConstantAccount.STATUS_CODE_200, ConstantAccount.DELETE ));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(ConstantAccount.STATUS_417, ConstantAccount.MESSAGE_417_DELETE));
        }
    }
}
