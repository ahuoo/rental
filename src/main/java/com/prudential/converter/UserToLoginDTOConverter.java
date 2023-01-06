package com.prudential.converter;

import com.prudential.domain.entity.User;
import com.prudential.dto.LoginDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class UserToLoginDTOConverter implements Converter<User, LoginDTO> {

	@Override
	public LoginDTO convert(User user) {

		return LoginDTO.builder()
				.id(user.getId())
				.created(user.getCreated().format(DateTimeFormatter.ISO_DATE_TIME))
				.name(user.getName())
				.email(user.getEmail())
				.build();
	}

}
