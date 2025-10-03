## Abstract Factory Pattern:
İlişkili object'lerin oluşturulmasından sorumlu bir interface'dir.
Factory method ile karıştırılmamalıdır. Factory method, bir method'dur. Abstract factory ise bir class'tır (interface).
Temelde abstract factory, içerisinde birbirleriyle ilişkili birden fazla factory method içeren bir class'tır (interface).

İçerisinde multiple factory method bulunduğu için bu pattern'a aynı zamanda **"super-factory"** de denir.

### Neden Abstract Factory Pattern?
Yazdığın projelerde doğrudan birçok concrete class yapısı kullanıyorsan, yeni concrete class'ların projeye entegre edilebilmesi 
için mevcuttaki diğer concrete class'ların değiştirilmesi gerekebilir. Bu da "closed for modification" problemine sebep olur 
yani diğer bir deyişle, OCR prensibine aykırı bir tutum sergilemiş olursun çünkü yazılan class'lar extendible ama modification'a kapalı 
olmalıdır.

### Nasıl Oluşturacağız?
Abstract factory pattern, birden fazla ama birbirleri ile ilişkili factory method'ları içermelidir. Öncelikle hazırda var olan 
ekosistemimizi inceleyelim.

```java
public interface IDBCommand {
    void execute(String query);
}

public interface IDBConnection {
    void connect();
}
```
Concrete class'lara method'lar sağlayan IDBCommand ve IDBConnection isimli iki tane interface'imiz var. 

IDBConnection, farklı database'lere bağlanmak için gerekli olan connect method'unun implement edilmesini sağlamak amaçlı 
oluşturulmuş bir interface'dir.

IDBCommand ise girilen query'yi ilgili database'de execute etmeyi sağlayan execute method'unu kendini implement edecek class'lara
verecek olan bir interface'dir.

```java
import creational.abstractFactory.products.IDBConnection;

public class MySQLConnection implements IDBConnection {
    @Override
    public void connect() {
        // SQL connection operations...
        System.out.println("Connected MySQL DB");
    }
}
// Postgre connection class...
// Oracle connection class...
```

```java
import creational.abstractFactory.products.IDBCommand;

public class MySQLCommand implements IDBCommand {
    @Override
    public void execute(String query) {
        // Query operations...
        System.out.println("Query is being executed on MySQL: " + query);
    }
}
// Postgre command class...
// Oracle command class...
```
MySQLConnection ve MySQLCommand class'ları sırasıyla IDBConnection ve IDBCommand interface'lerini implement ederek 
kendileri için gerekli olan method'ları alabilecekler.

Aynı işlemleri Postgre ve Oracle için de yapıyoruz. 

Bu aşamadan sonra abstract factory entegrasyonuna geçeceğiz. Şu an farklı db'lerle connection kuran ve bu db'lere query
gönderecek method'ları oluşturduk. Şimdi ise bu class'larımızın instance'larını oluşturmak için abstract factory pattern
kullanacağız. 

Öncelikle projemizin yapısını inceleyelim, birbirini tamamlayıcı özelliklere sahip ortak class ailesi içerisinde yer alan 
2 tane class vardır.

Birinin görevi db ile bağlantı kurmayı sağlamak iken diğerinin görevi bağlantı kurulmuş db'lere query yollamaya sağlıyor

##### **DB bağlantısı kurmayı sağlayan class'lar =** MySQLConnection, OracleConnection, PostgreConnection
##### **DB'ye query gönderen class'lar =** MySQLCommand, OracleCommand, PostgreCommand

Amacımız, her class için kendi özel object'leri oluşturmayı sağlayacak factory method'ları içerecek abstract factory
interface'ini oluşturmak ve bunu implement edecek concrete class'ların içerisinde object'leri oluşturup return etmek.

Öncelikle abstract factory interface'ini oluşturalım.

```java
import creational.abstractFactory.products.IDBCommand;
import creational.abstractFactory.products.IDBConnection;

public interface IDBFactory {
    IDBConnection createConnection();

    IDBCommand createCommand();
}
```

Görüldüğü üzere connection class'ları için createConnection(), command için ise createCommand() method'ları yer alıyor.

Şimdi ise bu interface'i implement edip bize gerekli olacak object'leri oluşturacak concrete impl class'larını oluşturalım.

```java
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

// Postgre impl class...
// Oracle impl class...
```

Bu aşamadan sonra client'in (object'leri oluşturmak isteyen vatandaşın) tek yapması gereken şey, ihtiyaç duyduğu
db'yi kullanabilmek için impl class'larının instance'larını oluşturmalı ve hemen sonrasında impl içerisinde yer alan object 
oluşturma method'larını kendi ihtiyacına göre call etmeli. 

##### **Client tarafı:**

