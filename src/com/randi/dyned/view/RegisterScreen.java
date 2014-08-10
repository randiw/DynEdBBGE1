package com.randi.dyned.view;

import com.randi.dyned.view.manager.RegisterManager;

public class RegisterScreen extends BasicScreen {

	public RegisterScreen() {
		super("Register");
		add(new RegisterManager());
	}
}