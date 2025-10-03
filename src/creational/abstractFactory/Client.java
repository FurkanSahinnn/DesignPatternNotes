package creational.abstractFactory;

import creational.abstractFactory.factory.IDBFactory;
import creational.abstractFactory.impls.MySQLFactoryImpl;
import creational.abstractFactory.impls.OracleFactoryImpl;
import creational.abstractFactory.impls.PostgresFactoryImpl;
import creational.abstractFactory.products.IDBCommand;
import creational.abstractFactory.products.IDBConnection;

public class Client {
    private IDBConnection connection;
    private IDBCommand command;

    public Client(IDBFactory factory) {
        this.connection = factory.createConnection();
        this.command = factory.createCommand();
    }

    public void run(String query) {
        connection.connect();
        command.execute(query);
    }

    public static void main(String[] args) {
        IDBFactory mysqlFactory = new MySQLFactoryImpl();
        IDBFactory postgresqlFactory = new PostgresFactoryImpl();
        IDBFactory oracleFactory = new OracleFactoryImpl();


        String clientQuery = "SELECT * FROM users;";

        Client clientForMySQL = new Client(mysqlFactory);
        clientForMySQL.run(clientQuery);

        Client clientForPostgres = new Client(postgresqlFactory);
        clientForPostgres.run(clientQuery);

        Client clientForOracle = new Client(oracleFactory);
        clientForOracle.run(clientQuery);
    }
}
