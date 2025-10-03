# Factory Method:
## Amacı:
Tek bir object oluşturma sürecini sağlayacak bir interface oluştur. Hangi class'ın instance'ının oluşturulacağına bu interface'i
implement edecek subclass'lar karar versin.
## Faydası:
Object oluşturma aşamalarının, projenin base class'larından olabildiğince bağımsızlaştırmak. Bu sayede base class'ların 
object oluşturma süreçlerindeki coupling'lerini minimize etmeyi sağlar.
Özetle, object oluşturmadan dolayı oluşan copy-paste kod dağınıklığının önüne geçmeyi sağlar.
## İsmi Factory Method ama niye interface içerisinde yer alıyor?
Java ve C# gibi oop tabanlı dillerde, C++ veya Python gibi dillerdeki gibi method'ları herhangi bir dosya içerisinde 
bağımsız bir şekilde yazamazsın. Bu sebepten ötürü Java ve C# gibi dillerde yazılacak tüm method'lar mutlaka bir class
veya interface içerisinde yer almak zorundadır. 
## Abstract Factory Pattern ile alakası:
Factory method'lar birçok şekillerde interface içerisinde yazılabilirler. Bir interface içerisine ihtiyaç durumunda birden 
fazla factory method yazılabilir ama bu yaklaşım factory method'un "tek bir object için tek bir interface oluştur." 
tanımına uymamaktadır. Bu görevi yerine getirecek olan abstract factory pattern'a ihtiyaç duyulur.
## Constructor vs Factory Method:
Bu pattern'da doğal olarak "Factory method ile uğraşmak yerine neden direkt olarak constructor kullanmıyoruz ki?" sorusu 
sorulmaktadır. Parametreli constructor'lar object oluşturmak için pek iyi tercihler değillerdir çünkü constructor'lar 
parametreleri kendi kendilerin handle edemezler. Dışarıdan kendilerine uygun parametrelerin verilmesi zorunludur ama 
factory method'larda durum biraz farklıdır. Factory method'lar, parametreleri handle etme özelliklerine de sahiptirler.
Amaç, constructor'ın object oluşturma yükünü object oluşturmak için özelleştirilmiş method'lar kullanarak üzerinden almaktır.
## Best Case:
Tek bir factory method yerine object'i oluşturulmak istenen her bir class için ayrı ayrı factory method'lar ve onları 
implement edip object'leri oluşturacak class'lar yazmak.

## Örnek:
**Manager class extends Employee class** = "Manager is a Employee"


**Director class extends Manager class** = "Director is a Manager"

### Employee için:

```java
import creational.factoryMethod.models.Employee;

public interface IEmployeeFactory {
    Employee create(String name);
}

public class EmployeeFactoryImpl implements IEmployeeFactory {
    @Override
    public Employee create(String name) {
        return new Employee(name);
    }
}
```

### Manager için:

```java
import creational.factoryMethod.models.Manager;

public interface IManagerFactory {
    Manager create(String name, String department);
}

public class ManagerFactoryImpl implements IManagerFactory {
    @Override
    public Manager create(String name, String department) {
        return new Manager(name, department);
    }
}
```

### Director için:

```java
import creational.factoryMethod.models.Director;

public interface IDirectorFactory {
    Director create(String name, String department, String plan);
}

public class DirectorFactoryImpl implements IDirectorFactory {
    @Override
    public Director create(String name, String department, String plan) {
        return new Director(name, department, plan);
    }
}
```

### Çalıştırma kısmı:
Oluşturma işini HR class'ı yapacak.

#### HR:
```java
public class HR {
    private List<Employee> employeeList = new ArrayList<>();

    public HR() {}

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void addNewEmployee(Employee employee) {
        employeeList.add(employee);
    }
}
```

