package abstractFactory.impls;

import abstractFactory.commands.OracleCommand;
import abstractFactory.connections.OracleConnection;
import abstractFactory.products.IDBCommand;
import abstractFactory.products.IDBConnection;
import abstractFactory.factory.IDBFactory;

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
