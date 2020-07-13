package by.krivorot.ishop.command;

import java.util.HashMap;
import java.util.Map;

import by.krivorot.ishop.command.impl.AddToCartCommand;
import by.krivorot.ishop.command.impl.AuthorizationCommand;
import by.krivorot.ishop.command.impl.ChangeFormCommand;
import by.krivorot.ishop.command.impl.ChangeLocaleCommand;
import by.krivorot.ishop.command.impl.DeleteFromCartCommand;
import by.krivorot.ishop.command.impl.EditUserDataCommand;
import by.krivorot.ishop.command.impl.GoToCartCommand;
import by.krivorot.ishop.command.impl.GoToCatalogCommand;
import by.krivorot.ishop.command.impl.GoToLoginCommand;
import by.krivorot.ishop.command.impl.GoToMainCommand;
import by.krivorot.ishop.command.impl.GoToPersonalCommand;
import by.krivorot.ishop.command.impl.GoToProductCommand;
import by.krivorot.ishop.command.impl.GoToProductListCommand;
import by.krivorot.ishop.command.impl.RegistrationCommand;
import by.krivorot.ishop.command.impl.SignOutCommand;


public class CommandProvider {

private Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandProvider() {
		commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.GO_TO_MAIN, new GoToMainCommand());
		commands.put(CommandName.GO_TO_LOGIN, new GoToLoginCommand());
		commands.put(CommandName.CHANGE_FORM, new ChangeFormCommand());
		commands.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());
		commands.put(CommandName.SIGN_OUT, new SignOutCommand());
		commands.put(CommandName.GO_TO_PERSONAL, new GoToPersonalCommand());
		commands.put(CommandName.EDIT_USER_DATA, new EditUserDataCommand());
		commands.put(CommandName.GO_TO_CART, new GoToCartCommand());
		commands.put(CommandName.GO_TO_PRODUCT, new GoToProductCommand());
		commands.put(CommandName.DELETE_FROM_CART, new DeleteFromCartCommand());
		commands.put(CommandName.GO_TO_CATALOG, new GoToCatalogCommand());
		commands.put(CommandName.GO_TO_PRODUCT_LIST, new GoToProductListCommand());
		commands.put(CommandName.ADD_TO_CART, new AddToCartCommand());
		
	}
	
	public Command getCommand(String name) {
		CommandName commandName = CommandName.valueOf(name.toUpperCase());
		Command command;
		command = commands.get(commandName);
		
		return command;
	}
}
