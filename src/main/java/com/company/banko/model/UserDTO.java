package com.company.banko.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class UserDTO implements Serializable {

   private Long id;

   @NotNull
   private String userName;

   @NotNull
   private String password;

   @NotNull
   private String role;

}
