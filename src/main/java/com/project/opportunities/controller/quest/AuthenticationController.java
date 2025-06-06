package com.project.opportunities.controller.quest;

import com.project.opportunities.domain.dto.user.request.UserLoginRequestDto;
import com.project.opportunities.domain.dto.user.request.UserRegistrationRequestDto;
import com.project.opportunities.domain.dto.user.response.UserLoginResponseDto;
import com.project.opportunities.domain.dto.user.response.UserResponseDto;
import com.project.opportunities.security.AuthenticationService;
import com.project.opportunities.service.core.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Authentication",
        description = "Endpoints for managing team members' authentication and registration"
)
@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Operation(
            summary = "Register a new team member",
            description = """
                    Registers a new team member by providing the required registration details.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User registered successfully",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data"
            )
    })
    @PostMapping(value = "/registration", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserResponseDto register(@ModelAttribute @Valid UserRegistrationRequestDto requestDto) {
        log.info("Registration request: {}", requestDto);
        return userService.register(requestDto);
    }

    @Operation(
            summary = "Login team member",
            description = "Allows a team member to login by providing their credentials."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User authenticated successfully",
                    content = @Content(schema = @Schema(
                            implementation = UserLoginResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid credentials"
            )
    })
    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        log.info("Login request: {}", requestDto);
        return authenticationService.authenticate(requestDto);
    }
}
