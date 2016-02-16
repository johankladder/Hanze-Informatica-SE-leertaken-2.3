package opdracht2;

import java.util.Enumeration;

public class TreeCreator {

    public static void main(String[] args) {
        new TreeCreator();
    }

    public TreeCreator() {
        init();
    }

    private void init() {
        // ROOT:
        TreeNode person =  new TreeNode("person");

        // Children (left):
        TreeNode employee =  new TreeNode("employee");
        TreeNode salesRep =  new TreeNode("sales_rep");
        TreeNode engineer =  new TreeNode("engineer");

        // Children (right):
        TreeNode customer =  new TreeNode("customer");
        TreeNode usCustomer =  new TreeNode("us_customer");
        TreeNode notUsCustomer =  new TreeNode("not_us_customer");
        TreeNode localCustomer =  new TreeNode("local_customer");
        TreeNode regionalCustomer =  new TreeNode("regional_customer");

        // Relations in tree:
        person.add(employee);
        employee.add(salesRep);
        employee.add(engineer);

        person.add(customer);
        customer.add(usCustomer);
        customer.add(notUsCustomer);
        usCustomer.add(localCustomer);
        usCustomer.add(regionalCustomer);
    }
}
