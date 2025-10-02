package abstractFactory.impls;

import abstractFactory.commands.MySQLCommand;
import abstractFactory.connections.MySQLConnection;
import abstractFactory.products.IDBCommand;
import abstractFactory.products.IDBConnection;
import abstractFactory.factory.IDBFactory;

public class MySQLFactoryImpl implements IDBFactory {
    @Override
    public IDBConnection createConnection() {
        return new MySQLConnection();
    }

    @Override
    public IDBCommand createCommand() {
        return new MySQLCommand();
    }
}
