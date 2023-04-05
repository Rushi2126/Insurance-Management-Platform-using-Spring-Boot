package com.rushi.insurance.validators;



import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.rushi.insurance.models.Client;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class ClientValidator {

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^[0-9]{10}$");

    @SuppressWarnings("deprecation")
	public List<String> validateClient(Client client) {
        List<String> errors = new ArrayList<>();

        if (StringUtils.isEmpty(client.getName())) {
            errors.add("Name cannot be empty");
        }

        if (!isValidDate(client.getDateOfBirth())) {
            errors.add("Invalid date of birth");
        }

        if (StringUtils.isEmpty(client.getAddress())) {
            errors.add("Address cannot be empty");
        }

        if (!isValidPhoneNumber(client.getPhoneNumber())) {
            errors.add("Invalid phone number");
        }

        return errors;
    }

    private boolean isValidDate(Date date) {
        try {
            LocalDate.parse(date.toInstant().toString());
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches();
    }
}
