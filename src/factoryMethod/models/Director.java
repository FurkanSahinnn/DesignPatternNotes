package factoryMethod.models;

public class Director extends Manager {
    // Buraya name ve managedDepartment field'larını yazarsan field shadowing problemi ile karşılaşırsın yani
    // name ve managedDepartment değerleri null'a set edilir.
    protected String plan;

    public Director(String name, String managedDepartment, String plan) {
        super(name, managedDepartment);
        this.plan = plan;
    }

    public void makePlan(String name, String plan) {
        System.out.println(name + "director is working, and plan: " + plan);
    }

    @Override
    public void work() {
        makePlan(name, plan);
    }

    @Override
    public String toString() {
        return "Director{" +
                "name='" + name + '\'' +
                ", managedDepartment='" + managedDepartment + '\'' +
                ", plan='" + plan + '\'' +
                '}';
    }


    // Getter, setter
}
