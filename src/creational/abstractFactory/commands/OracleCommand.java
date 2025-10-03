package creational.abstractFactory.commands;

import creational.abstractFactory.products.IDBCommand;

public class OracleCommand implements IDBCommand {
    @Override
    public void execute(String query) {
        // Query operations...
        System.out.println("Query is being executed on Oracle: " + query);
    }
}