```java
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
```
## Constraint:
Bu pattern'ın en büyük kısıtı, her bir object ailesi için her zaman aynı grup object'lerin üretiliyor olmasıdır.
Kendi örneğimizde mysql, postgres ve oracle object'lerini create etmek için oluşturulan FactoryImpl class'ları, IDBFactory
interface'ini implement ettiği için createConnection() ve createCommand() isimli iki method'u da kullanmak zorundadır. 

Eğer object ailesinin bağlamından daha farklı özelliklere sahip factory method'lar oluşturulması gerekiyorsa ortak bir 
abstract factory interface'i kullanamayız. Mesela, IDBFactory içerisine createButton() isimli bir factory method yazmak 
aynı proje içerisinde geliştirme yapan diğer developer'ların Button ile ilgili olan yapıların da DB ailesine ait olduğunu 
düşünmesine yol açacaktır.

Bu durumda, Button, List ve Table gibi yapıların ortak bir IGUIFactory isimli aileden object'lerinin oluşturulması mantıklı
bir çözüm olacaktır.

### Örnek 2:
Bu pattern'ın kısıtının daha iyi anlaşılması için bir üniversiteyi ele alalım.

```
clas Course
+ Course()

class Professor
+ Professor()

class Schedule
+ Schedule()

interface IUniversityFactory 
+ createCourse(): Course
+ createProfessor(): Professor
+ createSchedule(): Schedule

class XUniversityFactoryImpl implements IUniversityFactory
+ createCourse(): Course
+ createProfessor(): Professor
+ createSchedule(): Schedule

class YUniversityFactoryImpl implements IUniversityFactory
+ createCourse(): Course
+ createProfessor(): Professor
+ createSchedule(): Schedule
```

Burada XUniversityFactoryImpl ve YUniversityFactoryImpl class'ları IUniversityFactory ailesinden oldukları için 
createCourse(), createProfessor(), createSchedule() isimli 3 tane factory method'u override etmek zorundadırlar.

Yarın bir gün projeye hadi High School ile ilgili object oluşturmak factory method'larını da ekleyelim denirse, bu method'lar
IUniversityFactory interface'ine değil, IHighSchoolFactory isimli yeni bir object ailesine eklenmeli ve kendisini implement 
edecek object oluşturmayı sağlayan impl class'ları tanımlanmalı.

Fakat diyelim ki XUniversityFactoryImpl içerisinde createCourse() factory method'unu projenin hiçbir yerinde kullanmayacağız.
Böyle bir senaryoda ortak bir IUniversityFactory yerine XUniversity için XUniversityFactoryImpl, YUniversity için IYUniversityFactory
isimli farklı interface'ler oluşturmak gerekebilir. 

Bu yapının uml diagramı tahmini şu şekilde olur:
```
clas Course
+ Course()

class Professor
+ Professor()

class Schedule
+ Schedule()

interface IXUniversityFactory
+ createProfessor(): Professor
+ createSchedule(): Schedule

class XUniversityFactoryImpl implements IXUniversityFactory
+ createProfessor(): Professor
+ createSchedule(): Schedule

interface IYUniversityFactory
+ createCourse(): Course
+ createProfessor(): Professor
+ createSchedule(): Schedule

class YUniversityFactoryImpl implements IYUniversityFactory
+ createCourse(): Course
+ createProfessor(): Professor
+ createSchedule(): Schedule
```
XUniversity ile YUniversity'si aynı isimli factory method'lara ihtiyaç duyuyorlar fakat XUniversity'nin factory method'larına
String parametresi geçilmesi gerekiyorken YUniversity'sindekilere Integer parametresi gerekiyor olabilir. Böyle bir senaryoda da
farklı abstract factory'ler (interface'ler) oluşturmak gerekir.

## Sonuç:
Object oluşturmanın getirdiği yükü, client'in üzerinden alıp birbiri ile ilişkili object'leri oluşturmak için oluşturulan 
class'ları oluşturmaktır ve object oluşturma method'larını ortak bir interface içerisinde tutmaktır. Bu interface'e abstract
factory denir. Bu interface içerisinde yer alan method'lara ise factory method'lar denir.

Eğer class'ların ailesi birbirinden farklıysa, farklı interface'lerde bulunmaları gerekir. Abstract factory pattern'daki 
ana hedef, aynı class ailesine sahip object'lerin oluşturulmasını soyutlamaktır. Bu kodlarda yer alan IDBFactory interface'ine
GUI ailesi ile ilgili factory method koymak anlamsızdır çünkü DB ile GUI birbiri ile ilişkili değildir.

Projelerin ilerleyen aşamalarında factory interface'lerine sürekli yeni factory method signature'ları eklenmek zorunda kalınacaktır.
Böyle bir senaryoda kendini implement eden tüm class'lar bu yeni eklenen method'ları override etmek zorunda kalacaktır. 
Bu durumda OCP prensibine aykırı bir durumdur ama projenin ilerleyebilmesi için mecburen bu method'ların subclass'larda
override edilmesi gerekmektedir. Bu pattern'nın en sıkıntılı özelliği budur.