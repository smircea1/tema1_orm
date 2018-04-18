package com.example.ejb.webRemote;

import javax.ejb.Remote;
 
import com.example.ejb.dto.UserDTO;
import com.example.ejb.exception.ChangePasswordException;
import com.example.ejb.exception.LoginException;
import com.example.ejb.webDTO.ChangePasswordDTO;
import com.example.ejb.webDTO.LoginDTO;

@Remote 
public interface WebActionsRemote {
	UserDTO login(LoginDTO loginAction) throws LoginException;
	boolean changePassword(ChangePasswordDTO changePass) throws ChangePasswordException;
}