```java
public static void main(String[] args) {
    HR hr = new HR();

    IEmployeeFactory employeeFactory = new EmployeeFactoryImpl();
    IManagerFactory managerFactory = new ManagerFactoryImpl();
    IDirectorFactory directorFactory = new DirectorFactoryImpl();


    hr.addNewEmployee(employeeFactory.create("employee1"));
    hr.addNewEmployee(employeeFactory.create("employee2"));
    hr.addNewEmployee(managerFactory.create("manager1", "Software"));
    hr.addNewEmployee(directorFactory.create("director1", "Management", "Have new software produced."));

    for (Employee employee : hr.getEmployeeList()) {
        System.out.println(employee);
    }
}
```
#### Output:
```
Employee{name='employee1'}
Employee{name='employee2'}
Manager{name='manager1', managedDepartment='Software'}
Director{name='director1', managedDepartment='Management', plan='Have new software produced.'}
```

### Static Factory Method Niye Kullanmıyoruz?
Static factory method, factory method'un interface'lerden implement edilip subclass'larda object create edilmesi yerine 
bu factory impl class'larının içerisine static factory method'lar yazmayı amaçlayan bir yaklaşımdır. Bu yaklaşım 2 
farklı şekillerde olur:
1. Tek bir static factory method oluştur. Tüm class'lar, object oluşturma işini burada yapsın.
2. Her bir class için birden fazla static factory method oluştur.

İlki pek mantıklı bir yaklaşım değildir çünkü her bir class için method içerisine if-else atılmak zorunda kalınır.
#### Örnek:

```java
import creational.factoryMethod.models.Director;
import creational.factoryMethod.models.Employee;
import creational.factoryMethod.models.Manager;

public class EmployeeFactory {
    public static Employee create(String classType, String name) {
        Employee employee = null;
        if (classType == "Employee") {
            employee = new Employee(name);
        } else if (classType == "Manager") {
            employee = new Manager(name, "empty");
        } else if (classType == "Director") {
            employee = new Director(name, "empty", "empty");
        }
        return employee;
    }
}
```
Her bir class için if-else kontrolü gerekeceği için SRP ve OCP prensiplerine aykırıdır çünkü farklı işlevlere sahip 
class'lar aynı method içerisinde bulunurlar ve bu kod, her yeni class instance oluşturmak için sürekli değişmek zorundadır.

İkincisinde ise yukarıdaki tek bir create method'u yerine her bir class için ayrı ayrı isimlerde static factory method 
oluşturmayı hedefleyen bir yaklaşımdır. Bu yaklaşım, if-else kod kalabalığının oluşturduğu SRP ve OCP prensip sorununu 
ortadan kaldırır. 

### Örnek:

```java
import creational.factoryMethod.models.Director;
import creational.factoryMethod.models.Employee;
import creational.factoryMethod.models.Manager;

public class EmployeeFactory {
    public static Employee createEmployee(String name) {
        return new Employee(name);
    }

    public static Manager createManager(String name, String department) {
        return new Manager(name, department);
    }

    public static Director createDirector(String name, String department, String plan) {
        return new Director(name, department, plan);
    }
}
```

Bu yaklaşımdaki problem ise bir noktadan sonra artık factory method pattern'nının tanımından uzaklaşıp artık factory 
method'dan ziyade **"utility class"** olma eğilimine gidilmesidir.

### Önemli Notlar:
**Factory method pattern'nındaki en önemli özellik, bir class'ı birden fazla factory method içerebilmesi için bu 
method'ların bir object ailesinin parçası olması gerekiyor. (Abstract Factory Pattern tanımı)**

Bu yüzden factory method'ları içerecek class'ları oluştururken anti-pattern olma koşullarını da ele alarak class'lar yazmak 
gerekir.  

Static factory method gibi bağlamdan uzak çözümler yerine factory method'ların asıl amacının object oluşturma sürecinin 
abstract'laştırılması olduğu unutulmamalıdır!!! (Singleton return eden method olarak kullanılması hariç!!!!!)

Factory method'ların tek görevi object create etmek değildir. Daha önce oluşturulmuş bir object'i de return edebilir.
Bu pattern diğer pattern'larla birlikte sık sık birlikte kullanılır. (Örn. Builder ve prototype pattern)
