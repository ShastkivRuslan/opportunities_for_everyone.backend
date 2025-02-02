package com.project.opportunities.domain.dto.volunteer.request;

import com.project.opportunities.validation.phone.PhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

public record CreateVolunteerRequestDto(
        @NotBlank
        @Length(max = 20)
        String firstName,
        @NotBlank
        @Length(max = 20)
        String lastName,
        @NotBlank
        @Length(max = 20)
        String middleName,
        @NotBlank
        @PhoneNumber
        String phoneNumber,
        @NotBlank
        @Length(max = 50)
        @Email
        String email,
        @NotNull
        MultipartFile avatar
) {
}
