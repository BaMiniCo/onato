package com.assignment.onato.pojo;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthRequest {
    String username;
    String password;
}
