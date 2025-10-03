package creational.abstractFactory.connections;

import creational.abstractFactory.products.IDBConnection;

public class PostgreConnection implements IDBConnection {
    @Override
    public void connect() {
        // SQL connection operations...
        System.out.println("Connected PostgreSQL DB");
    }
}
