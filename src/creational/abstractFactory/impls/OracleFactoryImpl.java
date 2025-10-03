package creational.abstractFactory.impls;

import creational.abstractFactory.commands.OracleCommand;
import creational.abstractFactory.connections.OracleConnection;
import creational.abstractFactory.products.IDBCommand;
import creational.abstractFactory.products.IDBConnection;
import creational.abstractFactory.factory.IDBFactory;

public class OracleFactoryImpl implements IDBFactory {
    @Override
    public IDBConnection createConnection() {
        return new OracleConnection();
    }

    @Override
    public IDBCommand createCommand() {
        return new OracleCommand();
    }
}
